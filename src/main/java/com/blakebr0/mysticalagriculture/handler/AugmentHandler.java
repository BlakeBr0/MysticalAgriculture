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
    public void onPlayerUpdate(LivingEvent.LivingUpdateEvent event) {
        var entity = event.getEntityLiving();

        if (entity instanceof Player player) {
            var world = player.getCommandSenderWorld();
            var augments = AugmentUtils.getArmorAugments(player);

            augments.forEach(a -> a.onPlayerTick(world, player, ABILITY_CACHE));

            ABILITY_CACHE.getCachedAbilities(player).forEach(c -> {
                if (augments.stream().noneMatch(a -> c.equals(a.getId().toString()))) {
                    ABILITY_CACHE.remove(c, player);
                }
            });
        }
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        var entity = event.getEntityLiving();

        if (entity instanceof Player player) {
            var world = player.getCommandSenderWorld();

            AugmentUtils.getArmorAugments(player).forEach(a -> {
                a.onPlayerFall(world, player, event);
            });
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        var player = event.getPlayer();

        ABILITY_CACHE.getCachedAbilities(player).forEach(c -> {
            ABILITY_CACHE.removeQuietly(c, player);
        });
    }
}
