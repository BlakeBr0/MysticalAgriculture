package com.blakebr0.mysticalagriculture.jei;

import java.util.Collections;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class ReprocessorWrapper implements IRecipeWrapper {
    private ItemStack input;
    private ItemStack output;

    public ReprocessorWrapper(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(ItemStack.class, input);
        ingredients.setOutput(ItemStack.class, output);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ReprocessorWrapper)) {
            return false;
        }

        ReprocessorWrapper other = (ReprocessorWrapper)obj;

        if(!ItemStack.areItemStacksEqual(input, other.input)){
        	return false;
        }

        return ItemStack.areItemStacksEqual(output, other.output);
    }

    @Override
    public String toString() {
        return input + " = " + output;
    }
}
