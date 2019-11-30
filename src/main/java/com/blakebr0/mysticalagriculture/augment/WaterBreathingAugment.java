package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.EnumSet;

public class WaterBreathingAugment extends Augment {
    public WaterBreathingAugment(ResourceLocation id, int tier) {
        super(id, tier, EnumSet.of(AugmentType.HELMET), 0xF2FFFF, 0x5AAFCF);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 5, 0, true, false));
    }
}
