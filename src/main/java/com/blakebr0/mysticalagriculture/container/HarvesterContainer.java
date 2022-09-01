package com.blakebr0.mysticalagriculture.container;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.inventory.slot.BaseItemStackHandlerSlot;
import com.blakebr0.mysticalagriculture.container.inventory.UpgradeItemStackHandler;
import com.blakebr0.mysticalagriculture.init.ModContainerTypes;
import com.blakebr0.mysticalagriculture.item.MachineUpgradeItem;
import com.blakebr0.mysticalagriculture.tileentity.HarvesterTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;

import java.util.function.Function;

public class HarvesterContainer extends AbstractContainerMenu {
    private final Function<Player, Boolean> isUsableByPlayer;
    private final BlockPos pos;

    private HarvesterContainer(MenuType<?> type, int id, Inventory playerInventory, FriendlyByteBuf buffer) {
        this(type, id, playerInventory, p -> false, HarvesterTileEntity.createInventoryHandler(null), new UpgradeItemStackHandler(), buffer.readBlockPos());
    }

    private HarvesterContainer(MenuType<?> type, int id, Inventory playerInventory, Function<Player, Boolean> isUsableByPlayer, BaseItemStackHandler inventory, UpgradeItemStackHandler upgradeInventory, BlockPos pos) {
        super(type, id);
        this.isUsableByPlayer = isUsableByPlayer;
        this.pos = pos;

        this.addSlot(new BaseItemStackHandlerSlot(upgradeInventory, 0, 152, 9));
        this.addSlot(new BaseItemStackHandlerSlot(inventory, 0, 30, 56));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                this.addSlot(new BaseItemStackHandlerSlot(inventory, 1 + j + i * 5, 80 + j * 18, 42 + i * 18));
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 112 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 170));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return this.isUsableByPlayer.apply(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        var itemstack = ItemStack.EMPTY;
        var slot = this.slots.get(index);

        if (slot.hasItem()) {
            var itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index > 1) {
                if (itemstack1.getItem() instanceof MachineUpgradeItem) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (ForgeHooks.getBurnTime(itemstack1, null) > 0) {
                    if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 44) {
                    if (!this.moveItemStackTo(itemstack1, 44, 53, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 53 && !this.moveItemStackTo(itemstack1, 17, 44, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 17, 53, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public static HarvesterContainer create(int windowId, Inventory playerInventory, FriendlyByteBuf buffer) {
        return new HarvesterContainer(ModContainerTypes.HARVESTER.get(), windowId, playerInventory, buffer);
    }

    public static HarvesterContainer create(int windowId, Inventory playerInventory, Function<Player, Boolean> isUsableByPlayer, BaseItemStackHandler inventory, UpgradeItemStackHandler upgradeInventory, BlockPos pos) {
        return new HarvesterContainer(ModContainerTypes.HARVESTER.get(), windowId, playerInventory, isUsableByPlayer, inventory, upgradeInventory, pos);
    }
}
