package com.blakebr0.mysticalagriculture.api;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.registry.IAugmentRegistry;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;

public interface IMysticalAgriculturePlugin {
    /**
     * Override this method and use the supplied registry to register all of your crops
     * @param registry the crop registry
     */
    default void onRegisterCrops(ICropRegistry registry) { }

    /**
     * Override this method and use it add essence recipes to your or other crops using {@link ICrop#addEssenceRecipe}
     * @param registry the crop registry
     */
    default void onRegisterEssenceRecipes(ICropRegistry registry) { }

    /**
     * Override this method and use it to modify or remove essence recipes from crops
     * @param registry the crop registry
     */
    default void onModifyEssenceRecipes(ICropRegistry registry) { }

    /**
     * Override this method and use the supplied registry to register all of your augments
     * @param registry the augment registry
     */
    default void onRegisterAugments(IAugmentRegistry registry) { }
}
