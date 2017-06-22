package com.blakebr0.mysticalagriculture.registry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.blakebr0.mysticalagriculture.registry.object.BlockObject;
import com.blakebr0.mysticalagriculture.registry.object.ItemObject;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MysticalRegistry {
	
	public static Set<BlockObject> blocks = new HashSet<>();
	public static Set<ItemObject> items = new HashSet<>();
	public static Map<ItemStack, String> ores = new HashMap<>();
	
	public static void addBlock(BlockObject block){
		blocks.add(block);
	}
	
	public static void addItem(ItemObject item){
		items.add(item);
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
}
