package com.blakebr0.mysticalagriculture.compat;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import com.blakebr0.mysticalagriculture.block.MysticalCropBlock;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.WailaPlugin;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

@WailaPlugin
public class HwylaCompat implements IWailaPlugin {
    @Override
    public void register(IRegistrar registrar) {
        registrar.registerComponentProvider(new IComponentProvider() {
            @Override
            public void appendHead(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
                tooltip.set(0, accessor.getStack().getDisplayName().applyTextStyle(TextFormatting.WHITE));
            }
        }, TooltipPosition.HEAD, MysticalCropBlock.class);

        registrar.registerComponentProvider(new IComponentProvider() {
            @Override
            public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
                Block block = accessor.getBlock();
                ICrop crop = ((ICropGetter) block).getCrop();

                tooltip.add(ModTooltips.CROP_TIER.args(crop.getTier().getDisplayName()).build());

                BlockPos downPos = accessor.getPosition().down();
                Block belowBlock = accessor.getWorld().getBlockState(downPos).getBlock();
                int secondaryChance = crop.getSecondaryChance(belowBlock);

                if (secondaryChance > 0) {
                    ITextComponent chanceText = new StringTextComponent(String.valueOf(secondaryChance)).appendText("%").applyTextStyle(crop.getTier().getTextColor());
                    tooltip.add(ModTooltips.SECONDARY_CHANCE.args(chanceText).build());
                }
            }
        }, TooltipPosition.BODY, MysticalCropBlock.class);
    }
}
