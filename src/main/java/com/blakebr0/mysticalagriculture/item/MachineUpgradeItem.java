package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.lib.Tooltips;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import com.blakebr0.mysticalagriculture.util.IUpgradeableMachine;
import com.blakebr0.mysticalagriculture.util.MachineUpgradeTier;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.text.NumberFormat;
import java.util.List;

public class MachineUpgradeItem extends BaseItem {
    private final MachineUpgradeTier tier;

    public MachineUpgradeItem(MachineUpgradeTier tier) {
        super();
        this.tier = tier;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var level = context.getLevel();
        var pos = context.getClickedPos();
        var tile = level.getBlockEntity(pos);

        if (tile instanceof IUpgradeableMachine machine && machine.canApplyUpgrade(this.tier)) {
            var stack = context.getItemInHand();
            var remaining = machine.applyUpgrade(this);

            stack.shrink(1);

            if (!remaining.isEmpty()) {
                var item = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), remaining.copy());

                level.addFreshEntity(item);
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            tooltip.add(ModTooltips.UPGRADE_SPEED.args(this.getStatText(this.tier.getOperationTimeMultiplier())).build());
            tooltip.add(ModTooltips.UPGRADE_FUEL_RATE.args(this.getStatText(this.tier.getFuelUsageMultiplier())).build());
            tooltip.add(ModTooltips.UPGRADE_FUEL_CAPACITY.args(this.getStatText(this.tier.getFuelCapacityMultiplier())).build());
            tooltip.add(ModTooltips.UPGRADE_AREA.args(this.getStatText(this.tier.getAddedRange())).build());
        } else {
            tooltip.add(Tooltips.HOLD_SHIFT_FOR_INFO.build());
        }
    }

    public MachineUpgradeTier getTier() {
        return this.tier;
    }

    private Component getStatText(Object stat) {
        var number = NumberFormat.getInstance().format(stat);
        return Component.literal(number).withStyle(this.tier.getTextColor());
    }
}
