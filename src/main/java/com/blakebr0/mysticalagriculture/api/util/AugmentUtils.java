package com.blakebr0.mysticalagriculture.api.util;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;

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
        Item item = stack.getItem();
        if (item instanceof ITinkerable) {
            ITinkerable tinkerable = (ITinkerable) item;
            if (slot < tinkerable.getAugmentSlots() && tinkerable.getTinkerableTier() >= augment.getTier()) {
                CompoundTag nbt = stack.getTag();
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
        CompoundTag nbt = stack.getTag();
        if (nbt == null)
            return;

        Item item = stack.getItem();
        if (item instanceof ITinkerable) {
            ITinkerable tinkerable = (ITinkerable) item;
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
        CompoundTag nbt = stack.getTag();
        if (nbt == null)
            return null;

        Item item = stack.getItem();
        if (item instanceof ITinkerable) {
            ITinkerable tinkerable = (ITinkerable) item;
            if (slot < tinkerable.getAugmentSlots() && nbt.contains("Augment-" + slot)) {
                String name = nbt.getString("Augment-" + slot);
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
        CompoundTag nbt = stack.getTag();
        List<IAugment> augments = new ArrayList<>();
        if (nbt == null)
            return augments;

        Item item = stack.getItem();
        if (item instanceof ITinkerable) {
            ITinkerable tinkerable = (ITinkerable) item;
            int slots = tinkerable.getAugmentSlots();
            for (int i = 0; i < slots; i++) {
                IAugment augment = getAugment(stack, i);
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
        NonNullList<ItemStack> armor = player.inventory.armor;
        List<IAugment> augments = new ArrayList<>();
        for (ItemStack stack : armor) {
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
        switch (tier) {
            case 1: return CropTier.ONE.getTextColor();
            case 2: return CropTier.TWO.getTextColor();
            case 3: return CropTier.THREE.getTextColor();
            case 4: return CropTier.FOUR.getTextColor();
            case 5: return CropTier.FIVE.getTextColor();
            default: return ChatFormatting.GRAY;
        }
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
