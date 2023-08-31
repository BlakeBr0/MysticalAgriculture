package com.blakebr0.mysticalagriculture.container.slot;

import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class EnchanterOutputSlot extends Slot {
    private final AbstractContainerMenu container;
    private final Container matrix;

    public EnchanterOutputSlot(AbstractContainerMenu container, Container matrix, Container inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
        this.container = container;
        this.matrix = matrix;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        var remaining = player.level().getRecipeManager().getRemainingItemsFor(ModRecipeTypes.ENCHANTER.get(), this.matrix, player.level());

        for (int i = 0; i < remaining.size(); i++) {
            this.matrix.setItem(i, remaining.get(i));
        }

        this.container.slotsChanged(this.matrix);
    }
}
