package com.blakebr0.mysticalagriculture.api.crafting;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Optional;

public class RecipeTypes {
    public static final IRecipeType<IInfusionRecipe> INFUSION = new IRecipeType<IInfusionRecipe>() {
        @Override
        public <C extends IInventory> Optional<IInfusionRecipe> matches(IRecipe<C> recipe, World world, C inv) {
            return recipe.matches(inv, world) ? Optional.of((IInfusionRecipe) recipe) : Optional.empty();
        }
    };
    public static final IRecipeType<IReprocessorRecipe> REPROCESSOR = new IRecipeType<IReprocessorRecipe>() {
        @Override
        public <C extends IInventory> Optional<IReprocessorRecipe> matches(IRecipe<C> recipe, World world, C inv) {
            return recipe.matches(inv, world) ? Optional.of((IReprocessorRecipe) recipe) : Optional.empty();
        }
    };

    static {
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(MysticalAgriculture.MOD_ID, "infusion"), INFUSION);
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(MysticalAgriculture.MOD_ID, "reprocessor"), REPROCESSOR);
    }
}
