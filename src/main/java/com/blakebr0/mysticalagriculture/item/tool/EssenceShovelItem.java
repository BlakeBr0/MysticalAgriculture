package com.blakebr0.mysticalagriculture.item.tool;

import com.blakebr0.cucumber.item.tool.BaseShovelItem;
import net.minecraft.item.IItemTier;

import java.util.function.Function;

public class EssenceShovelItem extends BaseShovelItem {
    public EssenceShovelItem(IItemTier tier, Function<Properties, Properties> properties) {
        super(tier, properties);
    }
}
