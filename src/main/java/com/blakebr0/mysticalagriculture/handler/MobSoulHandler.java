package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import com.blakebr0.mysticalagriculture.api.soul.ISoulSiphoningItem;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.item.SoulJarItem;
import com.blakebr0.mysticalagriculture.registry.MobSoulTypeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.stream.Collectors;

public final class MobSoulHandler {
    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        Entity source = event.getSource().getTrueSource();

        if (source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source;
            ItemStack held = player.getHeldItem(Hand.MAIN_HAND);

            if (held.getItem() instanceof ISoulSiphoningItem) {
                LivingEntity entity = event.getEntityLiving();
                ISoulSiphoningItem siphoner = (ISoulSiphoningItem) held.getItem();
                IMobSoulType type = MobSoulTypeRegistry.getInstance().getMobSoulTypeByEntity(entity);

                if (type == null || !type.isEnabled())
                    return;

                List<ItemStack> jars = getValidSoulJars(player, type);

                if (!jars.isEmpty()) {
                    double remaining = siphoner.getSiphonAmount(held, entity);

                    for (ItemStack jar : jars) {
                        remaining = MobSoulUtils.addSoulsToJar(jar, type, remaining);
                        if (remaining <= 0)
                            break;
                    }
                }
            }
        }
    }

    private static List<ItemStack> getValidSoulJars(PlayerEntity player, IMobSoulType type) {
        return player.inventory.mainInventory.stream()
                .filter(s -> s.getItem() instanceof SoulJarItem)
                .filter(s -> MobSoulUtils.canAddTypeToJar(s, type))
                .sorted((a, b) -> MobSoulUtils.getType(a) != null ? -1 : MobSoulUtils.getType(b) != null ? 0 : 1)
                .collect(Collectors.toList());
    }
}
