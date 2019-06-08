package com.blakebr0.mysticalagriculture.api.crop;

import net.minecraft.block.Block;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

@SuppressWarnings("unchecked")
public class CropTier {
    private final String name;
    private final int tier;
    private final int color;
    private final TextFormatting textColor;
    private final String modid;
    private FarmlandBlock farmland;
    private Item essence;

    /**
     * Represents a tier/group of crops
     * @param name the name of the tier
     * @param tier the level of the tier, higher number = higher tier
     * @param color the text color of this tier
     * @param modid the modid that created this tier
     */
    public CropTier(String name, int tier, int color, TextFormatting textColor, String modid) {
        this.name = name;
        this.tier = tier;
        this.color = color;
        this.textColor = textColor;
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
     * @return the color of this tier
     */
    public int getColor() {
        return this.color;
    }

    /**
     * @return the text color of this tier
     */
    public TextFormatting getTextColor() {
        return this.textColor;
    }

    /**
     * @return the mod id of the mod that created this tier
     */
    public String getModId() {
        return this.modid;
    }

    /**
     * Gets the block instance of the effective farmland for this tier
     * @param <T> block type
     * @return the farmland for this tier
     */
    public <T extends FarmlandBlock> T getFarmland() {
        return (T) this.farmland;
    }

    /**
     * Used to set the farmland block instance for this tier
     * @param farmland the farmland block
     * @return this tier
     */
    public CropTier setFarmland(FarmlandBlock farmland) {
        this.farmland = farmland;
        return this;
    }

    /**
     * Gets the item instance of the essence for this tier
     * @param <T> item type
     * @return the essence for this tier
     */
    public <T extends Item> T getEssence() {
        return (T) this.essence;
    }

    /**
     * Used to se the essence item instance for this tier
     * @param essence the essence item
     * @return this tier
     */
    public CropTier setEssence(Item essence) {
        this.essence = essence;
        return this;
    }

    /**
     * Checks whether or not the supplied block is this tier's effective farmland
     * @param block the block to check
     * @return is the correct farmland
     */
    public boolean isEffectiveFarmland(Block block) {
        return this.farmland.equals(block);
    }

    /**
     * Gets the localized name of this tier using the key cropTier.{modid}.{name}
     * Example: cropTier.mysticalagriculture.1
     * @return the localized name of this tier
     */
    public String getDisplayName() {
        return new TranslationTextComponent(String.format("cropTier.%s.%s", this.getModId(), this.getName())).applyTextStyle(this.getTextColor()).getFormattedText();
    }
}
