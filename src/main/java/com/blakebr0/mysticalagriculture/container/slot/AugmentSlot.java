package com.blakebr0.mysticalagriculture.container.slot;

import com.blakebr0.cucumber.iface.IToggleableSlot;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugmentGetter;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class AugmentSlot extends SlotItemHandler implements IToggleableSlot {
    private final Container container;
    private final int augmentSlot;

    public AugmentSlot(Container container, IItemHandler inventory, int index, int xPosition, int yPosition, int augmentSlot) {
        super(inventory, index, xPosition, yPosition);
        this.container = container;
        this.augmentSlot = augmentSlot;
    }

    @Override
    public ItemStack onTake(PlayerEntity player, ItemStack stack) {
        ItemStack take = super.onTake(player, stack);
        this.container.slotsChanged(null);
        return take;
    }

    @Override
    public void set(ItemStack stack) {
        super.set(stack);
        this.container.slotsChanged(null);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        if (!super.mayPlace(stack) || !this.isActive())
            return false;

        ItemStack stackInSlot = this.getItemHandler().getStackInSlot(0);
        Item tinkerableItem = stackInSlot.getItem();
        Item augmentItem = stack.getItem();

        if (tinkerableItem instanceof ITinkerable && augmentItem instanceof IAugmentGetter) {
            ITinkerable tinkerable = (ITinkerable) tinkerableItem;
            IAugment augment = ((IAugmentGetter) augmentItem).getAugment();

            return tinkerable.canApplyAugment(augment);
        }

        return false;
    }

    @Override
    public boolean isActive() {
        ItemStack stack = this.getItemHandler().getStackInSlot(0);
        Item item = stack.getItem();
        if (item instanceof ITinkerable) {
            ITinkerable tinkerable = (ITinkerable) item;
            return this.augmentSlot < tinkerable.getAugmentSlots();
        }

        return false;
    }
}
