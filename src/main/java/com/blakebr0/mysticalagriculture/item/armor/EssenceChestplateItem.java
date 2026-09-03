package com.blakebr0.mysticalagriculture.item.armor;

import com.blakebr0.cucumber.item.BaseArmorItem;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModDataComponentTypes;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class EssenceChestplateItem extends BaseArmorItem implements ITinkerable {
    private static final EnumSet<AugmentType> TYPES = EnumSet.of(AugmentType.ARMOR, AugmentType.CHESTPLATE);
    private final int tinkerableTier;
    private final int slots;

    public EssenceChestplateItem(Holder<ArmorMaterial> material, int maxDamageFactor, int tinkerableTier, int slots) {
        super(material, Type.CHESTPLATE, maxDamageFactor, p -> {
            p.component(ModDataComponentTypes.EQUIPPED_AUGMENTS, new ArrayList<>(slots));

            // Supremium+ armor can be unbreakable if enabled
            if (tinkerableTier >= 5 && ModConfigs.UNBREAKABLE_SUPREMIUM_ARMOR.get()) {
                p.component(DataComponents.UNBREAKABLE, new Unbreakable(true));
            }

            return p;
        });
        this.tinkerableTier = tinkerableTier;
        this.slots = slots;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (slot == 38 && entity instanceof Player player) {
            for (var augment : AugmentUtils.getAugments(stack)) {
                augment.onArmorTick(stack, level, player);
            }

            if (ModConfigs.AWAKENED_SUPREMIUM_SET_BONUS.get() && !level.isClientSide() && level.getGameTime() % 20L == 0 && hasAwakenedSupremiumSet(player)) {
                handleGrowthTicks(level, player);
            }
        }

        for (var augment : AugmentUtils.getAugments(stack)) {
            augment.onInventoryTick(stack, level, entity, slot, isSelected);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(ModTooltips.getTooltipForTier(this.tinkerableTier));

        if (ModConfigs.AWAKENED_SUPREMIUM_SET_BONUS.get() && stack.is(ModItems.AWAKENED_SUPREMIUM_CHESTPLATE.get())) {
            tooltip.add(ModTooltips.SET_BONUS.args(ModTooltips.AWAKENED_SUPREMIUM_SET_BONUS.build()).build());
        }

        ModTooltips.addAugmentListToTooltip(tooltip, stack, this.slots);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return ModConfigs.ENCHANTABLE_SUPREMIUM_ARMOR.get();
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

    private static boolean hasAwakenedSupremiumSet(Player player) {
        var helmet = player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.AWAKENED_SUPREMIUM_HELMET.get());
        var chestplate = player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.AWAKENED_SUPREMIUM_CHESTPLATE.get());
        var leggings = player.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.AWAKENED_SUPREMIUM_LEGGINGS.get());
        var boots = player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.AWAKENED_SUPREMIUM_BOOTS.get());

        return helmet && chestplate && leggings && boots;
    }

    private static void handleGrowthTicks(Level level, Player player) {
        var pos = player.getOnPos();
        int range = 5;

        BlockPos.betweenClosedStream(pos.offset(-range, -range, -range), pos.offset(range, range, range)).forEach(aoePos -> {
            if (Math.random() < 0.5)
                return;

            var state = level.getBlockState(aoePos);

            if (state.is(BlockTags.CROPS)) {
                state.randomTick((ServerLevel) level, aoePos, Utils.RANDOM);

                double d0 = aoePos.getX() + level.getRandom().nextFloat();
                double d1 = aoePos.getY();
                double d2 = aoePos.getZ() + level.getRandom().nextFloat();

                ((ServerLevel) level).sendParticles(ParticleTypes.HAPPY_VILLAGER, d0, d1, d2, 1, 0, 0, 0, 0.1D);
            }
        });
    }
}
