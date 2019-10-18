package com.blakebr0.mysticalagriculture.item.tool;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.lib.Tooltips;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Function;

public class EssenceStaffItem extends BaseItem {
    public EssenceStaffItem(Function<Properties, Properties> properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(Tooltips.NOT_YET_IMPLEMENTED.build());
    }
}
