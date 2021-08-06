package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.mysticalagriculture.api.util.ExperienceCapsuleUtils;
import com.blakebr0.mysticalagriculture.item.ExperienceCapsuleItem;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.stream.Collectors;

public final class ExperienceCapsuleHandler {
    @SubscribeEvent
    public void onPlayerPickupXp(PlayerXpEvent.PickupXp event) {
        ExperienceOrb orb = event.getOrb();
        Player player = event.getPlayer();
        if (player != null) {
            List<ItemStack> capsules = this.getExperienceCapsules(player);
            if (!capsules.isEmpty()) {
                for (ItemStack stack : capsules) {
                    int remaining = ExperienceCapsuleUtils.addExperienceToCapsule(stack, orb.getValue());
                    if (remaining == 0) {
                        orb.value = 0;
                        return;
                    }

                    orb.value = remaining;
                }
            }
        }
    }

    private List<ItemStack> getExperienceCapsules(Player player) {
        return player.inventory.items.stream()
                .filter(s -> s.getItem() instanceof ExperienceCapsuleItem)
                .collect(Collectors.toList());
    }
}
