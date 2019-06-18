package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.ReusableItem;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class MasterInfusionCrystalItem extends ReusableItem {
    public MasterInfusionCrystalItem(String name, Function<Properties, Properties> properties) {
        super(name, properties);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
