package com.blakebr0.mysticalagriculture.compat.crafttweaker;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crafting.IAwakeningRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.AwakeningRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.openzen.zencode.java.ZenCodeType;

import java.util.HashMap;

@ZenCodeType.Name("mods.mysticalagriculture.AwakeningCrafting")
@ZenRegister
public final class AwakeningCrafting {
    @ZenCodeType.Method
    public static void addRecipe(String id, IItemStack output, IIngredient[] inputs, int[] essences) {
        addRecipe(id, output, inputs, essences, false);
    }

    @ZenCodeType.Method
    public static void addRecipe(String id, IItemStack output, IIngredient[] inputs, int[] essences, boolean transferNBT) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                var essenceRequirements = new IAwakeningRecipe.EssenceVesselRequirements(essences[0], essences[1], essences[2], essences[3]);
                var recipe = new AwakeningRecipe(new ResourceLocation("crafttweaker", id), toIngredientsList(inputs), essenceRequirements, output.getInternal(), transferNBT);

                RecipeHelper.addRecipe(recipe);
            }

            @Override
            public String describe() {
                return "Adding Awakening Crafting recipe for " + output.getCommandString();
            }

            @Override
            public String systemName() {
                return MysticalAgriculture.MOD_ID;
            }
        });
    }

    @ZenCodeType.Method
    public static void remove(IItemStack stack) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                var recipes = RecipeHelper.getRecipes()
                        .getOrDefault(ModRecipeTypes.AWAKENING.get(), new HashMap<>())
                        .values().stream()
                        .filter(r -> r.getResultItem().sameItem(stack.getInternal()))
                        .map(Recipe::getId)
                        .toList();

                recipes.forEach(r -> {
                    RecipeHelper.getRecipes().get(ModRecipeTypes.AWAKENING.get()).remove(r);
                });
            }

            @Override
            public String describe() {
                return "Removing Awakening Crafting recipes for " + stack.getCommandString();
            }

            @Override
            public String systemName() {
                return MysticalAgriculture.MOD_ID;
            }
        });
    }

    private static NonNullList<Ingredient> toIngredientsList(IIngredient... iingredients) {
        var ingredients = NonNullList.withSize(5, Ingredient.EMPTY);

        for (int i = 0; i < iingredients.length; i++) {
            ingredients.set(i, iingredients[i].asVanillaIngredient());
        }

        return ingredients;
    }
}
