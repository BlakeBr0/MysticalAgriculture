package com.blakebr0.mysticalagriculture.compat;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import com.blakebr0.mysticalagriculture.block.InferiumCropBlock;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.List;
import java.util.Set;

@WailaPlugin
public class HwylaCompat implements IWailaPlugin {
    @Override
    public void register(IRegistrar registrar) {
        registrar.registerComponentProvider(new IComponentProvider() {
            @Override
            public void appendHead(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
                ItemStack stack = accessor.getStack();
                StringTextComponent text = new StringTextComponent(Colors.WHITE + stack.getDisplayName().getString());

                tooltip.set(0, text);
            }
        }, TooltipPosition.HEAD, MysticalCropBlock.class);

        registrar.registerComponentProvider(new IComponentProvider() {
            @Override
            public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
                Block block = accessor.getBlock();
                ICrop crop = ((ICropGetter) block).getCrop();

                tooltip.add(ModTooltips.TIER.args(crop.getTier().getDisplayName()).build());

                BlockPos pos = accessor.getPosition();
                BlockPos downPos = pos.down();
                World world = accessor.getWorld();
                Block belowBlock = world.getBlockState(downPos).getBlock();

                double secondaryChance = crop.getSecondaryChance(belowBlock);
                if (secondaryChance > 0) {
                    ITextComponent chanceText = new StringTextComponent(String.valueOf((int) (secondaryChance * 100)))
                            .appendString("%")
                            .mergeStyle(crop.getTier().getTextColor());

                    tooltip.add(ModTooltips.SECONDARY_CHANCE.args(chanceText).build());
                }

                Block crux = crop.getCrux();
                if (crux != null) {
                    ItemStack stack = new ItemStack(crux);
                    tooltip.add(ModTooltips.REQUIRES_CRUX.args(stack.getDisplayName()).build());
                }

                Set<ResourceLocation> biomes = crop.getRequiredBiomes();
                if (!biomes.isEmpty()) {
                    Biome biome = world.getBiome(pos);
                    if (!biomes.contains(biome.getRegistryName())) {
                        tooltip.add(ModTooltips.INVALID_BIOME.color(TextFormatting.RED).build());
                    }
                }
            }
        }, TooltipPosition.BODY, MysticalCropBlock.class);

        registrar.registerComponentProvider(new IComponentProvider() {
            @Override
            public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
                Block block = accessor.getBlock();
                ICrop crop = ((ICropGetter) block).getCrop();
                BlockPos downPos = accessor.getPosition().down();
                Block belowBlock = accessor.getWorld().getBlockState(downPos).getBlock();

                int output = 100;
                if (belowBlock instanceof IEssenceFarmland) {
                    IEssenceFarmland farmland = (IEssenceFarmland) belowBlock;
                    int tier = farmland.getTier().getValue();
                    output = (tier * 50) + 50;
                }

                ITextComponent inferiumOutputText = new StringTextComponent(String.valueOf(output)).appendString("%").mergeStyle(crop.getTier().getTextColor());
                tooltip.add(ModTooltips.INFERIUM_OUTPUT.args(inferiumOutputText).build());
            }
        }, TooltipPosition.BODY, InferiumCropBlock.class);

        registrar.registerComponentProvider(new IComponentProvider() {
            @Override
            public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
                Block block = accessor.getBlock();
                IEssenceFarmland farmland = (IEssenceFarmland) block;

                tooltip.add(ModTooltips.TIER.args(farmland.getTier().getDisplayName()).build());
            }
        }, TooltipPosition.BODY, IEssenceFarmland.class);
    }
}
