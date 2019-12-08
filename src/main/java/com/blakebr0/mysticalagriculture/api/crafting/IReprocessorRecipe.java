package com.blakebr0.mysticalagriculture.api.crafting;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;

/**
 * Used to represent a Reprocessor recipe for the recipe type
 */
@SuppressWarnings("unchecked")
public interface IReprocessorRecipe extends IRecipe<IInventory> {
    default <T extends IReprocessorRecipe> T cast() {
        return (T) this;
    }
}
