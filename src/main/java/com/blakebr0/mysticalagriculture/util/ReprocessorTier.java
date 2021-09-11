package com.blakebr0.mysticalagriculture.util;

import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.tileentity.ReprocessorTileEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public enum ReprocessorTier {
    BASIC("basic", 200, 20, 80000, ReprocessorTileEntity.Basic::new, () -> ModTileEntities.BASIC_REPROCESSOR, ChatFormatting.GRAY),
    INFERIUM("inferium", 100, 40, 120000, ReprocessorTileEntity.Inferium::new, () -> ModTileEntities.INFERIUM_REPROCESSOR, ChatFormatting.YELLOW),
    PRUDENTIUM("prudentium", 80, 60, 180000, ReprocessorTileEntity.Prudentium::new, () -> ModTileEntities.PRUDENTIUM_REPROCESSOR, ChatFormatting.GREEN),
    TERTIUM("tertium", 55, 100, 300000, ReprocessorTileEntity.Tertium::new, () -> ModTileEntities.TERTIUM_REPROCESSOR, ChatFormatting.GOLD),
    IMPERIUM("imperium", 20, 320, 420000, ReprocessorTileEntity.Imperium::new, () -> ModTileEntities.IMPERIUM_REPROCESSOR, ChatFormatting.AQUA),
    SUPREMIUM("supremium", 5, 1440, 640000, ReprocessorTileEntity.Supremium::new, () -> ModTileEntities.SUPREMIUM_REPROCESSOR, ChatFormatting.RED);

    private final String name;
    private final int operationTime;
    private final int fuelUsage;
    private final int fuelCapacity;
    private final BiFunction<BlockPos, BlockState, ReprocessorTileEntity> tileEntityFunc;
    private final Supplier<BlockEntityType<? extends ReprocessorTileEntity>> tileEntityTypeSupplier;
    private final ChatFormatting textColor;

    @SuppressWarnings("unchecked")
    ReprocessorTier(String name, int operationTime, int fuelUsage, int fuelCapacity, BiFunction<BlockPos, BlockState, ReprocessorTileEntity> tileEntityFunc, Supplier<Object> tileEntityTypeSupplier, ChatFormatting textColor) {
        this.name = name;
        this.operationTime = operationTime;
        this.fuelUsage = fuelUsage;
        this.fuelCapacity = fuelCapacity;
        this.tileEntityFunc = tileEntityFunc;
        this.tileEntityTypeSupplier = (Supplier<BlockEntityType<? extends ReprocessorTileEntity>>) tileEntityTypeSupplier.get();
        this.textColor = textColor;
    }

    public String getName() {
        return this.name;
    }

    public int getOperationTime() {
        return this.operationTime;
    }

    public int getFuelUsage() {
        return this.fuelUsage;
    }

    public int getFuelCapacity() {
        return this.fuelCapacity;
    }

    @SuppressWarnings("unchecked")
    public <T extends ReprocessorTileEntity> BlockEntityType<T> getTileEntityType() {
        return (BlockEntityType<T>) this.tileEntityTypeSupplier.get();
    }

    public ReprocessorTileEntity createTileEntity(BlockPos pos, BlockState state) {
        return this.tileEntityFunc.apply(pos, state);
    }

    public ChatFormatting getTextColor() {
        return this.textColor;
    }
}
