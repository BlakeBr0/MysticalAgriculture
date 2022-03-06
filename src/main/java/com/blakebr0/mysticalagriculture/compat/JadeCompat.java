package com.blakebr0.mysticalagriculture.compat;

import com.blakebr0.mysticalagriculture.api.crop.ICropProvider;
import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import com.blakebr0.mysticalagriculture.block.InferiumCropBlock;
import com.blakebr0.mysticalagriculture.block.InfusedFarmlandBlock;
import com.blakebr0.mysticalagriculture.block.MysticalCropBlock;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.WailaPlugin;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;

@WailaPlugin
public class JadeCompat implements IWailaPlugin {
    @Override
    public void register(IRegistrar registrar) {
        registrar.registerComponentProvider((tooltip, accessor, config) -> {
            var block = accessor.getBlock();
            var crop = ((ICropProvider) block).getCrop();

            tooltip.add(ModTooltips.TIER.args(crop.getTier().getDisplayName()).build());

            var pos = accessor.getPosition();
            var downPos = pos.below();
            var level = accessor.getLevel();
            var belowBlock = level.getBlockState(downPos).getBlock();

            var secondaryChance = crop.getSecondaryChance(belowBlock);
            if (secondaryChance > 0) {
                var chanceText = new TextComponent(String.valueOf((int) (secondaryChance * 100)))
                        .append("%")
                        .withStyle(crop.getTier().getTextColor());

                tooltip.add(ModTooltips.SECONDARY_CHANCE.args(chanceText).build());
            }

            var crux = crop.getCruxBlock();
            if (crux != null) {
                var stack = new ItemStack(crux);
                tooltip.add(ModTooltips.REQUIRES_CRUX.args(stack.getHoverName()).build());
            }

            var biomes = crop.getRequiredBiomes();
            if (!biomes.isEmpty()) {
                var biome = level.getBiome(pos);
                if (!biomes.contains(biome.value().getRegistryName())) {
                    tooltip.add(ModTooltips.INVALID_BIOME.color(ChatFormatting.RED).build());
                }
            }
        }, TooltipPosition.BODY, MysticalCropBlock.class);

        registrar.registerComponentProvider((tooltip, accessor, config) -> {
            var block = accessor.getBlock();
            var crop = ((ICropProvider) block).getCrop();
            var downPos = accessor.getPosition().below();
            var belowBlock = accessor.getLevel().getBlockState(downPos).getBlock();

            int output = 100;
            if (belowBlock instanceof IEssenceFarmland farmland) {
                int tier = farmland.getTier().getValue();
                output = (tier * 50) + 50;
            }

            var inferiumOutputText = new TextComponent(String.valueOf(output)).append("%").withStyle(crop.getTier().getTextColor());

            tooltip.add(ModTooltips.INFERIUM_OUTPUT.args(inferiumOutputText).build());
        }, TooltipPosition.BODY, InferiumCropBlock.class);

        registrar.registerComponentProvider((tooltip, accessor, config) -> {
            var block = accessor.getBlock();
            var farmland = (IEssenceFarmland) block;

            tooltip.add(ModTooltips.TIER.args(farmland.getTier().getDisplayName()).build());
        }, TooltipPosition.BODY, InfusedFarmlandBlock.class);
    }
}
