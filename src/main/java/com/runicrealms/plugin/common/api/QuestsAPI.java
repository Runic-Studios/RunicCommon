package com.runicrealms.plugin.common.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An interface that allows us to interact with the RunicQuests plugin
 *
 * @author BoBoBalloon
 */
public interface QuestsAPI {
    /**
     * A method used to trigger a quest objective
     *
     * @param applyToParty  if the objective should be updated for the entire party
     * @param player        the target player
     * @param triggerId     the trigger id
     * @param objectiveName the name of the objective that will be sent to the player in an update message
     */
    void triggerQuest(boolean applyToParty, @NotNull Player player, @NotNull String triggerId, @Nullable String objectiveName);
}
