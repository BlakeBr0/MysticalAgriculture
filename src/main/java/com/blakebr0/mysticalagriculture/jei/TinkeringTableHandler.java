package com.blakebr0.mysticalagriculture.jei;

import java.util.List;

import com.blakebr0.mysticalagriculture.crafting.UpgradeRecipe;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class TinkeringTableHandler implements IRecipeHandler<UpgradeRecipe>{

    private IJeiHelpers helper;

    public TinkeringTableHandler(IJeiHelpers helper){
        this.helper = helper;
    }

    @Override
    public Class<UpgradeRecipe> getRecipeClass() {
        return UpgradeRecipe.class;
    }

    @Override
    public String getRecipeCategoryUid(UpgradeRecipe recipe) {
        return TinkeringTableCategory.UID;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(UpgradeRecipe recipe) {
        return new TinkeringTableWrapper(this.helper, recipe);
    }

    @Override
    public boolean isRecipeValid(UpgradeRecipe recipe) {
        if(recipe.getRecipeOutput() == null)
            return false;
        int inputCount = 0;
        for (Object input : recipe.getIngredients()) {
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