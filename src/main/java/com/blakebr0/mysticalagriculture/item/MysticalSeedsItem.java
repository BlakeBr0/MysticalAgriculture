package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.lib.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;

import java.util.List;
import java.util.function.Function;

public class MysticalSeedsItem extends BlockNamedItem implements ICropGetter, IEnableable {
    private final ICrop crop;

    public MysticalSeedsItem(ICrop crop, Function<Properties, Properties> properties) {
        super(crop.getCrop(), properties.apply(new Properties()));
        this.crop = crop;
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return Localizable.of("item.mysticalagriculture.mystical_seeds").args(this.crop.getDisplayName()).build();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        ITextComponent tier = this.crop.getTier().getDisplayName();
        tooltip.add(ModTooltips.TIER.args(tier).build());
        if (!this.crop.getModId().equals(MysticalAgriculture.MOD_ID)) {
            String name = ModList.get().getModFileById(this.crop.getModId()).getMods().get(0).getDisplayName();
            tooltip.add(ModTooltips.ADDED_BY.args(name).build());
        }

        if (flag.isAdvanced())
            tooltip.add(ModTooltips.CROP_ID.args(this.crop.getId()).color(TextFormatting.DARK_GRAY).build());
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
