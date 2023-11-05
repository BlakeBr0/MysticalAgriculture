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

public class SlowFallingAugment extends Augment {
    public SlowFallingAugment(ResourceLocation id, int tier) {
        super(id, tier, EnumSet.of(AugmentType.BOOTS), 0xFFEFD1, 0xA5957A);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (!player.isShiftKeyDown()) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 1, 0, true, false));
        }
    }
}
