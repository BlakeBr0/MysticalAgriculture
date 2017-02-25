package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.mysticalagriculture.config.ModConfig;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
	
	public static void initTileEntities(){
		if(ModConfig.seed_reprocessor){
			GameRegistry.registerTileEntity(TileEntitySeedReprocessor.class, "MA_Seed_Reprocessor");
		}
		
		if(ModConfig.essence_furnaces){
			GameRegistry.registerTileEntity(TileEntityInferiumFurnace.class, "MA_Inferium_Furnace");
			GameRegistry.registerTileEntity(TileEntityPrudentiumFurnace.class, "MA_Prudentium_Furnace");
			GameRegistry.registerTileEntity(TileEntityIntermediumFurnace.class, "MA_Intermedium_Furnace");
			GameRegistry.registerTileEntity(TileEntitySuperiumFurnace.class, "MA_Superium_Furnace");
			GameRegistry.registerTileEntity(TileEntitySupremiumFurnace.class, "MA_Supremium_Furnace");
			if(ModConfig.ultimate_furnace){
				GameRegistry.registerTileEntity(TileEntityUltimateFurnace.class, "MA_Ultimate_Furnace");
			}
		}
	}
}
