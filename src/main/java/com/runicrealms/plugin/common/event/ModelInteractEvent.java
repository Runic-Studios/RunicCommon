package com.runicrealms.plugin.common.event;

import com.ticxo.modelengine.api.model.ActiveModel;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player left-clicks or right-clicks on a modeled entity.
 * Fired synchronously.
 * <p>
 * You can get the model's base entity ID with model.getModeledEntity().getBase().getEntityID()
 */
public class ModelInteractEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Player clicker;
    private final InteractType interactType;
    private final ActiveModel model;
    private boolean cancelled = false;

    public ModelInteractEvent(Player clicker, InteractType interactType, ActiveModel model) {
        super(true);
        this.clicker = clicker;
        this.interactType = interactType;
        this.model = model;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getWhoClicked() {
        return this.clicker;
    }

    public InteractType getInteractionType() {
        return this.interactType;
    }

    public ActiveModel getActiveModel() {
        return this.model;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public enum InteractType {
        LEFT_CLICK,
        RIGHT_CLICK
    }

}
