package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.tool.BaseSwordItem;
import com.blakebr0.mysticalagriculture.api.soul.ISoulSiphoningItem;
import com.blakebr0.mysticalagriculture.lib.ModItemTier;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SouliumDaggerItem extends BaseSwordItem implements ISoulSiphoningItem {
    private final DaggerType type;

    public SouliumDaggerItem(Tier tier, DaggerType type, Function<Properties, Properties> properties) {
        super(tier, type.getDamage(), -2.4F, properties.compose(p -> p.defaultDurability(type.getDurability())));
        this.type = type;
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return "item.mysticalagriculture.soulium_dagger";
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        switch (this.type) {
            case PASSIVE -> {
                tooltip.add(ModTooltips.PASSIVE_ATTUNED.color(ChatFormatting.GREEN).build());
                tooltip.add(ModTooltips.PASSIVE_SOULIUM_DAGGER.build());
            }
            case HOSTILE -> {
                tooltip.add(ModTooltips.HOSTILE_ATTUNED.color(ChatFormatting.RED).build());
                tooltip.add(ModTooltips.HOSTILE_SOULIUM_DAGGER.build());
            }
            case CREATIVE -> {
                tooltip.add(ModTooltips.CREATIVE_ATTUNED.color(ChatFormatting.LIGHT_PURPLE).build());
                tooltip.add(ModTooltips.CREATIVE_SOULIUM_DAGGER.build());
            }
        }
    }

    @Override
    public double getSiphonAmount(ItemStack stack, LivingEntity entity) {
        return this.type.getSiphonAmount(stack, entity);
    }

    public enum DaggerType {
        BASIC(3, ModItemTier.SOULIUM.getUses(), (stack, entity) -> 1.0D),
        PASSIVE(6, ModItemTier.SOULIUM.getUses() * 2, (stack, entity) -> isPassive(entity) ? 1.5D : 1.0D),
        HOSTILE(6, ModItemTier.SOULIUM.getUses() * 2, (stack, entity) -> !isPassive(entity) ? 1.5D : 1.0D),
        CREATIVE(65, -1, (stack, entity) -> Double.MAX_VALUE);

        private final int damage;
        private final int durability;
        private final BiFunction<ItemStack, LivingEntity, Double> siphonAmountFunc;

        DaggerType(int damage, int durability, BiFunction<ItemStack, LivingEntity, Double> siphonAmountFunc) {
            this.damage = damage;
            this.durability = durability;
            this.siphonAmountFunc = siphonAmountFunc;
        }

        public double getSiphonAmount(ItemStack stack, LivingEntity entity) {
            return this.siphonAmountFunc.apply(stack, entity);
        }

        public int getDamage() {
            return this.damage;
        }

        public int getDurability() {
            return this.durability;
        }

        private static boolean isPassive(LivingEntity entity) {
            return entity.getClassification(false).isFriendly();
        }
    }
}
