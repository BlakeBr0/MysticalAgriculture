package com.blakebr0.mysticalagriculture.util;

import com.blakebr0.mysticalagriculture.container.inventory.UpgradeItemStackHandler;
import com.blakebr0.mysticalagriculture.item.MachineUpgradeItem;
import net.minecraft.world.item.ItemStack;

public interface IUpgradeableMachine {
    UpgradeItemStackHandler getUpgradeInventory();

    default MachineUpgradeTier getMachineTier() {
        return getUpgradeInventory().getUpgradeTier();
    }

    default ItemStack applyUpgrade(MachineUpgradeItem item) {
        var inventory = getUpgradeInventory();
        var current = inventory.getStackInSlot(0);

        inventory.setStackInSlot(0, new ItemStack(item));
        
        return current;
    }

    default boolean canApplyUpgrade(MachineUpgradeTier tier) {
        return getMachineTier() == null || getMachineTier().getValue() < tier.getValue();
    }
}
