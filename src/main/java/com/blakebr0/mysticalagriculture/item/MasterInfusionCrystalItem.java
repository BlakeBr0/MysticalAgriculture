package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseReusableItem;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

public class MasterInfusionCrystalItem extends BaseReusableItem {
    public MasterInfusionCrystalItem(Function<Properties, Properties> properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
