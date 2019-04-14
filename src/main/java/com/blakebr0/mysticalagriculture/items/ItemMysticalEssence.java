package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;

import java.util.function.Function;

public class ItemMysticalEssence extends ItemBase {
    private final ICrop crop;

    public ItemMysticalEssence(ICrop crop, Function<Properties, Properties> properties) {
        super(crop.getNameWithSuffix("essence"), properties);
        this.crop = crop;
    }
}
