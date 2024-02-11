package com.runicrealms.plugin.common;

import com.runicrealms.plugin.common.api.AchievementsAPI;
import com.runicrealms.plugin.common.api.ConfigAPI;
import com.runicrealms.plugin.common.api.GuildsAPI;
import com.runicrealms.plugin.common.api.LuckPermsAPI;
import com.runicrealms.plugin.common.api.QuestsAPI;
import com.runicrealms.plugin.common.api.RunicPvPAPI;
import com.runicrealms.plugin.common.resourcepack.ResourcePackManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RunicCommon extends JavaPlugin {

    private static RunicCommon instance;
    private static AchievementsAPI achievementsAPI;
    private static ConfigAPI configAPI;
    private static LuckPermsAPI luckPermsAPI;
    private static GuildsAPI guildsAPI;
    private static RunicPvPAPI pvpAPI;
    private static QuestsAPI questsAPI;

    public static RunicCommon getInstance() {
        return instance;
    }

    public static AchievementsAPI getAchievementsAPI() {
        return achievementsAPI;
    }

    public static ConfigAPI getConfigAPI() {
        return configAPI;
    }

    public static LuckPermsAPI getLuckPermsAPI() {
        return luckPermsAPI;
    }

    public static GuildsAPI getGuildsAPI() {
        return guildsAPI;
    }

    public static RunicPvPAPI getPvPAPI() {
        return pvpAPI;
    }

    public static QuestsAPI getQuestsAPI() {
        return questsAPI;
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

    public static void registerGuildsAPI(GuildsAPI api) {
        if (guildsAPI != null)
            throw new IllegalArgumentException("GuildsAPI cannot be registered: already registered!");
        guildsAPI = api;
    }

    public static void registerPvPAPI(RunicPvPAPI api) {
        if (pvpAPI != null)
            throw new IllegalArgumentException("PvPAPI cannot be registered: already registered!");
        pvpAPI = api;
    }

    public static void registerQuestsAPI(QuestsAPI api) {
        if (questsAPI != null)
            throw new IllegalArgumentException("QuestsAPI cannot be registered: already registered!");
        questsAPI = api;
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getLogger().info("[RunicCommon] Common Dependencies have loaded");

        Bukkit.getPluginManager().registerEvents(new ResourcePackManager(), this);
    }

}
