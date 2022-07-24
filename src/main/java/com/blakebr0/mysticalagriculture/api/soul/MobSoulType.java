package com.blakebr0.mysticalagriculture.api.soul;

import com.blakebr0.mysticalagriculture.api.registry.IMobSoulTypeRegistry;
import com.google.common.collect.Sets;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

/**
 * The default implementation of {@link MobSoulType}
 *
 * Use or extend this class for your mob soul types
 */
public class MobSoulType {
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

    /**
     * The id of this mob soul type, the modid is taken from the namespace for {@link MobSoulType#getModId()},
     * and the path is used for {@link MobSoulType#getName()}
     * @return the id of this mob soul type
     */
    public ResourceLocation getId() {
        return this.id;
    }

    /**
     * The internal name of this mob soul type.
     * This is used for registration, so it MUST be all lowercase with underscores for spaces
     * @return the internal name of this mob soul type
     */
    public String getName() {
        return this.getId().getPath();
    }

    /**
     * The modid of the mod that registered this mob soul type
     * @return the modid of this mob soul type
     */
    public String getModId() {
        return this.getId().getNamespace();
    }

    /**
     * The resource location ids of the entity types for this mob soul type.
     * Don't modify this directly, use {@link IMobSoulTypeRegistry#addEntityTo(MobSoulType, ResourceLocation)} or
     * {@link IMobSoulTypeRegistry#removeEntityFrom(MobSoulType, ResourceLocation)}
     * @return the ids of the entities for this mob soul type
     */
    public Set<ResourceLocation> getEntityIds() {
        return this.entityIds;
    }

    /**
     * The required amount of souls to fill up a soul jar
     * @return the max amount of souls
     */
    public double getSoulRequirement() {
        return this.soulRequirement;
    }

    /**
     * Sets the required amount of souls to fill up a soul jar
     * @param soulRequirement the new soul requirement
     */
    public MobSoulType setSoulRequirement(double soulRequirement) {
        this.soulRequirement = soulRequirement;
        return this;
    }

    /**
     * The color of a jar with this mob soul type in it
     * @return the color of this mob soul type
     */
    public int getColor() {
        return this.color;
    }

    /**
     * Sets the color of a jar with this mob soul type in it
     * @param color the new color of this mob soul type
     */
    public MobSoulType setColor(int color) {
        this.color = color;
        return this;
    }

    /**
     * Checks if the supplied entity is valid for this mob soul type
     * @param entity the entity to test
     * @return is the entity valid
     */
    public boolean isEntityApplicable(LivingEntity entity) {
        var id = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType());
        return this.entityIds.contains(id);
    }

    /**
     * Gets the display name of the entity tooltip for this mob soul type
     * @return the entity display name
     */
    public Component getEntityDisplayName() {
        if (this.entityDisplayName == null) {
            if (this.entityDisplayNameKey != null) {
                this.entityDisplayName = Component.translatable(String.format("mobSoulType.%s.%s", this.getModId(), this.entityDisplayNameKey));
            } else {
                var entityId = this.entityIds.stream().findFirst().orElse(null);

                if (entityId != null) {
                    var entity = ForgeRegistries.ENTITY_TYPES.getValue(entityId);

                    if (entity != null) {
                        this.entityDisplayName = entity.getDescription();
                        return this.entityDisplayName;
                    }
                }

                this.entityDisplayName = Component.translatable("tooltip.mysticalagriculture.invalid_entity");
            }
        }

        return this.entityDisplayName;
    }

    /**
     * Sets the display name of the entity tooltip for this mob soul type
     * @param name the new display name
     * @return this mob soul type
     */
    public MobSoulType setEntityDisplayName(Component name) {
        this.entityDisplayName = name;
        return this;
    }

    /**
     * Whether this mob soul type has recipes and shows up in the creative menu
     * @return is this mob soul type enabled
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Set whether this crop should be hidden from the game
     * @param enabled the enabled state
     */
    public MobSoulType setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
