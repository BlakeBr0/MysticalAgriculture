package com.blakebr0.mysticalagriculture.util;

import com.blakebr0.mysticalagriculture.item.MachineUpgradeItem;
import net.minecraft.world.item.ItemStack;

public interface IUpgradeableMachine {
    MachineUpgradeTier getMachineTier();
    UpgradeApplyResult apply(MachineUpgradeItem item);

    default boolean canApplyUpgrade(MachineUpgradeTier tier) {
        return getMachineTier() == null || getMachineTier().getValue() < tier.getValue();
    }

    record UpgradeApplyResult(boolean applied, ItemStack remaining) { }
}
