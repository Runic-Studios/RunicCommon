package com.runicrealms.plugin.common.api;

import java.util.Set;

public interface LuckPermsData {

    /**
     * Get all keys in this LuckPerms metadata
     */
    Set<String> getKeys();

    /**
     * Checks if this LuckPerms metadata contains a key
     */
    boolean containsKey(String key);

    /**
     * Gets a string value from this LuckPerms metadata
     */
    String getString(String key);

    /**
     * Gets an integer value from this LuckPerms metadata
     */
    int getInteger(String key);

    /**
     * Gets a double value from this LuckPerms metadata
     */
    double getDouble(String key);

    /**
     * Gets a float value from this LuckPerms metadata
     */
    float getFloat(String key);

    /**
     * Gets a boolean value from this LuckPerms metadata
     */
    boolean getBoolean(String key);

    /**
     * Gets a generic Object value from this LuckPerms metadata.
     * Type changes depending on how it is stored in the implementation of this interface.
     */
    Object get(String key);

    /**
     * Sets an object to this LuckPerms metadata.
     */
    void set(String key, Object object);

    /**
     * Merges this LuckPerms metadata with another object of the same type.
     * For overlapping keys, the new data is prioritized.
     */
    void add(LuckPermsData newData);

}
