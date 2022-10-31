package com.blakebr0.mysticalagriculture.api.util;

import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class TinkerableUtils {
    /**
     * Gets the {@link ITinkerable} instance from the provided item stack if applicable
     * @param stack the item
     * @return the {@link ITinkerable} or null
     */
    public static ITinkerable getTinkerable(ItemStack stack) {
        var item = stack.getItem();
        return item instanceof ITinkerable tinkerable ? tinkerable : null;
    }

    /**
     * Gets the minimum {@link ITinkerable} tier for the player's equipped armor
     * @param player the player
     * @return the minimum {@link ITinkerable} tier, or -1 if not wearing a full set
     */
    public static int getArmorSetMinimumTier(Player player) {
        int tier = -1;

        for (int i = 0; i < 4; i++) {
            var stack = player.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i));
            var tinkerable = getTinkerable(stack);

            if (tinkerable == null)
                return -1;
        }

        return tier;
    }

    /**
     * Checks if the provided player has a full set of the minimum {@link ITinkerable} tier equipped
     * @param player the player
     * @param tier the {@link ITinkerable} tier
     * @return has the minimum tier equipped
     */
    public static boolean hasArmorSetMinimumTier(Player player, int tier) {
        return getArmorSetMinimumTier(player) == tier;
    }
}
