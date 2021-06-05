package com.blakebr0.mysticalagriculture;

import com.blakebr0.cucumber.helper.ConfigHelper;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.client.ModelHandler;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.crafting.DynamicRecipeManager;
import com.blakebr0.mysticalagriculture.data.ModDataGenerators;
import com.blakebr0.mysticalagriculture.handler.AugmentHandler;
import com.blakebr0.mysticalagriculture.handler.ColorHandler;
import com.blakebr0.mysticalagriculture.handler.ExperienceCapsuleHandler;
import com.blakebr0.mysticalagriculture.handler.MobDropHandler;
import com.blakebr0.mysticalagriculture.handler.MobSoulHandler;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.init.ModContainerTypes;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.item.FertilizedEssenceItem;
import com.blakebr0.mysticalagriculture.item.MysticalFertilizerItem;
import com.blakebr0.mysticalagriculture.network.NetworkHandler;
import com.blakebr0.mysticalagriculture.registry.AugmentRegistry;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.blakebr0.mysticalagriculture.registry.MobSoulTypeRegistry;
import com.blakebr0.mysticalagriculture.registry.PluginRegistry;
import com.blakebr0.mysticalagriculture.world.ModWorldFeatures;
import com.blakebr0.mysticalagriculture.world.ModWorldgenRegistration;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MysticalAgriculture.MOD_ID)
public final class MysticalAgriculture {
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
		bus.register(new ModDataGenerators());
		bus.register(new ModWorldFeatures());

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			bus.register(new ColorHandler());
			bus.register(new ModelHandler());
		});

		MinecraftForge.EVENT_BUS.register(new ModWorldgenRegistration());

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModConfigs.CLIENT);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.COMMON);

		PluginRegistry.getInstance().loadPlugins();

		MysticalAgricultureAPI.setCropRegistry(CropRegistry.getInstance());
		MysticalAgricultureAPI.setAugmentRegistry(AugmentRegistry.getInstance());
		MysticalAgricultureAPI.setMobSoulTypeRegistry(MobSoulTypeRegistry.getInstance());

		ConfigHelper.load(ModConfigs.COMMON, "mysticalagriculture-common.toml");
	}

	@SubscribeEvent
	public void onCommonSetup(FMLCommonSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new DynamicRecipeManager());
		MinecraftForge.EVENT_BUS.register(new MobDropHandler());
		MinecraftForge.EVENT_BUS.register(new MobSoulHandler());
		MinecraftForge.EVENT_BUS.register(new ExperienceCapsuleHandler());
		MinecraftForge.EVENT_BUS.register(new AugmentHandler());

		ModRecipeSerializers.onCommonSetup();

		event.enqueueWork(() -> {
			ModWorldgenRegistration.onCommonSetup();
			NetworkHandler.onCommonSetup();

			FertilizedEssenceItem.DispenserBehavior.register();
			MysticalFertilizerItem.DispenserBehavior.register();
		});
	}

	@SubscribeEvent
	public void onClientSetup(FMLClientSetupEvent event) {
		ModelHandler.onClientSetup(event);

		ModTileEntities.onClientSetup();
		ModContainerTypes.onClientSetup();
	}
}
