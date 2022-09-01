package com.blakebr0.mysticalagriculture.container.inventory;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.mysticalagriculture.item.MachineUpgradeItem;
import com.blakebr0.mysticalagriculture.util.MachineUpgradeTier;
import net.minecraft.world.item.ItemStack;

public class UpgradeItemStackHandler extends BaseItemStackHandler {
    public UpgradeItemStackHandler() {
        super(1);
    }

    @Override
    public int getSlotLimit(int slot) {
        return 1;
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        var item = stack.getItem();
        return item instanceof MachineUpgradeItem;
    }

    public MachineUpgradeTier getUpgradeTier() {
        var item = this.getStackInSlot(0).getItem();
        if (item instanceof MachineUpgradeItem upgrade)
            return upgrade.getTier();

        return null;
    }
}
