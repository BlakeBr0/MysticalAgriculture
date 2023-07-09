package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.mysticalagriculture.api.lib.AbilityCache;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class StepAssistAugment extends Augment {
    public StepAssistAugment(ResourceLocation id, int tier) {
        super(id, tier, EnumSet.of(AugmentType.LEGGINGS, AugmentType.BOOTS), 0xFC4F00, 0x602600);
    }

    @Override
    public void onPlayerTick(Level level, Player player, AbilityCache cache) {
        if (player.maxUpStep() < 1.0625F || !cache.isCached(this, player)) {
            player.setMaxUpStep(1.0625F);

            cache.add(this, player, () -> {
                player.setMaxUpStep(0.6F);
            });
        }
    }
}
