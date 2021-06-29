package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.init.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {
    INFERIUM("mysticalagriculture:inferium", 40, new int[] { 3, 6, 8, 4 }, 12, SoundEvents.ARMOR_EQUIP_GOLD, 2.0F, 0.0F, () -> {
        return Ingredient.of(ModItems.INFERIUM_INGOT.get());
    }),
    PRUDENTIUM("mysticalagriculture:prudentium", 60, new int[] { 4, 7, 8, 4 }, 14, SoundEvents.ARMOR_EQUIP_GOLD, 2.25F, 0.0F, () -> {
        return Ingredient.of(ModItems.PRUDENTIUM_INGOT.get());
    }),
    TERTIUM("mysticalagriculture:tertium", 80, new int[] { 4, 8, 9, 5 }, 16, SoundEvents.ARMOR_EQUIP_GOLD, 2.5F, 0.0F, () -> {
       return Ingredient.of(ModItems.TERTIUM_INGOT.get());
    }),
    IMPERIUM("mysticalagriculture:imperium", 140, new int[] { 5, 8, 9, 5 }, 18, SoundEvents.ARMOR_EQUIP_GOLD, 2.75F, 0.0F, () -> {
        return Ingredient.of(ModItems.IMPERIUM_INGOT.get());
    }),
    SUPREMIUM("mysticalagriculture:supremium", 280, new int[] { 5, 8, 10, 6 }, 20, SoundEvents.ARMOR_EQUIP_GOLD, 3.0F, 0.0F, () -> {
        return Ingredient.of(ModItems.SUPREMIUM_INGOT.get());
    });

    private static final int[] MAX_DAMAGE_ARRAY = new int[] { 13, 15, 16, 11 };
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairMaterial;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return MAX_DAMAGE_ARRAY[slot.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slot) {
        return this.damageReductionAmountArray[slot.getIndex()];
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

    @OnlyIn(Dist.CLIENT)
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
