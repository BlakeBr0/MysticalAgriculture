package com.blakebr0.mysticalagriculture;

import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MysticalAgriculture.MOD_ID, name = MysticalAgriculture.NAME, version = MysticalAgriculture.VERSION, guiFactory = MysticalAgriculture.GUI_FACTORY)
public class MysticalAgriculture {
	
	public static final String MOD_ID = "mysticalagriculture";
	public static final String NAME = "Mystical Agriculture";
	public static final String VERSION = "1.6.0";
	public static final String GUI_FACTORY = "com.blakebr0.mysticalagriculture.config.GuiFactory";
		
	@Mod.Instance(MysticalAgriculture.MOD_ID)
	public static MysticalAgriculture INSTANCE;
	
	@SidedProxy(clientSide = "com.blakebr0.mysticalagriculture.proxy.ClientProxy",
				serverSide = "com.blakebr0.mysticalagriculture.proxy.ServerProxy")
	
	public static CommonProxy proxy;
		
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}

	public static CreativeTabs tabMysticalAgriculture = new CreativeTabs("tabMysticalAgriculture"){
		@Override
		public ItemStack getTabIconItem(){
			return ModItems.itemCrafting.itemSupremiumEssence;
		}
	};
}
