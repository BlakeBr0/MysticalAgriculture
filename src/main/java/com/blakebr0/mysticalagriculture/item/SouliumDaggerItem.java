package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.tool.BaseSwordItem;
import net.minecraft.item.IItemTier;

import java.util.function.Function;

public class SouliumDaggerItem extends BaseSwordItem {
    public SouliumDaggerItem(IItemTier tier, Function<Properties, Properties> properties) {
        super(tier, 3, -2.0F, properties);
    }
}
