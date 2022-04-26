package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseTileEntityBlock;
import com.blakebr0.cucumber.util.VoxelShapeBuilder;
import com.blakebr0.mysticalagriculture.tileentity.EssenceVesselTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EssenceVesselBlock extends BaseTileEntityBlock {
    public static final VoxelShape VESSEL_SHAPE = VoxelShapeBuilder.fromShapes(
            Shapes.box(0.690625, 0, 0.1875, 0.815625, 0.125, 0.3125),
            Shapes.box(0.1875, 0, 0.1875, 0.3125, 0.125, 0.3125),
            Shapes.box(0.1875, 0, 0.6875, 0.3125, 0.125, 0.8125),
            Shapes.box(0.6875, 0, 0.6875, 0.8125, 0.125, 0.8125),
            Shapes.box(0.1875, 0.71875, 0.1875, 0.8125, 1.15625, 0.8125),
            Shapes.box(0.4375, 0.6875, 0.15625, 0.5625, 1.1875, 0.21875),
            Shapes.box(0.15625, 0.6875, 0.4375, 0.21875, 1.1875, 0.5625),
            Shapes.box(0.78125, 0.6875, 0.4375, 0.84375, 1.1875, 0.5625),
            Shapes.box(0.4375, 0.6875, 0.78125, 0.5625, 1.1875, 0.84375),
            Shapes.box(0.21875, 0.6875, 0.4375, 0.40625, 0.71875, 0.5625),
            Shapes.box(0.59375, 0.6875, 0.4375, 0.78125, 0.71875, 0.5625),
            Shapes.box(0.4375, 0.6875, 0.59375, 0.5625, 0.71875, 0.78125),
            Shapes.box(0.4375, 0.6875, 0.21875, 0.5625, 0.71875, 0.40625),
            Shapes.box(0.3125, 0.625, 0.3125, 0.6875, 0.6875, 0.6875),
            Shapes.box(0.375, 0.15625, 0.375, 0.625, 0.625, 0.625),
            Shapes.box(0.25, 0.125, 0.25, 0.75, 0.1875, 0.75)
    ).build();

    public EssenceVesselBlock() {
        super(Material.STONE, SoundType.STONE, 10.0F, 12.0F, true);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EssenceVesselTileEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        var tile = level.getBlockEntity(pos);

        if (tile instanceof EssenceVesselTileEntity vessel) {
            var inventory = vessel.getInventory();
            var input = inventory.getStackInSlot(0);
            var held = player.getItemInHand(hand);

            var remaining = vessel.insert(held);
            if (held != remaining) {
                player.setItemInHand(hand, remaining);
                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
            } else if (!input.isEmpty()) {
                inventory.setStackInSlot(0, ItemStack.EMPTY);

                var item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), input);

                item.setNoPickUpDelay();
                level.addFreshEntity(item);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            var tile = level.getBlockEntity(pos);

            if (tile instanceof EssenceVesselTileEntity vessel) {
                Containers.dropContents(level, pos, vessel.getInventory().getStacks());
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return VESSEL_SHAPE;
    }
}
