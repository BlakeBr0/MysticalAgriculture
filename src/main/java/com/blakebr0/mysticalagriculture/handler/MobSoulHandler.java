package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import com.blakebr0.mysticalagriculture.api.soul.ISoulSiphoningItem;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.item.SoulJarItem;
import com.blakebr0.mysticalagriculture.registry.MobSoulTypeRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.stream.Collectors;

public final class MobSoulHandler {
    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        Entity source = event.getSource().getEntity();

        if (source instanceof Player) {
            Player player = (Player) source;
            ItemStack held = player.getItemInHand(InteractionHand.MAIN_HAND);

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

    private static List<ItemStack> getValidSoulJars(Player player, IMobSoulType type) {
        return player.inventory.items.stream()
                .filter(s -> s.getItem() instanceof SoulJarItem)
                .filter(s -> MobSoulUtils.canAddTypeToJar(s, type))
                .sorted((a, b) -> MobSoulUtils.getType(a) != null ? -1 : MobSoulUtils.getType(b) != null ? 0 : 1)
                .collect(Collectors.toList());
    }
}
