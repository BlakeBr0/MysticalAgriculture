package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileInferiumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileIntermediumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TilePrudentiumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileSuperiumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileSupremiumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileUltimateFurnace;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
	
	public static void initTileEntities(){
		
		if(ModConfig.confSeedReprocessor){
			GameRegistry.registerTileEntity(TileEntitySeedReprocessor.class, "MA_Seed_Reprocessor");
		}
		
		if(ModConfig.confEssenceFurnaces){
			GameRegistry.registerTileEntity(TileInferiumFurnace.class, "MA_Inferium_Furnace");
			GameRegistry.registerTileEntity(TilePrudentiumFurnace.class, "MA_Prudentium_Furnace");
			GameRegistry.registerTileEntity(TileIntermediumFurnace.class, "MA_Intermedium_Furnace");
			GameRegistry.registerTileEntity(TileSuperiumFurnace.class, "MA_Superium_Furnace");
			GameRegistry.registerTileEntity(TileSupremiumFurnace.class, "MA_Supremium_Furnace");
			if(ModConfig.confUltimateFurnace){
				GameRegistry.registerTileEntity(TileUltimateFurnace.class, "MA_Ultimate_Furnace");
			}
		}
	}
}
