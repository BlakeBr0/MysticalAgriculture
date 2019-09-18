package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.tool.BaseSwordItem;
import com.blakebr0.mysticalagriculture.api.soul.ISoulSiphoningItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;

import java.util.function.Function;

public class SouliumDaggerItem extends BaseSwordItem implements ISoulSiphoningItem {
    public SouliumDaggerItem(IItemTier tier, Function<Properties, Properties> properties) {
        super(tier, 3, -2.0F, properties);
    }

    @Override
    public double getSiphonAmount(LivingEntity entity) {
        return 1;
    }
}
