package com.blakebr0.mysticalagriculture.api.registry;

import com.blakebr0.mysticalagriculture.api.soul.MobSoulType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.Set;

public interface IMobSoulTypeRegistry {
    /**
     * Register a mob soul type in the mob soul type registry
     * @param type the mob soul type to register
     */
    void register(MobSoulType type);

    /**
     * Get an unmodifiable list of all the registered mob soul types
     * @return a list of registered mob soul types
     */
    List<MobSoulType> getMobSoulTypes();

    /**
     * Get the mob soul type with the specified internal name from the mob soul type registry
     * @param id the resource location id of the mob soul type
     * @return the mob soul type for this id
     */
    MobSoulType getMobSoulTypeById(ResourceLocation id);

    /**
     * Get the mob soul type for the specified entity
     * @param entity the entity to test
     * @return the mob soul type for this entity
     */
    MobSoulType getMobSoulTypeByEntity(LivingEntity entity);

    /**
     * Get an unmodifiable set of all of the entity ids currently used by a mob soul type
     * @return a set of the used entity ids
     */
    Set<ResourceLocation> getUsedEntityIds();

    /**
     * Adds a new entity id to the specified mob soul type, if it's currently unused
     * @param type the mob soul type to modify
     * @param entity the id of the entity to add
     * @return was this entity added successfully
     */
    boolean addEntityTo(MobSoulType type, ResourceLocation entity);

    /**
     * Removes an entity id from the specified mob soul type, if it's currently added
     * @param type the mob soul type to modify
     * @param entity the id of the entity to remove
     * @return was this entity removed successfully
     */
    boolean removeEntityFrom(MobSoulType type, ResourceLocation entity);
}
