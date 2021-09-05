package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class PoisonResistanceAugment extends Augment {
    public PoisonResistanceAugment(ResourceLocation id, int tier) {
        super(id, tier, EnumSet.of(AugmentType.ARMOR), 0x76DB4C, 0x364B15);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.hasEffect(Effects.POISON)) {
            player.removeEffect(Effects.POISON);
        }
    }
}
