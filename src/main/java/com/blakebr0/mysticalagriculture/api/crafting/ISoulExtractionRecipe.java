package com.blakebr0.mysticalagriculture.api.crafting;

import com.blakebr0.mysticalagriculture.api.soul.MobSoulType;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;

/**
 * Used to represent a Reprocessor recipe for the recipe type
 */
public interface ISoulExtractionRecipe extends Recipe<Container> {
    MobSoulType getMobSoulType();
    double getSouls();
}
