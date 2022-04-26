package com.blakebr0.mysticalagriculture.api.crafting;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;

/**
 * Used to represent an Awaking recipe for the recipe type
 */
public interface IAwakeningRecipe extends Recipe<Container> {
    EssenceVesselRequirements getEssenceRequirements();

    record EssenceVesselRequirements(int air, int earth, int water, int fire) { }
}
