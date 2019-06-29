package com.blakebr0.mysticalagriculture.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

import java.util.function.Function;

public class EssenceSwordItem extends SwordItem {
    public EssenceSwordItem(IItemTier tier, int attackDamage, float attackSpeed, Function<Properties, Properties> properties) {
        super(tier, attackDamage, attackSpeed, properties.apply(new Properties()));
    }
}
