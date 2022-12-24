package com.blakebr0.mysticalagriculture.container;

import com.blakebr0.cucumber.container.BaseContainerMenu;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugmentProvider;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import com.blakebr0.mysticalagriculture.container.slot.AugmentSlot;
import com.blakebr0.mysticalagriculture.container.slot.ElementSlot;
import com.blakebr0.mysticalagriculture.container.slot.TinkerableSlot;
import com.blakebr0.mysticalagriculture.init.ModContainerTypes;
import com.blakebr0.mysticalagriculture.tileentity.TinkeringTableTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;

public class TinkeringTableContainer extends BaseContainerMenu {
    private final IItemHandlerModifiable inventory;

    private TinkeringTableContainer(MenuType<?> type, int id, Inventory playerInventory, FriendlyByteBuf buffer) {
        this(type, id, playerInventory, TinkeringTableTileEntity.createInventoryHandler(), buffer.readBlockPos());
    }

    private TinkeringTableContainer(MenuType<?> type, int id, Inventory playerInventory, IItemHandlerModifiable inventory, BlockPos pos) {
        super(type, id, pos);
        this.inventory = inventory;

        this.addSlot(new TinkerableSlot(this, inventory, 0, 80, 49));
        this.addSlot(new AugmentSlot(this, inventory, 1, 140, 36, 0));
        this.addSlot(new AugmentSlot(this, inventory, 2, 140, 62, 1));

        this.addSlot(new ElementSlot(this, inventory, 3, 20, 18));
        this.addSlot(new ElementSlot(this, inventory, 4, 20, 39));
        this.addSlot(new ElementSlot(this, inventory, 5, 20, 60));
        this.addSlot(new ElementSlot(this, inventory, 6, 20, 81));

        for (int k = 0; k < 3; k++) {
            for (int i1 = 0; i1 < 9; i1++) {
                this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 115 + k * 18));
            }
        }

        for (int l = 0; l < 9; l++) {
            this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 173));
        }
    }

    @Override
    public void slotsChanged(Container inventory) {
        var tinkerable = this.inventory.getStackInSlot(0);

        if (!tinkerable.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                var stack = this.inventory.getStackInSlot(i + 1);
                var item = stack.getItem();
                var augmentInSlot = AugmentUtils.getAugment(tinkerable, i);

                if (!stack.isEmpty() && item instanceof IAugmentProvider) {
                    var augment = ((IAugmentProvider) item).getAugment();
                    if (augment != augmentInSlot)
                        AugmentUtils.addAugment(tinkerable, augment, i);
                } else if (augmentInSlot != null) {
                    AugmentUtils.removeAugment(tinkerable, i);
                }
            }
        }

        super.slotsChanged(inventory);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotNumber) {
        var itemstack = ItemStack.EMPTY;
        var slot = this.slots.get(slotNumber);

        if (slot.hasItem()) {
            var itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (slotNumber == 0) {
                if (!this.moveItemStackTo(itemstack1, 7, 43, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (slotNumber >= 7 && slotNumber < 43) {
                if (!this.moveItemStackTo(itemstack1, 0, 7, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 7, 43, false)) {
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

    public static TinkeringTableContainer create(int windowId, Inventory playerInventory, FriendlyByteBuf buffer) {
        return new TinkeringTableContainer(ModContainerTypes.TINKERING_TABLE.get(), windowId, playerInventory, buffer);
    }

    public static TinkeringTableContainer create(int windowId, Inventory playerInventory, IItemHandlerModifiable inventory, BlockPos pos) {
        return new TinkeringTableContainer(ModContainerTypes.TINKERING_TABLE.get(), windowId, playerInventory, inventory, pos);
    }
}
