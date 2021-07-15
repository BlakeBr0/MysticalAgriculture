package com.blakebr0.mysticalagriculture.compat.crafttweaker;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.crafting.recipe.InfusionRecipe;
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

@ZenCodeType.Name("mods.mysticalagriculture.InfusionCrafting")
@ZenRegister
public final class InfusionCrafting {
    @ZenCodeType.Method
    public static void addRecipe(String id, IItemStack output, IIngredient[] inputs) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                InfusionRecipe recipe = new InfusionRecipe(new ResourceLocation("crafttweaker", id), toIngredientsList(inputs), output.getInternal());
                RecipeHelper.addRecipe(recipe);
            }

            @Override
            public String describe() {
                return "Adding Infusion Crafting recipe for " + output.getCommandString();
            }
        });
    }

    @ZenCodeType.Method
    public static void remove(IItemStack stack) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                List<ResourceLocation> recipes = RecipeHelper.getRecipes()
                        .getOrDefault(RecipeTypes.INFUSION, new HashMap<>())
                        .values().stream()
                        .filter(r -> r.getResultItem().sameItem(stack.getInternal()))
                        .map(IRecipe::getId)
                        .collect(Collectors.toList());

                recipes.forEach(r -> {
                    RecipeHelper.getRecipes().get(RecipeTypes.INFUSION).remove(r);
                });
            }

            @Override
            public String describe() {
                return "Removing Infusion Crafting recipes for " + stack.getCommandString();
            }
        });
    }

    private static NonNullList<Ingredient> toIngredientsList(IIngredient... iingredients) {
        NonNullList<Ingredient> ingredients = NonNullList.withSize(InfusionRecipe.RECIPE_SIZE, Ingredient.EMPTY);

        for (int i = 0; i < iingredients.length; i++) {
            ingredients.set(i, iingredients[i].asVanillaIngredient());
        }

        return ingredients;
    }
}
