package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.init.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;
import java.util.function.Supplier;

public enum ModItemTier implements Tier {
    INFERIUM(3, 2000, 9.0F, 4.0F, 12, () -> {
        return Ingredient.of(ModItems.INFERIUM_INGOT.get());
    }),
    PRUDENTIUM(3, 2800, 11.0F, 6.0F, 14, () -> {
        return Ingredient.of(ModItems.PRUDENTIUM_INGOT.get());
    }),
    TERTIUM(4, 4000, 14.0F, 9.0F, 16, () -> {
        return Ingredient.of(ModItems.TERTIUM_INGOT.get());
    }),
    IMPERIUM(4, 6000, 19.0F, 13.0F, 18, () -> {
        return Ingredient.of(ModItems.IMPERIUM_INGOT.get());
    }),
    SUPREMIUM(5, -1, 25.0F, 20.0F, 20, () -> {
        return Ingredient.of(ModItems.SUPREMIUM_INGOT.get());
    }),
    AWAKENED_SUPREMIUM(5, -1, 30.0F, 25.0F, 22, () -> {
        return Ingredient.of(ModItems.AWAKENED_SUPREMIUM_INGOT.get());
    }),
    SOULIUM(0, 400, 5.0F, 3.0F, 15, () -> {
        return Ingredient.of(ModItems.SOULIUM_INGOT.get());
    });

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadedValue<Ingredient> repairMaterial;

    ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyLoadedValue<>(repairMaterial);
    }

    @Override
    public int getUses() {
        return this.maxUses;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    public static void onCommonSetup() {
        TierSortingRegistry.registerTier(INFERIUM, new ResourceLocation(MysticalAgriculture.MOD_ID, "inferium"), List.of(Tiers.DIAMOND), List.of());
        TierSortingRegistry.registerTier(PRUDENTIUM, new ResourceLocation(MysticalAgriculture.MOD_ID, "prudentium"), List.of(INFERIUM), List.of());
        TierSortingRegistry.registerTier(TERTIUM, new ResourceLocation(MysticalAgriculture.MOD_ID, "tertium"), List.of(PRUDENTIUM), List.of());
        TierSortingRegistry.registerTier(IMPERIUM, new ResourceLocation(MysticalAgriculture.MOD_ID, "imperium"), List.of(TERTIUM), List.of());
        TierSortingRegistry.registerTier(SUPREMIUM, new ResourceLocation(MysticalAgriculture.MOD_ID, "supremium"), List.of(IMPERIUM), List.of());
        TierSortingRegistry.registerTier(SOULIUM, new ResourceLocation(MysticalAgriculture.MOD_ID, "soulium"), List.of(Tiers.WOOD), List.of());
    }
}
