package com.blakebr0.mysticalagriculture.api.crafting;

import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;

/**
 * Used to represent a Reprocessor recipe for the recipe type
 */
public interface ISoulExtractionRecipe extends Recipe<Container> {
    IMobSoulType getMobSoulType();
    double getSouls();
}
