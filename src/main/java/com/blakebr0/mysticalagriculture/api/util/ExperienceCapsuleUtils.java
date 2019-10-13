package com.blakebr0.mysticalagriculture.api.util;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class ExperienceCapsuleUtils {
    private static final RegistryObject<Item> EXPERIENCE_CAPSULE = RegistryObject.of(new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "experience_capsule"), ForgeRegistries.ITEMS);
    /**
     * The maximum amount of xp points an experience capsule can hold
     */
    public static final int MAX_XP_POINTS = 1200;

    /**
     * Creates a tag for the experience capsule with this amount of xp
     * @param xp the amount of xp points
     * @return a tag compound for the specified amount of xp
     */
    public static CompoundNBT makeTag(int xp) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("Experience", Math.min(MAX_XP_POINTS, xp));

        return nbt;
    }

    /**
     * Get a new experience capsule with the specified amount of xp
     * @param xp the amount of xp points
     * @return the experience capsule
     */
    public static ItemStack getExperienceCapsule(int xp) {
        CompoundNBT nbt = makeTag(xp);
        ItemStack stack = new ItemStack(EXPERIENCE_CAPSULE.get());
        stack.setTag(nbt);
        return stack;
    }

    /**
     * Gets the amount of experience currently stored in the specified item stack
     * @param stack the item stack
     * @return the amount of experience
     */
    public static int getExperience(ItemStack stack) {
        CompoundNBT nbt = stack.getTag();
        if (nbt != null && nbt.contains("Experience"))
            return nbt.getInt("Experience");

        return 0;
    }

    /**
     * Add experience to an experience capsule
     * @param stack the experience capsule stack
     * @param amount the amount of experience to add
     * @return any experience that wasn't added
     */
    public static int addExperienceToCapsule(ItemStack stack, int amount) {
        int xp = getExperience(stack);
        if (xp >= MAX_XP_POINTS) {
            return amount;
        } else {
            int newAmount = Math.min(MAX_XP_POINTS, xp + amount);
            CompoundNBT nbt = stack.getTag();
            if (nbt == null) {
                CompoundNBT tag = makeTag(newAmount);
                stack.setTag(tag);
            } else {
                nbt.putInt("Experience", newAmount);
            }

            return Math.max(0, amount - (newAmount - xp));
        }
    }
}
