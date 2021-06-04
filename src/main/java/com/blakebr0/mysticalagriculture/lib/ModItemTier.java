package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.init.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum ModItemTier implements IItemTier {
    INFERIUM(3, 2000, 9.0F, 4.0F, 12, () -> {
        return Ingredient.fromItems(ModItems.INFERIUM_INGOT.get());
    }),
    PRUDENTIUM(3, 2800, 11.0F, 6.0F, 14, () -> {
        return Ingredient.fromItems(ModItems.PRUDENTIUM_INGOT.get());
    }),
    TERTIUM(4, 4000, 14.0F, 9.0F, 16, () -> {
        return Ingredient.fromItems(ModItems.TERTIUM_INGOT.get());
    }),
    IMPERIUM(4, 6000, 19.0F, 13.0F, 18, () -> {
        return Ingredient.fromItems(ModItems.IMPERIUM_INGOT.get());
    }),
    SUPREMIUM(5, -1, 25.0F, 20.0F, 20, () -> {
        return Ingredient.fromItems(ModItems.SUPREMIUM_INGOT.get());
    }),
    SOULIUM(0, 400, 5.0F, 3.0F, 15, () -> {
        return Ingredient.fromItems(ModItems.SOULIUM_INGOT.get());
    });

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

    @Override
    public int getMaxUses() {
        return this.maxUses;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }
}
