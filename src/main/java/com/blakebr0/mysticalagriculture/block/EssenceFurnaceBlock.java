package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import com.blakebr0.mysticalagriculture.tileentity.EssenceFurnaceTileEntity;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
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
        return this.tier.getNewTileEntity();
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

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        double cookingSpeedDifference = 200D * this.tier.getCookTimeMultiplier();
        double cookingSpeedValue = Math.ceil(((200D - cookingSpeedDifference) / cookingSpeedDifference) * 100D) + 100D;
        ITextComponent cookingSpeed = new StringTextComponent(String.valueOf((int) cookingSpeedValue)).appendText("%");
        double burnTimeDifference = (1600D * this.tier.getBurnTimeMultiplier()) / cookingSpeedDifference;
        double burnTimeValue = Math.ceil(((burnTimeDifference - 8D) / 8D) * 100D) + 100D;
        ITextComponent fuelEfficiency = new StringTextComponent(String.valueOf((int) burnTimeValue)).appendText("%");

        tooltip.add(ModTooltips.COOKING_SPEED.args(cookingSpeed).build());
        tooltip.add(ModTooltips.FUEL_EFFICIENCY.args(fuelEfficiency).build());
    }

    public enum FurnaceTier {
        INFERIUM("inferium", 0.84D, 0.84D, EssenceFurnaceTileEntity.Inferium::new),
        PRUDENTIUM("prudentium", 0.625D, 0.84D, EssenceFurnaceTileEntity.Prudentium::new),
        TERTIUM("tertium", 0.4D, 0.68D, EssenceFurnaceTileEntity.Tertium::new),
        IMPERIUM("imperium", 0.145D, 0.5D, EssenceFurnaceTileEntity.Imperium::new),
        SUPREMIUM("supremium", 0.025D, 0.2D, EssenceFurnaceTileEntity.Supremium::new);

        private String name;
        private double cookTimeMultiplier;
        private double burnTimeMultiplier;
        private Supplier<EssenceFurnaceTileEntity> tileEntitySupplier;

        FurnaceTier(String name, double cookTimeMultiplier, double burnTimeMultiplier, Supplier<EssenceFurnaceTileEntity> tileEntitySupplier) {
            this.name = name;
            this.cookTimeMultiplier = cookTimeMultiplier;
            this.burnTimeMultiplier = burnTimeMultiplier;
            this.tileEntitySupplier = tileEntitySupplier;
        }

        public String getName() {
            return this.name;
        }

        public double getCookTimeMultiplier() {
            return this.cookTimeMultiplier;
        }

        public double getBurnTimeMultiplier() {
            return this.burnTimeMultiplier;
        }

        public EssenceFurnaceTileEntity getNewTileEntity() {
            return this.tileEntitySupplier.get();
        }
    }
}
