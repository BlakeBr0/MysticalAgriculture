package com.blakebr0.mysticalagriculture.api.crafting;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class RecipeTypes {
    public static final RecipeType<IInfusionRecipe> INFUSION = new RecipeType<IInfusionRecipe>() {
        @Override
        public <C extends Container> Optional<IInfusionRecipe> tryMatch(Recipe<C> recipe, Level world, C inv) {
            return recipe.matches(inv, world) ? Optional.of((IInfusionRecipe) recipe) : Optional.empty();
        }
    };
    public static final RecipeType<IReprocessorRecipe> REPROCESSOR = new RecipeType<IReprocessorRecipe>() {
        @Override
        public <C extends Container> Optional<IReprocessorRecipe> tryMatch(Recipe<C> recipe, Level world, C inv) {
            return recipe.matches(inv, world) ? Optional.of((IReprocessorRecipe) recipe) : Optional.empty();
        }
    };
    public static final RecipeType<ISoulExtractionRecipe> SOUL_EXTRACTION = new RecipeType<ISoulExtractionRecipe>() {
        @Override
        public <C extends Container> Optional<ISoulExtractionRecipe> tryMatch(Recipe<C> recipe, Level world, C inv) {
            return recipe.matches(inv, world) ? Optional.of((ISoulExtractionRecipe) recipe) : Optional.empty();
        }
    };

    static {
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "infusion"), INFUSION);
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "reprocessor"), REPROCESSOR);
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "soul_extraction"), SOUL_EXTRACTION);
    }
}
