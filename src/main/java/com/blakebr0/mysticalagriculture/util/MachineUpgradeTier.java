package com.blakebr0.mysticalagriculture.util;

import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import net.minecraft.ChatFormatting;

public enum MachineUpgradeTier {
    INFERIUM(CropTier.ONE, 0.833D, 2, 2, 1),
    PRUDENTIUM(CropTier.TWO, 0.625D, 4, 3, 2),
    TERTIUM(CropTier.THREE, 0.4D, 6, 4, 3),
    IMPERIUM(CropTier.FOUR, 0.125D, 16, 5, 4),
    SUPREMIUM(CropTier.FIVE, 0.025D, 48, 6, 5),
    AWAKENED_SUPREMIUM(CropTier.FIVE, 0.005D, 96, 7, 6);

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
