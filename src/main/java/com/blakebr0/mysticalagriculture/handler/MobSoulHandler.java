package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import com.blakebr0.mysticalagriculture.api.soul.ISoulSiphoningItem;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.item.SoulJarItem;
import com.blakebr0.mysticalagriculture.registry.MobSoulTypeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.stream.Collectors;

public class MobSoulHandler {
    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        Entity source = event.getSource().getTrueSource();
        if (source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source;
            Item held = player.getHeldItem(Hand.MAIN_HAND).getItem();
            if (held instanceof ISoulSiphoningItem) {
                LivingEntity entity = event.getEntityLiving();
                ISoulSiphoningItem soulSiphoningItem = (ISoulSiphoningItem) held;
                IMobSoulType mobSoulType = MobSoulTypeRegistry.getInstance().getMobSoulTypeByEntity(entity);
                if (mobSoulType != null) {
                    List<ItemStack> soulJars = this.getSoulJars(player);
                    if (!soulJars.isEmpty()) {
                        ItemStack firstEmptyJar = ItemStack.EMPTY;
                        double soulsRemaining = soulSiphoningItem.getSiphonAmount(entity);
                        for (ItemStack stack : soulJars) {
                            IMobSoulType type = MobSoulUtils.getType(stack);
                            if (type == null && firstEmptyJar.isEmpty()) {
                                firstEmptyJar = stack;
                            } else if (type != null && type == mobSoulType) {
                                soulsRemaining = MobSoulUtils.addSoulsToJar(stack, mobSoulType, soulsRemaining);
                                if (soulsRemaining <= 0) break;
                            }
                        }

                        if (!firstEmptyJar.isEmpty() && soulsRemaining > 0) {
                            MobSoulUtils.addSoulsToJar(firstEmptyJar, mobSoulType, soulsRemaining);
                        }
                    }
                }
            }
        }
    }

    private List<ItemStack> getSoulJars(PlayerEntity player) {
        return player.inventory.mainInventory.stream()
                .filter(s -> s.getItem() instanceof SoulJarItem)
                .collect(Collectors.toList());
    }
}
