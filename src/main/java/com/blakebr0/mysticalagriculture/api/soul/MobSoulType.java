package com.blakebr0.mysticalagriculture.api.soul;

import com.google.common.collect.Sets;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

/**
 * The default implementation of {@link IMobSoulType}
 *
 * Use or extend this class for your mob soul types
 */
public class MobSoulType implements IMobSoulType {
    private final ResourceLocation id;
    private final Set<ResourceLocation> entityIds;
    private double soulRequirement;
    private int color;
    private String entityDisplayNameKey = null;
    private Component entityDisplayName = null;
    private boolean enabled;

    /**
     * Used to allow the Soul Jar to hold souls from the specified mob
     * @param id the id of this mob soul type
     * @param entityId the entity type id of this mob soul type
     * @param soulRequirement the amount of souls the soul jar can hold of this mob soul type
     * @param color the color of this mob soul type
     */
    public MobSoulType(ResourceLocation id, ResourceLocation entityId, double soulRequirement, int color) {
        this.id = id;
        this.entityIds = Sets.newHashSet(entityId);
        this.soulRequirement = soulRequirement;
        this.color = color;
        this.enabled = true;
    }

    /**
     * Used to allow the Soul Jar to hold souls from the specified mob
     * @param id the id of this mob soul type
     * @param entityId the entity type id of this mob soul type
     * @param entityDisplayNameKey the key used to create a translation key for the entity tooltip, automatically prepends 'mobSoulTpye.(modid).'
     * @param soulRequirement the amount of souls the soul jar can hold of this mob soul type
     * @param color the color of this mob soul type
     */
    public MobSoulType(ResourceLocation id, ResourceLocation entityId, String entityDisplayNameKey, double soulRequirement, int color) {
        this.id = id;
        this.entityIds = Sets.newHashSet(entityId);
        this.soulRequirement = soulRequirement;
        this.entityDisplayNameKey = entityDisplayNameKey;
        this.color = color;
        this.enabled = true;
    }

    /**
     * Used to allow the Soul Jar to hold souls from the specified mobs
     * @param id the id of this mob soul type
     * @param entityIds the set of entity type ids for this mob soul type
     * @param entityDisplayNameKey the key used to create a translation key for the entity tooltip, automatically prepends 'mobSoulTpye.(modid).'
     * @param soulRequirement the amount of souls the soul jar can hold of this mob soul type
     * @param color the color of this mob soul type
     */
    public MobSoulType(ResourceLocation id, Set<ResourceLocation> entityIds, String entityDisplayNameKey, double soulRequirement, int color) {
        this.id = id;
        this.entityIds = entityIds;
        this.soulRequirement = soulRequirement;
        this.entityDisplayNameKey = entityDisplayNameKey;
        this.color = color;
        this.enabled = true;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public Set<ResourceLocation> getEntityIds() {
        return this.entityIds;
    }

    @Override
    public double getSoulRequirement() {
        return this.soulRequirement;
    }

    @Override
    public IMobSoulType setSoulRequirement(double soulRequirement) {
        this.soulRequirement = soulRequirement;
        return this;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public IMobSoulType setColor(int color) {
        this.color = color;
        return this;
    }

    @Override
    public boolean isEntityApplicable(LivingEntity entity) {
        return this.entityIds.contains(entity.getType().getRegistryName());
    }

    @Override
    public Component getEntityDisplayName() {
        if (this.entityDisplayName == null) {
            if (this.entityDisplayNameKey != null) {
                this.entityDisplayName = new TranslatableComponent(String.format("mobSoulType.%s.%s", this.getModId(), this.entityDisplayNameKey));
            } else {
                var entityId = this.entityIds.stream().findFirst().orElse(null);

                if (entityId != null) {
                    var entity = ForgeRegistries.ENTITIES.getValue(entityId);

                    if (entity != null) {
                        this.entityDisplayName = entity.getDescription();
                        return this.entityDisplayName;
                    }
                }

                this.entityDisplayName = new TranslatableComponent("tooltip.mysticalagriculture.invalid_entity");
            }
        }

        return this.entityDisplayName;
    }

    @Override
    public MobSoulType setEntityDisplayName(Component name) {
        this.entityDisplayName = name;
        return this;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public IMobSoulType setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
