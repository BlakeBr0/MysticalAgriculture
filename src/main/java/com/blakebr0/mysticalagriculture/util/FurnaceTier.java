package com.blakebr0.mysticalagriculture.util;

import com.blakebr0.mysticalagriculture.tileentity.EssenceFurnaceTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;

public enum FurnaceTier {
    INFERIUM("inferium", 0.84D, 0.84D, EssenceFurnaceTileEntity.Inferium::new),
    PRUDENTIUM("prudentium", 0.625D, 0.84D, EssenceFurnaceTileEntity.Prudentium::new),
    TERTIUM("tertium", 0.4D, 0.68D, EssenceFurnaceTileEntity.Tertium::new),
    IMPERIUM("imperium", 0.145D, 0.5D, EssenceFurnaceTileEntity.Imperium::new),
    SUPREMIUM("supremium", 0.025D, 0.2D, EssenceFurnaceTileEntity.Supremium::new);

    private final String name;
    private final double cookTimeMultiplier;
    private final double burnTimeMultiplier;
    private final BiFunction<BlockPos, BlockState, EssenceFurnaceTileEntity> tileEntityFunc;

    FurnaceTier(String name, double cookTimeMultiplier, double burnTimeMultiplier, BiFunction<BlockPos, BlockState, EssenceFurnaceTileEntity> tileEntityFunc) {
        this.name = name;
        this.cookTimeMultiplier = cookTimeMultiplier;
        this.burnTimeMultiplier = burnTimeMultiplier;
        this.tileEntityFunc = tileEntityFunc;
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

    public EssenceFurnaceTileEntity createTileEntity(BlockPos pos, BlockState state) {
        return this.tileEntityFunc.apply(pos, state);
    }
}
