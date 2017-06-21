package com.blakebr0.mysticalagriculture.jei;

import java.util.Arrays;
import java.util.List;

import com.blakebr0.mysticalagriculture.crafting.UpgradeRecipe;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class TinkeringTableWrapper implements IRecipeWrapper {

    private final UpgradeRecipe recipe;
    private final IJeiHelpers helper;

    public TinkeringTableWrapper(IJeiHelpers helper, UpgradeRecipe recipe){
        this.helper = helper;
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients){
        IStackHelper helper = this.helper.getStackHelper();
        ItemStack output = recipe.getRecipeOutput();

        List<List<ItemStack>> inputs = helper.expandRecipeItemStackInputs(recipe.getIngredients());

        ingredients.setInputLists(ItemStack.class, inputs);
        ingredients.setOutput(ItemStack.class, output);
    }
}