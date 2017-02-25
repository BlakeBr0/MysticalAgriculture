package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModToolMaterials {
	
	public static ToolMaterial SOULIUM = EnumHelper.addToolMaterial("SOULIUM", 0, 400, 5.0F, 3.0F, 40);
			
    public static ToolMaterial INFERIUM = EnumHelper.addToolMaterial("INFERIUM", 2, 500, 8.0F, 2.0F, 20);
    public static ToolMaterial PRUDENTIUM = EnumHelper.addToolMaterial("PRUDENTIUM", 2, 1000, 10.0F, 4.0F, 25);
    public static ToolMaterial INTERMEDIUM = EnumHelper.addToolMaterial("INTERMEDIUM", 3, 2000, 14.0F, 7.0F, 30);
    public static ToolMaterial SUPERIUM = EnumHelper.addToolMaterial("SUPERIUM", 4, 4000, 19.0F, 11.0F, 35);
    public static ToolMaterial SUPREMIUM = EnumHelper.addToolMaterial("SUPREMIUM", 5, -1, 25.0F, 17.0F, 0);

    public static ToolMaterial SUPREMIUM_AOE = EnumHelper.addToolMaterial("SUPREMIUM_AOE", 5, -1, 15.0F, 13.0F, 0);
    
    public static ToolMaterial SUPREMIUM_STRENGTH1 = EnumHelper.addToolMaterial("SUPREMIUM_STRENGTH1", 5, -1, 25.0F, 27.0F, 0);
    public static ToolMaterial SUPREMIUM_STRENGTH2 = EnumHelper.addToolMaterial("SUPREMIUM_STRENGTH2", 5, -1, 25.0F, 37.0F, 0);
    
    public static ItemArmor.ArmorMaterial INFERIUM_ARMOR = EnumHelper.addArmorMaterial("INFERIUMARMOR", MysticalAgriculture.MODID + ":" + "inferium_armor", 20, 
    		new int[]{2, 4, 5, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.5F);
    public static ItemArmor.ArmorMaterial PRUDENTIUM_ARMOR = EnumHelper.addArmorMaterial("PRUDENTIUMARMOR", MysticalAgriculture.MODID + ":" + "prudentium_armor", 40,
    		new int[]{2, 4, 6, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.75F);
    public static ItemArmor.ArmorMaterial INTERMEDIUM_ARMOR = EnumHelper.addArmorMaterial("INTERMEDIUMARMOR", MysticalAgriculture.MODID + ":" + "intermedium_armor", 80,
    		new int[]{3, 6, 7, 3}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0F);
    public static ItemArmor.ArmorMaterial SUPERIUM_ARMOR = EnumHelper.addArmorMaterial("SUPERIUMARMOR", MysticalAgriculture.MODID + ":" + "superium_armor", 160,
    		new int[]{4, 7, 8, 4}, 35, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.25F);
    public static ItemArmor.ArmorMaterial SUPREMIUM_ARMOR = EnumHelper.addArmorMaterial("SUPREMIUMARMOR", MysticalAgriculture.MODID + ":" + "supremium_armor", 280,
    		new int[]{4, 8, 9, 5}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.5F);
}
