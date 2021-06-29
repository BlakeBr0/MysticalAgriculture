package com.blakebr0.mysticalagriculture.compat.crafttweaker;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.crafting.recipe.ReprocessorRecipe;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.actions.IRuntimeAction;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ZenCodeType.Name("mods.mysticalagriculture.ReprocessorCrafting")
@ZenRegister
public final class ReprocessorCrafting {
    @ZenCodeType.Method
    public static void addRecipe(String id, IItemStack output, IIngredient input) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                ReprocessorRecipe recipe = new ReprocessorRecipe(new ResourceLocation("crafttweaker", id), input.asVanillaIngredient(), output.getInternal());
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
                List<ResourceLocation> recipes = RecipeHelper.getRecipes()
                        .getOrDefault(RecipeTypes.REPROCESSOR, new HashMap<>())
                        .values().stream()
                        .filter(r -> r.getResultItem().sameItem(stack.getInternal()))
                        .map(IRecipe::getId)
                        .collect(Collectors.toList());

                recipes.forEach(r -> {
                    RecipeHelper.getRecipes().get(RecipeTypes.REPROCESSOR).remove(r);
                });
            }

            @Override
            public String describe() {
                return "Removing Reprocessor Crafting recipes for " + stack.getCommandString();
            }
        });
    }

    private static NonNullList<Ingredient> toIngredientsList(IIngredient... ingredients) {
        return Arrays.stream(ingredients)
                .map(IIngredient::asVanillaIngredient)
                .collect(Collectors.toCollection(NonNullList::create));
    }
}
