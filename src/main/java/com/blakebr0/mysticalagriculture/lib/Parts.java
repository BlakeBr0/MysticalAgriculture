package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ItemStackHolder;
import net.minecraftforge.oredict.OreDictionary;

public class Parts {
		
	public static Item itemTinkersIngots;
	public static Item itemEnderIOAlloys;
	public static Item itemBotaniaFlowers;
	public static Item itemBotaniaPetals;
	public static Item itemBotaniaResources;
	public static Item itemChiselMarble;
	public static Item itemChiselLimestone;
	public static Item itemChiselBasalt;
	public static Item itemBOPGems;
	public static Item itemRSIngot;
	public static Item itemAEMaterial;
	public static Item itemGCPMars;
	public static Item itemIC2MiscResource;
	public static Item itemAstralCrafting;
	public static Item itemAstralOre;
	
	public static void getParts(){
		
	    if(ModChecker.TINKERS){
	        try {
	            Item item = getItem("tconstruct:ingots");
	            itemTinkersIngots = item;
	        } catch(Throwable e){
	            e.printStackTrace();
	        }
	    }
	    
	    if(ModChecker.ENDERIO){
	        try {
	            Item item = getItem("EnderIO:itemAlloy");
	            itemEnderIOAlloys = item;
	        } catch(Throwable e){
	            e.printStackTrace();
	        }
	    }
	    
	    if(ModChecker.BOTANIA){
	        try {
	            Item item = getItem("botania:flower");
	            itemBotaniaFlowers = item;
	        } catch(Throwable e){
	            e.printStackTrace();
	        }
	        
	        try {
	        	Item item = getItem("botania:petal");
	        	itemBotaniaPetals = item;
	        } catch(Throwable e){
	        	e.printStackTrace();
	        }
	        
	        try {
	            Item item = getItem("botania:manaResource");
	            itemBotaniaResources = item;
	        } catch(Throwable e){
	            e.printStackTrace();
	        }
	    }
	    
	    if(ModChecker.CHISEL){
	        try {
	            Item item = getItem("chisel:marbleextra");
	            itemChiselMarble = item;
	        } catch(Throwable e){
	            e.printStackTrace();
	        }
	        
	        try {
	            Item item = getItem("chisel:limestoneextra");
	            itemChiselLimestone = item;
	        } catch(Throwable e){
	            e.printStackTrace();
	        }
	        
	        try {
	            Item item = getItem("chisel:basaltextra");
	            itemChiselBasalt = item;
	        } catch(Throwable e){
	            e.printStackTrace();
	        }
	    }
	    
	    if(ModChecker.BOP){
	    	try {
	    		Item item = getItem("biomesoplenty:gem");
	    		itemBOPGems = item;
	    	} catch(Throwable e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    if(ModChecker.RS){
	        try {
	            Item item = getItem("refinedstorage:quartz_enriched_iron");
	            itemRSIngot = item;
	        } catch(Throwable e){
	            e.printStackTrace();
	        }
	    }
	    
	    if(ModChecker.AE2){
	        try {
	            Item item = getItem("appliedenergistics2:material");
	            itemAEMaterial = item;
	        } catch(Throwable e){
	            e.printStackTrace();
	        }
	    }
	    
	    if(ModChecker.GALACTICRAFTPLANETS){
	    	try {
	    		Item item = getItem("galacticraftplanets:item_basic_mars");
	    		itemGCPMars = item;
	    	} catch(Throwable e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    if(ModChecker.IC2){
	    	try {
	    		Item item = getItem("IC2:misc_resource");
	    		itemIC2MiscResource = item;
	    	} catch(Throwable e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    if(ModChecker.ASTRAL){
	    	try {
	    		Item item = getItem("astralsorcery:ItemCraftingComponent");
	    		itemAstralCrafting = item;
	    	} catch(Throwable e){
	    		e.printStackTrace();
	    	}
	    	
	    	try {
	    		Item item = getItem("astralsorcery:BlockCustomOre");
	    		itemAstralOre = item;
	    	} catch(Throwable e){
	    		e.printStackTrace();
	    	}
	    }
	}

    public static Item getItem(String item) throws ItemNotFoundException {
        Item target = Item.getByNameOrId(item);
        
        if(target == null){
            throw new ItemNotFoundException(item);	
        }
        return target;
    }

    public static class ItemNotFoundException extends Exception {
        public ItemNotFoundException(String thing){
            super("Unable to find " + thing + "! Are you using the correct version of the mod?");
        }
    }
}
