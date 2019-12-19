package com.blakebr0.mysticalagriculture.api.crop;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import net.minecraft.block.Block;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import static com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI.MOD_ID;

public class CropTier {
    public static final CropTier ELEMENTAL = new CropTier(new ResourceLocation(MOD_ID, "elemental"), 1, 0x748E00, TextFormatting.YELLOW);
    public static final CropTier ONE = new CropTier(new ResourceLocation(MOD_ID, "1"), 1, 0x748E00, TextFormatting.YELLOW);
    public static final CropTier TWO = new CropTier(new ResourceLocation(MOD_ID, "2"), 2, 0x008C23, TextFormatting.GREEN);
    public static final CropTier THREE = new CropTier(new ResourceLocation(MOD_ID, "3"), 3, 0xB74900, TextFormatting.GOLD);
    public static final CropTier FOUR = new CropTier(new ResourceLocation(MOD_ID, "4"), 4, 0x007FDB, TextFormatting.AQUA);
    public static final CropTier FIVE = new CropTier(new ResourceLocation(MOD_ID, "5"), 5, 0xC40000, TextFormatting.RED);

    private final ResourceLocation id;
    private final int value;
    private final int color;
    private final TextFormatting textColor;
    private FarmlandBlock farmland;
    private Item essence;

    /**
     * Represents a tier/group of crops
     * @param name the name of the tier
     * @param value the level of the tier, higher number = higher tier
     * @param color the text color of this tier
     * @param modid the modid that created this tier
     */
    @Deprecated // USE RESOURCE LOCATION CONSTRUCTOR TODO: REMOVE 1.15
    public CropTier(String name, int value, int color, TextFormatting textColor, String modid) {
        this(new ResourceLocation(modid, name), value, color, textColor);
    }

    /**
     * Represents a tier/group of crops
     * @param id the id of this tier
     * @param value the level of this tier, higher number = higher tier
     * @param color the color of this tier
     * @param textColor the text color of this tier
     */
    public CropTier(ResourceLocation id, int value, int color, TextFormatting textColor) {
        this.id = id;
        this.value = value;
        this.color = color;
        this.textColor = textColor;

        MysticalAgricultureAPI.CROP_TIERS.add(this);
    }

    /**
     * @return the id of this crop tier
     */
    public ResourceLocation getId() {
        return this.id;
    }

    /**
     * @return the name of this tier
     */
    public String getName() {
        return this.id.getPath();
    }

    /**
     * @return the tier value of this tier
     */
    public int getValue() {
        return this.value;
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
        return this.id.getNamespace();
    }

    /**
     * Gets the block instance of the effective farmland for this tier
     * @return the farmland for this tier
     */
    public FarmlandBlock getFarmland() {
        return this.farmland;
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
     * @return the essence for this tier
     */
    public Item getEssence() {
        return this.essence;
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
        return this.farmland == block;
    }

    /**
     * Gets the localized name of this tier using the key cropTier.{modid}.{name}
     * Example: cropTier.mysticalagriculture.1
     * @return the localized name of this tier
     */
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent(String.format("cropTier.%s.%s", this.getModId(), this.getName())).applyTextStyle(this.getTextColor());
    }
}
