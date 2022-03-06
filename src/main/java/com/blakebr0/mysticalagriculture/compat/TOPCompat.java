package com.blakebr0.mysticalagriculture.compat;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.ICropProvider;
import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import com.blakebr0.mysticalagriculture.block.InferiumCropBlock;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.InterModComms;

import java.util.function.Function;

public class TOPCompat implements Function<ITheOneProbe, Void> {
    @Override
    public Void apply(ITheOneProbe probe) {
        probe.registerProvider(new IProbeInfoProvider() {
            @Override
            public ResourceLocation getID() {
                return new ResourceLocation(MysticalAgriculture.MOD_ID, "crops");
            }

            @Override
            public void addProbeInfo(ProbeMode mode, IProbeInfo info, Player player, Level world, BlockState state, IProbeHitData data) {
                var block = state.getBlock();
                var pos = data.getPos();

                if (block instanceof ICropProvider provider) {
                    var crop = provider.getCrop();
                    var belowBlock = world.getBlockState(pos.below()).getBlock();

                    info.text(ModTooltips.TIER.args(crop.getTier().getDisplayName()).build());

                    double secondaryChance = crop.getSecondaryChance(belowBlock);
                    if (secondaryChance > 0) {
                        var chanceText = new TextComponent(String.valueOf((int) (secondaryChance * 100)))
                                .append("%")
                                .withStyle(crop.getTier().getTextColor());

                        info.text(ModTooltips.SECONDARY_CHANCE.args(chanceText).build());
                    }

                    var crux = crop.getCruxBlock();
                    if (crux != null) {
                        var stack = new ItemStack(crux);

                        info.text(ModTooltips.REQUIRES_CRUX.args(stack.getHoverName()).build());
                    }

                    var biomes = crop.getRequiredBiomes();
                    if (!biomes.isEmpty()) {
                        var biome = world.getBiome(pos);

                        if (!biomes.contains(biome.value().getRegistryName())) {
                            info.text(ModTooltips.INVALID_BIOME.color(ChatFormatting.RED).build());
                        }
                    }

                    if (block instanceof InferiumCropBlock) {
                        int output = 100;
                        if (belowBlock instanceof IEssenceFarmland farmland) {
                            int tier = farmland.getTier().getValue();
                            output = (tier * 50) + 50;
                        }

                        var inferiumOutputText = new TextComponent(String.valueOf(output))
                                .append("%")
                                .withStyle(crop.getTier().getTextColor());

                        info.text(ModTooltips.INFERIUM_OUTPUT.args(inferiumOutputText).build());
                    }
                }

                if (block instanceof IEssenceFarmland farmland) {
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
