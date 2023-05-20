package com.runicrealms.plugin.common.util;

import org.bukkit.ChatColor;

public class ColorUtil {

    public static String format(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
