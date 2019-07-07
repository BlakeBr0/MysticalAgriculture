package com.blakebr0.mysticalagriculture.item.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

import java.util.function.Function;

public class EssenceChestplateItem extends ArmorItem {
    public EssenceChestplateItem(IArmorMaterial material, Function<Properties, Properties> properties) {
        super(material, EquipmentSlotType.CHEST, properties.apply(new Properties()));
    }
}
