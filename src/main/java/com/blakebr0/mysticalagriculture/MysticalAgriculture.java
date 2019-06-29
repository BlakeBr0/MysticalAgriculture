package com.blakebr0.mysticalagriculture;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.block.InfusedFarmlandBlock;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.crafting.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.crafting.ingredient.ModIngredients;
import com.blakebr0.mysticalagriculture.item.ModItems;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
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
	public static final String VERSION = "${version}";
	
	public static final ItemGroup ITEM_GROUP = new MAItemGroup();

	public MysticalAgriculture() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::onCommonSetup);
		bus.addListener(this::onInterModEnqueue);
		bus.addListener(this::onInterModProcess);
		bus.addListener(this::onClientSetup);

		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			bus.addListener(this::onBlockColors);
			bus.addListener(this::onItemColors);
		});

		bus.addGenericListener(Block.class, ModBlocks::onRegisterBlocks);
		bus.addGenericListener(Item.class, ModItems::onRegisterItems);
		bus.addGenericListener(IRecipeSerializer.class, ModRecipeSerializers::onRegisterSerializers);

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModConfigs.CLIENT);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.COMMON);

		bus.register(new ModCrops());

		MysticalAgricultureAPI.setCropRegistry(CropRegistry.getInstance());

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

	}

	@OnlyIn(Dist.CLIENT)
	public void onBlockColors(ColorHandlerEvent.Block event) {
		BlockColors colors = event.getBlockColors();

		colors.register(new IColored.BlockColors(), InfusedFarmlandBlock.FARMLANDS.toArray(new InfusedFarmlandBlock[0]));
	}

	@OnlyIn(Dist.CLIENT)
	public void onItemColors(ColorHandlerEvent.Item event) {
		ItemColors colors = event.getItemColors();

		colors.register(new IColored.ItemBlockColors(), InfusedFarmlandBlock.FARMLANDS.toArray(new InfusedFarmlandBlock[0]));
		colors.register((stack, tint) -> {
			float damage = (float) (stack.getMaxDamage() - stack.getDamage()) / stack.getMaxDamage();
			return Utils.saturate(0x00D9D9, damage);
		}, ModItems.INFUSION_CRYSTAL);
	}
}
