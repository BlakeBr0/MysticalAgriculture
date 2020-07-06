package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.util.Utils;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Function;

public class ExperienceDropletItem extends BaseItem {
    public ExperienceDropletItem(Function<Properties, Properties> properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        int used = 0;

        if (!world.isRemote()) {
            if (player.isCrouching()) {
                int xp = 0;
                for (int i = 0; i < stack.getCount(); i++) {
                    xp += Utils.randInt(8, 12);
                }

                ExperienceOrbEntity orb = new ExperienceOrbEntity(world, player.getPosX(), player.getPosY(), player.getPosZ(), xp);
                world.addEntity(orb);

                used = stack.getCount();
            } else {
                int xp = Utils.randInt(8, 12);
                ExperienceOrbEntity orb = new ExperienceOrbEntity(world, player.getPosX(), player.getPosY(), player.getPosZ(), xp);
                world.addEntity(orb);
                used = 1;
            }
        }

        if (!player.isCreative())
            stack.shrink(used);

        return new ActionResult<>(ActionResultType.SUCCESS, stack);
    }
}
