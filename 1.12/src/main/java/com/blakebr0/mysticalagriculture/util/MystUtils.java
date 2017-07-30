package com.blakebr0.mysticalagriculture.util;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.oredict.OreDictionary;

public class MystUtils {
	
    public static TextFormatting getColorFromMeta(int meta){
    	switch(meta){
    	case 0: 
    		return TextFormatting.YELLOW;
    	case 1:
    		return TextFormatting.GREEN;
    	case 2:
    		return TextFormatting.GOLD;
    	case 3:
    		return TextFormatting.AQUA;
    	case 4:
    		return TextFormatting.RED;
    	}
		return TextFormatting.GRAY;
    }
	
    // TODO: cucumber
    public static ItemStack getItem(String oreDict, int stackSize){
        ItemStack item = ItemStack.EMPTY;
 		List<ItemStack> list = OreDictionary.getOres(oreDict);
        if(!list.isEmpty()){
            item = list.get(0).copy(); {
                item.setCount(stackSize);
            }
        }
        return item;
    }

}
