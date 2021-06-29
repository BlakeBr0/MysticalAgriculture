package com.blakebr0.mysticalagriculture.api.crop;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import net.minecraft.block.Block;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.function.Supplier;

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
    private Supplier<? extends FarmlandBlock> farmland;
    private Supplier<? extends Item> essence;
    private IFormattableTextComponent displayName;
    private boolean fertilizable;
    private boolean secondarySeedDrop;

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
        this.fertilizable = true;
        this.secondarySeedDrop = true;

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
        return this.farmland == null ? null : this.farmland.get();
    }

    /**
     * Used to set the farmland block instance for this tier
     * @param farmland the farmland block
     * @return this tier
     */
    // TODO: 1.17: change to ? extends Block
    public CropTier setFarmland(Supplier<? extends FarmlandBlock> farmland) {
        this.farmland = farmland;
        return this;
    }

    /**
     * Gets the item instance of the essence for this tier
     * @return the essence for this tier
     */
    public Item getEssence() {
        return this.essence == null ? null : this.essence.get();
    }

    /**
     * Used to se the essence item instance for this tier
     * @param essence the essence item
     * @return this tier
     */
    public CropTier setEssence(Supplier<? extends Item> essence) {
        this.essence = essence;
        return this;
    }

    /**
     * Checks whether or not the supplied block is this tier's effective farmland
     * @param block the block to check
     * @return is the correct farmland
     */
    public boolean isEffectiveFarmland(Block block) {
        return this.farmland.get() == block;
    }

    /**
     * Gets the localized name of this tier using the key cropTier.{modid}.{name}
     * Example: cropTier.mysticalagriculture.1
     * @return the localized name of this tier
     */
    public IFormattableTextComponent getDisplayName() {
        if (this.displayName != null)
            return this.displayName.withStyle(this.getTextColor());

        return new TranslationTextComponent(String.format("cropTier.%s.%s", this.getModId(), this.getName())).withStyle(this.getTextColor());
    }

    /**
     * Sets the display name of this tier
     * @param name the new display name
     * @return this tier
     */
    public CropTier setDisplayName(IFormattableTextComponent name) {
        this.displayName = name;
        return this;
    }

    /**
     * Whether or not this tier's crops can be grown with Mystical Fertilizer or Fertilized Essence
     * @return is fertilizable
     */
    public boolean isFertilizable() {
        return this.fertilizable;
    }

    /**
     * Set whether or not this tier's crops can be grown using Mystical Fertilizer or Fertilized Essence
     * @param fertilizable the fertilizable state
     * @return this tier
     */
    public CropTier setFertilizable(boolean fertilizable) {
        this.fertilizable = fertilizable;
        return this;
    }

    /**
     * Whether or not this tier's crops can drop a second seed
     * @return has secondary seed drop
     */
    public boolean hasSecondarySeedDrop() {
        return this.secondarySeedDrop;
    }

    /**
     * Set whether or not this tier's crops can drop a second seed
     * @param secondarySeedDrop the secondary seed drop state
     * @return this tier
     */
    public CropTier setSecondarySeedDrop(boolean secondarySeedDrop) {
        this.secondarySeedDrop = secondarySeedDrop;
        return this;
    }
}
