package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.lib.Localizable;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugmentGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.function.Function;

public class AugmentItem extends BaseItem implements IAugmentGetter {
    private final IAugment augment;

    public AugmentItem(IAugment augment, Function<Properties, Properties> properties) {
        super(properties);
        this.augment = augment;
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return Localizable.of("item.mysticalagriculture.augment").args(this.augment.getDisplayName()).build();
    }

    @Override
    public IAugment getAugment() {
        return this.augment;
    }
}
