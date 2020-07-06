package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.EnumSet;
import java.util.UUID;

public class StrengthAugment extends Augment {
    private static final UUID ATTRIBUTE_ID = UUID.fromString("527f7039-70c4-45e5-bdb7-b8721642cee5");
    private final int amplifier;

    public StrengthAugment(ResourceLocation id, int tier, int amplifier) {
        super(id, tier, EnumSet.of(AugmentType.SWORD), getColor(0xFFFD90, tier), getColor(0xCC8E27, tier));
        this.amplifier = amplifier;
    }

    @Override
    public void addToolAttributeModifiers(Multimap<Attribute, AttributeModifier> attributes, EquipmentSlotType slot, ItemStack stack) {
        attributes.put(Attributes.field_233823_f_, new AttributeModifier(ATTRIBUTE_ID, "Tool modifier", 5 * this.amplifier, AttributeModifier.Operation.ADDITION));
    }

    private static int getColor(int color, int tier) {
        return Utils.saturate(color, Math.min((float) tier / 5, 1));
    }
}
