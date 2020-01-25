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
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Random;

public class GrowthAcceleratorBlock extends BaseBlock {
    private final int range;
    private final TextFormatting textColor;

    public GrowthAcceleratorBlock(int range, TextFormatting textColor) {
        super(Material.ROCK, SoundType.STONE, 5.0F, 8.0F);
        this.range = range;
        this.textColor = textColor;
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world));
    }

    @Override
    public int tickRate(IWorldReader world) {
        double variance = Math.random() * (1.1 - 0.9) + 0.9;
        return (int) (ModConfigs.GROWTH_ACCELERATOR_COOLDOWN.get() * variance) * 20;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockPos.getAllInBox(pos.up(), pos.add(0, this.range, 0))
                .filter(aoePos -> world.getBlockState(aoePos).getBlock() instanceof IGrowable)
                .findFirst()
                .ifPresent(aoePos -> world.getBlockState(aoePos).scheduledTick(world, aoePos, random));

        world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(ModTooltips.GROWTH_ACCELERATOR.build());
        ITextComponent rangeNumber = new StringTextComponent(String.valueOf(this.range)).applyTextStyle(this.textColor);
        tooltip.add(ModTooltips.GROWTH_ACCELERATOR_RANGE.args(rangeNumber).build());
    }
}
