package com.runicrealms.plugin.common;

import com.runicrealms.plugin.common.api.AchievementsAPI;
import com.runicrealms.plugin.common.api.ConfigAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RunicCommon extends JavaPlugin {

    private static AchievementsAPI achievementsAPI;
    private static ConfigAPI configAPI;

    public static AchievementsAPI getAchievementsAPI() {
        return achievementsAPI;
    }

    public static ConfigAPI getConfigAPI() {
        return configAPI;
    }

    public static void registerAchievementsAPI(AchievementsAPI api) {
        if (achievementsAPI == null)
            throw new IllegalStateException("Achievements API can not be registered: already registered!");
        achievementsAPI = api;
    }

    public static void registerConfigAPI(ConfigAPI api) {
        if (configAPI == null)
            throw new IllegalStateException("Config API can not be registered: already registered!");
        configAPI = api;
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[RunicCommon] Common Dependencies have loaded");
    }

}
