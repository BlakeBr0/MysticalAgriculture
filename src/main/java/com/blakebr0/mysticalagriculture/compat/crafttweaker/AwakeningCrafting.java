package com.blakebr0.mysticalagriculture.compat.crafttweaker;

import com.blakebr0.mysticalagriculture.api.crafting.IAwakeningRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.AwakeningRecipe;
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
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

@ZenCodeType.Name("mods.mysticalagriculture.AwakeningCrafting")
@ZenRegister
public final class AwakeningCrafting implements IRecipeManager<IAwakeningRecipe> {
    private static final AwakeningCrafting INSTANCE = new AwakeningCrafting();

    @Override
    public RecipeType<IAwakeningRecipe> getRecipeType() {
        return ModRecipeTypes.AWAKENING.get();
    }

    @ZenCodeType.Method
    public static void addRecipe(String name, IItemStack output, IIngredient[] inputs, IItemStack[] essences, @ZenCodeType.OptionalBoolean boolean transferNBT) {
        var id = CraftTweakerConstants.rl(INSTANCE.fixRecipeName(name));
        var recipe = new AwakeningRecipe(id, toIngredientsList(inputs), toItemStackList(essences), output.getInternal(), transferNBT);

        CraftTweakerAPI.apply(new ActionAddRecipe<>(INSTANCE, recipe));
    }

    @ZenCodeType.Method
    public static void remove(IItemStack stack) {
        CraftTweakerAPI.apply(new ActionRemoveRecipe<>(INSTANCE, recipe -> recipe.getResultItem(RegistryAccess.EMPTY).is(stack.getInternal().getItem())));
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
