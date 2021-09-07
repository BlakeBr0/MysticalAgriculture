package com.blakebr0.mysticalagriculture.compat;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.api.crop.ICropProvider;
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
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Set;

@WailaPlugin
public class HwylaCompat implements IWailaPlugin {
    @Override
    public void register(IRegistrar registrar) {
        registrar.registerComponentProvider(new IComponentProvider() {
            @Override
            public void appendHead(List<Component> tooltip, IDataAccessor accessor, IPluginConfig config) {
                ItemStack stack = accessor.getStack();
                TextComponent text = new TextComponent(Colors.WHITE + stack.getHoverName().getString());

                tooltip.set(0, text);
            }
        }, TooltipPosition.HEAD, MysticalCropBlock.class);

        registrar.registerComponentProvider(new IComponentProvider() {
            @Override
            public void appendBody(List<Component> tooltip, IDataAccessor accessor, IPluginConfig config) {
                Block block = accessor.getBlock();
                ICrop crop = ((ICropProvider) block).getCrop();

                tooltip.add(ModTooltips.TIER.args(crop.getTier().getDisplayName()).build());

                BlockPos pos = accessor.getPosition();
                BlockPos downPos = pos.below();
                Level world = accessor.getWorld();
                Block belowBlock = world.getBlockState(downPos).getBlock();

                double secondaryChance = crop.getSecondaryChance(belowBlock);
                if (secondaryChance > 0) {
                    Component chanceText = new TextComponent(String.valueOf((int) (secondaryChance * 100)))
                            .append("%")
                            .withStyle(crop.getTier().getTextColor());

                    tooltip.add(ModTooltips.SECONDARY_CHANCE.args(chanceText).build());
                }

                Block crux = crop.getCrux();
                if (crux != null) {
                    ItemStack stack = new ItemStack(crux);
                    tooltip.add(ModTooltips.REQUIRES_CRUX.args(stack.getHoverName()).build());
                }

                Set<ResourceLocation> biomes = crop.getRequiredBiomes();
                if (!biomes.isEmpty()) {
                    Biome biome = world.getBiome(pos);
                    if (!biomes.contains(biome.getRegistryName())) {
                        tooltip.add(ModTooltips.INVALID_BIOME.color(ChatFormatting.RED).build());
                    }
                }
            }
        }, TooltipPosition.BODY, MysticalCropBlock.class);

        registrar.registerComponentProvider(new IComponentProvider() {
            @Override
            public void appendBody(List<Component> tooltip, IDataAccessor accessor, IPluginConfig config) {
                Block block = accessor.getBlock();
                ICrop crop = ((ICropProvider) block).getCrop();
                BlockPos downPos = accessor.getPosition().below();
                Block belowBlock = accessor.getWorld().getBlockState(downPos).getBlock();

                int output = 100;
                if (belowBlock instanceof IEssenceFarmland) {
                    IEssenceFarmland farmland = (IEssenceFarmland) belowBlock;
                    int tier = farmland.getTier().getValue();
                    output = (tier * 50) + 50;
                }

                Component inferiumOutputText = new TextComponent(String.valueOf(output)).append("%").withStyle(crop.getTier().getTextColor());
                tooltip.add(ModTooltips.INFERIUM_OUTPUT.args(inferiumOutputText).build());
            }
        }, TooltipPosition.BODY, InferiumCropBlock.class);

        registrar.registerComponentProvider(new IComponentProvider() {
            @Override
            public void appendBody(List<Component> tooltip, IDataAccessor accessor, IPluginConfig config) {
                Block block = accessor.getBlock();
                IEssenceFarmland farmland = (IEssenceFarmland) block;

                tooltip.add(ModTooltips.TIER.args(farmland.getTier().getDisplayName()).build());
            }
        }, TooltipPosition.BODY, IEssenceFarmland.class);
    }
}
