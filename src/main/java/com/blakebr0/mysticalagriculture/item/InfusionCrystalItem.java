package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseReusableItem;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class InfusionCrystalItem extends BaseReusableItem {
    public InfusionCrystalItem(Function<Properties, Properties> properties) {
        super(1000, properties);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return ModConfigs.INFUSION_CRYSTAL_USES.get() - 1;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }
}
