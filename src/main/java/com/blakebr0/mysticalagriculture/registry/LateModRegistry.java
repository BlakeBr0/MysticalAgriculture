package com.blakebr0.mysticalagriculture.registry;

import java.util.Map;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.cucumber.registry.Ore;
import com.blakebr0.cucumber.registry.RegistryObject;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class LateModRegistry extends ModRegistry {
	
	public LateModRegistry(String modid) {
		super(modid);
	}
	
	public static LateModRegistry create(String modid) {
		LateModRegistry registry = new LateModRegistry(modid);
		MinecraftForge.EVENT_BUS.register(registry);
		return registry;
	}
	
	public void registerBlocksLate(Register<Item> event) {
		for (RegistryObject<Block> block : blocks) {
			if (block.get().getRegistryName() == null) {
				block.get().setRegistryName(block.getName());
			}
			ForgeRegistries.BLOCKS.register(block.get());
		}
	}
	
	@Override
	public void registerBlocks(Register<Block> event) {
		//do nothing to prevent registering some stuff twice
	}
	
	public void registerItemsLate(Register<Item> event) {
		super.registerItems(event);
	}
	
	@Override
	public void registerItems(Register<Item> event) {
		//do nothing to prevent registering some stuff twice
	}
	
}