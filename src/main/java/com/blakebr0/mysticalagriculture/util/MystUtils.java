package com.blakebr0.mysticalagriculture.util;

import net.minecraft.util.text.TextFormatting;

public class MystUtils {
	
    public static TextFormatting getColorFromMeta(int meta) {
    	switch(meta) {
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
}
