package com.blakebr0.mysticalagriculture.util;

import org.lwjgl.input.Keyboard;

public class ShiftChecker {
	
	public static boolean isShiftKeyDown() {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

}
