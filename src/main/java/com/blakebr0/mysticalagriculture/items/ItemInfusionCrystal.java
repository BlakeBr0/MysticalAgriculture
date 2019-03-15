package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.cucumber.item.ItemReusable;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class ItemInfusionCrystal extends ItemReusable {

    public ItemInfusionCrystal(String name, int uses, Function<Properties, Properties> properties) {
        super(name, uses, properties);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }
}
