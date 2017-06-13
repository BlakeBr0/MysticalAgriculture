package com.blakebr0.mysticalagriculture.crafting;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class ReprocessorManager {
	
    private static ArrayList<ReprocessorRecipe> recipes = new ArrayList<ReprocessorRecipe>();

    public static void addRecipe(ItemStack output, ItemStack input){
        recipes.add(new ReprocessorRecipe(output, 1, input, true));
    }

    public static ItemStack getOutput(ItemStack input){
        for(ReprocessorRecipe recipe : recipes){
            if(recipe.validInput(input))
                return recipe.getOutput();
        }
        return ItemStack.EMPTY; 
    }

    public static int getCost(ItemStack input){
        if(input == null)
            return 0;

        for(ReprocessorRecipe recipe : recipes){
            if(recipe.validInput(input))
                return recipe.getCost();
        }
        return 0;
    }

    public static int getPrice(ItemStack output){
        if(output == null)
            return 0;

        for(ReprocessorRecipe recipe : recipes){
            if(recipe.getOutput().isItemEqual(output))
                return recipe.getCost();
        }
        return 0;
    }

    public static String getName(ItemStack input){
        for(ReprocessorRecipe recipe : recipes){
            if(recipe.validInput(input))
                return recipe.getIngredientName();
        }
        return null;
    }

    public static ArrayList<ReprocessorRecipe> getRecipes(){
        return recipes;
    }

}
