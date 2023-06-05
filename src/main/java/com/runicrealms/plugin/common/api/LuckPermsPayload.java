package com.runicrealms.plugin.common.api;

import java.util.UUID;

public interface LuckPermsPayload {

    void saveToData(LuckPermsData data);

    UUID owner();

}
