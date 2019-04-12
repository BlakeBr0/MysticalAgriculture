package com.blakebr0.mysticalagriculture.api.registry;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;

import java.util.List;

public interface ICropRegistry {
    /**
     * Register a crop in the crop registry
     * @param crop the crop to register
     * @throws RuntimeException when registering outside of the {@link RegisterCropsEvent}
     */
    void register(ICrop crop);

    /**
     * Get an unmodifiable list of all the registered crops
     * @return a list of registered crops
     */
    List<ICrop> getRegisteredCrops();
}
