package com.blakebr0.mysticalagriculture.compat.crafttweaker;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.mysticalagriculture.crafting.recipe.ReprocessorRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import org.openzen.zencode.java.ZenCodeType;

import java.util.HashMap;

@ZenCodeType.Name("mods.mysticalagriculture.ReprocessorCrafting")
@ZenRegister
public final class ReprocessorCrafting {
    @ZenCodeType.Method
    public static void addRecipe(String id, IItemStack output, IIngredient input) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                var recipe = new ReprocessorRecipe(new ResourceLocation("crafttweaker", id), input.asVanillaIngredient(), output.getInternal());

                RecipeHelper.addRecipe(recipe);
            }

            @Override
            public String describe() {
                return "Adding Reprocessor Crafting recipe for " + output.getCommandString();
            }
        });
    }

    @ZenCodeType.Method
    public static void remove(IItemStack stack) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                var recipes = RecipeHelper.getRecipes()
                        .getOrDefault(ModRecipeTypes.REPROCESSOR.get(), new HashMap<>())
                        .values().stream()
                        .filter(r -> r.getResultItem().sameItem(stack.getInternal()))
                        .map(Recipe::getId)
                        .toList();

                recipes.forEach(r -> {
                    RecipeHelper.getRecipes().get(ModRecipeTypes.REPROCESSOR.get()).remove(r);
                });
            }

            @Override
            public String describe() {
                return "Removing Reprocessor Crafting recipes for " + stack.getCommandString();
            }
        });
    }
}
