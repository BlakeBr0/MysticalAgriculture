package com.blakebr0.mysticalagriculture.api.soul;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.Set;

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
     * The resource location ids of the entity types for this mob soul type
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
    ITextComponent getEntityDisplayName();

    /**
     * Sets the display name of the entity tooltip for this mob soul type
     * @param name the new display name
     * @return this mob soul type
     */
    MobSoulType setEntityDisplayName(ITextComponent name);
}
