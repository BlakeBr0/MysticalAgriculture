package com.blakebr0.mysticalagriculture.compat;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import com.blakebr0.mysticalagriculture.block.InferiumCropBlock;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.InterModComms;

import java.util.Set;
import java.util.function.Function;

public class TOPCompat implements Function<ITheOneProbe, Void> {
    @Override
    public Void apply(ITheOneProbe probe) {
        probe.registerProvider(new IProbeInfoProvider() {
            @Override
            public String getID() {
                return MysticalAgriculture.MOD_ID + ":crops";
            }

            @Override
            public void addProbeInfo(ProbeMode mode, IProbeInfo info, Player player, Level world, BlockState state, IProbeHitData data) {
                Block block = state.getBlock();
                BlockPos pos = data.getPos();

                if (block instanceof ICropGetter) {
                    ICrop crop = ((ICropGetter) block).getCrop();
                    Block belowBlock = world.getBlockState(pos.below()).getBlock();

                    info.text(ModTooltips.TIER.args(crop.getTier().getDisplayName()).build());

                    double secondaryChance = crop.getSecondaryChance(belowBlock);
                    if (secondaryChance > 0) {
                        Component chanceText = new TextComponent(String.valueOf((int) (secondaryChance * 100)))
                                .append("%")
                                .withStyle(crop.getTier().getTextColor());

                        info.text(ModTooltips.SECONDARY_CHANCE.args(chanceText).build());
                    }

                    Block crux = crop.getCrux();
                    if (crux != null) {
                        ItemStack stack = new ItemStack(crux);

                        info.text(ModTooltips.REQUIRES_CRUX.args(stack.getHoverName()).build());
                    }

                    Set<ResourceLocation> biomes = crop.getRequiredBiomes();
                    if (!biomes.isEmpty()) {
                        Biome biome = world.getBiome(pos);

                        if (!biomes.contains(biome.getRegistryName())) {
                            info.text(ModTooltips.INVALID_BIOME.color(ChatFormatting.RED).build());
                        }
                    }

                    if (block instanceof InferiumCropBlock) {
                        int output = 100;
                        if (belowBlock instanceof IEssenceFarmland) {
                            IEssenceFarmland farmland = (IEssenceFarmland) belowBlock;
                            int tier = farmland.getTier().getValue();
                            output = (tier * 50) + 50;
                        }

                        Component inferiumOutputText = new TextComponent(String.valueOf(output))
                                .append("%")
                                .withStyle(crop.getTier().getTextColor());

                        info.text(ModTooltips.INFERIUM_OUTPUT.args(inferiumOutputText).build());
                    }
                }

                if (block instanceof IEssenceFarmland) {
                    IEssenceFarmland farmland = (IEssenceFarmland) block;

                    info.text(ModTooltips.TIER.args(farmland.getTier().getDisplayName()).build());
                }
            }
        });

        return null;
    }

    public static void onInterModEnqueue() {
        InterModComms.sendTo("theoneprobe", "getTheOneProbe", TOPCompat::new);
    }
}
