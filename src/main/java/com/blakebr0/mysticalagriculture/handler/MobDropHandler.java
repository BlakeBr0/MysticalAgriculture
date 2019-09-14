package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.mysticalagriculture.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Collection;

public class MobDropHandler {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntityLiving();
        World world = entity.getEntityWorld();
        Collection<ItemEntity> drops = event.getDrops();

        if (entity instanceof AnimalEntity && Math.random() < 0.2) {
            drops.add(new ItemEntity(world, entity.posX, entity.posY, entity.posZ, new ItemStack(ModItems.INFERIUM_ESSENCE)));
        }

        if (entity instanceof MobEntity && Math.random() < 0.2) {
            drops.add(new ItemEntity(world, entity.posX, entity.posY, entity.posZ, new ItemStack(ModItems.INFERIUM_ESSENCE)));
        }
    }
}
