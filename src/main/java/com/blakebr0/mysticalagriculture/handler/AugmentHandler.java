package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.mysticalagriculture.api.lib.AbilityCache;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class AugmentHandler {
    private static final AbilityCache ABILITY_CACHE = new AbilityCache();

    @SubscribeEvent
    public void onPlayerUpdate(LivingEvent.LivingTickEvent event) {
        var entity = event.getEntity();

        if (entity instanceof Player player) {
            var level = player.level();
            var augments = AugmentUtils.getArmorAugments(player);

            for (var augment : augments) {
                augment.onPlayerTick(level, player, ABILITY_CACHE);
            }

            for (var augment : ABILITY_CACHE.getCachedAbilities(player)) {
                if (augments.stream().noneMatch(a -> augment.equals(a.getId().toString()))) {
                    ABILITY_CACHE.remove(augment, player);
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        var entity = event.getEntity();

        if (entity instanceof Player player) {
            var level = player.level();

            for (var augment : AugmentUtils.getArmorAugments(player)) {
                augment.onPlayerFall(level, player, event);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        var player = event.getEntity();

        for (var augment : ABILITY_CACHE.getCachedAbilities(player)) {
            ABILITY_CACHE.removeQuietly(augment, player);
        }
    }
}
