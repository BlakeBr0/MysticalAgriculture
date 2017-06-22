package com.blakebr0.mysticalagriculture.registry.object;

import com.blakebr0.mysticalagriculture.registry.IRegistryObject;
import com.blakebr0.mysticalagriculture.registry.MysticalRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class BlockObject implements IRegistryObject<Block> {
	
	private Block block;
	private Item item;
	private boolean enabled;
	
	public BlockObject(Block block){
		this(block, true);
	}
	
	public BlockObject(Block block, boolean enabled){
		this.block = block;
		this.enabled = enabled;
		MysticalRegistry.addBlock(this);
	}

	@Override
	public IRegistryObject setRegistryName(String name){
		this.block.setRegistryName(name);
		return this;
	}

	@Override
	public ResourceLocation getRegistryName(){
		return this.block.getRegistryName();
	}

	@Override
	public boolean isEnabled(){
		return this.enabled;
	}

	@Override
	public IRegistryObject<Block> getObject(){
		return this;
	}
	
	public Block getBlock(){
		return this.block;
	}
	
	public Item getItem(){
		return this.item;
	}
	
	public BlockObject withItem(){
		return withItem(new ItemBlock(getBlock()).setRegistryName(getRegistryName()));
	}
	
	public BlockObject withItem(Item itemBlock){
		this.item = itemBlock.setRegistryName(getRegistryName());
		MysticalRegistry.addItem(new ItemObject(this.item));
		return this;
	}
	
	public BlockObject withOre(String name){
		MysticalRegistry.addOre(getBlock(), name);
		return this;
	}
}
