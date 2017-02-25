package com.blakebr0.mysticalagriculture.lib;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictResources {
	
    public static ItemStack getItem(String oreDict, int stackSize){
        ItemStack item = null;
 		List<ItemStack> list = OreDictionary.getOres(oreDict);
        if(!list.isEmpty()){
            item = list.get(0).copy(); {
                item.stackSize = stackSize;
            }
        }
        return item;
    }
}