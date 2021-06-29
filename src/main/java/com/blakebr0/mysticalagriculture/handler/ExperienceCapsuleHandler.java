package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.mysticalagriculture.api.util.ExperienceCapsuleUtils;
import com.blakebr0.mysticalagriculture.item.ExperienceCapsuleItem;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.stream.Collectors;

public final class ExperienceCapsuleHandler {
    @SubscribeEvent
    public void onPlayerPickupXp(PlayerXpEvent.PickupXp event) {
        ExperienceOrbEntity orb = event.getOrb();
        PlayerEntity player = event.getPlayer();
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

    private List<ItemStack> getExperienceCapsules(PlayerEntity player) {
        return player.inventory.items.stream()
                .filter(s -> s.getItem() instanceof ExperienceCapsuleItem)
                .collect(Collectors.toList());
    }
}
