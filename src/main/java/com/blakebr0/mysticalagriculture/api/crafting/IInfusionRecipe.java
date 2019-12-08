package com.blakebr0.mysticalagriculture.api.crafting;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;

/**
 * Used to represent an Infusion recipe for the recipe type
 */
@SuppressWarnings("unchecked")
public interface IInfusionRecipe extends IRecipe<IInventory> {
    default <T extends IInfusionRecipe> T cast() {
        return (T) this;
    }
}
