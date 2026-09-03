package com.blakebr0.mysticalagriculture.item.armor;

import com.blakebr0.cucumber.item.BaseArmorItem;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModDataComponentTypes;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class EssenceHelmetItem extends BaseArmorItem implements ITinkerable {
    private static final EnumSet<AugmentType> TYPES = EnumSet.of(AugmentType.ARMOR, AugmentType.HELMET);
    private final int tinkerableTier;
    private final int slots;

    public EssenceHelmetItem(Holder<ArmorMaterial> material, int maxDamageFactor, int tinkerableTier, int slots) {
        super(material, Type.HELMET, maxDamageFactor, p -> {
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
        if (slot == 39 && entity instanceof Player player) {
            for (var augment : AugmentUtils.getAugments(stack)) {
                augment.onArmorTick(stack, level, player);
            }
        }

        for (var augment : AugmentUtils.getAugments(stack)) {
            augment.onInventoryTick(stack, level, entity, slot, isSelected);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(ModTooltips.getTooltipForTier(this.tinkerableTier));

        if (ModConfigs.AWAKENED_SUPREMIUM_SET_BONUS.get() && stack.is(ModItems.AWAKENED_SUPREMIUM_HELMET.get())) {
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
}
