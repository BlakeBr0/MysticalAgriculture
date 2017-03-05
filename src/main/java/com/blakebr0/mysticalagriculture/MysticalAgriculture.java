package com.blakebr0.mysticalagriculture;

import java.io.File;

import com.blakebr0.mysticalagriculture.config.EssenceConfig;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.proxy.CommonProxy;
import com.blakebr0.mysticalagriculture.world.OreGeneration;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = MysticalAgriculture.MOD_ID, name = MysticalAgriculture.NAME, version = MysticalAgriculture.VERSION, guiFactory = MysticalAgriculture.GUI_FACTORY)
public class MysticalAgriculture {
	
	public static final String MOD_ID = "mysticalagriculture";
	public static final String NAME = "Mystical Agriculture";
	public static final String VERSION = "1.4.3";
	public static final String GUI_FACTORY = "com.blakebr0.mysticalagriculture.config.GuiFactory";
		
	@Mod.Instance(MysticalAgriculture.MOD_ID)
	public static MysticalAgriculture INSTANCE;
	
	@SidedProxy(clientSide="com.blakebr0.mysticalagriculture.proxy.ClientProxy",
				serverSide="com.blakebr0.mysticalagriculture.proxy.ServerProxy")
	
	public static CommonProxy proxy;
		
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		File old_config = new File(e.getModConfigurationDirectory(), "mysticalagriculture.cfg");
		File old_essence_config = new File(e.getModConfigurationDirectory(), "mysticalagriculture_recipes.cfg");
		ModConfig.init(old_config);
		EssenceConfig.init(old_essence_config);
		MinecraftForge.EVENT_BUS.register(new ModConfig());
		MinecraftForge.EVENT_BUS.register(new EssenceConfig());
		proxy.preInit(e);
		FMLInterModComms.sendMessage("Waila", "register", "com.blakebr0.mysticalagriculture.util.WailaDataProvider.callbackRegister");
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	    GameRegistry.registerWorldGenerator(new OreGeneration(), 0);
	    MinecraftForge.EVENT_BUS.register(new MobDrops());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}

	public static CreativeTabs tabMysticalAgriculture = new CreativeTabs("tabMysticalAgriculture"){
		@Override
		public Item getTabIconItem(){
			return new ItemStack(ModItems.supremium_essence).getItem();
		}
	};
}
