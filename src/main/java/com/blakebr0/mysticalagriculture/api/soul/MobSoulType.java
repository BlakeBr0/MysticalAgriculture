package com.blakebr0.mysticalagriculture.api.soul;

import net.minecraft.util.ResourceLocation;

/**
 * The default implementation of {@link IMobSoulType}
 *
 * Use or extend this class for your mob soul types
 */
public class MobSoulType implements IMobSoulType {
    private final ResourceLocation id;
    private final ResourceLocation entityId;
    private double soulRequirement;
    private int color;

    /**
     * Used to allow the Soul Jar to hold souls from the specified mob
     * @param id the id of this soul type
     * @param entityId the entity type id of this soul type
     * @param soulRequirement the amount of souls the soul jar can hold of this soul type
     * @param color the color of this soul type
     */
    public MobSoulType(ResourceLocation id, ResourceLocation entityId, double soulRequirement, int color) {
        this.id = id;
        this.entityId = entityId;
        this.soulRequirement = soulRequirement;
        this.color = color;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public ResourceLocation getEntityId() {
        return this.entityId;
    }

    @Override
    public double getSoulRequirement() {
        return this.soulRequirement;
    }

    @Override
    public void setSoulRequirement(double soulRequirement) {
        this.soulRequirement = soulRequirement;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }
}
