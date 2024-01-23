package com.runicrealms.plugin.common.plugin;

import com.runicrealms.plugin.common.RunicCommon;
import com.runicrealms.plugin.common.event.RunicLoadedEvent;
import com.runicrealms.plugin.common.event.RunicShutdownEvent;
import com.runicrealms.plugin.common.plugin.annotation.OnShutdown;
import com.runicrealms.plugin.common.plugin.annotation.OnStartup;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.stream.Collectors;

public abstract class RunicPlugin extends JavaPlugin {

    private static final float DEFAULT_LOAD_TIMEOUT = 5.0f; // In seconds
    private static final float DEFAULT_SHUTDOWN_TIMEOUT = 5.0f; // In seconds

    private static final ActionManager STARTUP_MANAGER = new ActionManager(ActionManager.Type.STARTUP);
    private static final ActionManager SHUTDOWN_MANAGER = new ActionManager(ActionManager.Type.SHUTDOWN);

    private final float loadTimeout; // In seconds
    private final float shutdownTimeout; // In seconds
    private final Set<Action> onStartupActions = new HashSet<>();
    private final Set<Action> onShutdownActions = new HashSet<>();

    protected boolean hasStartedLoading = false;

    public RunicPlugin() {
        this(DEFAULT_LOAD_TIMEOUT, DEFAULT_SHUTDOWN_TIMEOUT);
    }

    public RunicPlugin(float loadTimeout) {
        this(loadTimeout, DEFAULT_SHUTDOWN_TIMEOUT);
    }

    public RunicPlugin(float loadTimeout, float shutdownTimeout) {
        this.loadTimeout = loadTimeout;
        this.shutdownTimeout = shutdownTimeout;

        Class<? extends RunicPlugin> type = this.getClass();
        for (Method method : type.getDeclaredMethods()) {
            boolean isOnLoad = method.isAnnotationPresent(OnStartup.class);
            boolean isOnShutdown = method.isAnnotationPresent(OnShutdown.class);

            if (isOnLoad || isOnShutdown) {
                if (method.getParameters().length > 0) throw new IllegalStateException("Cannot generate on load/shutdown method " + method.getName() + " for RunicPlugin that has parameters!");
                if (!method.canAccess(this)) throw new IllegalStateException("Cannot generate on load/shutdown method " + method.getName() + " for RunicPlugin that is inaccessible!");
            }

            if (isOnLoad) {
                OnStartup annotation = method.getAnnotation(OnStartup.class);
                onStartupActions.add(new Action(() -> {
                    try {
                        method.invoke(this);
                    } catch (InvocationTargetException | IllegalAccessException exception) {
                        exception.printStackTrace();
                    }
                }, annotation.async(), annotation.runAfter()));
            }
            if (isOnShutdown) {
                OnShutdown annotation = method.getAnnotation(OnShutdown.class);
                onShutdownActions.add(new Action(() -> {
                    try {
                        method.invoke(RunicPlugin.this);
                    } catch (InvocationTargetException | IllegalAccessException exception) {
                        exception.printStackTrace();
                    }
                }, annotation.async(), annotation.runAfter()));
            }
        }
    }

    @Override
    public final void onEnable() {
        hasStartedLoading = true;
        STARTUP_MANAGER.addPlugin(this);
        SHUTDOWN_MANAGER.addPlugin(this);
    }

    @Override
    public final void onDisable() {

    }

    public boolean hasLoaded() {
        return hasStartedLoading && onStartupActions.isEmpty();
    }

    public boolean hasShutDown() {
        return hasStartedLoading && onShutdownActions.isEmpty();
    }

    private record Action(Runnable runnable, boolean sync, Class<? extends RunicPlugin>... runAfter) {
        @SafeVarargs
        private Action {
        }

        private boolean canRun(Collection<RunicPlugin> loadedPlugins) {
            Set<Class<? extends RunicPlugin>> copyRunAfter = new HashSet<>(Set.of(runAfter));
            for (RunicPlugin loadedPlugin : loadedPlugins) {
                copyRunAfter.remove(loadedPlugin.getClass());
            }
            return copyRunAfter.isEmpty();
        }
    }

    private static final class ActionManager implements Listener {

        private final Type type;
        private ActionManager(Type type) {
            this.type = type;
            if (type == Type.SHUTDOWN) Bukkit.getPluginManager().registerEvents(this, RunicCommon.getInstance());
        }

