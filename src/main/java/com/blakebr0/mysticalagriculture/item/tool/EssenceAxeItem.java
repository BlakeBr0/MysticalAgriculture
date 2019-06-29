package com.blakebr0.mysticalagriculture.item.tool;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;

import java.util.function.Function;

public class EssenceAxeItem extends AxeItem {
    public EssenceAxeItem(IItemTier tier, float attackDamage, float attackSpeed, Function<Properties, Properties> properties) {
        super(tier, attackDamage, attackSpeed, properties.apply(new Properties()));
    }
}
