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
    public static final Crop DIRT = new Crop("dirt", CropTier.ONE, CropType.RESOURCE, MOD_ID);
    public static final Crop WOOD = new Crop("wood", CropTier.ONE, CropType.RESOURCE, MOD_ID);
    public static final Crop ICE = new Crop("ice", CropTier.ONE, CropType.RESOURCE, MOD_ID);

    public static final Crop NATURE = new Crop("nature", CropTier.TWO, CropType.RESOURCE, MOD_ID);
    public static final Crop DYE = new Crop("dye", CropTier.TWO, CropType.RESOURCE, MOD_ID);
    public static final Crop NETHER = new Crop("nether", CropTier.TWO, CropType.RESOURCE, new CropTextures(CropTextures.FLOWER_ROCK_BLANK, CropTextures.ESSENCE_ROCK_BLANK), MOD_ID, 0x723232);
    public static final Crop COAL = new Crop("coal", CropTier.TWO, CropType.RESOURCE, MOD_ID);

    public static final Crop IRON = new Crop("iron", CropTier.THREE, CropType.RESOURCE, MOD_ID);
    public static final Crop NETHER_QUARTZ = new Crop("nether_quartz", CropTier.THREE, CropType.RESOURCE, MOD_ID);
    public static final Crop GLOWSTONE = new Crop("glowstone", CropTier.THREE, CropType.RESOURCE, MOD_ID);
    public static final Crop REDSTONE = new Crop("redstone", CropTier.THREE, CropType.RESOURCE, MOD_ID);
    public static final Crop OBSIDIAN = new Crop("obsidian", CropTier.THREE, CropType.RESOURCE, new CropTextures(CropTextures.FLOWER_ROCK_BLANK, CropTextures.ESSENCE_ROCK_BLANK), MOD_ID, 0x271E3D);
    public static final Crop PRISMARINE = new Crop("prismarine", CropTier.THREE, CropType.RESOURCE, MOD_ID);

    public static final Crop GOLD = new Crop("gold", CropTier.FOUR, CropType.RESOURCE, MOD_ID);
    public static final Crop LAPIS_LAZULI = new Crop("lapis_lazuli", CropTier.FOUR, CropType.RESOURCE, MOD_ID);
    public static final Crop END = new Crop("end", CropTier.FOUR, CropType.RESOURCE, new CropTextures(CropTextures.FLOWER_ROCK_BLANK, CropTextures.ESSENCE_ROCK_BLANK), MOD_ID, 0xEEF6B4);
    public static final Crop EXPERIENCE = new Crop("experience", CropTier.FOUR, CropType.RESOURCE, MOD_ID);

    public static final Crop DIAMOND = new Crop("diamond", CropTier.FIVE, CropType.RESOURCE, MOD_ID);
    public static final Crop EMERALD = new Crop("emerald", CropTier.FIVE, CropType.RESOURCE, MOD_ID);

    @SubscribeEvent
    public void onRegisterCrops(RegisterCropsEvent event) {
        event.register(AIR);
        event.register(EARTH);
        event.register(WATER);
        event.register(FIRE);

        event.register(STONE);
        event.register(DIRT);
        event.register(WOOD);
        event.register(ICE);

        event.register(NATURE);
        event.register(DYE);
        event.register(NETHER);
        event.register(COAL);

        event.register(IRON);
        event.register(NETHER_QUARTZ);
        event.register(GLOWSTONE);
        event.register(REDSTONE);
        event.register(OBSIDIAN);
        event.register(PRISMARINE);

        event.register(GOLD);
        event.register(LAPIS_LAZULI);
        event.register(END);
        event.register(EXPERIENCE);

        event.register(DIAMOND);
        event.register(EMERALD);
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
