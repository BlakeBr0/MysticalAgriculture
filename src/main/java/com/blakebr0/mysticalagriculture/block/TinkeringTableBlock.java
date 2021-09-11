package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseTileEntityBlock;
import com.blakebr0.cucumber.util.VoxelShapeBuilder;
import com.blakebr0.mysticalagriculture.tileentity.TinkeringTableTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TinkeringTableBlock extends BaseTileEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final VoxelShape TABLE_SHAPE = new VoxelShapeBuilder()
            .cuboid(15, 4, 15, 1, 0, 1).cuboid(12.5, 9, 12.5, 3.5, 5, 3.5)
            .cuboid(16, 13, 16, 0, 10, 0).cuboid(13, 13.8, 15.8, 3, 12.8, 14.8)
            .cuboid(15.8, 13.8, 13, 14.8, 12.8, 3).cuboid(13, 13.8, 1.2, 3, 12.8, 0.2)
            .cuboid(15, 14, 15, 1, 13, 1).cuboid(1.2, 13.8, 13, 0.2, 12.8, 3)
            .cuboid(13.5, 5, 13.5, 2.5, 4, 2.5).cuboid(13.5, 10, 13.5, 2.5, 9, 2.5)
            .cuboid(1, 3, 3, 0, 0, 0).cuboid(1, 3, 16, 0, 0, 13)
            .cuboid(16, 3, 3, 15, 0, 0).cuboid(16, 3, 16, 15, 0, 13)
            .cuboid(3, 3, 1, 1, 0, 0).cuboid(15, 3, 1, 13, 0, 0)
            .cuboid(3, 3, 16, 1, 0, 15).cuboid(15, 3, 16, 13, 0, 15)
            .build();

    public TinkeringTableBlock() {
        super(Material.STONE, SoundType.STONE, 10.0F, 12.0F, true);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TinkeringTableTileEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide()) {
            var tile = world.getBlockEntity(pos);

            if (tile instanceof TinkeringTableTileEntity table)
                player.openMenu(table);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            var tile = world.getBlockEntity(pos);

            if (tile instanceof TinkeringTableTileEntity table) {
                Containers.dropContents(world, pos, table.getInventory().getStacks());
            }
        }

        super.onRemove(state, world, pos, newState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return TABLE_SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor world, BlockPos pos, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
