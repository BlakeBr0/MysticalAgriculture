package com.blakebr0.mysticalagriculture.item.tool;

import com.blakebr0.cucumber.item.tool.BaseHoeItem;
import net.minecraft.item.IItemTier;

import java.util.function.Function;

public class EssenceHoeItem extends BaseHoeItem {
    public EssenceHoeItem(IItemTier tier, Function<Properties, Properties> properties) {
        super(tier, properties);
    }
}
