package com.blakebr0.mysticalagriculture.api;

import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import net.minecraftforge.fml.ModLoadingContext;

public class MysticalAgricultureAPI {
    public static final String MOD_ID = "mysticalagriculture";
    private static ICropRegistry cropRegistry;

    /**
     * The registry in which all crops are stored,
     * Crops should only be registered using the {@link com.blakebr0.mysticalagriculture.api.registry.RegisterCropsEvent},
     * Crops should only be modified using the {@link com.blakebr0.mysticalagriculture.api.registry.ModifyCropsEvent}
     * @return the crop registry
     */
    public static ICropRegistry getCropRegistry() {
        return cropRegistry;
    }

    /**
     * Used internally to set the crop registry, don't use this
     */
    public static void setCropRegistry(ICropRegistry registry) {
        if (isAllowedToFuckWithStuff()) {
            cropRegistry = registry;
        }
    }

    private static boolean isAllowedToFuckWithStuff() {
        return ModLoadingContext.get().getActiveContainer().getModId().equals(MOD_ID);
    }
}
