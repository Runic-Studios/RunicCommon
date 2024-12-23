package com.runicrealms.plugin.common.api.guilds;

public enum StageReward {

    NONE
            (
                    0,
                    "",
                    ""
            ),
    BANNER
            (
                    0,
                    "Your guild can now create a banner!",
                    "Guild Banner"
            ),
    MOUNT_SPEED_BONUS
            (
                    0.1,
                    "All guild members receive {MODIFIER}% bonus speed while on a mount!",
                    "{MODIFIER}% bonus mount speed"
            ),
    COMBAT_BONUS
            (
                    0.05,
                    "All guild members receive a {MODIFIER}% exp buff!",
                    "{MODIFIER}% combat exp buff"
            ),
    EXP_BONUS
            (
                    0.05,
                    "All guild members receive a {MODIFIER}% damage buff against monsters!",
                    "{MODIFIER}% damage buff vs. monsters"
            );

    private final double buffPercent;
    private final String message;
    private final String formattedReward;

    /**
     * @param buffPercent     the percent modifier of the buff associated with the stage
     * @param message         to display in guild ui menus
     * @param formattedReward the reward string for use in messages
     */
    StageReward(double buffPercent, String message, String formattedReward) {
        this.buffPercent = buffPercent;
        this.message = message;
        this.formattedReward = formattedReward;
    }

    public double getBuffPercent() {
        return buffPercent;
    }

    public String getFormattedReward() {
        return formattedReward.replace("{MODIFIER}", (this.buffPercent * 100) + "");
    }

    public String getMessage() {
        return message.replace("{MODIFIER}", (this.buffPercent * 100) + "");
    }
}
