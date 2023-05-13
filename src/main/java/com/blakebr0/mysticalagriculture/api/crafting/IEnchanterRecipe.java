package com.blakebr0.mysticalagriculture.api.crafting;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;

/**
 * Used to represent am Enchanter recipe for the recipe type
 */
public interface IEnchanterRecipe extends Recipe<Container> {
    List<Integer> getIngredientCounts();
}
