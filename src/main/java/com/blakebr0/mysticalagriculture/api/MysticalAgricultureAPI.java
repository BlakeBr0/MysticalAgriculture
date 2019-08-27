package com.blakebr0.mysticalagriculture.api;

import com.blakebr0.mysticalagriculture.api.registry.IAugmentRegistry;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.blakebr0.mysticalagriculture.api.registry.ModifyCropsEvent;
import com.blakebr0.mysticalagriculture.api.registry.RegisterAugmentsEvent;
import com.blakebr0.mysticalagriculture.api.registry.RegisterCropsEvent;
import net.minecraftforge.fml.ModLoadingContext;

public class MysticalAgricultureAPI {
    public static final String MOD_ID = "mysticalagriculture";
    private static ICropRegistry cropRegistry;
    private static IAugmentRegistry augmentRegistry;

    /**
     * The registry in which all crops are stored,
     * Crops should only be registered using the {@link RegisterCropsEvent},
     * Crops should only be modified using the {@link ModifyCropsEvent}
     * @return the crop registry
     */
    public static ICropRegistry getCropRegistry() {
        return cropRegistry;
    }

    /**
     * Used internally to set the crop registry, don't use this
     */
    public static void setCropRegistry(ICropRegistry registry) {
        if (isMysticalAgriculture()) {
            cropRegistry = registry;
        }
    }

    /**
     * The registry in which all augments are stored,
     * Augments should only be registered using the {@link RegisterAugmentsEvent}
     * @return the augment registry
     */
    public static IAugmentRegistry getAugmentRegistry() {
        return augmentRegistry;
    }

    /**
     * Used internally to set the augment registry, don't use this
     */
    public static void setAugmentRegistry(IAugmentRegistry registry) {
        if (isMysticalAgriculture()) {
            augmentRegistry = registry;
        }
    }

    private static boolean isMysticalAgriculture() {
        return ModLoadingContext.get().getActiveContainer().getModId().equals(MOD_ID);
    }
}
