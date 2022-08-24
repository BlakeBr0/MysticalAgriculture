package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseTileEntityBlock;
import com.blakebr0.cucumber.util.VoxelShapeBuilder;
import com.blakebr0.mysticalagriculture.tileentity.TinkeringTableTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class TinkeringTableBlock extends BaseTileEntityBlock {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
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
        super(Material.STONE, SoundType.STONE, 10.0F, 12.0F, ToolType.PICKAXE);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TinkeringTableTileEntity();
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isClientSide()) {
            TileEntity tile = world.getBlockEntity(pos);
            if (tile instanceof TinkeringTableTileEntity)
                player.openMenu((TinkeringTableTileEntity) tile);
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tile = world.getBlockEntity(pos);
            if (tile instanceof TinkeringTableTileEntity) {
                TinkeringTableTileEntity table = (TinkeringTableTileEntity) tile;
                if (table.getInventory().getStacks().get(1).getItem()instanceof IAugmentGetter)
                    table.getInventory().getStacks().set(1, new ItemStack(Item.byId(0)));
                InventoryHelper.dropContents(world, pos, table.getInventory().getStacks());
            }
        }

        super.onRemove(state, world, pos, newState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return TABLE_SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
