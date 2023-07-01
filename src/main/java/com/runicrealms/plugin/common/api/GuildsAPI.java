package com.runicrealms.plugin.common.api;

import com.runicrealms.plugin.common.api.guilds.GuildCreationResult;
import com.runicrealms.plugin.common.api.guilds.GuildStage;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface GuildsAPI {

    /**
     * Attempts to add guild score to the given player.
     * Should not be called multiple times on the same thread, use the addBulkGuildScore method for that.
     *
     * @param player      Player to add guild score for
     * @param score       Amount of score to add
     * @param sendMessage Should we send a message to the player's guild members in chat that they earned points
     * @return true if successful
     */
    boolean addGuildScore(UUID player, Integer score, boolean sendMessage);

    /**
     * Attempts to add guild scores for several players at once.
     * If a given player is not in a guild (or the addGuildScore method fails),
     * it skips that player
     *
     * @param scores Map of members to how much score to grant them
     */
    void addBulkGuildScore(Map<UUID, Integer> scores, boolean sendMessage);

    /**
     * Attempts to create a guild
     *
     * @param owner      player to become owner
     * @param name       of the guild
     * @param prefix     of the guild
     * @param modCreated true if created by a mod command
     * @return the result of the creation
     */
    GuildCreationResult createGuild(Player owner, String name, String prefix, boolean modCreated);

    /**
     * @param guildUUID of the GUILD
     * @return the stage of the guild
     */
    GuildStage getGuildStage(UUID guildUUID);

    /**
     * Gives the specified guild an amount of exp
     *
     * @param guildUUID of the guild
     * @param exp       an amount of guild experience
     */
    void giveExperience(UUID guildUUID, int exp);

    /**
     * @param player to check
     * @return true if player in guild
     */
    boolean isInGuild(Player player);

}
