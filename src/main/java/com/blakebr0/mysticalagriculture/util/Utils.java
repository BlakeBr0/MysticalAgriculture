package com.blakebr0.mysticalagriculture.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.oredict.OreDictionary;

public class Utils {
	
	public static Random rand = new Random();

	public static String localize(String string){
		return new TextComponentTranslation(string).getFormattedText();
	}
	
	public static boolean isShiftKeyDown(){
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }
	
	public static List<String> asList(String string){
		return Collections.singletonList(string);
	}
	
	public static int randInt(int min, int max){
		return rand.nextInt(max - min + 1) + min;
	}
	
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
