package com.runicrealms.plugin.common;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RunicCommon extends JavaPlugin {

    private static RunicCommon instance;

    public static RunicCommon getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getLogger().info("[RunicCommon] Common Dependencies have loaded");
    }

}
