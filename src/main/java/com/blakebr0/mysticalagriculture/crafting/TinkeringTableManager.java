package com.blakebr0.mysticalagriculture.crafting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class TinkeringTableManager {

   private static final TinkeringTableManager INSTANCE = new TinkeringTableManager();
   private List recipes = new ArrayList();
   
   public static final TinkeringTableManager getInstance(){
	   return INSTANCE;
   }

/*   public TinkeringTableRecipe addRecipe(ItemStack result, Object... recipe){
	   TinkeringTableRecipe craft = new TinkeringTableRecipe(result, recipe);
       this.recipes.add(craft);
       return craft;
	}*/
   
   public UpgradeRecipe addUpgradeRecipe(ItemStack result, int type, Object... recipe){
	   UpgradeRecipe ur = new UpgradeRecipe(result, type, recipe);
	   this.getRecipeList().add(ur);
	   return ur;
   }

   public ItemStack findMatchingRecipe(InventoryCrafting grid, World world) {
       int i = 0;
       ItemStack stack = ItemStack.EMPTY;
       ItemStack stack1 = ItemStack.EMPTY;
       int j;

       for(j = 0; j < grid.getSizeInventory(); ++j) {
           ItemStack stack2 = grid.getStackInSlot(j);
           if(!stack2.isEmpty()) {
               if (i == 0) {
                   stack = stack2;
               }
               if (i == 1) {
                   stack1 = stack2;
               }
               ++i;
           }
       }

       if(i == 2 && stack.getItem() == stack1.getItem() && stack.getCount() == 1 && stack1.getCount() == 1 && stack.getItem().isRepairable()) {
           Item item = stack.getItem();
           int j1 = item.getMaxDamage() - stack.getItemDamage();
           int k = item.getMaxDamage() - stack1.getItemDamage();
           int l = j1 + k + item.getMaxDamage() * 5 / 100;
           int i1 = item.getMaxDamage() - l;

           if(i1 < 0){
               i1 = 0;
           }
           return new ItemStack(stack.getItem(), 1, i1);
       } else {
           for(j = 0; j < this.recipes.size(); ++j){
               IRecipe recipe = (IRecipe) this.recipes.get(j);
               if(recipe.matches(grid, world)) {
                   return recipe.getCraftingResult(grid);
               }
           }
           return ItemStack.EMPTY;
       }
   }

   public List getRecipeList(){
       return this.recipes;
   }

}
