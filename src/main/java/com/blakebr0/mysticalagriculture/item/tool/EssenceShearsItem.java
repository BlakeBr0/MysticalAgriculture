package com.blakebr0.mysticalagriculture.item.tool;

import com.blakebr0.cucumber.item.tool.BaseShearsItem;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Consumer;

public class EssenceShearsItem extends BaseShearsItem implements ITinkerable {
    private static final EnumSet<AugmentType> TYPES = EnumSet.of(AugmentType.TOOL, AugmentType.SHEARS);
    private final int tinkerableTier;
    private final int slots;

    public EssenceShearsItem(Tier tier, int tinkerableTier, int slots) {
        super(p -> p.durability(tier.getUses()));
        this.tinkerableTier = tinkerableTier;
        this.slots = slots;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var augments = AugmentUtils.getAugments(context.getItemInHand());
        var success = false;

        for (var augment : augments) {
            if (augment.onItemUse(context))
                success = true;
        }

        if (success)
            return InteractionResult.SUCCESS;

        return super.useOn(context);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        var stack = player.getItemInHand(hand);
        var augments = AugmentUtils.getAugments(stack);
        var success = false;

        for (var augment : augments) {
            if (augment.onRightClick(stack, level, player, hand))
                success = true;
        }

        if (success)
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);

        return new InteractionResultHolder<>(InteractionResult.PASS, stack);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        var augments = AugmentUtils.getAugments(stack);
        var success = false;

        for (var augment : augments) {
            if (augment.onRightClickEntity(stack, player, target, hand))
                success = true;
        }

        if (success)
            return InteractionResult.SUCCESS;

        return super.interactLivingEntity(stack, player, target, hand);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        var augments = AugmentUtils.getAugments(stack);
        var success = super.hurtEnemy(stack, target, attacker);

        for (var augment : augments) {
            if (augment.onHitEntity(stack, target, attacker))
                success = true;
        }

        return success;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        var augments = AugmentUtils.getAugments(stack);
        var success = super.mineBlock(stack, level, state, pos, entity);

        for (var augment : augments) {
            if (augment.onBlockDestroyed(stack, level, state, pos, entity))
                success = true;
        }

        return success;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, Player player) {
        var augments = AugmentUtils.getAugments(stack);
        var success = false;

        for (var augment : augments) {
            if (augment.onBlockStartBreak(stack, pos, player))
                success = true;
        }

        return success;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        AugmentUtils.getAugments(stack).forEach(a -> a.onInventoryTick(stack, level, entity, slot, isSelected));
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        if (stack.hurt(amount, entity.getRandom(), entity instanceof ServerPlayer ? (ServerPlayer) entity : null)) {
            onBroken.accept(entity);

            AugmentUtils.getAugments(stack).forEach(augment -> {
                Block.popResource(entity.getLevel(), entity.getOnPos(), new ItemStack(augment.getItem()));
            });

            stack.shrink(1);

            if (entity instanceof Player player) {
                player.awardStat(Stats.ITEM_BROKEN.get(this));
            }

            stack.setDamageValue(0);
        }

        return 0;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(ModTooltips.getTooltipForTier(this.tinkerableTier));

        AugmentUtils.getAugments(stack).forEach(a -> {
            tooltip.add(a.getDisplayName().withStyle(ChatFormatting.GRAY));
        });
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

        if (slot == EquipmentSlot.MAINHAND) {
            AugmentUtils.getAugments(stack).forEach(a -> {
                a.addToolAttributeModifiers(modifiers, slot, stack);
            });
        }

        return modifiers;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return ModConfigs.ENCHANTABLE_SUPREMIUM_TOOLS.get() || super.isEnchantable(stack);
    }

    @Override
    public int getAugmentSlots() {
        return this.slots;
    }

    @Override
    public EnumSet<AugmentType> getAugmentTypes() {
        return TYPES;
    }

    @Override
    public int getTinkerableTier() {
        return this.tinkerableTier;
    }
}
