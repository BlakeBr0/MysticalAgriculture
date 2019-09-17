package com.blakebr0.mysticalagriculture.api.soul;

import net.minecraft.util.ResourceLocation;

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
     * The resource location id of the entity type for this mob soul type
     * @return the id of this mob soul type's entity
     */
    ResourceLocation getEntityId();

    /**
     * The required amount of souls to fill up a soul jar
     * @return the max amount of souls
     */
    double getSoulRequirement();

    /**
     * Sets the required amount of souls to fill up a soul jar
     * @param soulRequirement the new soul requirement
     */
    void setSoulRequirement(double soulRequirement);

    /**
     * The color of a jar with this mob soul type in it
     * @return the color of this mob soul type
     */
    int getColor();

    /**
     * Sets the color of a jar with this mob soul type in it
     * @param color the new color of this mob soul type
     */
    void setColor(int color);
}
