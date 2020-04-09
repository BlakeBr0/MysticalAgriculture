package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import com.blakebr0.mysticalagriculture.registry.MobSoulTypeRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.Function;

public class SoulJarItem extends BaseItem {
    public SoulJarItem(Function<Properties, Properties> properties) {
        super(properties.compose(p -> p.maxStackSize(1)));
        this.addPropertyOverride(new ResourceLocation("fill"), (stack, world, entity) -> {
            IMobSoulType type = MobSoulUtils.getType(stack);
            if (type != null) {
                double souls = MobSoulUtils.getSouls(stack);
                if (souls > 0)
                    return (int) ((souls / type.getSoulRequirement()) * 9);
            }

            return 0;
        });
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            items.add(new ItemStack(this));

            MobSoulTypeRegistry.getInstance().getMobSoulTypes().forEach(type -> {
                items.add(MobSoulUtils.getFilledSoulJar(type));
            });
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        IMobSoulType type = MobSoulUtils.getType(stack);
        if (type != null) {
            ITextComponent entityName = type.getEntityDisplayName();
            double souls = MobSoulUtils.getSouls(stack);
            tooltip.add(ModTooltips.SOUL_JAR.args(entityName, souls, type.getSoulRequirement()).build());

            if (flag.isAdvanced())
                tooltip.add(ModTooltips.MST_ID.args(type.getId()).color(TextFormatting.DARK_GRAY).build());
        }
    }
}
