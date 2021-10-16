package com.blakebr0.mysticalagriculture.util;

import com.blakebr0.mysticalagriculture.tileentity.EssenceFurnaceTileEntity;

import java.util.function.Supplier;

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
