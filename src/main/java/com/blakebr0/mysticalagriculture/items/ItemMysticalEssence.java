package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;

import java.util.function.Function;

public class ItemMysticalEssence extends ItemBase implements ICropGetter {
    private final ICrop crop;

    public ItemMysticalEssence(ICrop crop, Function<Properties, Properties> properties) {
        super(crop.getNameWithSuffix("essence"), properties);
        this.crop = crop;
    }

    @Override
    public ICrop getCrop() {
        return this.crop;
    }
}
