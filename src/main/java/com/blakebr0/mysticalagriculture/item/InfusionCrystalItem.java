package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseReusableItem;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import net.minecraft.world.item.ItemStack;

public class InfusionCrystalItem extends BaseReusableItem {
    public InfusionCrystalItem() {
        super(1000);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return ModConfigs.INFUSION_CRYSTAL_USES.get() - 1;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return false;
    }
}
