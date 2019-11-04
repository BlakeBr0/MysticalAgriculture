package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.cucumber.crafting.ISpecialRecipeType;
import com.blakebr0.mysticalagriculture.crafting.recipe.InfusionRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.ReprocessorRecipe;

public class SpecialRecipeTypes {
    public static final ISpecialRecipeType<InfusionRecipe> INFUSION = new ISpecialRecipeType<InfusionRecipe>() {
        @Override
        public String toString() {
            return "Infusion Recipe Type";
        }
    };
    public static final ISpecialRecipeType<ReprocessorRecipe> REPROCESSOR = new ISpecialRecipeType<ReprocessorRecipe>() {
        @Override
        public String toString() {
            return "Reprocessor Recipe Type";
        }
    };
}
