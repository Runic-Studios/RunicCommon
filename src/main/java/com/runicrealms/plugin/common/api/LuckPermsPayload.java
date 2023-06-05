package com.runicrealms.plugin.common.api;

import java.util.UUID;

public interface LuckPermsPayload {

    /**
     * Apply the changes held in this payload to a LuckPermsData object.
     * Generally performed immediately upon save.
     */
    void saveToData(LuckPermsData data);

    /**
     * Gets the owner of this payload, whose metadata we will be writing to.
     */
    UUID owner();

}
