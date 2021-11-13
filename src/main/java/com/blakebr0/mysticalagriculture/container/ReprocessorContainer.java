package com.blakebr0.mysticalagriculture.container;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.inventory.slot.BaseItemStackHandlerSlot;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.init.ModContainerTypes;
import com.blakebr0.mysticalagriculture.tileentity.ReprocessorTileEntity;
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

public class ReprocessorContainer extends AbstractContainerMenu {
    private final Function<Player, Boolean> isUsableByPlayer;
    private final BlockPos pos;

    private ReprocessorContainer(MenuType<?> type, int id, Inventory playerInventory, BlockPos pos) {
        this(type, id, playerInventory, p -> false, ReprocessorTileEntity.createInventoryHandler(null), pos);
    }

    private ReprocessorContainer(MenuType<?> type, int id, Inventory playerInventory, Function<Player, Boolean> isUsableByPlayer, BaseItemStackHandler inventory, BlockPos pos) {
        super(type, id);
        this.isUsableByPlayer = isUsableByPlayer;
        this.pos = pos;

        this.addSlot(new BaseItemStackHandlerSlot(inventory, 0, 74, 52));
        this.addSlot(new BaseItemStackHandlerSlot(inventory, 1, 30, 56));
        this.addSlot(new BaseItemStackHandlerSlot(inventory, 2, 134, 52));

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

            if (index == 2) {
                if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (RecipeHelper.getRecipes(RecipeTypes.REPROCESSOR).values().stream().anyMatch(r -> r.getIngredients().get(0).test(itemstack1))) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (ForgeHooks.getBurnTime(itemstack1, null) > 0) {
                    if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 30) {
                    if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
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

    public static ReprocessorContainer create(int windowId, Inventory playerInventory, FriendlyByteBuf buffer) {
        return new ReprocessorContainer(ModContainerTypes.REPROCESSOR.get(), windowId, playerInventory, buffer.readBlockPos());
    }

    public static ReprocessorContainer create(int windowId, Inventory playerInventory, Function<Player, Boolean> isUsableByPlayer, BaseItemStackHandler inventory, BlockPos pos) {
        return new ReprocessorContainer(ModContainerTypes.REPROCESSOR.get(), windowId, playerInventory, isUsableByPlayer, inventory, pos);
    }
}
