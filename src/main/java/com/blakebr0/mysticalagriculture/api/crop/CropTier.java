package com.blakebr0.mysticalagriculture.api.crop;

import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class CropTier {
    private final String name;
    private final int tier;
    private final TextFormatting color;
    private final String modid;

    /**
     * Represents a tier/group of crops
     * @param name the name of the tier
     * @param tier the level of the tier, higher number = higher tier
     * @param color the text color of this tier
     * @param modid the modid that created this tier
     */
    public CropTier(String name, int tier, TextFormatting color, String modid) {
        this.name = name;
        this.tier = tier;
        this.color = color;
        this.modid = modid;
    }

    /**
     * @return the name of this tier
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the tier value of this tier
     */
    public int getTier() {
        return this.tier;
    }

    /**
     * @return the text color of this tier
     */
    public TextFormatting getColor() {
        return this.color;
    }

    /**
     * @return the mod id of the mod that created this tier
     */
    public String getModId() {
        return this.modid;
    }

    /**
     * Gets the localized name of this tier using the key cropTier.{modid}.{name}
     * Example: cropTier.mysticalagriculture.1
     * @return the localized name of this tier
     */
    public String getDisplayName() {
        return new TextComponentTranslation(String.format("cropTier.%s.%s", this.getModId(), this.getName())).applyTextStyle(this.getColor()).getFormattedText();
    }
}
