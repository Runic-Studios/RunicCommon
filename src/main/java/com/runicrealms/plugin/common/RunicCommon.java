package com.runicrealms.plugin.common;

import com.runicrealms.plugin.common.api.AchievementsAPI;
import com.runicrealms.plugin.common.api.ConfigAPI;
import com.runicrealms.plugin.common.api.LuckPermsAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RunicCommon extends JavaPlugin {

    private static AchievementsAPI achievementsAPI;
    private static ConfigAPI configAPI;
    private static LuckPermsAPI luckPermsAPI;

    public static AchievementsAPI getAchievementsAPI() {
        return achievementsAPI;
    }

    public static ConfigAPI getConfigAPI() {
        return configAPI;
    }

    public static LuckPermsAPI getLuckPermsAPI() {
        return luckPermsAPI;
    }

    public static void registerAchievementsAPI(AchievementsAPI api) {
        if (achievementsAPI != null)
            throw new IllegalStateException("Achievements API can not be registered: already registered!");
        achievementsAPI = api;
    }

    public static void registerConfigAPI(ConfigAPI api) {
        if (configAPI != null)
            throw new IllegalStateException("Config API can not be registered: already registered!");
        configAPI = api;
    }

    public static void registerLuckPermsAPI(LuckPermsAPI api) {
        if (luckPermsAPI != null)
            throw new IllegalStateException("LuckPerms API can not be registered: already registered!");
        luckPermsAPI = api;
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[RunicCommon] Common Dependencies have loaded");
    }

}
