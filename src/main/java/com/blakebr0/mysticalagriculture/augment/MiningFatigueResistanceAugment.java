package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class MiningFatigueResistanceAugment extends Augment {
    public MiningFatigueResistanceAugment(ResourceLocation id, int tier) {
        super(id, tier, EnumSet.of(AugmentType.ARMOR), 0xADB4E4, 0x101534);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (player.hasEffect(Effects.DIG_SLOWDOWN)) {
            player.removeEffect(Effects.DIG_SLOWDOWN);
        }
    }
}
