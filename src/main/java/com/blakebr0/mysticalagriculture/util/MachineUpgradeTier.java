package com.blakebr0.mysticalagriculture.util;

import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import net.minecraft.ChatFormatting;

public enum MachineUpgradeTier {
    INFERIUM(CropTier.ONE, 2, 2, 2, 1),
    PRUDENTIUM(CropTier.TWO, 4, 4, 4, 2),
    TERTIUM(CropTier.THREE, 8, 8, 8, 3),
    IMPERIUM(CropTier.FOUR, 16, 16, 16, 4),
    SUPREMIUM(CropTier.FIVE, 32, 32, 32, 5),
    AWAKENED_SUPREMIUM(CropTier.FIVE, 64, 64, 64, 6);

    private final int value;
    private final ChatFormatting textColor;
    private final double operationTimeMultiplier;
    private final double fuelUsageMultiplier;
    private final double fuelCapacityMultiplier;
    private final int addedRange;

    MachineUpgradeTier(CropTier tier, double operationTimeMultiplier, double fuelUsageMultiplier, double fuelCapacityMultiplier, int addedRange) {
        this.value = tier.getValue();
        this.textColor = tier.getTextColor();
        this.operationTimeMultiplier = operationTimeMultiplier;
        this.fuelUsageMultiplier = fuelUsageMultiplier;
        this.fuelCapacityMultiplier = fuelCapacityMultiplier;
        this.addedRange = addedRange;
    }

    public int getValue() {
        return this.value;
    }

    public ChatFormatting getTextColor() {
        return this.textColor;
    }

    public double getOperationTimeMultiplier() {
        return this.operationTimeMultiplier;
    }

    public double getFuelUsageMultiplier() {
        return this.fuelUsageMultiplier;
    }

    public double getFuelCapacityMultiplier() {
        return this.fuelCapacityMultiplier;
    }

    public int getAddedRange() {
        return this.addedRange;
    }
}
