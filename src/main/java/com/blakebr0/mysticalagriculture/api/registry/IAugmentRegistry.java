package com.blakebr0.mysticalagriculture.api.registry;

import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public interface IAugmentRegistry {
    /**
     * Register an augment in the augment registry
     * @param augment the augment to register
     */
    void register(IAugment augment);

    /**
     * Get an unmodifiable list of all the registered crops
     * @return a list of registered crops
     */
    List<IAugment> getAugments();

    /**
     * Get the crop with the specified internal name from the crop registry
     * @param id the resource location id of the crop
     * @return the crop for this id
     */
    IAugment getAugmentById(ResourceLocation id);
}
