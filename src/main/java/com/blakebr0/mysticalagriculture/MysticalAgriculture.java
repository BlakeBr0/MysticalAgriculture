package com.blakebr0.mysticalagriculture;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.cucumber.render.ColorHandler;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.block.BlockInfusedFarmland;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.crafting.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.crafting.ingredient.ModIngredients;
import com.blakebr0.mysticalagriculture.item.ModItems;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
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
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onInterModEnqueue);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onInterModProcess);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);

		FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Block.class, ModBlocks::onRegisterBlocks);
		FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Item.class, ModItems::onRegisterItems);

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModConfigs.CLIENT);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.COMMON);

		MinecraftForge.EVENT_BUS.register(ModCrops.class);

		MysticalAgricultureAPI.setCropRegistry(CropRegistry.getInstance());

		new ModRecipeSerializers();
		new ModIngredients();
	}
		
	public void onCommonSetup(FMLCommonSetupEvent event) {
		ModCrops.onCommonSetup();
		ModIngredients.onCommonSetup();
	}

	public void onInterModEnqueue(InterModEnqueueEvent event) {

	}

	public void onInterModProcess(InterModProcessEvent event) {

	}

	public void onClientSetup(FMLClientSetupEvent event) {
		ColorHandler.registerBlocks(new IColored.BlockColors(), BlockInfusedFarmland.FARMLANDS.toArray(new BlockInfusedFarmland[0]));
		ColorHandler.registerItems(new IColored.ItemBlockColors(), BlockInfusedFarmland.FARMLANDS.toArray(new BlockInfusedFarmland[0]));
		ColorHandler.registerItems((stack, tint) -> {
			float damage = (float) (stack.getMaxDamage() - stack.getDamage()) / stack.getMaxDamage();
			return Utils.saturate(0x00D9D9, damage);
		}, ModItems.INFUSION_CRYSTAL);
	}
}
