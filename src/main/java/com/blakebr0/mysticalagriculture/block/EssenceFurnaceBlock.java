package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.mysticalagriculture.tileentity.EssenceFurnaceTileEntity;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class EssenceFurnaceBlock extends AbstractFurnaceBlock {
    public EssenceFurnaceBlock() {
        super(Properties.from(Blocks.FURNACE));
    }

    @Override
    protected void interactWith(World world, BlockPos pos, PlayerEntity player) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof EssenceFurnaceTileEntity) {
            player.openContainer((EssenceFurnaceTileEntity) tile);
            player.addStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new EssenceFurnaceTileEntity();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {

    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof EssenceFurnaceTileEntity) {
                EssenceFurnaceTileEntity furnace = (EssenceFurnaceTileEntity) tile;
                InventoryHelper.dropInventoryItems(world, pos, furnace);
            }

            if (state.hasTileEntity())
                world.removeTileEntity(pos);
        }
    }
}
