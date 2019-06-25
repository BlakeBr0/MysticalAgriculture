package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.ReusableItem;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class MasterInfusionCrystalItem extends ReusableItem {
    public MasterInfusionCrystalItem(Function<Properties, Properties> properties) {
        super(properties);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
