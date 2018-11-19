package com.blakebr0.mysticalagriculture.jei;

import com.blakebr0.mysticalagriculture.crafting.ReprocessorRecipe;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class ReprocessorWrapper implements IRecipeWrapper {
	
    private ItemStack input;
    private ItemStack output;

    public ReprocessorWrapper(IJeiHelpers helper, ReprocessorRecipe recipe) {
        this.input = recipe.getInput();
        this.output = recipe.getOutput();
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(ItemStack.class, this.input);
        ingredients.setOutput(ItemStack.class, this.output);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ReprocessorWrapper)) {
            return false;
        }

        ReprocessorWrapper other = (ReprocessorWrapper) obj;

        if (!ItemStack.areItemStacksEqual(this.input, other.input)){
        	return false;
        }

        return ItemStack.areItemStacksEqual(this.output, other.output);
    }

    @Override
    public String toString() {
        return this.input + " = " + this.output;
    }
}
