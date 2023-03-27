package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.init.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial {
    INFERIUM("mysticalagriculture:inferium", 40, new int[] { 3, 8, 6, 4 }, 12, SoundEvents.ARMOR_EQUIP_GOLD, 2.0F, 0.0F, () -> {
        return Ingredient.of(ModItems.INFERIUM_INGOT.get());
    }),
    PRUDENTIUM("mysticalagriculture:prudentium", 60, new int[] { 4, 8, 7, 4 }, 14, SoundEvents.ARMOR_EQUIP_GOLD, 2.25F, 0.0F, () -> {
        return Ingredient.of(ModItems.PRUDENTIUM_INGOT.get());
    }),
    TERTIUM("mysticalagriculture:tertium", 80, new int[] { 4, 9, 8, 5 }, 16, SoundEvents.ARMOR_EQUIP_GOLD, 2.5F, 0.0F, () -> {
       return Ingredient.of(ModItems.TERTIUM_INGOT.get());
    }),
    IMPERIUM("mysticalagriculture:imperium", 140, new int[] { 5, 9, 8, 5 }, 18, SoundEvents.ARMOR_EQUIP_GOLD, 2.75F, 0.0F, () -> {
        return Ingredient.of(ModItems.IMPERIUM_INGOT.get());
    }),
    SUPREMIUM("mysticalagriculture:supremium", 280, new int[] { 5, 10, 8, 6 }, 20, SoundEvents.ARMOR_EQUIP_GOLD, 3.0F, 0.0F, () -> {
        return Ingredient.of(ModItems.SUPREMIUM_INGOT.get());
    }),
    AWAKENED_SUPREMIUM("mysticalagriculture:awakened_supremium", 320, new int[] { 6, 12, 10, 8 }, 22, SoundEvents.ARMOR_EQUIP_GOLD, 3.5F, 0.1F, () -> {
        return Ingredient.of(ModItems.AWAKENED_SUPREMIUM_INGOT.get());
    });

    private static final int[] MAX_DAMAGE_ARRAY = new int[] { 13, 16, 15, 11 };
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairMaterial;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyLoadedValue<>(repairMaterial);
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return MAX_DAMAGE_ARRAY[type.ordinal()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.damageReductionAmountArray[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
