package com.blakebr0.mysticalagriculture.item.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

import java.util.function.Function;

public class EssenceHelmetItem extends ArmorItem {
    public EssenceHelmetItem(IArmorMaterial material, Function<Properties, Properties> properties) {
        super(material, EquipmentSlotType.HEAD, properties.apply(new Properties()));
    }
}
