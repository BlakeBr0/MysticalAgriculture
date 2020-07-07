package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.tool.BaseSwordItem;
import com.blakebr0.mysticalagriculture.api.soul.ISoulSiphoningItem;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.Function;

public class SouliumDaggerItem extends BaseSwordItem implements ISoulSiphoningItem {
    public SouliumDaggerItem(IItemTier tier, Function<Properties, Properties> properties) {
        super(tier, 3, -2.0F, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(ModTooltips.SOULIUM_DAGGER.build());
    }

    @Override
    public double getSiphonAmount(ItemStack stack, LivingEntity entity) {
        boolean isPeaceful = entity.getClassification(false).getPeacefulCreature();
        return isPeaceful ? 1.5D : 1.0D;
    }
}
