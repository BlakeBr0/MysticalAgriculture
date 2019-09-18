package com.blakebr0.mysticalagriculture.api.registry;

import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public interface IMobSoulTypeRegistry {
    /**
     * Register a mob soul type in the mob soul type registry
     * @param type the mob soul type to register
     */
    void register(IMobSoulType type);

    /**
     * Get an unmodifiable list of all the registered mob soul types
     * @return a list of registered mob soul types
     */
    List<IMobSoulType> getMobSoulTypes();

    /**
     * Get the mob soul type with the specified internal name from the mob soul type registry
     * @param id the resource location id of the mob soul type
     * @return the mob soul type for this id
     */
    IMobSoulType getMobSoulTypeById(ResourceLocation id);

    /**
     * Get the mob soul type for the specified entity
     * @param entity the entity to test
     * @return the mob soul type for this entity
     */
    IMobSoulType getMobSoulTypeByEntity(LivingEntity entity);
}
