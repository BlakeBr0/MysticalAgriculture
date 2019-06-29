package com.blakebr0.mysticalagriculture.item.tool;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;

import java.util.function.Function;

public class EssenceHoeItem extends HoeItem {
    public EssenceHoeItem(IItemTier tier, float attackSpeed, Function<Properties, Properties> properties) {
        super(tier, attackSpeed, properties.apply(new Properties()));
    }
}
