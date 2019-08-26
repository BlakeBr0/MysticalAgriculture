package com.blakebr0.mysticalagriculture.item.tool;

import com.blakebr0.cucumber.item.tool.BasePickaxeItem;
import net.minecraft.item.IItemTier;

import java.util.function.Function;

public class EssencePickaxeItem extends BasePickaxeItem {
    public EssencePickaxeItem(IItemTier tier, Function<Properties, Properties> properties) {
        super(tier, properties);
    }
}
