package com.blakebr0.mysticalagriculture.util;

import com.blakebr0.mysticalagriculture.tileentity.ReprocessorTileEntity;
import net.minecraft.util.text.TextFormatting;

import java.util.function.Supplier;

public enum ReprocessorTier {
    BASIC("basic", 200, 20, 80000, ReprocessorTileEntity.Basic::new, TextFormatting.GRAY),
    INFERIUM("inferium", 100, 40, 120000, ReprocessorTileEntity.Inferium::new, TextFormatting.YELLOW),
    PRUDENTIUM("prudentium", 80, 60, 180000, ReprocessorTileEntity.Prudentium::new, TextFormatting.GREEN),
    TERTIUM("tertium", 55, 100, 300000, ReprocessorTileEntity.Tertium::new, TextFormatting.GOLD),
    IMPERIUM("imperium", 20, 320, 420000, ReprocessorTileEntity.Imperium::new, TextFormatting.AQUA),
    SUPREMIUM("supremium", 5, 1440, 640000, ReprocessorTileEntity.Supremium::new, TextFormatting.RED);

    private final String name;
    private final int operationTime;
    private final int fuelUsage;
    private final int fuelCapacity;
    private final Supplier<ReprocessorTileEntity> tileEntitySupplier;
    private final TextFormatting textColor;

    ReprocessorTier(String name, int operationTime, int fuelUsage, int fuelCapacity, Supplier<ReprocessorTileEntity> tileEntitySupplier, TextFormatting textColor) {
        this.name = name;
        this.operationTime = operationTime;
        this.fuelUsage = fuelUsage;
        this.fuelCapacity = fuelCapacity;
        this.tileEntitySupplier = tileEntitySupplier;
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

    public ReprocessorTileEntity getNewTileEntity() {
        return this.tileEntitySupplier.get();
    }

    public TextFormatting getTextColor() {
        return this.textColor;
    }
}
