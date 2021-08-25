package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.api.util.ExperienceCapsuleUtils;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.Function;

public class ExperienceCapsuleItem extends BaseItem {
    public ExperienceCapsuleItem(Function<Properties, Properties> properties) {
        super(properties.compose(p -> p.stacksTo(1)));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        int experience = ExperienceCapsuleUtils.getExperience(stack);
        tooltip.add(ModTooltips.EXPERIENCE_CAPSULE.args(experience, ExperienceCapsuleUtils.MAX_XP_POINTS).build());
    }

    public static ItemPropertyFunction getFillPropertyGetter() {
        return (stack, world, entity, _unused) -> {
            int experience = ExperienceCapsuleUtils.getExperience(stack);

            if (experience > 0) {
                double level = (double) experience / ExperienceCapsuleUtils.MAX_XP_POINTS;
                return (int) (level * 10);
            }

            return 0;
        };
    }
}
