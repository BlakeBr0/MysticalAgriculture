package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.ItemReusable;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class InfusionCrystalItem extends ItemReusable {
    public InfusionCrystalItem(String name, int uses, Function<Properties, Properties> properties) {
        super(name, uses, properties);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }
}
