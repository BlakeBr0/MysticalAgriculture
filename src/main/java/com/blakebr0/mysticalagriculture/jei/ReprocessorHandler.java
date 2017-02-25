package com.blakebr0.mysticalagriculture.jei;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class ReprocessorHandler implements IRecipeHandler<ReprocessorWrapper> {
    @Override
    @Nonnull
    public Class<ReprocessorWrapper> getRecipeClass() {
        return ReprocessorWrapper.class;
    }

    @Override
    @Nonnull
    public String getRecipeCategoryUid() {
        return ReprocessorCategory.uid;
    }

    @Override
    @Nonnull
    public String getRecipeCategoryUid(@Nonnull ReprocessorWrapper recipe) {
        return ReprocessorCategory.uid;
    }

    @Override
    @Nonnull
    public IRecipeWrapper getRecipeWrapper(@Nonnull ReprocessorWrapper recipe) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(@Nonnull ReprocessorWrapper recipe) {
        return true;
    }
}
