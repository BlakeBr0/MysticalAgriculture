package com.blakebr0.mysticalagriculture.crafting;

import net.minecraft.item.ItemStack;

public class ReprocessorRecipe {
	
    private ItemStack product;
    private int cost;
    private ItemStack input;
    private boolean specific;

    public ReprocessorRecipe(ItemStack output, int amount, ItemStack ingredient, boolean exact){
        product = output;
        cost = amount;
        input = ingredient;
        specific = exact;
    }

    public ReprocessorRecipe(ItemStack output, int amount, ItemStack ingredient){
        this(output, amount, ingredient, false);
    }

    public ItemStack getOutput(){
        return product.copy();
    }
    
    public ItemStack getInput(){
    	return input.copy();
    }

    public int getCost(){
        return cost;
    }

    public boolean validInput(ItemStack ingredient){
        return ingredient.isItemEqual(input);
    }

    public String getIngredientName(){
        return input.getDisplayName();
    }

    public Object getIngredient(){
        return input;
    }

}
