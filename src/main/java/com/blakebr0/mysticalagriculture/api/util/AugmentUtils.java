package com.blakebr0.mysticalagriculture.api.util;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AugmentUtils {
    /**
     * Add an augment to the specified tinkerable in the specified slot
     * @param stack the {@link ITinkerable} item
     * @param augment the augment
     * @param slot the augment slot
     */
    public static void addAugment(ItemStack stack, IAugment augment, int slot) {
        var item = stack.getItem();

        if (item instanceof ITinkerable tinkerable) {
            if (slot < tinkerable.getAugmentSlots() && tinkerable.getTinkerableTier() >= augment.getTier()) {
                var nbt = stack.getTag();

                if (nbt == null) {
                    nbt = new CompoundTag();
                    stack.setTag(nbt);
                }

                nbt.putString("Augment-" + slot, augment.getId().toString());
            }
        }
    }

    /**
     * Remove an augment from the specified tinkerable from the specified slot
     * @param stack the {@link ITinkerable} item
     * @param slot the augment slot
     */
    public static void removeAugment(ItemStack stack, int slot) {
        var nbt = stack.getTag();
        if (nbt == null)
            return;

        var item = stack.getItem();

        if (item instanceof ITinkerable tinkerable) {
            if (slot < tinkerable.getAugmentSlots() && nbt.contains("Augment-" + slot)) {
                nbt.remove("Augment-" + slot);
            }
        }
    }

    /**
     * Get the augment in the specified augment slot
     * @param stack the {@link ITinkerable} item
     * @param slot the augment slot
     * @return the augment
     */
    public static IAugment getAugment(ItemStack stack, int slot) {
        var nbt = stack.getTag();
        if (nbt == null)
            return null;

        var item = stack.getItem();

        if (item instanceof ITinkerable tinkerable) {
            if (slot < tinkerable.getAugmentSlots() && nbt.contains("Augment-" + slot)) {
                var name = nbt.getString("Augment-" + slot);
                return MysticalAgricultureAPI.getAugmentRegistry().getAugmentById(new ResourceLocation(name));
            }
        }

        return null;
    }

    /**
     * Gets the augments currently installed on this tinkerable
     * @param stack the {@link ITinkerable}
     * @return the installed augments
     */
    public static List<IAugment> getAugments(ItemStack stack) {
        var nbt = stack.getTag();
        List<IAugment> augments = new ArrayList<>();

        if (nbt == null)
            return augments;

        var item = stack.getItem();

        if (item instanceof ITinkerable tinkerable) {
            int slots = tinkerable.getAugmentSlots();

            for (int i = 0; i < slots; i++) {
                var augment = getAugment(stack, i);
                if (augment != null)
                    augments.add(augment);
            }
        }

        return augments;
    }

    /**
     * Helper method to get the augments from the player's armor
     * @param player the player
     * @return the installed augments
     */
    public static List<IAugment> getArmorAugments(Player player) {
        var armor = player.getInventory().armor;
        List<IAugment> augments = new ArrayList<>();

        for (var stack : armor) {
            augments.addAll(getAugments(stack));
        }

        return augments;
    }

    /**
     * Get the tooltip color for the provided int tier
     * @param tier the tier
     * @return the color
     */
    public static ChatFormatting getColorForTier(int tier) {
        return switch (tier) {
            case 1 -> CropTier.ONE.getTextColor();
            case 2 -> CropTier.TWO.getTextColor();
            case 3 -> CropTier.THREE.getTextColor();
            case 4 -> CropTier.FOUR.getTextColor();
            case 5 -> CropTier.FIVE.getTextColor();
            default -> ChatFormatting.GRAY;
        };
    }

    /**
     * Gets the text component variant of the provided tier number for use in tooltips
     * @param tier the tier
     * @return the formatted tier
     */
    public static Component getTooltipForTier(int tier) {
        return new TextComponent(String.valueOf(tier)).withStyle(getColorForTier(tier));
    }
}
