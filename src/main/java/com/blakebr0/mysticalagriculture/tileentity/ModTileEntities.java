package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.helper.ResourceHelper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileInferiumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileIntermediumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TilePrudentiumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileSuperiumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileSupremiumFurnace;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileUltimateFurnace;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileInferiumReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileIntermediumReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TilePrudentiumReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileSuperiumReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileSupremiumReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileUltimateReprocessor;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
	
	public static void init() {
		if (ModConfig.confSeedReprocessor) {
			GameRegistry.registerTileEntity(TileEntitySeedReprocessor.class, "MA_Seed_Reprocessor");
			GameRegistry.registerTileEntity(TileInferiumReprocessor.class, ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "inferium_reprocessor"));
			GameRegistry.registerTileEntity(TilePrudentiumReprocessor.class, ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "prudentium_reprocessor"));
			GameRegistry.registerTileEntity(TileIntermediumReprocessor.class, ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "intermedium_reprocessor"));
			GameRegistry.registerTileEntity(TileSuperiumReprocessor.class, ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "superium_reprocessor"));
			GameRegistry.registerTileEntity(TileSupremiumReprocessor.class, ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "supremium_reprocessor"));
			
			if (ModConfig.confUltimateReprocessor) {
				GameRegistry.registerTileEntity(TileUltimateReprocessor.class, ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "ultimate_reprocessor"));
			}
		}
		
		if (ModConfig.confEssenceFurnaces) {
			GameRegistry.registerTileEntity(TileInferiumFurnace.class, "MA_Inferium_Furnace");
			GameRegistry.registerTileEntity(TilePrudentiumFurnace.class, "MA_Prudentium_Furnace");
			GameRegistry.registerTileEntity(TileIntermediumFurnace.class, "MA_Intermedium_Furnace");
			GameRegistry.registerTileEntity(TileSuperiumFurnace.class, "MA_Superium_Furnace");
			GameRegistry.registerTileEntity(TileSupremiumFurnace.class, "MA_Supremium_Furnace");
			
			if (ModConfig.confUltimateFurnace) {
				GameRegistry.registerTileEntity(TileUltimateFurnace.class, "MA_Ultimate_Furnace");
			}
		}
	}
}
