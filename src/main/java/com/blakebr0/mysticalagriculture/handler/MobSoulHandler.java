package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.mysticalagriculture.api.soul.ISoulSiphoningItem;
import com.blakebr0.mysticalagriculture.api.soul.MobSoulType;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.init.ModEnchantments;
import com.blakebr0.mysticalagriculture.item.SoulJarItem;
import com.blakebr0.mysticalagriculture.registry.MobSoulTypeRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.stream.Collectors;

public final class MobSoulHandler {
    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        var source = event.getSource().getEntity();

        if (source instanceof Player player) {
            var held = player.getItemInHand(InteractionHand.MAIN_HAND);

            if (held.getItem() instanceof ISoulSiphoningItem siphoner) {
                var entity = event.getEntity();
                var type = MobSoulTypeRegistry.getInstance().getMobSoulTypeByEntity(entity);

                if (type == null || !type.isEnabled())
                    return;

                var jars = getValidSoulJars(player, type);

                if (!jars.isEmpty()) {
                    double remaining = getSoulSiphonerTotal(siphoner, held, entity);

                    for (var jar : jars) {
                        remaining = MobSoulUtils.addSoulsToJar(jar, type, remaining);
                        if (remaining <= 0)
                            break;
                    }
                }
            }
        }
    }

    private static List<ItemStack> getValidSoulJars(Player player, MobSoulType type) {
        return player.getInventory().items.stream()
                .filter(s -> s.getItem() instanceof SoulJarItem)
                .filter(s -> MobSoulUtils.canAddTypeToJar(s, type))
                .sorted((a, b) -> MobSoulUtils.getType(a) != null ? -1 : MobSoulUtils.getType(b) != null ? 0 : 1)
                .collect(Collectors.toList());
    }

    private static double getSoulSiphonerTotal(ISoulSiphoningItem siphoner, ItemStack stack, LivingEntity entity) {
        double amount = siphoner.getSiphonAmount(stack, entity);

        var enchantmentLevel = EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.SOUL_SIPHONER.get(), stack);
        if (enchantmentLevel > 0) {
            amount *= (1.0D + (0.1D * enchantmentLevel));
        }

        return amount;
    }
}
