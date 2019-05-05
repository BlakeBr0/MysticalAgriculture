package com.blakebr0.mysticalagriculture.api.crop;

import net.minecraft.util.text.TextFormatting;

import static com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI.MOD_ID;

public class CropTiers {
    public static final CropTier ELEMENTAL = new CropTier("elemental", 1, 0x748E00, TextFormatting.GRAY, MOD_ID);
    public static final CropTier ONE = new CropTier("1", 1, 0x748E00, TextFormatting.YELLOW, MOD_ID);
    public static final CropTier TWO = new CropTier("2", 2, 0x008C23, TextFormatting.GREEN, MOD_ID);
    public static final CropTier THREE = new CropTier("3", 3, 0xB74900, TextFormatting.GOLD, MOD_ID);
    public static final CropTier FOUR = new CropTier("4", 4, 0x007FDB, TextFormatting.AQUA, MOD_ID);
    public static final CropTier FIVE = new CropTier("5", 5, 0xC40000, TextFormatting.RED, MOD_ID);
}
