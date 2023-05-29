package com.runicrealms.plugin.common;

import com.runicrealms.plugin.common.api.AchievementsAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RunicCommon extends JavaPlugin {

    private static AchievementsAPI achievementsAPI;

    public static AchievementsAPI getAchievementsAPI() {
        return achievementsAPI;
    }

    public static void registerAchievementsAPI(AchievementsAPI api) {
        if (achievementsAPI != null)
            throw new IllegalStateException("Achievements API can not be registered: already registered!");
        achievementsAPI = api;
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[RunicCommon] Common Dependencies have loaded");
    }

}
