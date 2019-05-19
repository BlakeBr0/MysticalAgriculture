package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.cucumber.lib.Localizable;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.function.Function;

public class ItemMysticalEssence extends ItemBase implements ICropGetter {
    private final ICrop crop;

    public ItemMysticalEssence(ICrop crop, Function<Properties, Properties> properties) {
        super(crop.getNameWithSuffix("essence"), properties);
        this.crop = crop;
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return Localizable.of("item.mysticalagriculture.mystical_essence").args(this.crop.getDisplayName()).build();
    }

    @Override
    public ICrop getCrop() {
        return this.crop;
    }
}
