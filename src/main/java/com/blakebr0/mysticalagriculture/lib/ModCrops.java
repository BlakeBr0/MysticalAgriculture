package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.registry.RegisterCropsEvent;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.item.ModItems;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.MOD_ID;

public class ModCrops {
    public static final Crop AIR = new Crop("air", CropTier.ELEMENTAL, CropType.RESOURCE, new CropTextures(CropTextures.FLOWER_INGOT_BLANK, CropTextures.ESSENCE_FLAME_BLANK), MOD_ID, 0xDAD64D);
    public static final Crop EARTH = new Crop("earth", CropTier.ELEMENTAL, CropType.RESOURCE, new CropTextures(CropTextures.FLOWER_INGOT_BLANK, CropTextures.ESSENCE_FLAME_BLANK), MOD_ID, 0x54DA4D);
    public static final Crop WATER = new Crop("water", CropTier.ELEMENTAL, CropType.RESOURCE, new CropTextures(CropTextures.FLOWER_INGOT_BLANK, CropTextures.ESSENCE_FLAME_BLANK), MOD_ID, 0x4D7EDA);
    public static final Crop FIRE = new Crop("fire", CropTier.ELEMENTAL, CropType.RESOURCE, new CropTextures(CropTextures.FLOWER_INGOT_BLANK, CropTextures.ESSENCE_FLAME_BLANK), MOD_ID, 0xDA4D4D);

    public static final Crop STONE = new Crop("stone", CropTier.ONE, CropType.RESOURCE, new CropTextures(CropTextures.FLOWER_ROCK_BLANK, CropTextures.ESSENCE_ROCK_BLANK), MOD_ID, 0x7F7F7F);

    @SubscribeEvent
    public void onRegisterCrops(RegisterCropsEvent event) {
        event.register(AIR);
        event.register(EARTH);
        event.register(WATER);
        event.register(FIRE);

        event.register(STONE);
    }

    public static void onCommonSetup() {
        CropTier.ELEMENTAL.setFarmland(ModBlocks.INFERIUM_FARMLAND).setEssence(ModItems.INFERIUM_ESSENCE);
        CropTier.ONE.setFarmland(ModBlocks.INFERIUM_FARMLAND).setEssence(ModItems.INFERIUM_ESSENCE);
        CropTier.TWO.setFarmland(ModBlocks.PRUDENTIUM_FARMLAND).setEssence(ModItems.PRUDENTIUM_ESSENCE);
        CropTier.THREE.setFarmland(ModBlocks.TERTIUM_FARMLAND).setEssence(ModItems.TERTIUM_ESSENCE);
        CropTier.FOUR.setFarmland(ModBlocks.IMPERIUM_FARMLAND).setEssence(ModItems.IMPERIUM_ESSENCE);
        CropTier.FIVE.setFarmland(ModBlocks.SUPREMIUM_FARMLAND).setEssence(ModItems.SUPREMIUM_ESSENCE);

        CropType.RESOURCE.setCraftingSeed(ModItems.PROSPERITY_SEED_BASE);
        CropType.MOB.setCraftingSeed(ModItems.SOULIUM_SEED_BASE);
    }
}
