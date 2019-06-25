package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.ReusableItem;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class InfusionCrystalItem extends ReusableItem {
    public InfusionCrystalItem(int uses, Function<Properties, Properties> properties) {
        super(uses, properties);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }
}
