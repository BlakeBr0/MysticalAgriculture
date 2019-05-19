package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.lib.Localizable;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Function;

public class ItemMysticalSeeds extends ItemSeeds implements ICropGetter {
    private final ICrop crop;

    public ItemMysticalSeeds(ICrop crop, Function<Properties, Properties> properties) {
        super(crop.getCrop(), properties.apply(new Properties()));
        this.setRegistryName(crop.getNameWithSuffix("seeds"));
        this.crop = crop;
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return Localizable.of("item.mysticalagriculture.mystical_seeds").args(this.crop.getDisplayName()).build();
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        String tier = this.crop.getTier().getDisplayName();
        tooltip.add(ModTooltips.CROP_TIER.args(tier).build());
    }

    @Override
    public ICrop getCrop() {
        return this.crop;
    }
}
