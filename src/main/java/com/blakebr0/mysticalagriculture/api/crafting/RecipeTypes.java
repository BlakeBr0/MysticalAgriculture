package com.blakebr0.mysticalagriculture.api.crafting;

import net.minecraft.world.item.crafting.RecipeType;

public class RecipeTypes {
    public static final RecipeType<IInfusionRecipe> INFUSION = new RecipeType<>() {
        @Override
        public String toString() {
            return "mysticalagriculture:infusion";
        }
    };
    public static final RecipeType<IAwakeningRecipe> AWAKENING = new RecipeType<>() {
        @Override
        public String toString() {
            return "mysticalagriculture:awakening";
        }
    };
    public static final RecipeType<IReprocessorRecipe> REPROCESSOR = new RecipeType<>() {
        @Override
        public String toString() {
            return "mysticalagriculture:reprocessor";
        }
    };
    public static final RecipeType<ISoulExtractionRecipe> SOUL_EXTRACTION = new RecipeType<>() {
        @Override
        public String toString() {
            return "mysticalagriculture:soul_extraction";
        }
    };
}
