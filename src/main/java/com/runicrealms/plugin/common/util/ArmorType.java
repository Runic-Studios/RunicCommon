package com.runicrealms.plugin.common.util;

import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public enum ArmorType {

    HELMET(5, EquipmentSlot.HEAD),
    CHESTPLATE(6, EquipmentSlot.CHEST),
    LEGGINGS(7, EquipmentSlot.LEGS),
    BOOTS(8, EquipmentSlot.FEET),
    OFFHAND(40, EquipmentSlot.OFF_HAND);

    private final int slot;
    private final EquipmentSlot equipmentSlot;

    ArmorType(int slot, @NotNull EquipmentSlot equipmentSlot) {
        this.slot = slot;
        this.equipmentSlot = equipmentSlot;
    }

    /**
     * Returns the type of armor. If none found, returns 'none'
     *
     * @param itemStack item in hand
     * @return type of runic weapon held
     */
    public static ArmorType matchType(final ItemStack itemStack) {
        if (itemStack == null) return null;
        return switch (itemStack.getType()) {
            case CHAINMAIL_HELMET, GOLDEN_HELMET, DIAMOND_HELMET, LEATHER_HELMET, IRON_HELMET,
                    CARVED_PUMPKIN, SKELETON_SKULL, WITHER_SKELETON_SKULL, CREEPER_HEAD, DRAGON_HEAD, PLAYER_HEAD, ZOMBIE_HEAD ->
                    HELMET;
            case CHAINMAIL_CHESTPLATE, GOLDEN_CHESTPLATE, DIAMOND_CHESTPLATE, LEATHER_CHESTPLATE, IRON_CHESTPLATE ->
                    CHESTPLATE;
            case CHAINMAIL_LEGGINGS, GOLDEN_LEGGINGS, DIAMOND_LEGGINGS, LEATHER_LEGGINGS, IRON_LEGGINGS -> LEGGINGS;
            case CHAINMAIL_BOOTS, GOLDEN_BOOTS, DIAMOND_BOOTS, LEATHER_BOOTS, IRON_BOOTS -> BOOTS;
            default -> null;
        };
    }

    public int getSlot() {
        return slot;
    }

    @NotNull
    public EquipmentSlot getEquipmentSlot() {
        return this.equipmentSlot;
    }
}
