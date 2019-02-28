package com.blakebr0.mysticalagriculture;

import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MysticalAgriculture.MOD_ID)
public class MysticalAgriculture {
	
	public static final String MOD_ID = "mysticalagriculture";
	public static final String NAME = "Mystical Agriculture";
	public static final String VERSION = "${version}";
	public static final String DEPENDENCIES = "required-after:cucumber@[1.1.2,)";
	public static final String GUI_FACTORY = "com.blakebr0.mysticalagriculture.config.GuiFactory";
	
	public static final ItemGroup ITEM_GROUP = new MAItemGroup();

	public MysticalAgriculture() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientInit);
	}
		
	public void preInit(FMLCommonSetupEvent event) {

	}

	public void init(InterModEnqueueEvent event) {

	}

	public void postInit(InterModProcessEvent event) {

	}

	public void clientInit(FMLClientSetupEvent event) {

	}
}
