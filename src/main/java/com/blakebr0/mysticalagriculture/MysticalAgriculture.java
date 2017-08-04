package com.blakebr0.mysticalagriculture;

import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MysticalAgriculture.MOD_ID, name = MysticalAgriculture.NAME, version = MysticalAgriculture.VERSION, dependencies = MysticalAgriculture.DEPENDENCIES, guiFactory = MysticalAgriculture.GUI_FACTORY)
public class MysticalAgriculture {
	
	public static final String MOD_ID = "mysticalagriculture";
	public static final String NAME = "Mystical Agriculture";
	public static final String VERSION = "1.6.3";
	public static final String DEPENDENCIES = "required-after:cucumber@[1.0.0,)";
	public static final String GUI_FACTORY = "com.blakebr0.mysticalagriculture.config.GuiFactory";
	
	public static final ModRegistry REGISTRY = new ModRegistry(MOD_ID);
		
	@Mod.Instance(MysticalAgriculture.MOD_ID)
	public static MysticalAgriculture INSTANCE;
	
	@SidedProxy(clientSide = "com.blakebr0.mysticalagriculture.proxy.ClientProxy",
				serverSide = "com.blakebr0.mysticalagriculture.proxy.ServerProxy")
	
	public static CommonProxy proxy;
		
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.init(event);
		FMLInterModComms.sendMessage("waila", "register", "com.blakebr0.mysticalagriculture.compat.Waila.registerProvider");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		proxy.postInit(event);
	}

	public static CreativeTabs tabMysticalAgriculture = new CreativeTabs("tabMysticalAgriculture"){
		@Override
		public ItemStack getTabIconItem(){
			return ModItems.itemCrafting.itemSupremiumEssence;
		}
	};
}
