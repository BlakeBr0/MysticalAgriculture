package com.blakebr0.mysticalagriculture;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.client.ModelHandler;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.container.ModContainerTypes;
import com.blakebr0.mysticalagriculture.crafting.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.crafting.ingredient.ModIngredients;
import com.blakebr0.mysticalagriculture.handler.ColorHandler;
import com.blakebr0.mysticalagriculture.item.ModItems;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;
import com.blakebr0.mysticalagriculture.world.ModWorldFeatures;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MysticalAgriculture.MOD_ID)
public class MysticalAgriculture {
	public static final String MOD_ID = "mysticalagriculture";
	public static final String NAME = "Mystical Agriculture";
	
	public static final ItemGroup ITEM_GROUP = new MAItemGroup();

	public MysticalAgriculture() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.register(this);
		bus.register(new ModBlocks());
		bus.register(new ModItems());
		bus.register(new ModRecipeSerializers());
		bus.register(new ModTileEntities());
		bus.register(new ModContainerTypes());
		bus.register(new ModCrops());

		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			bus.register(new ColorHandler());
			bus.register(new ModelHandler());
		});

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModConfigs.CLIENT);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.COMMON);

		MysticalAgricultureAPI.setCropRegistry(CropRegistry.getInstance());

		new ModIngredients();
	}

	@SubscribeEvent
	public void onCommonSetup(FMLCommonSetupEvent event) {
		ModCrops.onCommonSetup();
		ModIngredients.onCommonSetup();

		DeferredWorkQueue.runLater(() -> {
			ModWorldFeatures.onCommonSetup();
		});
	}

	@SubscribeEvent
	public void onInterModEnqueue(InterModEnqueueEvent event) {

	}

	@SubscribeEvent
	public void onInterModProcess(InterModProcessEvent event) {

	}

	@SubscribeEvent
	public void onClientSetup(FMLClientSetupEvent event) {
		ModTileEntities.onClientSetup();
		ModContainerTypes.onClientSetup();
	}
}
