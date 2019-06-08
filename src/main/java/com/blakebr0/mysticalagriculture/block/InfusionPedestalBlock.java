package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseBlock;
import com.blakebr0.cucumber.util.VoxelShapeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class InfusionPedestalBlock extends BaseBlock {
    private static final VoxelShape FOOT_1_SHAPE = Block.makeCuboidShape(2.0, 0.0, 2.0, 5.0, 2.0, 5.0);
    private static final VoxelShape FOOT_2_SHAPE = Block.makeCuboidShape(11.0, 0.0, 2.0, 14.0, 2.0, 5.0);
    private static final VoxelShape FOOT_3_SHAPE = Block.makeCuboidShape(2.0, 0.0, 11.0, 5.0, 2.0, 14.0);
    private static final VoxelShape FOOT_4_SHAPE = Block.makeCuboidShape(11.0, 0.0, 11.0, 14.0, 2.0, 14.0);
    private static final VoxelShape PEDESTAL_BOTTOM_SHAPE = Block.makeCuboidShape(3.0, 2.0, 3.0, 13.0, 4.0, 13.0);
    private static final VoxelShape PEDESTAL_MIDDLE_SHAPE = Block.makeCuboidShape(4.0, 4.0, 4.0, 12.0, 14.0, 12.0);
    private static final VoxelShape PEDESTAL_TOP_1_SHAPE = Block.makeCuboidShape(3.0, 14.0, 3.0, 13.0, 16.0, 5.0);
    private static final VoxelShape PEDESTAL_TOP_2_SHAPE = Block.makeCuboidShape(3.0, 14.0, 11.0, 13.0, 16.0, 13.0);
    private static final VoxelShape PEDESTAL_TOP_3_SHAPE = Block.makeCuboidShape(3.0, 14.0, 5.0, 5.0, 16.0, 11.0);
    private static final VoxelShape PEDESTAL_TOP_4_SHAPE = Block.makeCuboidShape(11.0, 14.0, 5.0, 13.0, 16.0, 11.0);
    public static final VoxelShape PEDESTAL_SHAPE = new VoxelShapeBuilder().fromShapes(
            FOOT_1_SHAPE, FOOT_2_SHAPE, FOOT_3_SHAPE, FOOT_4_SHAPE, PEDESTAL_BOTTOM_SHAPE, PEDESTAL_MIDDLE_SHAPE,
            PEDESTAL_TOP_1_SHAPE, PEDESTAL_TOP_2_SHAPE, PEDESTAL_TOP_3_SHAPE, PEDESTAL_TOP_4_SHAPE
    ).build();

    public InfusionPedestalBlock(String name) {
        super(name, Material.ROCK, SoundType.STONE, 10.0F, 12.0F);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return PEDESTAL_SHAPE;
    }
}
