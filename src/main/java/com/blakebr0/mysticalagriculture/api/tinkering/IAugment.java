package com.blakebr0.mysticalagriculture.api.tinkering;

import com.blakebr0.mysticalagriculture.api.lib.AbilityCache;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import java.util.EnumSet;

public interface IAugment {
    /**
     * The id of this augment, the modid is taken from the namespace for {@link IAugment#getModId()},
     * and the path is used for {@link IAugment#getName()}
     * @return the id of this augment
     */
    ResourceLocation getId();

    /**
     * The internal name of this augment.
     * This is used for registration, so it MUST be all lowercase with underscores for spaces
     * @return the internal name of this augment
     */
    default String getName() {
        return this.getId().getPath();
    }

    /**
     * Used to get the internal name of this augment with an _suffix
     * @param suffix the suffix to append (without the initial underscore)
     * @return the name with _suffix
     */
    default String getNameWithSuffix(String suffix) {
        return String.format("%s_%s", this.getName(), suffix);
    }

    /**
     * Get the localized name of this augment using the key augment.{@link IAugment#getModId()}.{@link IAugment#getName()}
     * @return the localized name of this augment
     */
    default ITextComponent getDisplayName() {
        return new TranslationTextComponent(String.format("augment.%s.%s", this.getModId(), this.getName()));
    }

    /**
     * The modid of the mod that registered this augment
     * @return the modid of this augment
     */
    default String getModId() {
        return this.getId().getNamespace();
    }

    /**
     * The augment types that this augment represents
     * @return applicable augment types
     */
    EnumSet<AugmentType> getAugmentTypes();

    /**
     * The tier of this augment, used to define the minimum tier tinkerable required
     * @return the numerical tier
     */
    int getTier();

    /**
     * Get the augment item for this augment
     * @return the augment item
     */
    Item getItem();

    /**
     * The primary color of this augment (for the lighter middle areas of the augment)
     * @return the primary color
     */
    int getPrimaryColor();

    /**
     * The secondary color of this augment (for the darker areas of the augment)
     * @return the secondary color
     */
    int getSecondaryColor();

    /**
     * Should this augment have the enchantment glint?
     * @return has glint effect
     */
    default boolean hasEffect() {
        return this.getTier() >= 5;
    }

    /**
     * Whether or not this augment has recipes and shows up in the creative menu
     * @return is this augment enabled
     */
    boolean isEnabled();

    /**
     * Set whether or not this augment should be hidden from the game
     * @param enabled the enabled state
     */
    void setEnabled(boolean enabled);

    /**
     * Called when the item is used while targeting a block, {@link Item#onItemUse(ItemUseContext)}
     * @param context the item use context
     * @return was the action successful
     */
    default boolean onItemUse(ItemUseContext context) {
        return false;
    }

    /**
     * Called when the item is right clicked while not targeting a block, {@link Item#onItemRightClick(World, PlayerEntity, Hand)}
     * @param stack the item
     * @param world the world
     * @param player the player
     * @param hand the hand
     * @return was the action successful
     */
    default boolean onRightClick(ItemStack stack, World world, PlayerEntity player, Hand hand) {
        return false;
    }

    /**
     * Called when this item is right clicked on an entity, {@link Item#itemInteractionForEntity(ItemStack, PlayerEntity, LivingEntity, Hand)}
     * @param stack the item
     * @param player the player
     * @param target the clicked entity
     * @param hand the hand
     * @return was the action successful
     */
    default boolean onRightClickEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
        return false;
    }

    /**
     * Called when this item is used to attack an entity, {@link Item#hitEntity(ItemStack, LivingEntity, LivingEntity)}
     * @param stack the item
     * @param target the attacked entity
     * @param attacker the attacking entity
     * @return was the action successful
     */
    default boolean onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return false;
    }

    /**
     * Called when a block is destroyed using this item, {@link Item#onBlockDestroyed(ItemStack, World, BlockState, BlockPos, LivingEntity)}
     * @param stack the item
     * @param world the world
     * @param state the block destroyed
     * @param pos the pos of the block destroyed
     * @param entity the entity that destroyed the block
     * @return was the action successful
     */
    default boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entity) {
        return false;
    }

    /**
     * Called when a block is broken with this item, {@link Item#onBlockStartBreak(ItemStack, BlockPos, PlayerEntity)}
     * @param stack the item
     * @param pos the pos of the block broken
     * @param player the player
     * @return was the action successful
     */
    default boolean onBlockStartBreak(ItemStack stack, BlockPos pos, PlayerEntity player) {
        return false;
    }

    /**
     * Called when the item is ticked in the player's inventory
     * @param stack the item
     * @param world the world
     * @param entity the player
     * @param slot the slot
     * @param isSelected is currently being held
     */
    default void onInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {

    }

    /**
     * Called every tick for equipped armor, {@link Item#onArmorTick(ItemStack, World, PlayerEntity)}
     * @param stack the item
     * @param world the world
     * @param player the player
     */
    default void onArmorTick(ItemStack stack, World world, PlayerEntity player) {

    }

    /**
     * Called every tick for equipped armor, meant for player ability changes, {@link LivingUpdateEvent}
     * @param world the world
     * @param player the player
     * @param cache the ability cache
     */
    default void onPlayerTick(World world, PlayerEntity player, AbilityCache cache) {

    }

    /**
     * Called when the player hits the ground
     * @param world the world
     * @param player the player
     * @param event the fall event
     */
    default void onPlayerFall(World world, PlayerEntity player, LivingFallEvent event) {

    }

    /**
     * Add or modify the tool attributes
     * @param attributes the attribute map
     * @param slot the equipment slot type
     * @param stack the item
     */
    default void addToolAttributeModifiers(Multimap<String, AttributeModifier> attributes, EquipmentSlotType slot, ItemStack stack) {

    }

    /**
     * Add or modify the armor attributes
     * @param attributes the attribute map
     * @param slot the equipment slot type
     * @param stack the item
     */
    default void addArmorAttributeModifiers(Multimap<String, AttributeModifier> attributes, EquipmentSlotType slot, ItemStack stack) {

    }
}
