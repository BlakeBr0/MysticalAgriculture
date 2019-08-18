package com.blakebr0.mysticalagriculture.api.crop;

import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * Represents a crop and all of its information
 */
public interface ICrop {
    /**
     * The internal name of this crop.
     * This is used for registration, so it MUST be all lowercase with underscores for spaces
     * @return the name of this crop
     */
    String getName();

    /**
     * Used to get the internal name of this crop with an _suffix
     * @param suffix the suffix to append (without the initial underscore)
     * @return the name with _suffix
     */
    default String getNameWithSuffix(String suffix) {
        return String.format("%s_%s", this.getName(), suffix);
    }

    /**
     * Get the localized name of this crop using the key crop.{modid}.{name}
     * @return the localized name of this crop
     */
    default ITextComponent getDisplayName() {
        return new TranslationTextComponent(String.format("crop.%s.%s", this.getModId(), this.getName()));
    }

    /**
     * The tier/group this crop belongs to
     * @return the tier of this crop
     */
    CropTier getTier();

    /**
     * The type of crop this crop belongs to
     * @return the type of this crop
     */
    CropType getType();

    /**
     * Whether or not this crop's flower should be colored using the color defined by {@link #getFlowerColor()}
     * @return is the crop's flower colored
     */
    default boolean isFlowerColored() {
        return this.getFlowerColor() > -1;
    }

    /**
     * The color that should overlay this crop's flower
     * @return the color of this crop's flower
     */
    int getFlowerColor();

    /**
     * All the textures related to this crop
     * @return the crop's textures
     */
    CropTextures getTextures();

    /**
     * Whether or not this crop's essence should be colored using the color defined by {@link #getEssenceColor()}
     * @return is the crop's essence colored
     */
    default boolean isEssenceColored() {
        return this.getEssenceColor() > -1;
    }

    /**
     * The color that should overlay this crop's essence
     * @return the color of this crop's essence
     */
    int getEssenceColor();

    /**
     * Whether or not this crop's seed should be colored using the color defined by {@link #getSeedColor()}
     * @return is the crop's seed colored
     */
    default boolean isSeedColored() {
        return this.getSeedColor() > -1;
    }

    /**
     * The color that should overlay this crop's seed
     * @return the color of this crop's seed
     */
    int getSeedColor();

    /**
     * The modid of the mod that registered this crop
     * @return the modid of this crop
     */
    String getModId();

    /**
     * The crop block for this crop type
     * @param <T> crop block type
     * @return the crop block
     */
    <T extends CropsBlock> T getCrop();

    /**
     * Used to set the crop block instance for this crop
     * @param crop the crop block
     * @return this crop
     */
    ICrop setCrop(CropsBlock crop);

    /**
     * The essence item for this crop type
     * @param <T> essence item type
     * @return the essence item
     */
    <T extends Item> T getEssence();

    /**
     * Used to set the essence item instance for this crop
     * @param essence the essence item
     * @return this crop
     */
    ICrop setEssence(Item essence);

    /**
     * The seeds item for this crop type
     * @param <T> seed item type
     * @return the seed item
     */
    <T extends BlockNamedItem> T getSeeds();

    /**
     * Used to set the seeds item instance for this crop
     * @param seeds the seeds item
     * @return this crop
     */
    ICrop setSeeds(BlockNamedItem seeds);

    /**
     * Get the chance of a second seed/essence dropping based on the block below
     * @param block the block below the crop
     * @return the chance of a second seed/essence dropping
     */
    default int getSecondaryChance(Block block) {
        int chance = 0;
        if (block instanceof IEssenceFarmland)
            chance += 10;
        if (this.getTier().isEffectiveFarmland(block))
            chance += 10;

        return chance;
    }
}
