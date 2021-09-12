package com.blakebr0.mysticalagriculture.api.registry;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public interface ICropRegistry {
    /**
     * Register a crop in the crop registry
     * @param crop the crop to register
     */
    void register(Crop crop);

    /**
     * Register a crop tier in the crop registry
     * @param tier the crop tier to register
     */
    void registerTier(CropTier tier);

    /**
     * Register a crop type in the crop registry
     * @param type the crop type to register
     */
    void registerType(CropType type);

    /**
     * Get an unmodifiable list of all the registered crops
     * @return a list of registered crops
     */
    List<Crop> getCrops();

    /**
     * Get the crop with the specified internal id from the crop registry
     * @param id the resource location id of the crop
     * @return the crop for this id
     */
    Crop getCropById(ResourceLocation id);

    /**
     * Gets the crop with the specified internal name from the crop registry
     * @param name the internal name of this crop
     * @return the crop for this name
     */
    Crop getCropByName(String name);

    /**
     * Get an unmodifiable list of all the registered crop tiers
     * @return a list of registered crop tiers
     */
    List<CropTier> getTiers();

    /**
     * Gets the crop tier with the specified internal id from the crop registry
     * @param id the resource location id of the crop tier
     * @return the crop tier for this id
     */
    CropTier getTierById(ResourceLocation id);

    /**
     * Get an unmodifiable list of all the registered crop types
     * @return a list of registered crop types
     */
    List<CropType> getTypes();

    /**
     * Get an unmodifiable list of all the registered crop type
     * @param id the resource location id of the crop type
     * @return the crop type for this tier
     */
    CropType getTypeById(ResourceLocation id);
}
