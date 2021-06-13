package com.blakebr0.mysticalagriculture.api.crafting;

import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;

/**
 * Used to represent a Reprocessor recipe for the recipe type
 */
public interface ISoulExtractionRecipe extends IRecipe<IInventory> {
    IMobSoulType getMobSoulType();
    double getSouls();
}
