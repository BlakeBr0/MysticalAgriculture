package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.item.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {
    INFERIUM("mysticalagriculture:inferium", 20, new int[] { 2, 4, 5, 2 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.5F, () -> {
        return Ingredient.fromItems(ModItems.INFERIUM_INGOT.get());
    }),
    PRUDENTIUM("mysticalagriculture:prudentium", 40, new int[] { 2, 4, 6, 3 }, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.75F, () -> {
        return Ingredient.fromItems(ModItems.PRUDENTIUM_INGOT.get());
    }),
    TERTIUM("mysticalagriculture:tertium", 80, new int[] { 3, 6, 7, 3 }, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0F, () -> {
       return Ingredient.fromItems(ModItems.TERTIUM_INGOT.get());
    }),
    IMPERIUM("mysticalagriculture:imperium", 160, new int[] { 4, 7, 8, 4 }, 35, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.25F, () -> {
        return Ingredient.fromItems(ModItems.IMPERIUM_INGOT.get());
    }),
    SUPREMIUM("mysticalagriculture:supremium", 280, new int[] { 4, 8, 9, 5 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.5F, () -> {
        return Ingredient.fromItems(ModItems.SUPREMIUM_INGOT.get());
    });

    private static final int[] MAX_DAMAGE_ARRAY = new int[] { 13, 15, 16, 11 };
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final LazyLoadBase<Ingredient> repairMaterial;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.repairMaterial = new LazyLoadBase<>(repairMaterial);
    }

    @Override
    public int getDurability(EquipmentSlotType slot) {
        return MAX_DAMAGE_ARRAY[slot.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slot) {
        return this.damageReductionAmountArray[slot.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }
}
