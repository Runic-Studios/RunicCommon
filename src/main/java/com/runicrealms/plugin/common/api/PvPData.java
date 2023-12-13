package com.runicrealms.plugin.common.api;

import java.util.UUID;

public interface PvPData {

    /**
     * Gets the UUID of the player whose PvPData this is
     */
    UUID getOwner();

    /**
     * Checks if outlaw is enabled for a given character slot
     */
    boolean isOutlawEnabled(int slot);

    /**
     * Checks if outlaw is enabled on the currently selected character slot
     */
    boolean isOutlawEnabled();

    /**
     * Sets the outlaw status on the currently selected character slot
     */
    void setOutlawEnabled(boolean enabled);

    /**
     * Sets the outlaw status on a specific character slot
     */
    void setOutlawEnabled(int slot, boolean enabled);

}
