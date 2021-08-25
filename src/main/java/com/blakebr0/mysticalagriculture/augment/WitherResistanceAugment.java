package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class WitherResistanceAugment extends Augment {
    public WitherResistanceAugment(ResourceLocation id, int tier) {
        super(id, tier, EnumSet.of(AugmentType.ARMOR), 0x764857, 0x1A1616);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        player.removeEffect(MobEffects.WITHER);
    }
}
