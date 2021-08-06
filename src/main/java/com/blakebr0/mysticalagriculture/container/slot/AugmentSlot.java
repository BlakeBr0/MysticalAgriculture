package com.blakebr0.mysticalagriculture.container.slot;

import com.blakebr0.cucumber.iface.IToggleableSlot;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugmentGetter;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class AugmentSlot extends SlotItemHandler implements IToggleableSlot {
    private final AbstractContainerMenu container;
    private final int augmentSlot;

    public AugmentSlot(AbstractContainerMenu container, IItemHandler inventory, int index, int xPosition, int yPosition, int augmentSlot) {
        super(inventory, index, xPosition, yPosition);
        this.container = container;
        this.augmentSlot = augmentSlot;
    }

    @Override
    public ItemStack onTake(Player player, ItemStack stack) {
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
    public boolean mayPlace(ItemStack stack) {
        if (!super.mayPlace(stack))
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
