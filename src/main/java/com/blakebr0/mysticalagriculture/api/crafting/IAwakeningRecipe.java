package com.blakebr0.mysticalagriculture.api.crafting;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;
import java.util.Map;

/**
 * Used to represent an Awakening recipe for the recipe type
 */
public interface IAwakeningRecipe extends Recipe<Container> {
    /**
     * The list of essence items required for this recipe
     * @return the list of essences
     */
    NonNullList<ItemStack> getEssences();

    /**
     * Gets a map of missing essences and the amount of each that is missing
     * @param items the list essence vessel items
     * @return a map of missing essences -> amount missing
     */
    Map<ItemStack, Integer> getMissingEssences(List<ItemStack> items);

    /**
     * Checks if the given list of items has the required essences and stack sizes for this recipe
     * @param items the list essence vessel items
     * @return all required essences are provided
     */
    default boolean hasRequiredEssences(List<ItemStack> items) {
        return this.getMissingEssences(items).isEmpty();
    }
}
