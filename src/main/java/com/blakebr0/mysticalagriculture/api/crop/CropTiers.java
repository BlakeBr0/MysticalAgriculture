package com.blakebr0.mysticalagriculture.api.crop;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import net.minecraft.util.text.TextFormatting;

public class CropTiers {

    public static final CropTier ELEMENTAL = new CropTier("elemental", 1, TextFormatting.GRAY, MysticalAgricultureAPI.MOD_ID);
    public static final CropTier ONE = new CropTier("1", 1, TextFormatting.YELLOW, MysticalAgricultureAPI.MOD_ID);
    public static final CropTier TWO = new CropTier("2", 2, TextFormatting.GREEN, MysticalAgricultureAPI.MOD_ID);
    public static final CropTier THREE = new CropTier("3", 3, TextFormatting.GOLD, MysticalAgricultureAPI.MOD_ID);
    public static final CropTier FOUR = new CropTier("4", 4, TextFormatting.AQUA, MysticalAgricultureAPI.MOD_ID);
    public static final CropTier FIVE = new CropTier("5", 5, TextFormatting.RED, MysticalAgricultureAPI.MOD_ID);
}
