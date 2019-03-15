package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.cucumber.item.ItemReusable;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class ItemMasterInfusionCrystal extends ItemReusable {

    public ItemMasterInfusionCrystal(String name, Function<Properties, Properties> properties) {
        super(name, properties);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
