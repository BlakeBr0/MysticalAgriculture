package com.blakebr0.mysticalagriculture.compat;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.ICropProvider;
import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import com.blakebr0.mysticalagriculture.block.InferiumCropBlock;
import com.blakebr0.mysticalagriculture.block.InfusedFarmlandBlock;
import com.blakebr0.mysticalagriculture.block.MysticalCropBlock;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import snownee.jade.api.config.IPluginConfig;

@WailaPlugin
public class JadeCompat implements IWailaPlugin {
    private static final ResourceLocation CROP_PROVIDER = new ResourceLocation(MysticalAgriculture.MOD_ID, "crop");
    private static final ResourceLocation INFERIUM_CROP_PROVIDER = new ResourceLocation(MysticalAgriculture.MOD_ID, "inferium_crop");
    private static final ResourceLocation INFUSED_FARMLAND_PROVIDER = new ResourceLocation(MysticalAgriculture.MOD_ID, "infused_farmland");

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(new IBlockComponentProvider() {
            @Override
            public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
                var block = accessor.getBlock();
                var crop = ((ICropProvider) block).getCrop();

                tooltip.add(ModTooltips.TIER.args(crop.getTier().getDisplayName()).build());

                var pos = accessor.getPosition();
                var downPos = pos.below();
                var level = accessor.getLevel();
                var belowBlock = level.getBlockState(downPos).getBlock();

                if (ModConfigs.SECONDARY_SEED_DROPS.get()) {
                    var secondaryChance = crop.getSecondaryChance(belowBlock);
                    if (secondaryChance > 0) {
                        var chanceText = Component.literal(String.valueOf((int) (secondaryChance * 100)))
                                .append("%")
                                .withStyle(crop.getTier().getTextColor());

                        tooltip.add(ModTooltips.SECONDARY_CHANCE.args(chanceText).build());
                    }
                }

                var crux = crop.getCruxBlock();
                if (crux != null) {
                    var stack = new ItemStack(crux);
                    tooltip.add(ModTooltips.REQUIRES_CRUX.args(stack.getHoverName()).build());
                }

                var biomes = crop.getRequiredBiomes();
                if (!biomes.isEmpty()) {
                    var biome = level.getBiome(pos);
                    var id = ForgeRegistries.BIOMES.getKey(biome.value());
                    if (!biomes.contains(id)) {
                        tooltip.add(ModTooltips.INVALID_BIOME.color(ChatFormatting.RED).build());
                    }
                }
            }

            @Override
            public ResourceLocation getUid() {
                return CROP_PROVIDER;
            }
        }, MysticalCropBlock.class);

        registration.registerBlockComponent(new IBlockComponentProvider() {
            @Override
            public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
                var block = accessor.getBlock();
                var crop = ((ICropProvider) block).getCrop();
                var downPos = accessor.getPosition().below();
                var belowBlock = accessor.getLevel().getBlockState(downPos).getBlock();

                int output = 100;
                if (belowBlock instanceof IEssenceFarmland farmland) {
                    int tier = farmland.getTier().getValue();
                    output = (tier * 50) + 50;
                }

                var inferiumOutputText = Component.literal(String.valueOf(output)).append("%").withStyle(crop.getTier().getTextColor());

                tooltip.add(ModTooltips.INFERIUM_OUTPUT.args(inferiumOutputText).build());
            }

            @Override
            public ResourceLocation getUid() {
                return INFERIUM_CROP_PROVIDER;
            }
        }, InferiumCropBlock.class);

        registration.registerBlockComponent(new IBlockComponentProvider() {
            @Override
            public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
                var block = accessor.getBlock();
                var farmland = (IEssenceFarmland) block;

                tooltip.add(ModTooltips.TIER.args(farmland.getTier().getDisplayName()).build());
            }

            @Override
            public ResourceLocation getUid() {
                return INFUSED_FARMLAND_PROVIDER;
            }
        }, InfusedFarmlandBlock.class);
    }
}
