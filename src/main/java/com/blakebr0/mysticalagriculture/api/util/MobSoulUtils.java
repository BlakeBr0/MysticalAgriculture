package com.blakebr0.mysticalagriculture.api.util;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class MobSoulUtils {
    private static final RegistryObject<Item> SOUL_JAR = RegistryObject.of(new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "soul_jar"), ForgeRegistries.ITEMS);

    /**
     * Creates a tag compound for this mob soul type using the max amount of souls
     * @param type the mod soul type
     * @return a tag compound for the specified mob soul type
     */
    public static CompoundNBT makeTag(IMobSoulType type) {
        return makeTag(type, type.getSoulRequirement());
    }

    /**
     * Creates a tag compound for this mob soul type using the provided soul amount
     * @param type the mob soul type
     * @param souls the amount of souls in this tag
     * @return a tag compound for the specified mob soul type
     */
    public static CompoundNBT makeTag(IMobSoulType type, double souls) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("Type", type.getId().toString());
        nbt.putDouble("Souls", Math.min(souls, type.getSoulRequirement()));

        return nbt;
    }

    /**
     * Get a new soul jar filled with the provided amount of souls of the provided mob soul type
     * @param type the mob soul type
     * @param souls the amount of souls in this soul jar
     * @return the soul jar
     */
    public static ItemStack getSoulJar(IMobSoulType type, double souls) {
        return getSoulJar(type, souls, SOUL_JAR.get());
    }

    /**
     * Get a new soul jar filled with the provided amount of souls of the provided mob soul type
     * @param type the mob soul type
     * @param souls the amount of souls in this soul jar
     * @param item the soul jar item instance
     * @return the soul jar
     */
    public static ItemStack getSoulJar(IMobSoulType type, double souls, Item item) {
        CompoundNBT nbt = makeTag(type, souls);
        ItemStack stack = new ItemStack(item);
        stack.setTag(nbt);

        return stack;
    }

    /**
     * Gets a new soul jar filled with the provided soul type
     * @param type the mob soul type
     * @return the filled soul jar
     */
    public static ItemStack getFilledSoulJar(IMobSoulType type) {
        return getFilledSoulJar(type, SOUL_JAR.get());
    }

    /**
     * Gets a new soul jar filled with the provided soul type
     * @param type the mob soul type
     * @param item the soul jar item instance
     * @return the filled soul jar
     */
    public static ItemStack getFilledSoulJar(IMobSoulType type, Item item) {
        CompoundNBT nbt = makeTag(type);
        ItemStack stack = new ItemStack(item);
        stack.setTag(nbt);

        return stack;
    }

    /**
     * Gets the mob soul type from the provided item stack
     * @param stack the soul jar stack
     * @return the mob soul type
     */
    public static IMobSoulType getType(ItemStack stack) {
        CompoundNBT nbt = stack.getTag();
        if (nbt != null && nbt.contains("Type")) {
            String type = nbt.getString("Type");
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
        CompoundNBT nbt = stack.getTag();
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
    public static boolean canAddTypeToJar(ItemStack stack, IMobSoulType type) {
        IMobSoulType containedType = getType(stack);
        return containedType == null || containedType == type;
    }

    /**
     * Checks if the provided soul jar contains the max amount of souls for it's contained mob soul type
     * @param stack the soul jar stack
     * @return is the provided soul jar full
     */
    public static boolean isJarFull(ItemStack stack) {
        IMobSoulType type = getType(stack);
        return type != null && getSouls(stack) >= type.getSoulRequirement();
    }

    /**
     * Add souls to a soul jar
     * @param stack the soul jar stack
     * @param type the mob soul type to add
     * @param amount the amount of souls to add
     * @return any souls that weren't added
     */
    public static double addSoulsToJar(ItemStack stack, IMobSoulType type, double amount) {
        IMobSoulType containedType = getType(stack);
        if (containedType != null && containedType != type)
            return amount;

        double requirement = type.getSoulRequirement();
        if (containedType == null) {
            CompoundNBT nbt = makeTag(type, amount);
            stack.setTag(nbt);

            return Math.max(0, amount - requirement);
        } else {
            double souls = getSouls(stack);
            if (souls < requirement) {
                CompoundNBT nbt = stack.getTag();

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
