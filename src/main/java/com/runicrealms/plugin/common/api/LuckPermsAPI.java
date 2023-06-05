package com.runicrealms.plugin.common.api;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface LuckPermsAPI {

    /**
     * Save a payload asynchronously to the LuckPerms database
     */
    void savePayload(LuckPermsPayload payload);

    /**
     * Save a payload asynchronously to the LuckPerms database.
     * If ignoreCache is true, forcibly reloads it from the DB, ignoring cached values.
     */
    void savePayload(LuckPermsPayload payload, boolean ignoreCache);

    /**
     * Retrieve all LuckPerms metadata for a given user.
     */
    CompletableFuture<LuckPermsData> retrieveData(UUID owner);

    /**
     * Retrieve all LuckPerms metadata for a given user.
     * If ignoreCache is true, forcibly reloads it from the DB, ignoring cached values.
     */
    CompletableFuture<LuckPermsData> retrieveData(UUID owner, boolean ignoreCache);

    /**
     * Creates a generic payload object, where the given Consumer of LuckPermsData
     * is used as a means of applying a write operation to existing LuckPerms data
     * when the payload is to be executed.
     */
    LuckPermsPayload createPayload(UUID owner, Consumer<LuckPermsData> writeConsumer);

}
