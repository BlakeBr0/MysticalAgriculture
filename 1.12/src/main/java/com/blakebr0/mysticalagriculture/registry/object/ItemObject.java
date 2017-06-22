package com.blakebr0.mysticalagriculture.registry.object;

import com.blakebr0.mysticalagriculture.registry.IRegistryObject;
import com.blakebr0.mysticalagriculture.registry.MysticalRegistry;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemObject implements IRegistryObject<Item> {
	
	private Item item;
	private boolean enabled;
	
	public ItemObject(Item item){
		this(item, true);
	}
	
	public ItemObject(Item item, boolean enabled){
		this.item = item;
		this.enabled = enabled;
	}

	@Override
	public IRegistryObject setRegistryName(String name){
		this.item.setRegistryName(name);
		return this;
	}

	@Override
	public ResourceLocation getRegistryName(){
		return this.item.getRegistryName();
	}

	@Override
	public boolean isEnabled(){
		return this.enabled;
	}

	@Override
	public IRegistryObject<Item> getObject(){
		return this;
	}
	
	public Item getItem(){
		return this.item;
	}
	
	public ItemObject withOre(String name){
		MysticalRegistry.addOre(getItem(), name);
		return this;
	}
}
