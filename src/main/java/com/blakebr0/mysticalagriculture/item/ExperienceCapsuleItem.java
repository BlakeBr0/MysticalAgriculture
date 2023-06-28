package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.util.Formatting;
import com.blakebr0.mysticalagriculture.api.util.ExperienceCapsuleUtils;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class ExperienceCapsuleItem extends BaseItem {
    public ExperienceCapsuleItem() {
        super(p -> p.stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        var stack = player.getItemInHand(hand);
        var experience = ExperienceCapsuleUtils.getExperience(stack);

        if (player.isCrouching()) {
            if (experience > 0) {
                var xpToGive = Math.min(experience, getExperienceToGive(player));

                xpToGive -= ExperienceCapsuleUtils.removeExperienceFromCapsule(stack, xpToGive);

                giveExperiencePoints(player, xpToGive);

                return InteractionResultHolder.success(stack);
            }
        } else {
            if (experience < ExperienceCapsuleUtils.MAX_XP_POINTS && player.totalExperience > 0) {
                var xpToTake = Math.min(ExperienceCapsuleUtils.MAX_XP_POINTS - experience, getExperienceToTake(player));

                xpToTake -= ExperienceCapsuleUtils.addExperienceToCapsule(stack, xpToTake);

                giveExperiencePoints(player, -xpToTake);

                return InteractionResultHolder.success(stack);
            }
        }

        return InteractionResultHolder.pass(stack);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        var experience = ExperienceCapsuleUtils.getExperience(stack);

        tooltip.add(ModTooltips.EXPERIENCE_CAPSULE.args(Formatting.number(experience), Formatting.number(ExperienceCapsuleUtils.MAX_XP_POINTS)).build());
    }

    public static ItemPropertyFunction getFillPropertyGetter() {
        return (stack, _level, _entity, _unused) -> {
            int experience = ExperienceCapsuleUtils.getExperience(stack);

            if (experience > 0) {
                double level = (double) experience / ExperienceCapsuleUtils.MAX_XP_POINTS;
                return (int) (level * 10);
            }

            return 0;
        };
    }

    private static int getExperienceToGive(Player player) {
        int xpNeeded = player.getXpNeededForNextLevel();
        int extraXp = Math.round(xpNeeded * player.experienceProgress);

        return xpNeeded - extraXp;
    }

    private static int getExperienceToTake(Player player) {
        // if they have progress towards the next level, then we give the player their progress
        // if they don't, then we need to give them everything in their current level
        if (player.experienceProgress > 0.0F) {
            var xpNeeded = player.getXpNeededForNextLevel();
            return Math.round(xpNeeded * player.experienceProgress);
        } else {
            // decrease player level by 1 temporarily to get the experience needed for the current level
            player.experienceLevel--;

            var xpNeeded = player.getXpNeededForNextLevel();

            // set the players level back to where it was
            player.experienceLevel++;

            return xpNeeded;
        }
    }

    // copy of Player#giveExperiencePoints
    private static void giveExperiencePoints(Player player, int points) {
        player.experienceProgress += (float) points / (float) player.getXpNeededForNextLevel();
        player.totalExperience = Mth.clamp(player.totalExperience + points, 0, Integer.MAX_VALUE);

        while (player.experienceProgress < 0.0F) {
            float f = player.experienceProgress * (float) player.getXpNeededForNextLevel();
            if (player.experienceLevel > 0) {
                giveExperienceLevels(player, -1);
                player.experienceProgress = 1.0F + f / (float) player.getXpNeededForNextLevel();
            } else {
                giveExperienceLevels(player, -1);
                player.experienceProgress = 0.0F;
            }
        }

        while (player.experienceProgress >= 1.0F) {
            player.experienceProgress = (player.experienceProgress - 1.0F) * (float) player.getXpNeededForNextLevel();
            giveExperienceLevels(player, 1);
            player.experienceProgress /= (float) player.getXpNeededForNextLevel();
        }

    }

    // copy of Player#giveExperienceLevels
    private static void giveExperienceLevels(Player player, int levels) {
        player.experienceLevel += levels;

        if (player.experienceLevel < 0) {
            player.experienceLevel = 0;
            player.experienceProgress = 0.0F;
            player.totalExperience = 0;
        }
    }
}
