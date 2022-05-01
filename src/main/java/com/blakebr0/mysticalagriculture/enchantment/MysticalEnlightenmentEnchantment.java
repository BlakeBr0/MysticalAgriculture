package com.blakebr0.mysticalagriculture.enchantment;

import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class MysticalEnlightenmentEnchantment extends Enchantment {
    private static final EquipmentSlot[] SLOTS = new EquipmentSlot[] { EquipmentSlot.MAINHAND };

    public MysticalEnlightenmentEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, SLOTS);
    }

    @Override
    public int getMinCost(int level) {
        return 2 + (level - 1) * 11;
    }

    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 20;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() instanceof ITinkerable && super.canApplyAtEnchantingTable(stack);
    }
}
