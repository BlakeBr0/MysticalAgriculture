package com.blakebr0.mysticalagriculture.item.tool;

import com.blakebr0.cucumber.item.tool.BaseSwordItem;
import net.minecraft.item.IItemTier;

import java.util.function.Function;

public class EssenceSwordItem extends BaseSwordItem {
    public EssenceSwordItem(IItemTier tier, Function<Properties, Properties> properties) {
        super(tier, properties);
    }
}
