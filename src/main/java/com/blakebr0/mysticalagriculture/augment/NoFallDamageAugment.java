package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import java.util.EnumSet;

public class NoFallDamageAugment extends Augment {
    public NoFallDamageAugment(ResourceLocation id, int tier) {
        super(id, tier, EnumSet.of(AugmentType.BOOTS), 0x123123, 0x123123);
    }

    @Override
    public void onPlayerFall(World world, PlayerEntity player, LivingFallEvent event) {
        event.setDamageMultiplier(0);
    }
}
