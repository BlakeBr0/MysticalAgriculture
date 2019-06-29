package com.blakebr0.mysticalagriculture.item.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;

import java.util.function.Function;

public class EssencePickaxeItem extends PickaxeItem {
    public EssencePickaxeItem(IItemTier tier, int attackDamage, float attackSpeed, Function<Properties, Properties> properties) {
        super(tier, attackDamage, attackSpeed, properties.apply(new Properties()));
    }
}
