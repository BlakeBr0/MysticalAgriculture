package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
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
        return new TextComponentTranslation("item.mysticalagriculture.mystical_seeds", this.crop.getDisplayName());
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
