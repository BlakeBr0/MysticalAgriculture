package com.blakebr0.mysticalagriculture.api.crop;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

import static com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI.MOD_ID;

public class CropTier {
    public static final CropTier ELEMENTAL = new CropTier(new ResourceLocation(MOD_ID, "elemental"), 1, 0x748E00, ChatFormatting.YELLOW);
    public static final CropTier ONE = new CropTier(new ResourceLocation(MOD_ID, "1"), 1, 0x748E00, ChatFormatting.YELLOW);
    public static final CropTier TWO = new CropTier(new ResourceLocation(MOD_ID, "2"), 2, 0x008C23, ChatFormatting.GREEN);
    public static final CropTier THREE = new CropTier(new ResourceLocation(MOD_ID, "3"), 3, 0xB74900, ChatFormatting.GOLD);
    public static final CropTier FOUR = new CropTier(new ResourceLocation(MOD_ID, "4"), 4, 0x007FDB, ChatFormatting.AQUA);
    public static final CropTier FIVE = new CropTier(new ResourceLocation(MOD_ID, "5"), 5, 0xC40000, ChatFormatting.RED);

    private final ResourceLocation id;
    private final int value;
    private final int color;
    private final ChatFormatting textColor;
    private Supplier<? extends Block> farmland;
    private Supplier<? extends Item> essence;
    private MutableComponent displayName;
    private boolean fertilizable;
    private boolean secondarySeedDrop;
    private double baseSecondaryChance;

    /**
     * Represents a tier/group of crops
     * @param id the id of this tier
     * @param value the level of this tier, higher number = higher tier
     * @param color the color of this tier
     * @param textColor the text color of this tier
     */
    public CropTier(ResourceLocation id, int value, int color, ChatFormatting textColor) {
        this.id = id;
        this.value = value;
        this.color = color;
        this.textColor = textColor;
        this.fertilizable = true;
        this.secondarySeedDrop = true;
        this.baseSecondaryChance = 0.1;
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
     * @return the mod id of the mod that created this tier
     */
    public String getModId() {
        return this.id.getNamespace();
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
    public ChatFormatting getTextColor() {
        return this.textColor;
    }

    /**
     * Gets the block instance of the effective farmland for this tier
     * @return the farmland for this tier
     */
    public Block getFarmland() {
        return this.farmland == null ? null : this.farmland.get();
    }

    /**
     * Used to set the farmland block instance for this tier
     * @param farmland the farmland block
     * @return this crop tier
     */
    public CropTier setFarmland(Supplier<? extends Block> farmland) {
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
     * @return this crop tier
     */
    public CropTier setEssence(Supplier<? extends Item> essence) {
        this.essence = essence;
        return this;
    }

    /**
     * Checks whether the supplied block is this tier's effective farmland
     * @param block the block to check
     * @return is the correct farmland
     */
    public boolean isEffectiveFarmland(Block block) {
        return this.farmland != null && this.farmland.get() == block;
    }

    /**
     * Gets the localized name of this tier using the key cropTier.{modid}.{name}
     * Example: cropTier.mysticalagriculture.1
     * @return the localized name of this tier
     */
    public MutableComponent getDisplayName() {
        if (this.displayName != null)
            return this.displayName.withStyle(this.getTextColor());

        return Component.translatable(String.format("cropTier.%s.%s", this.getModId(), this.getName())).withStyle(this.getTextColor());
    }

    /**
     * Sets the display name of this tier
     * @param name the new display name
     * @return this crop tier
     */
    public CropTier setDisplayName(MutableComponent name) {
        this.displayName = name;
        return this;
    }

    /**
     * Whether this tier's crops can be grown with Mystical Fertilizer or Fertilized Essence
     * @return is fertilizable
     */
    public boolean isFertilizable() {
        return this.fertilizable;
    }

    /**
     * Set whether this tier's crops can be grown using Mystical Fertilizer or Fertilized Essence
     * @param fertilizable the fertilizable state
     * @return this crop tier
     */
    public CropTier setFertilizable(boolean fertilizable) {
        this.fertilizable = fertilizable;
        return this;
    }

    /**
     * Whether this tier's crops can drop a second seed
     * @return has secondary seed drop
     */
    public boolean hasSecondarySeedDrop() {
        return this.secondarySeedDrop;
    }

    /**
     * Set whether this tier's crops can drop a second seed
     * @param secondarySeedDrop the secondary seed drop state
     * @return this crop tier
     */
    public CropTier setSecondarySeedDrop(boolean secondarySeedDrop) {
        this.secondarySeedDrop = secondarySeedDrop;
        return this;
    }

    /**
     * Gets the base chance of this tier's crops dropping a second seed/essence
     * @return the base secondary drop chance
     */
    public double getBaseSecondaryChance() {
        return this.baseSecondaryChance;
    }

    /**
     * Sets the base chance of this tier's crops dropping a second seed/essence
     * @param chance the base secondary drop chance
     * @return this crop tier
     */
    public CropTier setBaseSecondaryChance(double chance) {
        this.baseSecondaryChance = chance;
        return this;
    }
}
