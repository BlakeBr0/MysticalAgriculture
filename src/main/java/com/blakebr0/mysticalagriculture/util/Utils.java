package com.blakebr0.mysticalagriculture.util;

import org.lwjgl.input.Keyboard;

import net.minecraft.util.text.TextComponentTranslation;

public class Utils {

	public static String localize(String string){
		return new TextComponentTranslation(string).getFormattedText();
	}
	
	public static boolean isShiftKeyDown(){
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }
}
