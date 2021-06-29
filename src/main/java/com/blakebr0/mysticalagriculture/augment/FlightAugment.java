package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.mysticalagriculture.api.lib.AbilityCache;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.EnumSet;

public class FlightAugment extends Augment {
    public FlightAugment(ResourceLocation id, int tier) {
        super(id, tier, EnumSet.of(AugmentType.CHESTPLATE), 0xCBD6D6, 0x556B6B);
    }

    @Override
    public void onPlayerTick(World world, PlayerEntity player, AbilityCache cache) {
        if (!player.abilities.mayfly || !cache.isCached(this, player)) {
            player.abilities.mayfly = true;

            cache.add(this, player, () -> {
                if (!player.abilities.instabuild && !player.isSpectator()) {
                    player.abilities.mayfly = false;
                    player.abilities.flying = false;
                }
            });
        }
    }
}
