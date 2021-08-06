package com.blakebr0.mysticalagriculture.api.crafting;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;

/**
 * Used to represent a Reprocessor recipe for the recipe type
 */
@SuppressWarnings("unchecked")
public interface IReprocessorRecipe extends Recipe<Container> {
    default <T extends IReprocessorRecipe> T cast() {
        return (T) this;
    }
}