        private final Set<RunicPlugin> plugins = Collections.newSetFromMap(new ConcurrentHashMap<>());
        private long started = 0L;
        private volatile float totalTimeout = 0;
        private boolean hasBegun = false;
        private @Nullable BukkitTask timeoutTask;

        private void startTimeoutTask() {
            started = System.currentTimeMillis();
            timeoutTask = new BukkitRunnable() {
                @Override
                public void run() {
                    float secondsSinceStart = (System.currentTimeMillis() - started) / 1000f;
                    if (secondsSinceStart >= totalTimeout) {
                        Bukkit.getLogger().log(Level.SEVERE, "FATAL: Could not " + type.name().toLowerCase() + " plugins! Failed: " + plugins.stream().map(plugin -> plugin.getClass().getName()).collect(Collectors.toSet()));
                        Bukkit.getLogger().log(Level.INFO, "Shutting down not safely...");
                        this.cancel();
                        timeoutTask = null;
                        Bukkit.shutdown();
                    }
                }
            }.runTaskTimer(RunicCommon.getInstance(), 20L, 20L);
        }

        private synchronized void addPlugin(RunicPlugin plugin) {
            totalTimeout += type.getTimeout(plugin);
            plugins.add(plugin);

            if (type == Type.STARTUP) runActions();
        }

        private void begin() {
            if (type != Type.SHUTDOWN) throw new IllegalStateException("Cannot begin Action Manager execution manually for startup.");
            if (!hasBegun) runActions();
        }

        private synchronized void runActions() {
            hasBegun = true;
            if (started == 0) {
                startTimeoutTask();
            }
            boolean changed = true;
            while (changed) {
                changed = false;
                for (RunicPlugin target : plugins) {
                    Set<RunicPlugin> loadedPlugins = plugins.stream().filter(RunicPlugin::hasLoaded).collect(Collectors.toSet());
                    Collection<Action> actions = type.getActions(target);
                    Iterator<Action> iterator = actions.iterator();
                    while (iterator.hasNext()) { // Do not convert to for, will give CME
                        Action action = iterator.next();
                        if (!action.canRun(loadedPlugins)) {

                            if (action.sync) {
                                if (Bukkit.isPrimaryThread()) {
                                    action.runnable.run();
                                } else {
                                    Bukkit.getScheduler().runTask(RunicCommon.getInstance(), () -> {
                                        action.runnable.run();
                                        runActions();
                                    });
                                }
                            } else {
                                if (Bukkit.isPrimaryThread()) {
                                    Bukkit.getScheduler().runTaskAsynchronously(RunicCommon.getInstance(), () -> {
                                        action.runnable.run();
                                        runActions();
                                    });
                                } else {
                                    action.runnable.run();
                                }
                            }

                            actions.remove(action);
                        }
                    }
                }
            }
            if (plugins.stream().allMatch(type::hasFinished)) {
                if (timeoutTask != null) timeoutTask.cancel();
                if (type == Type.STARTUP) {
                    Bukkit.getScheduler().runTask(RunicCommon.getInstance(), () -> Bukkit.getPluginManager().callEvent(new RunicLoadedEvent()));
                }
            }
        }

        @EventHandler(priority = EventPriority.HIGHEST)
        public void onShutdown(RunicShutdownEvent event) {
            if (type != Type.SHUTDOWN) return;
            begin();
        }

        private enum Type {
            STARTUP, SHUTDOWN;

            private Collection<Action> getActions(RunicPlugin plugin) {
                switch (this) {
                    case STARTUP -> {
                        return plugin.onStartupActions;
                    }
                    case SHUTDOWN -> {
                        return plugin.onShutdownActions;
                    }
                }
                throw new IllegalStateException();
            }

            private float getTimeout(RunicPlugin plugin) {
                switch (this) {
                    case STARTUP -> {
                        return plugin.loadTimeout;
                    }
                    case SHUTDOWN -> {
                        return plugin.shutdownTimeout;
                    }
                }
                throw new IllegalStateException();
            }

            private boolean hasFinished(RunicPlugin plugin) {
                switch (this) {
                    case STARTUP -> {
                        return plugin.hasLoaded();
                    }
                    case SHUTDOWN -> {
                        return plugin.hasShutDown();
                    }
                }
                throw new IllegalStateException();
            }

        }

    }

}
