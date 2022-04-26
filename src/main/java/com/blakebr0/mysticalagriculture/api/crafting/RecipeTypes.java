package com.blakebr0.mysticalagriculture.api.crafting;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class RecipeTypes {
    public static final RecipeType<IInfusionRecipe> INFUSION = new RecipeType<>() {
        @Override
        public <C extends Container> Optional<IInfusionRecipe> tryMatch(Recipe<C> recipe, Level level, C inv) {
            return recipe.matches(inv, level) ? Optional.of((IInfusionRecipe) recipe) : Optional.empty();
        }
    };
    public static final RecipeType<IAwakeningRecipe> AWAKENING = new RecipeType<>() {
        @Override
        public <C extends Container> Optional<IAwakeningRecipe> tryMatch(Recipe<C> recipe, Level level, C inv) {
            return recipe.matches(inv, level) ? Optional.of((IAwakeningRecipe) recipe) : Optional.empty();
        }
    };
    public static final RecipeType<IReprocessorRecipe> REPROCESSOR = new RecipeType<>() {
        @Override
        public <C extends Container> Optional<IReprocessorRecipe> tryMatch(Recipe<C> recipe, Level level, C inv) {
            return recipe.matches(inv, level) ? Optional.of((IReprocessorRecipe) recipe) : Optional.empty();
        }
    };
    public static final RecipeType<ISoulExtractionRecipe> SOUL_EXTRACTION = new RecipeType<>() {
        @Override
        public <C extends Container> Optional<ISoulExtractionRecipe> tryMatch(Recipe<C> recipe, Level level, C inv) {
            return recipe.matches(inv, level) ? Optional.of((ISoulExtractionRecipe) recipe) : Optional.empty();
        }
    };
}
