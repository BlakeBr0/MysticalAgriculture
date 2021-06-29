package com.blakebr0.mysticalagriculture.container.slot;

import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class TinkerableSlot extends SlotItemHandler {
    private final Container container;

    public TinkerableSlot(Container container, IItemHandler inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
        this.container = container;
    }

    @Override
    public ItemStack onTake(PlayerEntity player, ItemStack stack) {
        for (int i = 0; i < 2; i++) {
            this.getItemHandler().extractItem(i + 1, 1, false);
        }

        return super.onTake(player, stack);
    }

    @Override
    public void set(ItemStack stack) {
        for (int i = 0; i < 2; i++) {
            ItemStack augmentStack = this.getItemHandler().getStackInSlot(i + 1);
            if (!augmentStack.isEmpty()) {
                this.getItemHandler().extractItem(i + 1, augmentStack.getMaxStackSize(), false);
            }

            IAugment augment = AugmentUtils.getAugment(stack, i);
            if (augment != null) {
                this.getItemHandler().insertItem(i + 1, new ItemStack(augment.getItem()), false);
            }
        }

        super.set(stack);
        this.container.slotsChanged(null);
    }
}
