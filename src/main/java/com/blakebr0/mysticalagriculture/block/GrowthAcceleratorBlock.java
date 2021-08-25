package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseBlock;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import java.util.List;
import java.util.Random;

public class GrowthAcceleratorBlock extends BaseBlock {
    private final int range;
    private final ChatFormatting textColor;

    public GrowthAcceleratorBlock(int range, ChatFormatting textColor) {
        super(Material.STONE, SoundType.STONE, 5.0F, 8.0F, ToolType.PICKAXE);
        this.range = range;
        this.textColor = textColor;
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        world.getBlockTicks().scheduleTick(pos, this, getTickRate());
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        BlockPos.betweenClosedStream(pos.above(2), pos.offset(0, this.range + 2, 0))
                .filter(aoePos -> world.getBlockState(aoePos).getBlock() instanceof BonemealableBlock)
                .findFirst()
                .ifPresent(aoePos -> world.getBlockState(aoePos).randomTick(world, aoePos, random));

        world.getBlockTicks().scheduleTick(pos, this, getTickRate());
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, BlockGetter world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(ModTooltips.GROWTH_ACCELERATOR.build());

        var rangeNumber = new TextComponent(String.valueOf(this.range)).withStyle(this.textColor);

        tooltip.add(ModTooltips.GROWTH_ACCELERATOR_RANGE.args(rangeNumber).build());
    }

    private static int getTickRate() {
        double variance = Math.random() * (1.1 - 0.9) + 0.9;
        return (int) (ModConfigs.GROWTH_ACCELERATOR_COOLDOWN.get() * variance) * 20;
    }
}
