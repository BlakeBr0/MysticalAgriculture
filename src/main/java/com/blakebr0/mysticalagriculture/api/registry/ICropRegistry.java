package com.blakebr0.mysticalagriculture.api.registry;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;

import java.util.List;

public interface ICropRegistry {
    /**
     * Register a crop in the crop registry
     * @param crop the crop to register
     */
    void register(ICrop crop);

    /**
     * Get an unmodifiable list of all the registered crops
     * @return a list of registered crops
     */
    List<ICrop> getRegisteredCrops();

    /**
     * Get the crop with the specified internal name from the crop registry
     * @param name the internal name of the crop (found in the seed with advanced tooltips enabled)
     * @return the crop for this name
     */
    ICrop getCropByName(String name);
}
