package com.blakebr0.mysticalagriculture.api;

import com.blakebr0.mysticalagriculture.api.lib.PluginConfig;
import com.blakebr0.mysticalagriculture.api.registry.IAugmentRegistry;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.blakebr0.mysticalagriculture.api.registry.IMobSoulTypeRegistry;

public interface IMysticalAgriculturePlugin {
    /**
     * Override this method to configure plugin wide settings
     * @param config the plugin config
     */
    default void configure(PluginConfig config) { }

    /**
     * Override this method and use the supplied registry to register all of your crops
     * @param registry the crop registry
     */
    default void onRegisterCrops(ICropRegistry registry) { }

    /**
     * Override this method and use the supplied registry to modify crops from the crop registry
     * @param registry the crop registry
     */
    default void onPostRegisterCrops(ICropRegistry registry) { }

    /**
     * Override this method and use the supplied registry to register all of your augments
     * @param registry the augment registry
     */
    default void onRegisterAugments(IAugmentRegistry registry) { }

    /**
     * Override this method and use to the supplied registry to modify augments from the augment registry
     * @param registry the augment registry
     */
    default void onPostRegisterAugments(IAugmentRegistry registry) { }

    /**
     * Override this method and use the supplied registry to register all of your mob soul types
     * @param registry the mob soul type registry
     */
    default void onRegisterMobSoulTypes(IMobSoulTypeRegistry registry) { }

    /**
     * Override this method and ise the supplied registry to modify mob soul types from the mob soul type registry
     * @param registry the mob soul type registry
     */
    default void onPostRegisterMobSoulTypes(IMobSoulTypeRegistry registry) { }
}
