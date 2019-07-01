package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseTileEntityBlock;
import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.mysticalagriculture.tileentity.InfusionAltarTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class InfusionAltarBlock extends BaseTileEntityBlock {
    public static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public InfusionAltarBlock() {
        super(Material.ROCK, SoundType.STONE, 10.0F, 12.0F);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new InfusionAltarTileEntity();
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof InfusionAltarTileEntity) {
            InfusionAltarTileEntity altar = (InfusionAltarTileEntity) tile;
            ItemStack input = altar.getStackInSlot(0);
            ItemStack output = altar.getStackInSlot(1);
            if (!output.isEmpty()) {
                ItemEntity item = new ItemEntity(world, player.posX, player.posY, player.posZ, output);
                item.setNoPickupDelay();
                world.addEntity(item);
                altar.setInventorySlotContents(1, ItemStack.EMPTY);
            } else {
                ItemStack held = player.getHeldItem(hand);
                if (input.isEmpty() && !held.isEmpty()) {
                    altar.setInventorySlotContents(0, StackHelper.withSize(held.copy(), 1, false));
                    player.setHeldItem(hand, StackHelper.decrease(held, 1, false));
                    world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                } else {
                    ItemEntity item = new ItemEntity(world, player.posX, player.posY, player.posZ, input);
                    item.setNoPickupDelay();
                    world.addEntity(item);
                    altar.setInventorySlotContents(0, ItemStack.EMPTY);
                }
            }
        }

        return true;
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof InfusionAltarTileEntity) {
            InventoryHelper.dropInventoryItems(world, pos, (InfusionAltarTileEntity) tile);
        }

        super.onReplaced(state, world, pos, newState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos post, ISelectionContext context) {
        return SHAPE;
    }
}
