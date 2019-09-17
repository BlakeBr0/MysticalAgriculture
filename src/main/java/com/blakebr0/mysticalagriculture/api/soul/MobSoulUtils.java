package com.blakebr0.mysticalagriculture.api.soul;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class MobSoulUtils {
    private static Item soulJar;

    /**
     * Creates a tag compound for this mob soul type using the max amount of souls
     * @param type the mod soul type
     * @return a tag compound for the specified mob soul type
     */
    public static CompoundNBT makeTag(IMobSoulType type) {
        return makeTag(type, type.getSoulRequirement());
    }

    /**
     * Creates a tag compound for this mob soul type using the specified soul amount
     * @param type the mob soul type
     * @param souls the amount of souls in this tag
     * @return a tag compound for the specified mob soul type
     */
    public static CompoundNBT makeTag(IMobSoulType type, double souls) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("type", type.getId().toString());
        nbt.putDouble("souls", Math.min(souls, type.getSoulRequirement()));

        return nbt;
    }

    /**
     * Get a new soul jar filled with the specified amount of souls of the specified mob soul type
     * @param type the mob soul type
     * @param souls the amount of souls in this soul jar
     * @return the soul jar
     */
    public static ItemStack getSoulJar(IMobSoulType type, double souls) {
        CompoundNBT nbt = makeTag(type);
        nbt.putDouble("souls", souls);

        if (soulJar == null)
            soulJar = ForgeRegistries.ITEMS.getValue(new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "soul_jar"));

        ItemStack stack = new ItemStack(soulJar);
        stack.setTag(nbt);
        return stack;
    }

    /**
     * Gets a new soul jar filled with the specified soul type
     * @param type the mob soul type
     * @return the filled soul jar
     */
    public static ItemStack getFilledSoulJar(IMobSoulType type) {
        CompoundNBT nbt = makeTag(type);
        nbt.putDouble("souls", type.getSoulRequirement());

        if (soulJar == null)
            soulJar = ForgeRegistries.ITEMS.getValue(new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "soul_jar"));

        ItemStack stack = new ItemStack(soulJar);
        stack.setTag(nbt);
        return stack;
    }

    /**
     * Gets the mob soul type from the specified item stack
     * @param stack the item stack
     * @return the mob soul type
     */
    public static IMobSoulType getType(ItemStack stack) {
        CompoundNBT nbt = stack.getTag();
        if (nbt != null && nbt.contains("type")) {
            String type = nbt.getString("type");
            return MysticalAgricultureAPI.getMobSoulTypeRegistry().getMobSoulTypeById(new ResourceLocation(type));
        }

        return null;
    }

    /**
     * Gets the amount of souls currently stored in the specified item stack
     * @param stack the item stack
     * @return the mob soul type
     */
    public static double getSouls(ItemStack stack) {
        CompoundNBT nbt = stack.getTag();
        if (nbt != null && nbt.contains("souls")) {
            return nbt.getDouble("souls");
        }

        return 0D;
    }
}
