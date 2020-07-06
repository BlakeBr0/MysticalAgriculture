package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModItems;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Collection;

public final class MobDropHandler {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntityLiving();
        World world = entity.getEntityWorld();
        Collection<ItemEntity> drops = event.getDrops();
        double inferiumDropChance = ModConfigs.INFERIUM_DROP_CHANCE.get();

        if (entity instanceof CreatureEntity && Math.random() < inferiumDropChance) {
            drops.add(new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), new ItemStack(ModItems.INFERIUM_ESSENCE.get())));
        }
    }
}
