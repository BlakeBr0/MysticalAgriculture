package com.blakebr0.mysticalagriculture;

import com.blakebr0.cucumber.helper.ConfigHelper;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.client.ModRecipeBookCategories;
import com.blakebr0.mysticalagriculture.client.ModTESRs;
import com.blakebr0.mysticalagriculture.client.ModelHandler;
import com.blakebr0.mysticalagriculture.client.handler.GuiOverlayHandler;
import com.blakebr0.mysticalagriculture.compat.TOPCompat;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.crafting.DynamicRecipeManager;
import com.blakebr0.mysticalagriculture.data.ModDataGenerators;
import com.blakebr0.mysticalagriculture.handler.AugmentHandler;
import com.blakebr0.mysticalagriculture.handler.ColorHandler;
import com.blakebr0.mysticalagriculture.handler.ExperienceCapsuleHandler;
import com.blakebr0.mysticalagriculture.handler.MobDropHandler;
import com.blakebr0.mysticalagriculture.handler.MobSoulHandler;
import com.blakebr0.mysticalagriculture.init.ModBiomeModifiers;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.init.ModContainerTypes;
import com.blakebr0.mysticalagriculture.init.ModEnchantments;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.lib.ModItemTier;
import com.blakebr0.mysticalagriculture.network.NetworkHandler;
import com.blakebr0.mysticalagriculture.registry.AugmentRegistry;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.blakebr0.mysticalagriculture.registry.MobSoulTypeRegistry;
import com.blakebr0.mysticalagriculture.registry.PluginRegistry;
import com.blakebr0.mysticalagriculture.init.ModWorldFeatures;
import com.blakebr0.mysticalagriculture.util.RecipeIngredientCache;
import com.blakebr0.mysticalagriculture.world.ModWorldgenRegistration;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(MysticalAgriculture.MOD_ID)
public final class MysticalAgriculture {
	public static final String MOD_ID = "mysticalagriculture";
	public static final String NAME = "Mystical Agriculture";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
	public static final CreativeModeTab CREATIVE_TAB = new MACreativeTab();

	public MysticalAgriculture() throws NoSuchFieldException, IllegalAccessException {
		var bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.register(this);
		bus.register(new ModBlocks());
		bus.register(new ModItems());
		bus.register(new ModRecipeSerializers());
		bus.register(new ModDataGenerators());

		ModEnchantments.REGISTRY.register(bus);
		ModTileEntities.REGISTRY.register(bus);
		ModContainerTypes.REGISTRY.register(bus);
		ModRecipeTypes.REGISTRY.register(bus);
		ModRecipeSerializers.REGISTRY.register(bus);
		ModWorldFeatures.REGISTRY.register(bus);
		ModBiomeModifiers.REGISTRY.register(bus);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			bus.register(new ColorHandler());
			bus.register(new ModelHandler());
			bus.register(new ModTESRs());
			bus.register(new ModRecipeBookCategories());
			bus.register(new GuiOverlayHandler());
		});

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModConfigs.CLIENT);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.COMMON);

		initAPI();

		PluginRegistry.getInstance().loadPlugins();

		ConfigHelper.load(ModConfigs.COMMON, "mysticalagriculture-common.toml");
	}

	@SubscribeEvent
	public void onCommonSetup(FMLCommonSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new MobDropHandler());
		MinecraftForge.EVENT_BUS.register(new MobSoulHandler());
		MinecraftForge.EVENT_BUS.register(new ExperienceCapsuleHandler());
		MinecraftForge.EVENT_BUS.register(new AugmentHandler());
		MinecraftForge.EVENT_BUS.register(DynamicRecipeManager.getInstance());
		MinecraftForge.EVENT_BUS.register(RecipeIngredientCache.INSTANCE);

		ModRecipeSerializers.onCommonSetup();
		ModItemTier.onCommonSetup();

		event.enqueueWork(() -> {
			ModWorldgenRegistration.onCommonSetup();
			NetworkHandler.onCommonSetup();
		});
	}

	@SubscribeEvent
	public void onClientSetup(FMLClientSetupEvent event) {
		ModelHandler.onClientSetup(event);
		ModContainerTypes.onClientSetup();
	}

	@SubscribeEvent
	public void onInterModEnqueue(InterModEnqueueEvent event) {
		if (ModList.get().isLoaded("theoneprobe")) {
			TOPCompat.onInterModEnqueue();
		}
	}

	private static void initAPI() throws NoSuchFieldException, IllegalAccessException {
		var api = MysticalAgricultureAPI.class;

		var cropRegistry = api.getDeclaredField("cropRegistry");
		var augmentRegistry = api.getDeclaredField("augmentRegistry");
		var soulTypeRegistry = api.getDeclaredField("soulTypeRegistry");

		cropRegistry.setAccessible(true);
		cropRegistry.set(null, CropRegistry.getInstance());
		augmentRegistry.setAccessible(true);
		augmentRegistry.set(null, AugmentRegistry.getInstance());
		soulTypeRegistry.setAccessible(true);
		soulTypeRegistry.set(null, MobSoulTypeRegistry.getInstance());
	}
}
