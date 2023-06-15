package com.blakebr0.mysticalagriculture.compat.crafttweaker;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.mysticalagriculture.crafting.recipe.AwakeningRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.openzen.zencode.java.ZenCodeType;

import java.util.HashMap;

@ZenCodeType.Name("mods.mysticalagriculture.AwakeningCrafting")
@ZenRegister
public final class AwakeningCrafting {
    @ZenCodeType.Method
    public static void addRecipe(String id, IItemStack output, IIngredient[] inputs, IItemStack[] essences) {
        addRecipe(id, output, inputs, essences, false);
    }

    @ZenCodeType.Method
    public static void addRecipe(String id, IItemStack output, IIngredient[] inputs, IItemStack[] essences, boolean transferNBT) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                var recipe = new AwakeningRecipe(new ResourceLocation("crafttweaker", id), toIngredientsList(inputs), toItemStackList(essences), output.getInternal(), transferNBT);

                RecipeHelper.addRecipe(recipe);
            }

            @Override
            public String describe() {
                return "Adding Awakening Crafting recipe for " + output.getCommandString();
            }
        });
    }

    @ZenCodeType.Method
    public static void remove(IItemStack stack) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                var access = ServerLifecycleHooks.getCurrentServer().registryAccess();
                var recipes = RecipeHelper.getRecipes()
                        .getOrDefault(ModRecipeTypes.AWAKENING.get(), new HashMap<>())
                        .values().stream()
                        .filter(r -> r.getResultItem(access).is(stack.getInternal().getItem()))
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
        });
    }

    private static NonNullList<Ingredient> toIngredientsList(IIngredient... iingredients) {
        var ingredients = NonNullList.withSize(5, Ingredient.EMPTY);

        for (int i = 0; i < iingredients.length; i++) {
            ingredients.set(i, iingredients[i].asVanillaIngredient());
        }

        return ingredients;
    }

    private static NonNullList<ItemStack> toItemStackList(IItemStack... iitemStacks) {
        var itemStacks = NonNullList.withSize(4, ItemStack.EMPTY);

        for (int i = 0; i < iitemStacks.length; i++) {
            itemStacks.set(i, iitemStacks[i].getInternal());
        }

        return itemStacks;
    }
}
