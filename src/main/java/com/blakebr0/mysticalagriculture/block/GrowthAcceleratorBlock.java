package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseBlock;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import java.util.List;
import java.util.Random;

public class GrowthAcceleratorBlock extends BaseBlock {
    private final int range;
    private final TextFormatting textColor;

    public GrowthAcceleratorBlock(int range, TextFormatting textColor) {
        super(Material.STONE, SoundType.STONE, 5.0F, 8.0F, ToolType.PICKAXE);
        this.range = range;
        this.textColor = textColor;
    }

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        world.getBlockTicks().scheduleTick(pos, this, getTickRate());
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockPos.betweenClosedStream(pos.above(2), pos.offset(0, this.range + 2, 0))
                .filter(aoePos -> world.getBlockState(aoePos).getBlock() instanceof IGrowable)
                .findFirst()
                .ifPresent(aoePos -> world.getBlockState(aoePos).randomTick(world, aoePos, random));

        world.getBlockTicks().scheduleTick(pos, this, getTickRate());
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(ModTooltips.GROWTH_ACCELERATOR.build());
        ITextComponent rangeNumber = new StringTextComponent(String.valueOf(this.range)).withStyle(this.textColor);
        tooltip.add(ModTooltips.GROWTH_ACCELERATOR_RANGE.args(rangeNumber).build());
    }

    private static int getTickRate() {
        double variance = Math.random() * (1.1 - 0.9) + 0.9;
        return (int) (ModConfigs.GROWTH_ACCELERATOR_COOLDOWN.get() * variance) * 20;
    }
}
