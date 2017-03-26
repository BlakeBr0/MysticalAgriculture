package com.blakebr0.mysticalagriculture.jei;

import java.util.Arrays;
import java.util.List;

import com.blakebr0.mysticalagriculture.crafting.TinkeringTableRecipe;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class TinkeringTableWrapper extends BlankRecipeWrapper {

    private final TinkeringTableRecipe recipe;
    private final IJeiHelpers helper;

    public TinkeringTableWrapper(IJeiHelpers helper, TinkeringTableRecipe recipe){
        this.helper = helper;
        this.recipe = recipe;
        for(Object obj : this.recipe.getInput()){
            if(obj instanceof ItemStack){
                ItemStack stack = (ItemStack)obj;
                if(stack != null && stack.stackSize != 1){
                    stack.stackSize = 1;
                }
            }
        }
    }

    @Override
    public void getIngredients(IIngredients ingredients){
        IStackHelper helper = this.helper.getStackHelper();
        ItemStack output = recipe.getRecipeOutput();

        List<List<ItemStack>> inputs = helper.expandRecipeItemStackInputs(Arrays.asList(recipe.getInput()));

        ingredients.setInputLists(ItemStack.class, inputs);
        ingredients.setOutput(ItemStack.class, output);
    }
}