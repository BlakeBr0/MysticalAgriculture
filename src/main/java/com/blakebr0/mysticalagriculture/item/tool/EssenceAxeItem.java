package com.blakebr0.mysticalagriculture.item.tool;

import com.blakebr0.cucumber.item.tool.BaseAxeItem;
import net.minecraft.item.IItemTier;

import java.util.function.Function;

public class EssenceAxeItem extends BaseAxeItem {
    public EssenceAxeItem(IItemTier tier, Function<Properties, Properties> properties) {
        super(tier, properties);
    }
}
