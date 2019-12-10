package com.blakebr0.mysticalagriculture.container;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.inventory.slot.BaseItemStackHandlerSlot;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.tileentity.ReprocessorTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraftforge.common.ForgeHooks;

import java.util.function.Function;

public class ReprocessorContainer extends Container {
    private final Function<PlayerEntity, Boolean> isUsableByPlayer;
    private final IIntArray data;

    private ReprocessorContainer(ContainerType<?> type, int id, PlayerInventory playerInventory) {
        this(type, id, playerInventory, p -> false, (new ReprocessorTileEntity.Basic()).getInventory(), new IntArray(6));
    }

    private ReprocessorContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, Function<PlayerEntity, Boolean> isUsableByPlayer, BaseItemStackHandler inventory, IIntArray data) {
        super(type, id);
        this.isUsableByPlayer = isUsableByPlayer;
        this.data = data;

        this.addSlot(new BaseItemStackHandlerSlot(inventory, 0, 74, 42));
        this.addSlot(new BaseItemStackHandlerSlot(inventory, 1, 36, 50));
        this.addSlot(new BaseItemStackHandlerSlot(inventory, 2, 134, 42));

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
        return this.isUsableByPlayer.apply(player);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (player.getEntityWorld().getRecipeManager().getRecipes(RecipeTypes.REPROCESSOR).values().stream().anyMatch(r -> r.getIngredients().get(0).test(itemstack1))) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (ForgeHooks.getBurnTime(itemstack1) > 0) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    public static ReprocessorContainer create(int windowId, PlayerInventory playerInventory) {
        return new ReprocessorContainer(ModContainerTypes.REPROCESSOR.get(), windowId, playerInventory);
    }

    public static ReprocessorContainer create(int windowId, PlayerInventory playerInventory, Function<PlayerEntity, Boolean> isUsableByPlayer, BaseItemStackHandler inventory, IIntArray data) {
        return new ReprocessorContainer(ModContainerTypes.REPROCESSOR.get(), windowId, playerInventory, isUsableByPlayer, inventory, data);
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
