package com.runicrealms.plugin.common.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Called after all runic plugins have finished loading. Indicates to remove whitelist and such.
 */
public final class RunicLoadedEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public RunicLoadedEvent() {
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

