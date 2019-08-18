package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.mysticalagriculture.tileentity.furnace.EssenceFurnaceTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.furnace.ImperiumFurnaceTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.furnace.InferiumFurnaceTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.furnace.IntermediumFurnaceTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.furnace.PrudentiumFurnaceTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.furnace.SupremiumFurnaceTileEntity;
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

import java.util.function.Supplier;

public class EssenceFurnaceBlock extends AbstractFurnaceBlock {
    private final FurnaceTier tier;

    public EssenceFurnaceBlock(FurnaceTier tier) {
        super(Properties.from(Blocks.FURNACE));
        this.tier = tier;
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
        return this.tier.getTileEntitySupplier().get();
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

    public enum FurnaceTier {
        INFERIUM("inferium", 1.0, InferiumFurnaceTileEntity::new),
        PRUDENTIUM("prudentium", 1.0, PrudentiumFurnaceTileEntity::new),
        INTERMEDIUM("intermedium", 1.0, IntermediumFurnaceTileEntity::new),
        IMPERIUM("imperium", 1.0, ImperiumFurnaceTileEntity::new),
        SUPREMIUM("supremium", 1.0, SupremiumFurnaceTileEntity::new);

        private String name;
        private double cookTimeMultiplier;
        private Supplier<EssenceFurnaceTileEntity> tileEntitySupplier;

        FurnaceTier(String name, double cookTimeMultiplier, Supplier<EssenceFurnaceTileEntity> tileEntitySupplier) {
            this.name = name;
            this.cookTimeMultiplier = cookTimeMultiplier;
            this.tileEntitySupplier = tileEntitySupplier;
        }

        public String getName() {
            return this.name;
        }

        public double getCookTimeMultiplier() {
            return this.cookTimeMultiplier;
        }

        public Supplier<EssenceFurnaceTileEntity> getTileEntitySupplier() {
            return this.tileEntitySupplier;
        }
    }
}
