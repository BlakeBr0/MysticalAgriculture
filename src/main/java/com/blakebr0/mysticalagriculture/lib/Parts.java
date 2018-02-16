package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.item.Item;

public class Parts {
		
	public static Item itemTinkersIngots;
	public static Item itemBotaniaFlowers;
	public static Item itemBotaniaPetals;
	public static Item itemBotaniaResources;
	public static Item itemChiselMarble;
	public static Item itemChiselLimestone;
	public static Item itemChiselBasalt;
	public static Item itemBOPGems;
	public static Item itemRSIngot;
	public static Item itemAEMaterial;
	public static Item itemAESkyStone;
	public static Item itemGCPMars;
	public static Item itemIC2MiscResource;
	public static Item itemIC2Nuclear;
	public static Item itemAstralCrafting;
	public static Item itemAstralOre;
	public static Item itemAstralRockCrystal;
	public static Item itemRusticSlate;
	
	public static void getParts(){
		
	    if(ModChecker.TINKERS_CONSTRUCT){
	        try {
	            Item item = getItem("tconstruct:ingots");
	            itemTinkersIngots = item;
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
	            Item item = getItem("chisel:marble2");
	            itemChiselMarble = item;
	        } catch(Throwable e){
	            e.printStackTrace();
	        }
	        
	        try {
	            Item item = getItem("chisel:limestone2");
	            itemChiselLimestone = item;
	        } catch(Throwable e){
	            e.printStackTrace();
	        }
	        
	        try {
	            Item item = getItem("chisel:basalt2");
	            itemChiselBasalt = item;
	        } catch(Throwable e){
	            e.printStackTrace();
	        }
	    }
	    
	    if(ModChecker.BIOMES_O_PLENTY){
	    	try {
	    		Item item = getItem("biomesoplenty:gem");
	    		itemBOPGems = item;
	    	} catch(Throwable e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    if(ModChecker.REFINED_STORAGE){
	        try {
	            Item item = getItem("refinedstorage:quartz_enriched_iron");
	            itemRSIngot = item;
	        } catch(Throwable e){
	            e.printStackTrace();
	        }
	    }
	    
	    if(ModChecker.APPLIED_ENERGISTICS_2){
	        try {
	            Item item = getItem("appliedenergistics2:material");
	            itemAEMaterial = item;
	        } catch(Throwable e){
	            e.printStackTrace();
	        }
	        
	        try {
	        	Item item = getItem("appliedenergistics2:sky_stone_block");
	        	itemAESkyStone = item;
	        } catch (Throwable e) {
	        	e.printStackTrace();
	        }
	    }
	    
	    if(ModChecker.GALACTICRAFT_PLANETS){
	    	try {
	    		Item item = getItem("galacticraftplanets:item_basic_mars");
	    		itemGCPMars = item;
	    	} catch(Throwable e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    if(ModChecker.IC2){
	    	try {
	    		Item item = getItem("ic2:misc_resource");
	    		itemIC2MiscResource = item;
	    	} catch(Throwable e){
	    		e.printStackTrace();
	    	}
	    	
	    	try {
	    		Item item = getItem("ic2:nuclear");
	    		itemIC2Nuclear = item;
	    	} catch(Throwable e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    if(ModChecker.ASTRAL_SORCERY){
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
	    	
	    	try {
	    		Item item = getItem("astralsorcery:ItemRockCrystalSimple");
	    		itemAstralRockCrystal = item;
	    	} catch(Throwable e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    if (ModChecker.RUSTIC) {
	    	try {
	    		Item item = getItem("rustic:slate");
	    		itemRusticSlate = item;
	    	} catch (Throwable e) {
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
