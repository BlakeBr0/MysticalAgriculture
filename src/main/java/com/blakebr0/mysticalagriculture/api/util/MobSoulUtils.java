package com.blakebr0.mysticalagriculture.api.util;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.soul.MobSoulType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MobSoulUtils {
    /**
     * Creates a tag compound for this mob soul type using the max amount of souls
     * @param type the mod soul type
     * @return a tag compound for the specified mob soul type
     */
    public static CompoundTag makeTag(MobSoulType type) {
        return makeTag(type, type.getSoulRequirement());
    }

    /**
     * Creates a tag compound for this mob soul type using the provided soul amount
     * @param type the mob soul type
     * @param souls the amount of souls in this tag
     * @return a tag compound for the specified mob soul type
     */
    public static CompoundTag makeTag(MobSoulType type, double souls) {
        var nbt = new CompoundTag();

        nbt.putString("Type", type.getId().toString());
        nbt.putDouble("Souls", Math.min(souls, type.getSoulRequirement()));

        return nbt;
    }

    /**
     * Get a new soul jar filled with the provided amount of souls of the provided mob soul type
     * @param type the mob soul type
     * @param souls the amount of souls in this soul jar
     * @param item the soul jar item instance
     * @return the soul jar
     */
    public static ItemStack getSoulJar(MobSoulType type, double souls, Item item) {
        var nbt = makeTag(type, souls);
        var stack = new ItemStack(item);

        stack.setTag(nbt);

        return stack;
    }

    /**
     * Gets a new soul jar filled with the provided soul type
     * @param type the mob soul type
     * @param item the soul jar item instance
     * @return the filled soul jar
     */
    public static ItemStack getFilledSoulJar(MobSoulType type, Item item) {
        var nbt = makeTag(type);
        var stack = new ItemStack(item);

        stack.setTag(nbt);

        return stack;
    }

    /**
     * Gets the mob soul type from the provided item stack
     * @param stack the soul jar stack
     * @return the mob soul type
     */
    public static MobSoulType getType(ItemStack stack) {
        var nbt = stack.getTag();

        if (nbt != null && nbt.contains("Type")) {
            var type = nbt.getString("Type");
            return MysticalAgricultureAPI.getMobSoulTypeRegistry().getMobSoulTypeById(new ResourceLocation(type));
        }

        return null;
    }

    /**
     * Gets the amount of souls currently stored in the provided item stack
     * @param stack the soul jar stack
     * @return the amount of souls
     */
    public static double getSouls(ItemStack stack) {
        var nbt = stack.getTag();
        if (nbt != null && nbt.contains("Souls"))
            return nbt.getDouble("Souls");

        return 0D;
    }

    /**
     * Checks if the provided mob soul type can be added to the provided item stack
     * @param stack the soul jar stack
     * @param type the mob soul type to add
     * @return can this soul type be added to this soul jar
     */
    public static boolean canAddTypeToJar(ItemStack stack, MobSoulType type) {
        var containedType = getType(stack);
        return containedType == null || containedType == type;
    }

    /**
     * Checks if the provided soul jar contains the max amount of souls for it's contained mob soul type
     * @param stack the soul jar stack
     * @return is the provided soul jar full
     */
    public static boolean isJarFull(ItemStack stack) {
        var type = getType(stack);
        return type != null && getSouls(stack) >= type.getSoulRequirement();
    }

    /**
     * Add souls to a soul jar
     * @param stack the soul jar stack
     * @param type the mob soul type to add
     * @param amount the amount of souls to add
     * @return any souls that weren't added
     */
    public static double addSoulsToJar(ItemStack stack, MobSoulType type, double amount) {
        var containedType = getType(stack);
        if (containedType != null && containedType != type)
            return amount;

        double requirement = type.getSoulRequirement();
        if (containedType == null) {
            var nbt = makeTag(type, amount);

            stack.setTag(nbt);

            return Math.max(0, amount - requirement);
        } else {
            double souls = getSouls(stack);
            if (souls < requirement) {
                var nbt = stack.getTag();

                if (nbt != null) {
                    double newSouls = Math.min(requirement, souls + amount);

                    nbt.putDouble("Souls", newSouls);

                    return Math.max(0, amount - (newSouls - souls));
                }
            }
        }

        return amount;
    }
}
