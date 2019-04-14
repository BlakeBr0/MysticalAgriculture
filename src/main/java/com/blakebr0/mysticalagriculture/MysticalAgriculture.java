package com.blakebr0.mysticalagriculture;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.cucumber.render.ColorHandler;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.blocks.BlockInfusedFarmland;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.crafting.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
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
	public static final String VERSION = "${version}";
	public static final String DEPENDENCIES = "required-after:cucumber@[1.1.2,)";
	public static final String GUI_FACTORY = "com.blakebr0.mysticalagriculture.config.GuiFactory";
	
	public static final ItemGroup ITEM_GROUP = new MAItemGroup();

	public MysticalAgriculture() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientInit);

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModConfigs.CLIENT);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.COMMON);

		MinecraftForge.EVENT_BUS.register(new ModCrops());
	}
		
	public void preInit(FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(ModRecipeSerializers::new);
	}

	public void init(InterModEnqueueEvent event) {

	}

	public void postInit(InterModProcessEvent event) {

	}

	public void clientInit(FMLClientSetupEvent event) {
		ColorHandler.registerBlocks(new IColored.BlockColors(), BlockInfusedFarmland.FARMLANDS.toArray(new BlockInfusedFarmland[0]));
		ColorHandler.registerItems(new IColored.ItemBlockColors(), BlockInfusedFarmland.FARMLANDS.toArray(new BlockInfusedFarmland[0]));
		ColorHandler.registerItems((stack, tint) -> {
			float damage = (float) (stack.getMaxDamage() - stack.getDamage()) / stack.getMaxDamage();
			return Utils.saturate(0x00D9D9, damage);
		}, ModItems.INFUSION_CRYSTAL);
	}
}
