package com.blakebr0.mysticalagriculture.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class TinkeringTableContainer extends Container {
    private TinkeringTableContainer(ContainerType<?> type, int id, PlayerInventory playerInventory) {
        this(type, id, playerInventory, new ItemStackHandler());
    }

    private TinkeringTableContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, IItemHandler inventory) {
        super(type, id);

        this.addSlot(new SlotItemHandler(inventory, 0, 52, 50));

        for (int k = 0; k < 3; k++) {
            for (int i1 = 0; i1 < 9; i1++) {
                this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 119 + k * 18));
            }
        }

        for (int l = 0; l < 9; l++) {
            this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 177));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        return ItemStack.EMPTY;
    }

    public static TinkeringTableContainer create(int windowId, PlayerInventory playerInventory) {
        return new TinkeringTableContainer(ModContainerTypes.TINKERING_TABLE.get(), windowId, playerInventory);
    }

    public static TinkeringTableContainer create(int windowId, PlayerInventory playerInventory, IItemHandler inventory) {
        return new TinkeringTableContainer(ModContainerTypes.TINKERING_TABLE.get(), windowId, playerInventory, inventory);
    }
}
