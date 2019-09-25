package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.lib.Localizable;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.function.Function;

public class MysticalEssenceItem extends BaseItem implements ICropGetter, IEnableable {
    private final ICrop crop;

    public MysticalEssenceItem(ICrop crop, Function<Properties, Properties> properties) {
        super(properties);
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

    @Override
    public boolean isEnabled() {
        return this.crop.isEnabled();
    }
}
