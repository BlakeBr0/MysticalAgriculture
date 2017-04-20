package com.blakebr0.mysticalagriculture.util;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.oredict.OreDictionary;

public class Utils {

	public static String localize(String string){
		return new TextComponentTranslation(string).getFormattedText();
	}
	
	public static boolean isShiftKeyDown(){
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }
	
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
