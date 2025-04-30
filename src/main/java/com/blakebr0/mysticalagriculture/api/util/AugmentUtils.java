package com.blakebr0.mysticalagriculture.api.util;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureDataComponentTypes;
import com.blakebr0.mysticalagriculture.api.components.AugmentComponent;
import com.blakebr0.mysticalagriculture.api.tinkering.AOEAugment;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AugmentUtils {
    /**
     * Add an augment to the specified tinkerable in the specified slot
     *
     * @param stack   the {@link ITinkerable} item
     * @param augment the augment
     * @param slot    the augment slot
     */
    public static void addAugment(ItemStack stack, Augment augment, int slot) {
        var item = stack.getItem();

        if (item instanceof ITinkerable tinkerable) {
            if (slot < tinkerable.getAugmentSlots() && tinkerable.getTinkerableTier() >= augment.getTier()) {
                var component = stack.get(MysticalAgricultureDataComponentTypes.EQUIPPED_AUGMENTS);
                var augments = new ArrayList<AugmentComponent>();

                if (component != null && !component.isEmpty()) {
                    augments.addAll(component);
                }

                augments.removeIf(a -> a.slot() == slot);
                augments.add(new AugmentComponent(augment.getId(), slot));

                stack.set(MysticalAgricultureDataComponentTypes.EQUIPPED_AUGMENTS, augments);
            }
        }
    }

    /**
     * Remove an augment from the specified tinkerable from the specified slot
     *
     * @param stack the {@link ITinkerable} item
     * @param slot  the augment slot
     */
    public static void removeAugment(ItemStack stack, int slot) {
        var component = stack.get(MysticalAgricultureDataComponentTypes.EQUIPPED_AUGMENTS);
        if (component == null)
            return;

        var item = stack.getItem();

        if (item instanceof ITinkerable tinkerable) {
            var augment = getAugment(stack, slot);
            if (slot < tinkerable.getAugmentSlots() && augment != null) {
                var augments = new ArrayList<AugmentComponent>();

                if (!component.isEmpty()) {
                    augments.addAll(component);
                }

                augments.removeIf(a -> a.slot() == slot);

                stack.set(MysticalAgricultureDataComponentTypes.EQUIPPED_AUGMENTS, augments);
                stack.remove(MysticalAgricultureDataComponentTypes.AOE_AUGMENT_OFFSET);
            }
        }
    }

    /**
     * Get the augment in the specified augment slot
     *
     * @param stack the {@link ITinkerable} item
     * @param slot  the augment slot
     * @return the augment
     */
    public static Augment getAugment(ItemStack stack, int slot) {
        var component = stack.get(MysticalAgricultureDataComponentTypes.EQUIPPED_AUGMENTS);
        if (component == null)
            return null;

        var item = stack.getItem();

        if (item instanceof ITinkerable tinkerable) {
            var augment = component.stream()
                    .filter(a -> a.slot() == slot)
                    .findFirst()
                    .orElse(null);

            if (augment == null)
                return null;

            if (slot < tinkerable.getAugmentSlots()) {
                return MysticalAgricultureAPI.getAugmentRegistry().getAugmentById(augment.id());
            }
        }

        return null;
    }

    /**
     * Gets the augments currently installed on this tinkerable
     *
     * @param stack the {@link ITinkerable}
     * @return the installed augments
     */
    public static List<Augment> getAugments(ItemStack stack) {
        var component = stack.get(MysticalAgricultureDataComponentTypes.EQUIPPED_AUGMENTS);
        List<Augment> augments = new ArrayList<>();

        if (component == null)
            return augments;

        var item = stack.getItem();

        if (item instanceof ITinkerable tinkerable) {
            int slots = tinkerable.getAugmentSlots();

            for (int i = 0; i < slots; i++) {
                var augment = getAugment(stack, i);
                if (augment != null)
                    augments.add(augment);
            }
        }

        return augments;
    }

    /**
     * Helper method to get the augments from the player's armor
     *
     * @param player the player
     * @return the installed augments
     */
    public static List<Augment> getArmorAugments(Player player) {
        var armor = player.getInventory().armor;
        List<Augment> augments = new ArrayList<>();

        for (var stack : armor) {
            augments.addAll(getAugments(stack));
        }

        return augments;
    }

    /**
     * Gets that largest AOE augment size
     * @param stack the {@link ITinkerable}
     * @return the range
     */
    public static int getMaxAOEAugmentRange(ItemStack stack) {
        int range = 0;

        var augments = getAugments(stack);
        for (var augment : augments) {
            if (augment instanceof AOEAugment aoeAugment) {
                range = Math.max(range, aoeAugment.getRange());
            }
        }

        return range;
    }
}
