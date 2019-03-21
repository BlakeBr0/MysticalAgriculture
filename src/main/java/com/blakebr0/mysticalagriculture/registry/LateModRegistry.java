package com.blakebr0.mysticalagriculture.registry;

import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.cucumber.registry.RegistryObject;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class LateModRegistry extends ModRegistry {

	public LateModRegistry(String modid) {
		super(modid);
	}
	
	public static LateModRegistry create(String modid) {
		LateModRegistry registry = new LateModRegistry(modid);
		MinecraftForge.EVENT_BUS.register(registry);
		return registry;
	}

	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void registerBlocksLate(Register<Item> event) {
		for (RegistryObject<Block> block : blocks) {
			if (block.get().getRegistryName() == null) {
				block.get().setRegistryName(block.getName());
			}
			ForgeRegistries.BLOCKS.register(block.get());
		}
	}

	@Override
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void registerItems(Register<Item> event) {
		super.registerItems(event);
	}

}