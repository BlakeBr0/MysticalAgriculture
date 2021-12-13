package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import com.blakebr0.mysticalagriculture.tileentity.EssenceFurnaceTileEntity;
import com.blakebr0.mysticalagriculture.util.FurnaceTier;
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
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public abstract class EssenceFurnaceBlock extends AbstractFurnaceBlock {
    private final FurnaceTier tier;

    public EssenceFurnaceBlock(FurnaceTier tier) {
        super(Properties.copy(Blocks.FURNACE));
        this.tier = tier;
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        var tile = level.getBlockEntity(pos);

        if (tile instanceof EssenceFurnaceTileEntity furnace) {
            player.openMenu(furnace);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return this.tier.createTileEntity(pos, state);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) { }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            var tile = level.getBlockEntity(pos);

            if (tile instanceof EssenceFurnaceTileEntity furnace) {
                Containers.dropContents(level, pos, furnace);
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
        double cookingSpeedDifference = 200D * this.tier.getCookTimeMultiplier();
        double cookingSpeedValue = Math.ceil(((200D - cookingSpeedDifference) / cookingSpeedDifference) * 100D) + 100D;
        var cookingSpeed = new TextComponent(String.valueOf((int) cookingSpeedValue)).append("%");
        double burnTimeDifference = (1600D * this.tier.getBurnTimeMultiplier()) / cookingSpeedDifference;
        double burnTimeValue = Math.ceil(((burnTimeDifference - 8D) / 8D) * 100D) + 100D;
        var fuelEfficiency = new TextComponent(String.valueOf((int) burnTimeValue)).append("%");

        tooltip.add(ModTooltips.COOKING_SPEED.args(cookingSpeed).build());
        tooltip.add(ModTooltips.FUEL_EFFICIENCY.args(fuelEfficiency).build());
    }

    public static class Inferium extends EssenceFurnaceBlock {
        public Inferium() {
            super(FurnaceTier.INFERIUM);
        }

        @Override
        public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
            return createTicker(level, type);
        }

        protected <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> type) {
            return level.isClientSide ? null : createTickerHelper(type, ModTileEntities.INFERIUM_FURNACE.get(), EssenceFurnaceTileEntity.Inferium::tick);
        }
    }

    public static class Prudentium extends EssenceFurnaceBlock {
        public Prudentium() {
            super(FurnaceTier.PRUDENTIUM);
        }

        @Override
        public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
            return createTicker(level, type);
        }

        protected <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> type) {
            return level.isClientSide ? null : createTickerHelper(type, ModTileEntities.PRUDENTIUM_FURNACE.get(), EssenceFurnaceTileEntity.Prudentium::tick);
        }
    }

    public static class Tertium extends EssenceFurnaceBlock {
        public Tertium() {
            super(FurnaceTier.TERTIUM);
        }

        @Override
        public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
            return createTicker(level, type);
        }

        protected <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> type) {
            return level.isClientSide ? null : createTickerHelper(type, ModTileEntities.TERTIUM_FURNACE.get(), EssenceFurnaceTileEntity.Tertium::tick);
        }
    }

    public static class Imperium extends EssenceFurnaceBlock {
        public Imperium() {
            super(FurnaceTier.IMPERIUM);
        }

        @Override
        public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
            return createTicker(level, type);
        }

        protected <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> type) {
            return level.isClientSide ? null : createTickerHelper(type, ModTileEntities.IMPERIUM_FURNACE.get(), EssenceFurnaceTileEntity.Imperium::tick);
        }
    }

    public static class Supremium extends EssenceFurnaceBlock {
        public Supremium() {
            super(FurnaceTier.SUPREMIUM);
        }

        @Override
        public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
            return createTicker(level, type);
        }

        protected <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> type) {
            return level.isClientSide ? null : createTickerHelper(type, ModTileEntities.SUPREMIUM_FURNACE.get(), EssenceFurnaceTileEntity.Supremium::tick);
        }
    }
}
