package com.blakebr0.mysticalagriculture.util;

import net.minecraftforge.energy.EnergyStorage;

public class DynamicEnergyStorage extends EnergyStorage {
    private final int initialCapacity;

    public DynamicEnergyStorage(int capacity) {
        super(capacity);
        this.initialCapacity = capacity;
    }

    public void setMaxEnergyStorage(int capacity) {
        this.capacity = capacity;
    }

    public void resetMaxEnergyStorage() {
        this.capacity = this.initialCapacity;
    }
}
