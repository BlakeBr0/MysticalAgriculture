package com.blakebr0.mysticalagriculture.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.items.ItemMeta;
import com.blakebr0.mysticalagriculture.lib.IModelHelper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

public class MysticalRegistry {
	
	public static List<RegistryObject<Block>> blocks = new ArrayList<>();
	public static List<RegistryObject<Item>> items = new ArrayList<>();
	public static Map<Class, String> tiles = new HashMap<>();
	public static Map<ItemStack, String> ores = new HashMap<>();
	
	public static Block register(Block block, String name){
		blocks.add(new RegistryObject<Block>(block, name));
		return block;
	}
	
	public static Item register(Item item, String name){
		items.add(new RegistryObject<Item>(item, name));
		if(item instanceof ItemMeta){
			((ItemMeta)item).init();
		}
		return item;
	}
	
	public static void register(Class clazz, String name){
		tiles.put(clazz, name);
	}
	
	public static void addOre(Object ore, String name){
		if(ore instanceof Block){
			ores.put(new ItemStack((Block)ore), name);
		} else if(ore instanceof Item){
			ores.put(new ItemStack((Item)ore), name);
		} else if(ore instanceof ItemStack){
			ores.put(((ItemStack)ore).copy(), name);
		} else {
			throw new RuntimeException("Tried to add an invalid object to the OreDictionary, well done.");
		}
	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event){
		for(RegistryObject<Block> block : blocks){
			if(block.get().getRegistryName() == null){
				block.get().setRegistryName(block.getName());
			}
			event.getRegistry().register(block.get());
		}
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event){
		for(RegistryObject<Item> item : items){
			if(item.get().getRegistryName() == null){
				item.get().setRegistryName(item.getName());
			}
			event.getRegistry().register(item.get());
		}
		
		for(Map.Entry<ItemStack, String> ore : ores.entrySet()){
			OreDictionary.registerOre(ore.getValue(), ore.getKey());
		}
	}
	
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event){
		for(RegistryObject<Item> item : items){
			if(item.get() instanceof ItemMeta){
				((ItemMeta)item.get()).initModels();
			} else if(item.get() instanceof ItemBlock){
				if(((ItemBlock)item.get()).getBlock() instanceof IModelHelper){
					((IModelHelper)((ItemBlock)item.get()).getBlock()).initModels();
				} else {
					ModelLoader.setCustomModelResourceLocation(item.get(), 0, new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + item.getName(), "inventory")); 
				}
			} else {
				ModelLoader.setCustomModelResourceLocation(item.get(), 0, new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + item.getName(), "inventory")); 
			}
		}
	}
}
