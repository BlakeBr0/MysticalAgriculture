package com.blakebr0.mysticalagriculture.api.crop;

/**
 * Implement this interface on objects that have an associated CropTier
 */
public interface ICropTierProvider {
    /**
     * Gets the crop tier from this object
     * @return the crop tier
     */
    CropTier getTier();
}
