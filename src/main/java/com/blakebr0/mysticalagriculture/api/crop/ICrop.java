package com.blakebr0.mysticalagriculture.api.crop;

import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * Represents a crop and all of its information
 */
public interface ICrop {
    /**
     * The id of this crop, the modid is taken from the namespace for {@link ICrop#getModId()},
     * and the path is used for {@link ICrop#getName()}
     * @return the id of this crop
     */
    ResourceLocation getId();

    /**
     * The internal name of this crop.
     * This is used for registration, so it MUST be all lowercase with underscores for spaces
     * @return the internal name of this crop
     */
    default String getName() {
        return this.getId().getPath();
    }

    /**
     * Used to get the internal name of this crop with an _suffix
     * @param suffix the suffix to append (without the initial underscore)
     * @return the name with _suffix
     */
    default String getNameWithSuffix(String suffix) {
        return String.format("%s_%s", this.getName(), suffix);
    }

    /**
     * Get the localized name of this crop using the key crop.{@link ICrop#getModId()}.{@link ICrop#getName()}
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
    default String getModId() {
        return this.getId().getNamespace();
    }

    /**
     * The crop block for this crop type
     * @return the crop block
     */
    CropsBlock getCrop();

    /**
     * Used to set the crop block instance for this crop
     * @param crop the crop block
     * @return this crop
     */
    ICrop setCrop(CropsBlock crop);

    /**
     * The essence item for this crop type
     * @return the essence item
     */
    Item getEssence();

    /**
     * Used to set the essence item instance for this crop
     * @param essence the essence item
     * @return this crop
     */
    ICrop setEssence(Item essence);

    /**
     * The seeds item for this crop type
     * @return the seed item
     */
    BlockNamedItem getSeeds();

    /**
     * Used to set the seeds item instance for this crop
     * @param seeds the seeds item
     * @return this crop
     */
    ICrop setSeeds(BlockNamedItem seeds);

    /**
     * Get the chance of a second seed/essence dropping based on the block provided
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

    /**
     * Get the crafting ingredient for this crop from the underlying {@link CropIngredient}
     * @return the crafting material of this crop
     */
    Ingredient getCraftingMaterial();

    /**
     * Used to set the underlying {@link CropIngredient} for this crop
     * @param ingredient the crafting ingredient for this crop
     * @return this crop
     */
    ICrop setCraftingMaterial(CropIngredient ingredient);
}
