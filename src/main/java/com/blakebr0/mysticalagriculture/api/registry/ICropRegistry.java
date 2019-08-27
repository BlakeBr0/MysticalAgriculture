package com.blakebr0.mysticalagriculture.api.registry;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import net.minecraft.util.ResourceLocation;

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
    List<ICrop> getCrops();

    /**
     * Get the crop with the specified internal name from the crop registry
     * @param id the resource location id of the crop
     * @return the crop for this id
     */
    ICrop getCropById(ResourceLocation id);
}
