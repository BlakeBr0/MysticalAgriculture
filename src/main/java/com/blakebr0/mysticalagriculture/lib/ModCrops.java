package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTiers;
import com.blakebr0.mysticalagriculture.api.crop.CropTypes;
import com.blakebr0.mysticalagriculture.api.registry.RegisterCropsEvent;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.item.ModItems;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.MOD_ID;

public class ModCrops {
    public static final Crop STONE = new Crop("stone", CropTiers.ONE, CropTypes.RESOURCE, MOD_ID);

    @SubscribeEvent
    public void onRegisterCrops(RegisterCropsEvent event) {
        event.register(STONE);
    }

    public static void onCommonSetup() {
        CropTiers.ELEMENTAL.setFarmland(ModBlocks.INFERIUM_FARMLAND.orElse(null)).setEssence(ModItems.INFERIUM_ESSENCE.orElse(null));
        CropTiers.ONE.setFarmland(ModBlocks.INFERIUM_FARMLAND.orElse(null)).setEssence(ModItems.INFERIUM_ESSENCE.orElse(null));
        CropTiers.TWO.setFarmland(ModBlocks.PRUDENTIUM_FARMLAND.orElse(null)).setEssence(ModItems.PRUDENTIUM_ESSENCE.orElse(null));
        CropTiers.THREE.setFarmland(ModBlocks.INTERMEDIUM_FARMLAND.orElse(null)).setEssence(ModItems.INTERMEDIUM_ESSENCE.orElse(null));
        CropTiers.FOUR.setFarmland(ModBlocks.IMPERIUM_FARMLAND.orElse(null)).setEssence(ModItems.IMPERIUM_ESSENCE.orElse(null));
        CropTiers.FIVE.setFarmland(ModBlocks.SUPREMIUM_FARMLAND.orElse(null)).setEssence(ModItems.SUPREMIUM_ESSENCE.orElse(null));

        CropTypes.RESOURCE.setCraftingSeed(ModItems.PROSPERITY_SEED_BASE.orElse(null));
        CropTypes.MOB.setCraftingSeed(ModItems.SOULIUM_SEED_BASE.orElse(null));
    }
}
