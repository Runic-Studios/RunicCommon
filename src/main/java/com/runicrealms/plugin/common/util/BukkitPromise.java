package com.runicrealms.plugin.common.util;

import com.runicrealms.plugin.common.RunicCommon;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public class BukkitPromise<T> {

    private final Queue<Consumer<T>> consumers = new ConcurrentLinkedQueue<>();
    private @Nullable T value;

    public BukkitPromise() {
    }

    public static <E> BukkitPromise<E> fulfilled(@NotNull E value) {
        BukkitPromise<E> promise = new BukkitPromise<>();
        promise.fulfill(value);
        return promise;
    }

    public void fulfill(@NotNull T value) {
        synchronized (BukkitPromise.class) {
            if (this.value != null)
                throw new IllegalStateException("Cannot fulfill promise that has already been fulfilled!");
            this.value = value;
            for (Consumer<T> consumer : consumers) {
                try {
                    consumer.accept(this.value);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public void then(Consumer<T> consumer) {
        synchronized (BukkitPromise.class) {
            if (this.value == null) {
                consumers.add(consumer);
            } else {
                try {
                    consumer.accept(this.value);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public void thenAsync(Consumer<T> consumer) {
        synchronized (BukkitPromise.class) {
            if (Bukkit.isPrimaryThread()) {
                Bukkit.getScheduler().runTaskAsynchronously(RunicCommon.getInstance(), () -> then(consumer));
            } else {
                then(consumer);
            }
        }
    }

    public void thenSync(Consumer<T> consumer) {
        synchronized (BukkitPromise.class) {
            if (!Bukkit.isPrimaryThread()) {
                Bukkit.getScheduler().runTask(RunicCommon.getInstance(), () -> then(consumer));
            } else {
                then(consumer);
            }
        }
    }

}
