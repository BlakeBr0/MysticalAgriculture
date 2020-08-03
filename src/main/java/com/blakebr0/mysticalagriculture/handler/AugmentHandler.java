package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.mysticalagriculture.api.lib.AbilityCache;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class AugmentHandler {
    private static final AbilityCache CACHE = new AbilityCache();

    @SubscribeEvent
    public void onPlayerUpdate(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            World world = player.getEntityWorld();
            List<IAugment> augments = AugmentUtils.getArmorAugments(player);

            augments.forEach(a -> a.onPlayerTick(world, player, CACHE));

            CACHE.getCachedAbilities(player).forEach(c -> {
                if (augments.stream().noneMatch(a -> c.equals(a.getId().toString()))) {
                    CACHE.remove(c, player);
                }
            });
        }
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            World world = player.getEntityWorld();

            AugmentUtils.getArmorAugments(player).forEach(a -> {
                a.onPlayerFall(world, player, event);
            });
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        PlayerEntity player = event.getPlayer();

        CACHE.getCachedAbilities(player).forEach(c -> {
            CACHE.remove(c, player);
        });
    }
}
