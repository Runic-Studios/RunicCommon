package com.runicrealms.plugin.common;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RunicCommon extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[RunicCommon] Common Dependencies have loaded");
    }

}
