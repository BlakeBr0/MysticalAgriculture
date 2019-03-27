package com.blakebr0.mysticalagriculture.api.crop;

import net.minecraft.util.ResourceLocation;

/**
 * Represents a crop and all its information
 */
public interface ICrop {
    /**
     * The internal name of this crop.
     * This is used for registration, so it MUST be all lowercase with underscores for spaces
     * @return the name of this crop
     */
    String getName();

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
        return getFlowerColor() > -1;
    }

    /**
     * The color that should overlay this crop's flower
     * @return the color of this crop's flower
     */
    int getFlowerColor();

    /**
     * The resource location of the flower texture for this crop
     * @return the crop's flower texture location
     */
    ResourceLocation getFlowerTexture();

    /**
     * Whether or not this crop's essence should be colored using the color defined by {@link #getEssenceColor()}
     * @return is the crop's essence colored
     */
    default boolean isEssenceColored() {
        return getEssenceColor() > -1;
    }

    /**
     * The color that should overlay this crop's essence
     * @return the color of this crop's essence
     */
    int getEssenceColor();

    /**
     * The resource location of the essence texture for this crop
     * @return the crop's essence texture location
     */
    ResourceLocation getEssenceTexture();

    /**
     * Whether or not this crop's seed should be colored using the color defined by {@link #getSeedColor()}
     * @return is the crop's seed colored
     */
    default boolean isSeedColored() {
        return getSeedColor() > -1;
    }

    /**
     * The color that should overlay this crop's seed
     * @return the color of this crop's seed
     */
    int getSeedColor();

    /**
     * The resource location of the seed texture for this crop
     * @return the crop's seed texture location
     */
    ResourceLocation getSeedTexture();
}
