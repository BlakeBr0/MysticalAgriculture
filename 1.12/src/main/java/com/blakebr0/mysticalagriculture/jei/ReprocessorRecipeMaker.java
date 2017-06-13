package com.blakebr0.mysticalagriculture.jei;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.mysticalagriculture.crafting.ReprocessorManager;
import com.blakebr0.mysticalagriculture.crafting.ReprocessorRecipe;

import net.minecraft.item.ItemStack;

public class ReprocessorRecipeMaker {
	
    public static List<ReprocessorWrapper> getRecipes(){
        List<ReprocessorWrapper> recipes = new ArrayList<ReprocessorWrapper>();

        for(ReprocessorRecipe recipe : ReprocessorManager.getRecipes()) {

            ItemStack input = recipe.getInput();
            ItemStack output = recipe.getOutput();

            recipes.add(new ReprocessorWrapper(input, output));
        }

        return recipes;
    }

}
