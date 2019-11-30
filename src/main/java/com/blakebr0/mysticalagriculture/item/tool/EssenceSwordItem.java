package com.blakebr0.mysticalagriculture.item.tool;

import com.blakebr0.cucumber.item.tool.BaseSwordItem;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Function;

public class EssenceSwordItem extends BaseSwordItem implements ITinkerable {
    private static final EnumSet<AugmentType> TYPES = EnumSet.of(AugmentType.WEAPON, AugmentType.SWORD);
    private final int tinkerableTier;
    private final int slots;

    public EssenceSwordItem(IItemTier tier, int tinkerableTier, int slots, Function<Properties, Properties> properties) {
        super(tier, properties);
        this.tinkerableTier = tinkerableTier;
        this.slots = slots;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(ModTooltips.getTooltipForTier(this.tinkerableTier));
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        Multimap<String, AttributeModifier> modifiers = HashMultimap.create();
        if (slot == EquipmentSlotType.MAINHAND) {
            modifiers.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
            modifiers.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));

            AugmentUtils.getAugments(stack).forEach(a -> {
                a.addToolAttributeModifiers(modifiers, slot, stack);
            });
        }

        return modifiers;
    }

    @Override
    public int getAugmentSlots() {
        return this.slots;
    }

    @Override
    public EnumSet<AugmentType> getAugmentTypes() {
        return TYPES;
    }

    @Override
    public int getTinkerableTier() {
        return this.tinkerableTier;
    }
}
