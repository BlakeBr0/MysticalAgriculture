package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import net.minecraft.item.ItemSeeds;

import java.util.function.Function;

public class ItemMysticalSeeds extends ItemSeeds {
    private final ICrop crop;

    public ItemMysticalSeeds(ICrop crop, Function<Properties, Properties> properties) {
        super(crop.getCrop(), properties.apply(new Properties()));
        this.setRegistryName(crop.getNameWithSuffix("seeds"));
        this.crop = crop;
    }
}
