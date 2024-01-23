package com.runicrealms.plugin.common.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Called when the server first intends to shut down.
 * Triggers all RunicPlugins to save properly, and only then attempts to Bukkit.shutdown().
 *
 * IMPORTANT WARNING: DO NOT listen for this event! Instead, to handle shutdown procedures please use RunicPlugin#onShutdown
 */
public final class RunicShutdownEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public RunicShutdownEvent() {
        super(false);
    }

    /**
     * Gets a list of handlers handling this event.
     *
     * @return A list of handlers handling this event.
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Gets a list of handlers handling this event.
     *
     * @return A list of handlers handling this event.
     */
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
