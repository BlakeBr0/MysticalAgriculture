package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.util.Utils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ExperienceDropletItem extends BaseItem {
    public ExperienceDropletItem() {
        super();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        var stack = player.getItemInHand(hand);
        int used = 0;

        if (!world.isClientSide()) {
            if (player.isCrouching()) {
                int xp = 0;
                for (int i = 0; i < stack.getCount(); i++) {
                    xp += Utils.randInt(8, 12);
                }

                var orb = new ExperienceOrb(world, player.getX(), player.getY(), player.getZ(), xp);

                world.addFreshEntity(orb);

                used = stack.getCount();
            } else {
                int xp = Utils.randInt(8, 12);
                var orb = new ExperienceOrb(world, player.getX(), player.getY(), player.getZ(), xp);

                world.addFreshEntity(orb);

                used = 1;
            }
        }

        if (!player.isCreative())
            stack.shrink(used);

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }
}
