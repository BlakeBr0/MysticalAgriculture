package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import net.minecraft.item.ItemSeeds;

import java.util.function.Function;

public class ItemMysticalSeeds extends ItemSeeds implements ICropGetter {
    private final ICrop crop;

    public ItemMysticalSeeds(ICrop crop, Function<Properties, Properties> properties) {
        super(crop.getCrop(), properties.apply(new Properties()));
        this.setRegistryName(crop.getNameWithSuffix("seeds"));
        this.crop = crop;
    }

    @Override
    public ICrop getCrop() {
        return this.crop;
    }
}
