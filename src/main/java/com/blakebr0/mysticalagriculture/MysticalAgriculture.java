package com.blakebr0.mysticalagriculture;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.client.EssenceVesselColorManager;
import com.blakebr0.mysticalagriculture.client.ModClientExtensions;
import com.blakebr0.mysticalagriculture.client.ModClientTooltipComponentFactories;
import com.blakebr0.mysticalagriculture.client.ModMenuScreens;
import com.blakebr0.mysticalagriculture.client.ModRecipeBookCategories;
import com.blakebr0.mysticalagriculture.client.ModTESRs;
import com.blakebr0.mysticalagriculture.client.ModelHandler;
import com.blakebr0.mysticalagriculture.client.handler.AOEAugmentClientHandler;
import com.blakebr0.mysticalagriculture.client.handler.AugmentTooltipHandler;
import com.blakebr0.mysticalagriculture.client.handler.ColorHandler;
import com.blakebr0.mysticalagriculture.client.handler.GuiOverlayHandler;
import com.blakebr0.mysticalagriculture.compat.TOPCompat;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.crafting.DynamicRecipeManager;
import com.blakebr0.mysticalagriculture.data.ModDataGenerators;
import com.blakebr0.mysticalagriculture.handler.AugmentHandler;
import com.blakebr0.mysticalagriculture.handler.ExperienceCapsuleHandler;
import com.blakebr0.mysticalagriculture.handler.MobDropHandler;
import com.blakebr0.mysticalagriculture.handler.MobSoulHandler;
import com.blakebr0.mysticalagriculture.handler.RegisterCapabilityHandler;
import com.blakebr0.mysticalagriculture.handler.TinkerableHandler;
import com.blakebr0.mysticalagriculture.init.ModArmorMaterials;
import com.blakebr0.mysticalagriculture.init.ModBiomeModifiers;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.init.ModConditionSerializers;
import com.blakebr0.mysticalagriculture.init.ModCreativeModeTabs;
import com.blakebr0.mysticalagriculture.init.ModDataComponentTypes;
import com.blakebr0.mysticalagriculture.init.ModIngredientTypes;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.init.ModMenuTypes;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.init.ModWorldFeatures;
import com.blakebr0.mysticalagriculture.network.NetworkHandler;
import com.blakebr0.mysticalagriculture.registry.AugmentRegistry;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.blakebr0.mysticalagriculture.registry.MobSoulTypeRegistry;
import com.blakebr0.mysticalagriculture.registry.PluginRegistry;
import com.blakebr0.mysticalagriculture.util.RecipeIngredientCache;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(MysticalAgriculture.MOD_ID)
public final class MysticalAgriculture {
	public static final String MOD_ID = "mysticalagriculture";
	public static final String NAME = "Mystical Agriculture";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	public MysticalAgriculture(IEventBus bus, ModContainer mod) throws NoSuchFieldException, IllegalAccessException {
		bus.register(this);
		bus.register(new ModBlocks());
		bus.register(new ModItems());
		bus.register(new ModDataGenerators());

		ModArmorMaterials.REGISTRY.register(bus);
		ModCreativeModeTabs.REGISTRY.register(bus);
		ModTileEntities.REGISTRY.register(bus);
		ModDataComponentTypes.REGISTRY.register(bus);
		ModMenuTypes.REGISTRY.register(bus);
		ModIngredientTypes.REGISTRY.register(bus);
		ModConditionSerializers.REGISTRY.register(bus);
		ModRecipeTypes.REGISTRY.register(bus);
		ModRecipeSerializers.REGISTRY.register(bus);
		ModWorldFeatures.REGISTRY.register(bus);
		ModBiomeModifiers.REGISTRY.register(bus);

		bus.register(new NetworkHandler());
		bus.register(new RegisterCapabilityHandler());

		if (FMLEnvironment.dist == Dist.CLIENT) {
			bus.register(new ColorHandler());
			bus.register(new ModelHandler());
			bus.register(new ModTESRs());
			bus.register(new ModRecipeBookCategories());
			bus.register(new ModClientTooltipComponentFactories());
			bus.register(new ModMenuScreens());
			bus.register(new ModClientExtensions());
			bus.register(EssenceVesselColorManager.INSTANCE);
		}

		mod.registerConfig(ModConfig.Type.STARTUP, ModConfigs.COMMON, "mysticalagriculture-common.toml");

		initAPI();

		PluginRegistry.getInstance().loadPlugins();
	}

	@SubscribeEvent
	public void onCommonSetup(FMLCommonSetupEvent event) {
		NeoForge.EVENT_BUS.register(new MobDropHandler());
		NeoForge.EVENT_BUS.register(new MobSoulHandler());
		NeoForge.EVENT_BUS.register(new ExperienceCapsuleHandler());
		NeoForge.EVENT_BUS.register(new AugmentHandler());
		NeoForge.EVENT_BUS.register(new TinkerableHandler());
		NeoForge.EVENT_BUS.register(DynamicRecipeManager.INSTANCE);
		NeoForge.EVENT_BUS.register(RecipeIngredientCache.INSTANCE);

		CropRegistry.getInstance().onCommonSetup();
		AugmentRegistry.getInstance().onCommonSetup();
		MobSoulTypeRegistry.getInstance().onCommonSetup();
	}

	@SubscribeEvent
	public void onClientSetup(FMLClientSetupEvent event) {
		NeoForge.EVENT_BUS.register(new AugmentTooltipHandler());
		NeoForge.EVENT_BUS.register(new GuiOverlayHandler());
		NeoForge.EVENT_BUS.register(new AOEAugmentClientHandler());

		ModelHandler.onClientSetup(event);
	}

	@SubscribeEvent
	public void onInterModEnqueue(InterModEnqueueEvent event) {
		if (ModConfigs.isTheOneProbeInstalled()) {
			TOPCompat.onInterModEnqueue();
		}
	}

	public static ResourceLocation resource(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
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
