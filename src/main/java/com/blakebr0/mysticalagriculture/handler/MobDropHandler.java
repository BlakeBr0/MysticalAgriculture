package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModItems;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Collection;

public final class MobDropHandler {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntityLiving();
        World world = entity.getCommandSenderWorld();

        if (!world.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT))
            return;

        Collection<ItemEntity> drops = event.getDrops();
        Entity attacker = event.getSource().getEntity();
        double inferiumDropChance = ModConfigs.INFERIUM_DROP_CHANCE.get();

        if (entity instanceof CreatureEntity && Math.random() < inferiumDropChance) {
            drops.add(new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.INFERIUM_ESSENCE.get())));
        }

        if (attacker instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) attacker;
            Item item = player.getMainHandItem().getItem();

            if (item instanceof ITinkerable) {
                ITinkerable tinkerable = (ITinkerable) item;

                boolean witherDropsEssence = ModConfigs.WITHER_DROPS_ESSENCE.get();
                if (witherDropsEssence && entity instanceof WitherEntity) {
                    ItemStack stack = getEssenceForTinkerable(tinkerable, 1, 3);

                    if (!stack.isEmpty()) {
                        drops.add(new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), stack));
                    }
                }

                boolean dragonDropsEssence = ModConfigs.DRAGON_DROPS_ESSENCE.get();
                if (dragonDropsEssence && entity instanceof EnderDragonEntity) {
                    ItemStack stack = getEssenceForTinkerable(tinkerable, 2, 4);

                    if (!stack.isEmpty()) {
                        drops.add(new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), stack));
                    }
                }
            }
        }
    }

    private static ItemStack getEssenceForTinkerable(ITinkerable tinkerable, int min, int max) {
        switch (tinkerable.getTinkerableTier()) {
            case 1: return new ItemStack(ModItems.INFERIUM_ESSENCE.get(), Utils.randInt(min, max));
            case 2: return new ItemStack(ModItems.PRUDENTIUM_ESSENCE.get(), Utils.randInt(min, max));
            case 3: return new ItemStack(ModItems.TERTIUM_ESSENCE.get(), Utils.randInt(min, max));
            case 4: return new ItemStack(ModItems.IMPERIUM_ESSENCE.get(), Utils.randInt(min, max));
            case 5: return new ItemStack(ModItems.SUPREMIUM_ESSENCE.get(), Utils.randInt(min, max));
            default: return ItemStack.EMPTY;
        }
    }
}
