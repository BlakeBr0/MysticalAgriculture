package com.blakebr0.mysticalagriculture.item.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

import java.util.function.Function;

public class EssenceBootsItem extends ArmorItem {
    public EssenceBootsItem(IArmorMaterial material, Function<Properties, Properties> properties) {
        super(material, EquipmentSlotType.FEET, properties.apply(new Properties()));
    }
}
