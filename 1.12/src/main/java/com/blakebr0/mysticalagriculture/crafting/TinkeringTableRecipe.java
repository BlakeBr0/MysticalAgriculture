package com.blakebr0.mysticalagriculture.crafting;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nonnull;

import com.blakebr0.mysticalagriculture.items.armor.ArmorType;
import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;
import com.blakebr0.mysticalagriculture.util.NBTHelper;

import java.util.*;

public class TinkeringTableRecipe extends ShapedOreRecipe {

    private final Item resultItem;
    private final int resultMeta;
    private final int type;

    public TinkeringTableRecipe(ItemStack result, int type, Object... recipe){
		super(new ResourceLocation("", ""), result, recipe);
		this.resultItem = result.getItem();
		this.resultMeta = result.getMetadata();
		this.type = type;
		this.setMirrored(false);
	}

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {        
        ItemStack result = new ItemStack((Item)this.resultItem, 1, this.resultMeta);
        NBTTagCompound tag = result.getTagCompound();
        if(tag == null){
        	tag = new NBTTagCompound();
        }
    	tag.setInteger(ArmorType.ARMOR_TYPE, type);
    	result.setTagCompound(tag);
        return result;
    }
    
    @Override
    public ItemStack getRecipeOutput(){
        ItemStack result = new ItemStack((Item) this.resultItem, 1, this.resultMeta);
        NBTTagCompound tag = result.getTagCompound();
        if(tag == null){
        	tag = new NBTTagCompound();
        }
    	tag.setInteger(ArmorType.ARMOR_TYPE, type);
    	result.setTagCompound(tag);
    	return result;
    }
    
    @Override
    public boolean matches(InventoryCrafting inv, World world){
        for(int x = 0; x <= MAX_CRAFT_GRID_WIDTH - width; x++){
            for(int y = 0; y <= MAX_CRAFT_GRID_HEIGHT - height; ++y){
            	ItemStack stack = inv.getStackInRowAndColumn(x, y);
            	if(!stack.isEmpty() && stack.hasTagCompound()){
            		if(stack.getTagCompound().hasKey(ArmorType.ARMOR_TYPE)){
            			return false;
            		} else {
                        if(checkMatch(inv, x, y, false)){
                            return true;
                        }
            		}
            	} else {
                    if(checkMatch(inv, x, y, false)){
                        return true;
                    }
            	}
            }
        }
        return false;
    }
}