package com.blakebr0.mysticalagriculture.item.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

import java.util.function.Function;

public class EssenceLeggingsItem extends ArmorItem {
    public EssenceLeggingsItem(IArmorMaterial material, Function<Properties, Properties> properties) {
        super(material, EquipmentSlotType.LEGS, properties.apply(new Properties()));
    }
}
