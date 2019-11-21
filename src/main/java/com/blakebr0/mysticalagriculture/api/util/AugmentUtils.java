package com.blakebr0.mysticalagriculture.api.util;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

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
            if (slot < tinkerable.getAugmentSlots() && tinkerable.getToolTier() >= augment.getTier()) {
                NBTHelper.setString(stack, "Augment-" + slot, augment.getId().toString());
            }
        }
    }

    /**
     * Remove an augment from the specified tinkerable from the specified slot
     * @param stack the {@link ITinkerable} item
     * @param slot the augment slot
     */
    public static void removeAugment(ItemStack stack, int slot) {
        CompoundNBT nbt = stack.getTag();
        if (nbt == null)
            return;

        Item item = stack.getItem();
        if (item instanceof ITinkerable) {
            ITinkerable tinkerable = (ITinkerable) item;
            if (slot < tinkerable.getAugmentSlots()) {
                if (NBTHelper.hasKey(stack, "Augment-" + slot)) {
                    NBTHelper.removeTag(stack, "Augment-" + slot);
                }
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
        CompoundNBT nbt = stack.getTag();
        if (nbt == null)
            return null;

        Item item = stack.getItem();
        if (item instanceof ITinkerable) {
            ITinkerable tinkerable = (ITinkerable) item;
            if (slot < tinkerable.getAugmentSlots() && NBTHelper.hasKey(stack, "Augment-" + slot)) {
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
        CompoundNBT nbt = stack.getTag();
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
}
