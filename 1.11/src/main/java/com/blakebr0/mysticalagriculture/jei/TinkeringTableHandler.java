package com.blakebr0.mysticalagriculture.jei;

import java.util.List;

import com.blakebr0.mysticalagriculture.crafting.TinkeringTableRecipe;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class TinkeringTableHandler implements IRecipeHandler<TinkeringTableRecipe>{

    private IJeiHelpers helper;

    public TinkeringTableHandler(IJeiHelpers helper){
        this.helper = helper;
    }

    @Override
    public Class<TinkeringTableRecipe> getRecipeClass() {
        return TinkeringTableRecipe.class;
    }

    @Override
    public String getRecipeCategoryUid(TinkeringTableRecipe recipe) {
        return TinkeringTableCategory.UID;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(TinkeringTableRecipe recipe) {
        return new TinkeringTableWrapper(this.helper, recipe);
    }

    @Override
    public boolean isRecipeValid(TinkeringTableRecipe recipe) {
        if(recipe.getRecipeOutput() == null)
            return false;
        int inputCount = 0;
        for (Object input : recipe.getInput()) {
            if(input != null) {
                if(input instanceof List && ((List) input).isEmpty())
                {
                    return false;
                }
                inputCount++;
            }
        }
        if(inputCount > 9)
            return false;

        return inputCount > 0;
    }
}