package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTiers;
import com.blakebr0.mysticalagriculture.api.crop.CropTypes;
import com.blakebr0.mysticalagriculture.api.registry.RegisterCropsEvent;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.item.ModItems;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.MOD_ID;

public class ModCrops {
    public static final Crop STONE = new Crop("stone", CropTiers.ONE, CropTypes.RESOURCE, MOD_ID);

    @SubscribeEvent
    public void onRegisterCrops(RegisterCropsEvent event) {
        event.register(STONE);
    }

    public static void onCommonSetup(FMLCommonSetupEvent event) {
        CropTiers.ELEMENTAL.setFarmland(ModBlocks.INFERIUM_FARMLAND).setEssence(ModItems.INFERIUM_ESSENCE);
        CropTiers.ONE.setFarmland(ModBlocks.INFERIUM_FARMLAND).setEssence(ModItems.INFERIUM_ESSENCE);
        CropTiers.TWO.setFarmland(ModBlocks.PRUDENTIUM_FARMLAND).setEssence(ModItems.PRUDENTIUM_ESSENCE);
        CropTiers.THREE.setFarmland(ModBlocks.INTERMEDIUM_FARMLAND).setEssence(ModItems.INTERMEDIUM_ESSENCE);
        CropTiers.FOUR.setFarmland(ModBlocks.IMPERIUM_FARMLAND).setEssence(ModItems.IMPERIUM_ESSENCE);
        CropTiers.FIVE.setFarmland(ModBlocks.SUPREMIUM_FARMLAND).setEssence(ModItems.SUPREMIUM_ESSENCE);

        CropTypes.RESOURCE.setCraftingSeed(ModItems.PROSPERITY_SEED_BASE);
        CropTypes.MOB.setCraftingSeed(ModItems.SOULIUM_SEED_BASE);
    }
}
