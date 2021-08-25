package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.helper.ColorHelper;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

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
    public void addToolAttributeModifiers(Multimap<Attribute, AttributeModifier> attributes, EquipmentSlot slot, ItemStack stack) {
        attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTRIBUTE_ID, "Tool modifier", 5 * this.amplifier, AttributeModifier.Operation.ADDITION));
    }

    private static int getColor(int color, int tier) {
        return ColorHelper.saturate(color, Math.min((float) tier / 5, 1));
    }
}
