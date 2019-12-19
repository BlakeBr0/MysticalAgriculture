package com.blakebr0.mysticalagriculture.api;

import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.registry.IAugmentRegistry;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.blakebr0.mysticalagriculture.api.registry.IMobSoulTypeRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModLoadingContext;

import java.util.HashSet;
import java.util.Set;

public class MysticalAgricultureAPI {
    public static final String MOD_ID = "mysticalagriculture";
    public static final Set<CropType> CROP_TYPES = new HashSet<>();
    public static final Set<CropTier> CROP_TIERS = new HashSet<>();
    private static ICropRegistry cropRegistry;
    private static IAugmentRegistry augmentRegistry;
    private static IMobSoulTypeRegistry soulTypeRegistry;

    /**
     * Get the {@link CropType} object with the provided name
     * @param name thr name of the crop type
     * @return the crop type
     */
    public static CropType getCropTypeByName(String name) {
        return CROP_TYPES.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * Get the {@link CropTier} object with the provided id
     * @param id the id of the crop tier
     * @return the crop tier
     */
    public static CropTier getCropTierById(ResourceLocation id) {
        return CROP_TIERS.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * The registry in which all crops are stored
     * @return the crop registry
     */
    public static ICropRegistry getCropRegistry() {
        return cropRegistry;
    }

    /**
     * Used internally to set the crop registry, don't use this
     */
    public static void setCropRegistry(ICropRegistry registry) {
        if (cropRegistry == null && isMysticalAgriculture()) {
            cropRegistry = registry;
        }
    }

    /**
     * The registry in which all augments are stored
     * @return the augment registry
     */
    public static IAugmentRegistry getAugmentRegistry() {
        return augmentRegistry;
    }

    /**
     * Used internally to set the augment registry, don't use this
     */
    public static void setAugmentRegistry(IAugmentRegistry registry) {
        if (augmentRegistry == null && isMysticalAgriculture()) {
            augmentRegistry = registry;
        }
    }

    /**
     * The registry in which all mob soul types are stored
     * @return the mob soul type registry
     */
    public static IMobSoulTypeRegistry getMobSoulTypeRegistry() {
        return soulTypeRegistry;
    }

    /**
     * Used internally to set the mob soul type registry, don't use this
     */
    public static void setMobSoulTypeRegistry(IMobSoulTypeRegistry registry) {
        if (soulTypeRegistry == null && isMysticalAgriculture()) {
            soulTypeRegistry = registry;
        }
    }

    private static boolean isMysticalAgriculture() {
        return ModLoadingContext.get().getActiveContainer().getModId().equals(MOD_ID);
    }
}
