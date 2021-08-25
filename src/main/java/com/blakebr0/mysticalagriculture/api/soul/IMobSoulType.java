package com.blakebr0.mysticalagriculture.api.soul;

import com.blakebr0.mysticalagriculture.api.registry.IMobSoulTypeRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import java.util.Set;

// TODO: 1.17: remove
@Deprecated
public interface IMobSoulType {
    /**
     * The id of this mob soul type, the modid is taken from the namespace for {@link IMobSoulType#getModId()},
     * and the path is used for {@link IMobSoulType#getName()}
     * @return the id of this mob soul type
     */
    ResourceLocation getId();

    /**
     * The internal name of this mob soul type.
     * This is used for registration, so it MUST be all lowercase with underscores for spaces
     * @return the internal name of this mob soul type
     */
    default String getName() {
        return this.getId().getPath();
    }

    /**
     * The modid of the mod that registered this mob soul type
     * @return the modid of this mob soul type
     */
    default String getModId() {
        return this.getId().getNamespace();
    }

    /**
     * The resource location ids of the entity types for this mob soul type,
     * don't modify this directly use {@link IMobSoulTypeRegistry#addEntityTo(IMobSoulType, ResourceLocation)} or
     * {@link IMobSoulTypeRegistry#removeEntityFrom(IMobSoulType, ResourceLocation)}
     * @return the ids of the entities for this mob soul type
     */
    Set<ResourceLocation> getEntityIds();

    /**
     * The required amount of souls to fill up a soul jar
     * @return the max amount of souls
     */
    double getSoulRequirement();

    /**
     * Sets the required amount of souls to fill up a soul jar
     * @param soulRequirement the new soul requirement
     */
    IMobSoulType setSoulRequirement(double soulRequirement);

    /**
     * The color of a jar with this mob soul type in it
     * @return the color of this mob soul type
     */
    int getColor();

    /**
     * Sets the color of a jar with this mob soul type in it
     * @param color the new color of this mob soul type
     */
    IMobSoulType setColor(int color);

    /**
     * Checks if the supplied entity is valid for this mob soul type
     * @param entity the entity to test
     * @return is the entity valid
     */
    boolean isEntityApplicable(LivingEntity entity);

    /**
     * Gets the display name of the entity tooltip for this mob soul type
     * @return the entity display name
     */
    Component getEntityDisplayName();

    /**
     * Sets the display name of the entity tooltip for this mob soul type
     * @param name the new display name
     * @return this mob soul type
     */
    MobSoulType setEntityDisplayName(Component name);

    /**
     * Whether or not this mob soul type has recipes and shows up in the creative menu
     * @return is this mob soul type enabled
     */
    default boolean isEnabled() {
        return true;
    }

    /**
     * Set whether or not this crop should be hidden from the game
     * @param enabled the enabled state
     */
    default IMobSoulType setEnabled(boolean enabled) {
        return this;
    }
}
