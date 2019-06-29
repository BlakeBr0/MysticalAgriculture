package com.blakebr0.mysticalagriculture.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;

import java.util.function.Function;

public class EssenceShovelItem extends ShovelItem {
    public EssenceShovelItem(IItemTier tier, float attackDamage, float attackSpeed, Function<Properties, Properties> properties) {
        super(tier, attackDamage, attackSpeed, properties.apply(new Properties()));
    }
}
