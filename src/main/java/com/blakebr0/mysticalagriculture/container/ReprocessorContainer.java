package com.blakebr0.mysticalagriculture.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ReprocessorContainer extends Container {
    private final IItemHandler inventory;
    private final IIntArray data;

    private ReprocessorContainer(ContainerType<?> type, int id, PlayerInventory playerInventory) {
        this(type, id, playerInventory, new ItemStackHandler(3), new IntArray(6));
    }

    private ReprocessorContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, IItemHandler inventory, IIntArray data) {
        super(type, id);
        this.inventory = inventory;
        this.data = data;

        this.addSlot(new SlotItemHandler(this.inventory, 0, 74, 42));
        this.addSlot(new SlotItemHandler(this.inventory, 1, 36, 50));
        this.addSlot(new SlotItemHandler(this.inventory, 2, 134, 42));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 101 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 159));
        }

        this.trackIntArray(data);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        return ItemStack.EMPTY;
    }

    public static ReprocessorContainer create(int windowId, PlayerInventory playerInventory) {
        return new ReprocessorContainer(ModContainerTypes.REPROCESSOR.get(), windowId, playerInventory);
    }

    public static ReprocessorContainer create(int windowId, PlayerInventory playerInventory, IItemHandler inventory, IIntArray data) {
        return new ReprocessorContainer(ModContainerTypes.REPROCESSOR.get(), windowId, playerInventory, inventory, data);
    }

    public int getCookProgressScaled(int pixels) {
        int i = this.data.get(0);
        int j = this.data.get(4);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    public int getFuelBarScaled(int pixels) {
        int i = this.data.get(1);
        int j = this.data.get(5);
        return (int) (j != 0 && i != 0 ? (long) i * pixels / j : 0);
    }

    public int getBurnLeftScaled(int pixels) {
        int i = this.data.get(2);
        int j = this.data.get(3);
        return (int) (j != 0 && i != 0 ? (long) i * pixels / j : 0);
    }

    public boolean isFuelItemValuable() {
        return this.data.get(3) > 0;
    }

    public boolean isProgressing() {
        return this.data.get(0) > 0;
    }

    public boolean hasFuel() {
        return this.data.get(2) > 0;
    }

    public int getFuel() {
        return this.data.get(1);
    }

    public int getFuelLeft() {
        return this.data.get(2);
    }

    public int getFuelCapacity() {
        return this.data.get(5);
    }
}
