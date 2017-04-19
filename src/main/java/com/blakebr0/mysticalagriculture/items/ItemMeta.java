package com.blakebr0.mysticalagriculture.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemMeta extends ItemBase {

	public Map<Integer, MetaItem> items = new HashMap<>();
	public ArrayList<Integer> metas = new ArrayList<>();
	
	public ItemMeta(String name){
		super(name);
		this.setHasSubtypes(true);
	}
	
	public ItemStack addItem(int meta, String name, boolean enabled){
		if(!enabled){ return null; }
		if(items.containsKey(meta)){ return null; }
		items.put(meta, new MetaItem(name, enabled));
		metas.add(meta);
		return new ItemStack(this, 1, meta);
	}
	
	public ItemStack addItem(int meta, String name){
		return addItem(meta, name, true);
	}
	
	public ItemStack addItem(int meta, String name, String ore, boolean enabled){
		ItemStack stack = addItem(meta, name, enabled);
		OreDictionary.registerOre(ore, stack);
		return stack;
	}
	
	public ItemStack addItem(int meta, String name, String ore){
		return addItem(meta, name, ore, true);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> stacks){
		for(int meta : metas){
			stacks.add(new ItemStack(item, 1, meta));	
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack){
		int i = stack.getMetadata();
		if(!items.containsKey(i)){ return "invalid"; }
		MetaItem item = items.get(i);
		return this.getUnlocalizedName() + "_" + item.getName();
	}
	
    @SideOnly(Side.CLIENT)
    public void initModels(){
    	for(Map.Entry<Integer, MetaItem> item : items.entrySet()){
        	ModelLoader.setCustomModelResourceLocation(this, item.getKey(), new ModelResourceLocation(getRegistryName().toString() + "_" + item.getValue().getName()));
    	}
    }
    
    public void init(){
    	
    }
	
	public class MetaItem {
		private String name;
		private boolean enabled;
		
		MetaItem(String name, boolean enabled){
			this.name = name;
			this.enabled = enabled;
		}
		
		public String getName(){
			return name;
		}
		
		public boolean isEnabled(){
			return enabled;
		}
	}
}
