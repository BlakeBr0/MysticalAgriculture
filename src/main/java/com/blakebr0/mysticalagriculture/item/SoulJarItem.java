package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import com.blakebr0.mysticalagriculture.registry.MobSoulTypeRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.text.DecimalFormat;
import java.util.List;
import java.util.function.Function;

import net.minecraft.item.Item.Properties;

public class SoulJarItem extends BaseItem {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    public SoulJarItem(Function<Properties, Properties> properties) {
        super(properties.compose(p -> p.stacksTo(1)));
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            items.add(new ItemStack(this));

            MobSoulTypeRegistry.getInstance().getMobSoulTypes().forEach(type -> {
                if (type.isEnabled()) {
                    items.add(MobSoulUtils.getFilledSoulJar(type, this));
                }
            });
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        IMobSoulType type = MobSoulUtils.getType(stack);
        if (type != null) {
            ITextComponent entityName = type.getEntityDisplayName();
            String souls = DECIMAL_FORMAT.format(MobSoulUtils.getSouls(stack));
            String requirement = DECIMAL_FORMAT.format(type.getSoulRequirement());

            tooltip.add(ModTooltips.SOUL_JAR.args(entityName, souls, requirement).build());

            if (flag.isAdvanced()) {
                tooltip.add(ModTooltips.MST_ID.args(type.getId()).color(TextFormatting.DARK_GRAY).build());
            }
        }
    }

    public static IItemPropertyGetter getFillPropertyGetter() {
        return (stack, world, entity) -> {
            IMobSoulType type = MobSoulUtils.getType(stack);
            if (type != null) {
                double souls = MobSoulUtils.getSouls(stack);
                if (souls > 0) {
                    return (int) ((souls / type.getSoulRequirement()) * 9);
                }
            }

            return 0;
        };
    }
}
