package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.util.IActivatable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;

public class WandItem extends BaseItem {
    public WandItem() {
        super(p -> p.stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var level = context.getLevel();
        var pos = context.getClickedPos();

        var tile = level.getBlockEntity(pos);
        if (tile instanceof IActivatable activatable) {
            activatable.activate();

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
