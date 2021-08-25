package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MysticalSeedsItem extends ItemNameBlockItem implements ICropGetter, IEnableable {
    private final ICrop crop;

    public MysticalSeedsItem(ICrop crop, Function<Properties, Properties> properties) {
        super(crop.getCrop(), properties.apply(new Properties()));
        this.crop = crop;
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (this.isEnabled()) {
            super.fillItemCategory(group, items);
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        return Localizable.of("item.mysticalagriculture.mystical_seeds").args(this.crop.getDisplayName()).build();
    }

    @Override
    public Component getDescription() {
        return this.getName(ItemStack.EMPTY);
    }

    @Override
    public String getDescriptionId() {
        return Localizable.of("item.mysticalagriculture.mystical_seeds").args(this.crop.getDisplayName()).buildString();
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return this.crop.hasEffect(stack) || super.isFoil(stack);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        var tier = this.crop.getTier().getDisplayName();

        tooltip.add(ModTooltips.TIER.args(tier).build());

        if (!this.crop.getModId().equals(MysticalAgriculture.MOD_ID)) {
            tooltip.add(ModTooltips.getAddedByTooltip(this.crop.getModId()));
        }

        var biomes = this.crop.getRequiredBiomes();

        if (!biomes.isEmpty()) {
            tooltip.add(ModTooltips.REQUIRED_BIOMES.build());

            var ids = biomes.stream()
                    .map(ResourceLocation::toString)
                    .map(s -> " - " + s)
                    .map(TextComponent::new)
                    .collect(Collectors.toList());

            tooltip.addAll(ids);
        }

        if (flag.isAdvanced()) {
            tooltip.add(ModTooltips.CROP_ID.args(this.crop.getId()).color(ChatFormatting.DARK_GRAY).build());
        }
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
