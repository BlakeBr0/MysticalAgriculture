package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugmentProvider;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class AugmentItem extends BaseItem implements IAugmentProvider {
    private final Augment augment;

    public AugmentItem(Augment augment) {
        super();
        this.augment = augment;
    }

    @Override
    public Component getName(ItemStack stack) {
        return Localizable.of("item.mysticalagriculture.augment").args(this.augment.getDisplayName()).build();
    }

    @Override
    public Component getDescription() {
        return this.getName(ItemStack.EMPTY);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return this.augment.hasEffect();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(ModTooltips.getTooltipForTier(this.augment.getTier()));
        tooltip.add(this.augment.getDescriptionDisplayText());

        if (this.augment.hasSetBonus()) {
            tooltip.add(ModTooltips.SET_BONUS.args(this.augment.getSetBonusDisplayText()).build());
        }

        if (flag.isAdvanced()) {
            tooltip.add(ModTooltips.AUGMENT_ID.args(this.augment.getId()).color(ChatFormatting.DARK_GRAY).build());
        }
    }

    @Override
    public Augment getAugment() {
        return this.augment;
    }
}
