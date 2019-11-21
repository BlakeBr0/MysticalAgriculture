package com.blakebr0.mysticalagriculture.container.slot;

import com.blakebr0.mysticalagriculture.api.tinkering.IElementalItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ElementSlot extends SlotItemHandler {
    private final Container container;

    public ElementSlot(Container container, IItemHandler inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
        this.container = container;
    }

    @Override
    public ItemStack onTake(PlayerEntity player, ItemStack stack) {
        ItemStack take = super.onTake(player, stack);
        this.container.onCraftMatrixChanged(null);
        return take;
    }

    @Override
    public void putStack(ItemStack stack) {
        super.putStack(stack);
        this.container.onCraftMatrixChanged(null);
    }

    @Override
    public boolean isEnabled() {
        ItemStack stack = this.getItemHandler().getStackInSlot(0);
        Item item = stack.getItem();
        return item instanceof IElementalItem;
    }
}
