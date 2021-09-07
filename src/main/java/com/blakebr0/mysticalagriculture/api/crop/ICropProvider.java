package com.blakebr0.mysticalagriculture.api.crop;

/**
 * Used as an easy way to get the crop from an object
 */
public interface ICropProvider {
    /**
     * Gets the crop from this object
     * @return the crop
     */
    Crop getCrop();
}
