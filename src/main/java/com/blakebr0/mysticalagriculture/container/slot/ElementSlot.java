package com.blakebr0.mysticalagriculture.container.slot;

import com.blakebr0.cucumber.iface.IToggleableSlot;
import com.blakebr0.mysticalagriculture.api.tinkering.IElementalItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ElementSlot extends SlotItemHandler implements IToggleableSlot {
    private final AbstractContainerMenu container;

    public ElementSlot(AbstractContainerMenu container, IItemHandler inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
        this.container = container;
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        super.onTake(player, stack);
        this.container.slotsChanged(null);
    }

    @Override
    public void set(ItemStack stack) {
        super.set(stack);
        this.container.slotsChanged(null);
    }

    @Override
    public boolean isActive() {
        var stack = this.getItemHandler().getStackInSlot(0);
        var item = stack.getItem();

        return item instanceof IElementalItem;
    }
}
