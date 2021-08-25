package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModItems;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class MobDropHandler {
    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        var entity = event.getEntityLiving();
        var world = entity.getCommandSenderWorld();

        if (!world.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT))
            return;

        var drops = event.getDrops();
        var attacker = event.getSource().getEntity();
        double inferiumDropChance = ModConfigs.INFERIUM_DROP_CHANCE.get();

        if (entity instanceof PathfinderMob && Math.random() < inferiumDropChance) {
            drops.add(new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.INFERIUM_ESSENCE.get())));
        }

        if (attacker instanceof Player player) {
            var item = player.getMainHandItem().getItem();

            if (item instanceof ITinkerable tinkerable) {
                boolean witherDropsEssence = ModConfigs.WITHER_DROPS_ESSENCE.get();

                if (witherDropsEssence && entity instanceof WitherBoss) {
                    var stack = getEssenceForTinkerable(tinkerable, 1, 3);

                    if (!stack.isEmpty()) {
                        drops.add(new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), stack));
                    }
                }

                boolean dragonDropsEssence = ModConfigs.DRAGON_DROPS_ESSENCE.get();

                if (dragonDropsEssence && entity instanceof EnderDragon) {
                    var stack = getEssenceForTinkerable(tinkerable, 2, 4);

                    if (!stack.isEmpty()) {
                        drops.add(new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), stack));
                    }
                }
            }
        }
    }

    private static ItemStack getEssenceForTinkerable(ITinkerable tinkerable, int min, int max) {
        return switch (tinkerable.getTinkerableTier()) {
            case 1 -> new ItemStack(ModItems.INFERIUM_ESSENCE.get(), Utils.randInt(min, max));
            case 2 -> new ItemStack(ModItems.PRUDENTIUM_ESSENCE.get(), Utils.randInt(min, max));
            case 3 -> new ItemStack(ModItems.TERTIUM_ESSENCE.get(), Utils.randInt(min, max));
            case 4 -> new ItemStack(ModItems.IMPERIUM_ESSENCE.get(), Utils.randInt(min, max));
            case 5 -> new ItemStack(ModItems.SUPREMIUM_ESSENCE.get(), Utils.randInt(min, max));
            default -> ItemStack.EMPTY;
        };
    }
}
