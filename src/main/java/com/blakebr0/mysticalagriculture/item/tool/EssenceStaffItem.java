package com.blakebr0.mysticalagriculture.item.tool;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.lib.Tooltips;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import com.blakebr0.mysticalagriculture.api.tinkering.IElementalItem;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Function;

public class EssenceStaffItem extends BaseItem implements ITinkerable, IElementalItem {
    private static final EnumSet<AugmentType> TYPES = EnumSet.of(AugmentType.STAFF);
    private final int tinkerableTier;
    private final int slots;

    public EssenceStaffItem(int tinkerableTier, int slots, Function<Properties, Properties> properties) {
        super(properties);
        this.tinkerableTier = tinkerableTier;
        this.slots = slots;
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        // hidden
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(ModTooltips.getTooltipForTier(this.tinkerableTier));
        tooltip.add(Tooltips.NOT_YET_IMPLEMENTED.build());
    }

    @Override
    public int getAugmentSlots() {
        return this.slots;
    }

    @Override
    public EnumSet<AugmentType> getAugmentTypes() {
        return TYPES;
    }

    @Override
    public int getTinkerableTier() {
        return this.tinkerableTier;
    }
}
