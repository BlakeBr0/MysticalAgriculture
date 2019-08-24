package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.cucumber.crafting.ISpecialRecipeType;
import com.blakebr0.mysticalagriculture.crafting.recipe.InfusionRecipe;

public class SpecialRecipeTypes {
    public static final ISpecialRecipeType<InfusionRecipe> INFUSION = new ISpecialRecipeType<InfusionRecipe>() {
        @Override
        public String toString() {
            return "infusion";
        }
    };
}
