package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import com.blakebr0.mysticalagriculture.tileentity.EssenceFurnaceTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.Supplier;

public class EssenceFurnaceBlock extends AbstractFurnaceBlock {
    private final FurnaceTier tier;

    public EssenceFurnaceBlock(FurnaceTier tier) {
        super(Properties.copy(Blocks.FURNACE));
        this.tier = tier;
    }

    @Override
    protected void openContainer(Level world, BlockPos pos, Player player) {
        var tile = world.getBlockEntity(pos);

        if (tile instanceof EssenceFurnaceTileEntity furnace) {
            player.openMenu(furnace);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return this.tier.getNewTileEntity();
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) { }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            var tile = world.getBlockEntity(pos);

            if (tile instanceof EssenceFurnaceTileEntity furnace) {
                Containers.dropContents(world, pos, furnace);
            }
        }

        super.onRemove(state, world, pos, newState, isMoving);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, BlockGetter world, List<Component> tooltip, TooltipFlag flag) {
        double cookingSpeedDifference = 200D * this.tier.getCookTimeMultiplier();
        double cookingSpeedValue = Math.ceil(((200D - cookingSpeedDifference) / cookingSpeedDifference) * 100D) + 100D;
        var cookingSpeed = new TextComponent(String.valueOf((int) cookingSpeedValue)).append("%");
        double burnTimeDifference = (1600D * this.tier.getBurnTimeMultiplier()) / cookingSpeedDifference;
        double burnTimeValue = Math.ceil(((burnTimeDifference - 8D) / 8D) * 100D) + 100D;
        var fuelEfficiency = new TextComponent(String.valueOf((int) burnTimeValue)).append("%");

        tooltip.add(ModTooltips.COOKING_SPEED.args(cookingSpeed).build());
        tooltip.add(ModTooltips.FUEL_EFFICIENCY.args(fuelEfficiency).build());
    }

    public enum FurnaceTier {
        INFERIUM("inferium", 0.84D, 0.84D, EssenceFurnaceTileEntity.Inferium::new),
        PRUDENTIUM("prudentium", 0.625D, 0.84D, EssenceFurnaceTileEntity.Prudentium::new),
        TERTIUM("tertium", 0.4D, 0.68D, EssenceFurnaceTileEntity.Tertium::new),
        IMPERIUM("imperium", 0.145D, 0.5D, EssenceFurnaceTileEntity.Imperium::new),
        SUPREMIUM("supremium", 0.025D, 0.2D, EssenceFurnaceTileEntity.Supremium::new);

        private final String name;
        private final double cookTimeMultiplier;
        private final double burnTimeMultiplier;
        private final Supplier<EssenceFurnaceTileEntity> tileEntitySupplier;

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
