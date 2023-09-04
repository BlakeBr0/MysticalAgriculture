package com.blakebr0.mysticalagriculture.compat.crafttweaker;

import com.blakebr0.mysticalagriculture.api.crafting.IEnchanterRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.EnchanterRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.action.recipe.ActionRemoveRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;
import java.util.Objects;

@ZenCodeType.Name("mods.mysticalagriculture.EnchanterCrafting")
@ZenRegister
public final class EnchanterCrafting implements IRecipeManager<IEnchanterRecipe> {
    private static final EnchanterCrafting INSTANCE = new EnchanterCrafting();

    @Override
    public RecipeType<IEnchanterRecipe> getRecipeType() {
        return ModRecipeTypes.ENCHANTER.get();
    }

    @ZenCodeType.Method
    public static void addRecipe(String name, String enchantmentID, IItemStack[] inputs) {
        var id = CraftTweakerConstants.rl(INSTANCE.fixRecipeName(name));
        var enchantment = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(enchantmentID));
        var recipe = new EnchanterRecipe(id, toIngredientsList(inputs), Arrays.stream(inputs).map(IItemStack::getAmount).toList(), enchantment);

        CraftTweakerAPI.apply(new ActionAddRecipe<>(INSTANCE, recipe));
    }

    @ZenCodeType.Method
    public static void remove(String enchantmentID) {
        CraftTweakerAPI.apply(new ActionRemoveRecipe<>(INSTANCE, recipe -> {
            var enchantment = recipe.getEnchantment();
            return enchantment != null && Objects.equals(ForgeRegistries.ENCHANTMENTS.getKey(enchantment), new ResourceLocation(enchantmentID));
        }));
    }

    private static NonNullList<Ingredient> toIngredientsList(IIngredient... iingredients) {
        var ingredients = NonNullList.withSize(2, Ingredient.EMPTY);

        for (int i = 0; i < iingredients.length; i++) {
            ingredients.set(i, iingredients[i].asVanillaIngredient());
        }

        return ingredients;
    }
}
