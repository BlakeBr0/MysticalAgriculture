package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugmentGetter;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import net.minecraft.item.Item.Properties;

public class AugmentItem extends BaseItem implements IAugmentGetter, IEnableable {
    private final IAugment augment;

    public AugmentItem(IAugment augment, Function<Properties, Properties> properties) {
        super(properties);
        this.augment = augment;
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        return Localizable.of("item.mysticalagriculture.augment").args(this.augment.getDisplayName()).build();
    }

    @Override
    public ITextComponent getDescription() {
        return this.getName(ItemStack.EMPTY);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return this.augment.hasEffect();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(ModTooltips.getTooltipForTier(this.augment.getTier()));
        tooltip.add(new StringTextComponent(Colors.GRAY + this.augment.getAugmentTypes()
                .stream()
                .map(AugmentType::getDisplayName)
                .map(ITextComponent::getString)
                .collect(Collectors.joining(", "))
        ));

        if (flag.isAdvanced()) {
            tooltip.add(ModTooltips.AUGMENT_ID.args(this.augment.getId()).color(TextFormatting.DARK_GRAY).build());
        }
    }

    @Override
    public IAugment getAugment() {
        return this.augment;
    }

    @Override
    public boolean isEnabled() {
        return this.augment.isEnabled();
    }
}
