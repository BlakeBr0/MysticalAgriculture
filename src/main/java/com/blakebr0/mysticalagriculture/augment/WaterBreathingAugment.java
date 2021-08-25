package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class WaterBreathingAugment extends Augment {
    public WaterBreathingAugment(ResourceLocation id, int tier) {
        super(id, tier, EnumSet.of(AugmentType.HELMET), 0xF2FFFF, 0x5AAFCF);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 5, 0, true, false));
    }
}
