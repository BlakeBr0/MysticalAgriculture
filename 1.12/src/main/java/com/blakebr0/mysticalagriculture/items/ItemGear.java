package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.cucumber.item.ItemMeta;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemGear extends ItemMeta {

	public static ItemStack itemInferiumToolCore;
	public static ItemStack itemPrudentiumToolCore;
	public static ItemStack itemIntermediumToolCore;
	public static ItemStack itemSuperiumToolCore;
	public static ItemStack itemSupremiumToolCore;
	
	public static ItemStack itemInferiumArmorCore;
	public static ItemStack itemPrudentiumArmorCore;
	public static ItemStack itemIntermediumArmorCore;
	public static ItemStack itemSuperiumArmorCore;
	public static ItemStack itemSupremiumArmorCore;
	
	public static ItemStack itemInferiumArrowHead;
	public static ItemStack itemPrudentiumArrowHead;
	public static ItemStack itemIntermediumArrowHead;
	public static ItemStack itemSuperiumArrowHead;
	public static ItemStack itemSupremiumArrowHead;
	
	public ItemGear(){
		super("ma.gear", MysticalAgriculture.REGISTRY);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}

	@Override
	public void init(){
		itemInferiumToolCore = addItem(0, "inferium_tool_core", true, Utils.asList(Tooltips.CORE_REMOVAL));
		itemPrudentiumToolCore = addItem(1, "prudentium_tool_core", true, Utils.asList(Tooltips.CORE_REMOVAL));
		itemIntermediumToolCore = addItem(2, "intermedium_tool_core", true, Utils.asList(Tooltips.CORE_REMOVAL));
		itemSuperiumToolCore = addItem(3, "superium_tool_core", true, Utils.asList(Tooltips.CORE_REMOVAL));
		itemSupremiumToolCore = addItem(4, "supremium_tool_core", true, Utils.asList(Tooltips.CORE_REMOVAL));
		
		itemInferiumArmorCore = addItem(5, "inferium_armor_core", true, Utils.asList(Tooltips.CORE_REMOVAL));
		itemPrudentiumArmorCore = addItem(6, "prudentium_armor_core", true, Utils.asList(Tooltips.CORE_REMOVAL));
		itemIntermediumArmorCore = addItem(7, "intermedium_armor_core", true, Utils.asList(Tooltips.CORE_REMOVAL));
		itemSuperiumArmorCore = addItem(8, "superium_armor_core", true, Utils.asList(Tooltips.CORE_REMOVAL));
		itemSupremiumArmorCore = addItem(9, "supremium_armor_core", true, Utils.asList(Tooltips.CORE_REMOVAL));
		
		itemInferiumArrowHead = addItem(15, "inferium_arrow_head");
		itemPrudentiumArrowHead = addItem(16, "prudentium_arrow_head");
		itemIntermediumArrowHead = addItem(17, "intermedium_arrow_head");
		itemSuperiumArrowHead = addItem(18, "superium_arrow_head");
		itemSupremiumArrowHead = addItem(19, "supremium_arrow_head");
	}
}
