package com.blakebr0.mysticalagriculture.compat.crafttweaker;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crafting.IEnchanterRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.EnchanterRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.InfusionRecipe;
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
import net.minecraftforge.registries.ForgeRegistries;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

@ZenCodeType.Name("mods.mysticalagriculture.EnchanterCrafting")
@ZenRegister
public final class EnchanterCrafting {
    @ZenCodeType.Method
    public static void addRecipe(String id, String enchantmentID, IItemStack[] inputs) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                var enchantment = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(enchantmentID));
                var recipe = new EnchanterRecipe(new ResourceLocation("crafttweaker", id), toIngredientsList(inputs), Arrays.stream(inputs).map(IItemStack::getAmount).toList(), enchantment);

                RecipeHelper.addRecipe(recipe);
            }

            @Override
            public String describe() {
                return "Adding Enchanter Crafting recipe for enchantment " + enchantmentID;
            }

            @Override
            public String systemName() {
                return MysticalAgriculture.MOD_ID;
            }
        });
    }

    @ZenCodeType.Method
    public static void remove(String enchantmentID) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                var recipes = RecipeHelper.getRecipes()
                        .getOrDefault(ModRecipeTypes.ENCHANTER.get(), new HashMap<>())
                        .values().stream()
                        .filter(r -> {
                            var enchantment = ((IEnchanterRecipe) r).getEnchantment();
                            return enchantment != null && Objects.equals(ForgeRegistries.ENCHANTMENTS.getKey(enchantment), new ResourceLocation(enchantmentID));
                        })
                        .map(Recipe::getId)
                        .toList();

                recipes.forEach(r -> {
                    RecipeHelper.getRecipes().get(ModRecipeTypes.ENCHANTER.get()).remove(r);
                });
            }

            @Override
            public String describe() {
                return "Removing Infusion Crafting recipes for enchantment " + enchantmentID;
            }

            @Override
            public String systemName() {
                return MysticalAgriculture.MOD_ID;
            }
        });
    }

    private static NonNullList<Ingredient> toIngredientsList(IIngredient... iingredients) {
        var ingredients = NonNullList.withSize(2, Ingredient.EMPTY);

        for (int i = 0; i < iingredients.length; i++) {
            ingredients.set(i, iingredients[i].asVanillaIngredient());
        }

        return ingredients;
    }
}
