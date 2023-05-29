package com.runicrealms.plugin.common.api;

import org.bukkit.entity.Player;

public interface AchievementsAPI {

    /**
     * Check whether the player has unlocked the given achievement
     *
     * @param player        to check
     * @param achievementId unique internal id of the achievement
     * @return true if the player has unlocked it
     */
    boolean hasAchievement(Player player, String achievementId);

}
