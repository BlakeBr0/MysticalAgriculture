package com.blakebr0.mysticalagriculture.lib;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ItemStackHolder;
import net.minecraftforge.oredict.OreDictionary;

public class Parts {
	
	@ItemStackHolder(value="tconstruct:ingots", meta= 5)
	public static final ItemStack aluminum_brass = null;
	@ItemStackHolder(value="tconstruct:ingots", meta= 3)
	public static final ItemStack knightslime = null;
	@ItemStackHolder(value="tconstruct:ingots", meta= 1)
	public static final ItemStack ardite = null;
	@ItemStackHolder(value="tconstruct:ingots", meta= 0)
	public static final ItemStack cobalt = null;
	@ItemStackHolder(value="tconstruct:ingots", meta= 2)
	public static final ItemStack manyullyn = null;
	
	@ItemStackHolder(value="EnderIO:itemAlloy", meta= 0)
	public static final ItemStack electrical_steel = null;
	@ItemStackHolder(value="EnderIO:itemAlloy", meta= 3)
	public static final ItemStack redstone_alloy = null;
	@ItemStackHolder(value="EnderIO:itemAlloy", meta= 4)
	public static final ItemStack conductive_iron = null;
	@ItemStackHolder(value="EnderIO:itemAlloy", meta= 7)
	public static final ItemStack soularium = null;
	@ItemStackHolder(value="EnderIO:itemAlloy", meta= 6)
	public static final ItemStack dark_steel = null;
	@ItemStackHolder(value="EnderIO:itemAlloy", meta= 5)
	public static final ItemStack pulsating_iron = null;
	@ItemStackHolder(value="EnderIO:itemAlloy", meta= 1)
	public static final ItemStack energetic_alloy = null;
	@ItemStackHolder(value="EnderIO:itemAlloy", meta= 2)
	public static final ItemStack vibrant_alloy = null;
	
	@ItemStackHolder(value="botania:flower", meta= OreDictionary.WILDCARD_VALUE)
	public static final ItemStack botania_flower = null;
	
	@ItemStackHolder(value="botania:petal", meta= 0)
	public static final ItemStack white_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 1)
	public static final ItemStack orange_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 2)
	public static final ItemStack magenta_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 3)
	public static final ItemStack light_blue_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 4)
	public static final ItemStack yellow_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 5)
	public static final ItemStack lime_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 6)
	public static final ItemStack pink_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 7)
	public static final ItemStack gray_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 8)
	public static final ItemStack light_gray_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 9)
	public static final ItemStack cyan_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 10)
	public static final ItemStack purple_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 11)
	public static final ItemStack blue_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 12)
	public static final ItemStack brown_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 13)
	public static final ItemStack green_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 14)
	public static final ItemStack red_petal = null;
	@ItemStackHolder(value="botania:petal", meta= 15)
	public static final ItemStack black_petal = null;
	
	@ItemStackHolder(value="botania:manaResource", meta= 0)
	public static final ItemStack manasteel = null;
	@ItemStackHolder(value="botania:manaResource", meta= 4)
	public static final ItemStack terrasteel = null;
	
	@ItemStackHolder(value="appliedenergistics2:material", meta= 10)
	public static final ItemStack pure_certus_quartz = null;
	
	@ItemStackHolder(value="refinedstorage:quartz_enriched_iron", meta= 0)
	public static final ItemStack quartz_enriched_iron = null;

	public static Item tcon_ingots;
	public static Item eio_alloys;
	public static Item botania_flowers;
	public static Item botania_resources;
	public static Item rs_ingot;
	public static Item ae_material;
	
	public static void getParts(){
	    if(Loader.isModLoaded("tconstruct")){
	        try {
	            Item tcon_ingots = getItem("tconstruct:ingots");
	            Parts.tcon_ingots = tcon_ingots;
	        }
	        catch (Throwable e){
	            e.printStackTrace();
	        }
	    }
	    
	    if(Loader.isModLoaded("EnderIO")){
	        try {
	            Item eio_alloys = getItem("EnderIO:itemAlloy");
	            Parts.eio_alloys = eio_alloys;
	        }
	        catch (Throwable e){
	            e.printStackTrace();
	        }
	    }
	    
	    if(Loader.isModLoaded("Botania")){
	        try {
	            Item botania_flowers = getItem("botania:flower");
	            Parts.botania_flowers = botania_flowers;
	        }
	        catch (Throwable e){
	            e.printStackTrace();
	        }
	        
	        try {
	            Item botania_resources = getItem("botania:manaResource");
	            Parts.botania_resources = botania_resources;
	        }
	        catch (Throwable e){
	            e.printStackTrace();
	        }
	    }
	    
	    if(Loader.isModLoaded("refinedstorage")){
	        try {
	            Item rs_ingot = getItem("refinedstorage:quartz_enriched_iron");
	            Parts.rs_ingot = rs_ingot;
	        }
	        catch (Throwable e){
	            e.printStackTrace();
	        }
	    }
	    
	    if(Loader.isModLoaded("appliedenergistics2")){
	        try {
	            Item ae_material = getItem("appliedenergistics2:material");
	            Parts.ae_material = ae_material;
	        }
	        catch (Throwable e){
	            e.printStackTrace();
	        }
	    }
	}
	
    public static Block getBlock(String block) throws ItemNotFoundException {
        Block target = Block.getBlockFromName(block);
        if(target == null)
            throw new ItemNotFoundException(block);
        return target;
    }

    public static Item getItem(String item) throws ItemNotFoundException {
        Item target = Item.getByNameOrId(item);
        
        if(target == null)
            throw new ItemNotFoundException(item);
        return target;
    }

    public static class ItemNotFoundException extends Exception {
        public ItemNotFoundException(String thing){
            super("Unable to find " + thing + "! Are you using the correct version of the mod?");
        }
    }
}
