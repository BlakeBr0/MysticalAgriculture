package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropIngredient;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.registry.RegisterCropsEvent;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.item.ModItems;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.MOD_ID;

public class ModCrops {
    private static final CropTextures ELEMENTAL_CROP_TEXTURES = new CropTextures(CropTextures.FLOWER_INGOT_BLANK, CropTextures.ESSENCE_FLAME_BLANK);
    private static final CropTextures ROCK_CROP_TEXTURES = new CropTextures(CropTextures.FLOWER_ROCK_BLANK, CropTextures.ESSENCE_ROCK_BLANK);

    public static final Crop AIR = new Crop("air", CropTier.ELEMENTAL, CropType.RESOURCE, ELEMENTAL_CROP_TEXTURES, MOD_ID, 0xDAD64D, CropIngredient.EMPTY);
    public static final Crop EARTH = new Crop("earth", CropTier.ELEMENTAL, CropType.RESOURCE, ELEMENTAL_CROP_TEXTURES, MOD_ID, 0x54DA4D, CropIngredient.EMPTY);
    public static final Crop WATER = new Crop("water", CropTier.ELEMENTAL, CropType.RESOURCE, ELEMENTAL_CROP_TEXTURES, MOD_ID, 0x4D7EDA, CropIngredient.EMPTY);
    public static final Crop FIRE = new Crop("fire", CropTier.ELEMENTAL, CropType.RESOURCE, ELEMENTAL_CROP_TEXTURES, MOD_ID, 0xDA4D4D, CropIngredient.EMPTY);

    public static final Crop STONE = new Crop("stone", CropTier.ONE, CropType.RESOURCE, ROCK_CROP_TEXTURES, MOD_ID, 0x7F7F7F, CropIngredient.tag("forge:stone"));
    public static final Crop DIRT = new Crop("dirt", CropTier.ONE, CropType.RESOURCE, MOD_ID, CropIngredient.item("minecraft:dirt"));
    public static final Crop WOOD = new Crop("wood", CropTier.ONE, CropType.RESOURCE, MOD_ID, CropIngredient.tag("minecraft:logs"));
    public static final Crop ICE = new Crop("ice", CropTier.ONE, CropType.RESOURCE, MOD_ID, CropIngredient.item("minecraft:ice"));
    public static final Crop ZOMBIE = new Crop("zombie", CropTier.ONE, CropType.MOB, MOD_ID, CropIngredient.EMPTY);

    public static final Crop NATURE = new Crop("nature", CropTier.TWO, CropType.RESOURCE, MOD_ID, CropIngredient.EMPTY);
    public static final Crop DYE = new Crop("dye", CropTier.TWO, CropType.RESOURCE, MOD_ID, CropIngredient.EMPTY);
    public static final Crop NETHER = new Crop("nether", CropTier.TWO, CropType.RESOURCE, ROCK_CROP_TEXTURES, MOD_ID, 0x723232, CropIngredient.EMPTY);
    public static final Crop COAL = new Crop("coal", CropTier.TWO, CropType.RESOURCE, MOD_ID, CropIngredient.item("minecraft:coal"));
    public static final Crop PIG = new Crop("pig", CropTier.TWO, CropType.MOB, MOD_ID, CropIngredient.EMPTY);
    public static final Crop CHICKEN = new Crop("chicken", CropTier.TWO, CropType.MOB, MOD_ID, CropIngredient.EMPTY);
    public static final Crop COW = new Crop("cow", CropTier.TWO, CropType.MOB, MOD_ID, CropIngredient.EMPTY);
    public static final Crop SHEEP = new Crop("sheep", CropTier.TWO, CropType.MOB, MOD_ID, CropIngredient.EMPTY);
    public static final Crop SLIME = new Crop("slime", CropTier.TWO, CropType.MOB, MOD_ID, CropIngredient.EMPTY);

    public static final Crop IRON = new Crop("iron", CropTier.THREE, CropType.RESOURCE, MOD_ID, CropIngredient.tag("forge:ingots/iron"));
    public static final Crop NETHER_QUARTZ = new Crop("nether_quartz", CropTier.THREE, CropType.RESOURCE, MOD_ID, CropIngredient.tag("forge:gems/quartz"));
    public static final Crop GLOWSTONE = new Crop("glowstone", CropTier.THREE, CropType.RESOURCE, MOD_ID, CropIngredient.tag("forge:dusts/glowstone"));
    public static final Crop REDSTONE = new Crop("redstone", CropTier.THREE, CropType.RESOURCE, MOD_ID, CropIngredient.tag("forge:dusts/redstone"));
    public static final Crop OBSIDIAN = new Crop("obsidian", CropTier.THREE, CropType.RESOURCE, ROCK_CROP_TEXTURES, MOD_ID, 0x271E3D, CropIngredient.item("minecraft:obsidian"));
    public static final Crop PRISMARINE = new Crop("prismarine", CropTier.THREE, CropType.RESOURCE, MOD_ID, CropIngredient.EMPTY);
    public static final Crop SKELETON = new Crop("skeleton", CropTier.THREE, CropType.MOB, MOD_ID, CropIngredient.EMPTY);
    public static final Crop CREEPER = new Crop("creeper", CropTier.THREE, CropType.MOB, MOD_ID, CropIngredient.EMPTY);
    public static final Crop SPIDER = new Crop("spider", CropTier.THREE, CropType.MOB, MOD_ID, CropIngredient.EMPTY);
    public static final Crop RABBIT = new Crop("rabbit", CropTier.THREE, CropType.MOB, MOD_ID, CropIngredient.EMPTY);

    public static final Crop GOLD = new Crop("gold", CropTier.FOUR, CropType.RESOURCE, MOD_ID, CropIngredient.tag("forge:ingots/gold"));
    public static final Crop LAPIS_LAZULI = new Crop("lapis_lazuli", CropTier.FOUR, CropType.RESOURCE, MOD_ID, CropIngredient.tag("forge:gems/lapis"));
    public static final Crop END = new Crop("end", CropTier.FOUR, CropType.RESOURCE, ROCK_CROP_TEXTURES, MOD_ID, 0xEEF6B4, CropIngredient.EMPTY);
    public static final Crop EXPERIENCE = new Crop("experience", CropTier.FOUR, CropType.RESOURCE, MOD_ID, CropIngredient.EMPTY);
    public static final Crop BLAZE = new Crop("blaze", CropTier.FOUR, CropType.MOB, MOD_ID, CropIngredient.EMPTY);
    public static final Crop GHAST = new Crop("ghast", CropTier.FOUR, CropType.MOB, MOD_ID, CropIngredient.EMPTY);
    public static final Crop ENDERMAN = new Crop("enderman", CropTier.FOUR, CropType.MOB, MOD_ID, CropIngredient.EMPTY);

    public static final Crop DIAMOND = new Crop("diamond", CropTier.FIVE, CropType.RESOURCE, MOD_ID, CropIngredient.tag("forge:gems/diamond"));
    public static final Crop EMERALD = new Crop("emerald", CropTier.FIVE, CropType.RESOURCE, MOD_ID, CropIngredient.tag("forge:gems/emerald"));
    public static final Crop WITHER_SKELETON = new Crop("wither_skeleton", CropTier.FIVE, CropType.MOB, MOD_ID, CropIngredient.EMPTY);

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
        event.register(ZOMBIE);

        event.register(NATURE);
        event.register(DYE);
        event.register(NETHER);
        event.register(COAL);
        event.register(PIG);
        event.register(CHICKEN);
        event.register(COW);
        event.register(SHEEP);
        event.register(SLIME);

        event.register(IRON);
        event.register(NETHER_QUARTZ);
        event.register(GLOWSTONE);
        event.register(REDSTONE);
        event.register(OBSIDIAN);
        event.register(PRISMARINE);
        event.register(SKELETON);
        event.register(CREEPER);
        event.register(SPIDER);
        event.register(RABBIT);

        event.register(GOLD);
        event.register(LAPIS_LAZULI);
        event.register(END);
        event.register(EXPERIENCE);
        event.register(BLAZE);
        event.register(GHAST);
        event.register(ENDERMAN);

        event.register(DIAMOND);
        event.register(EMERALD);
        event.register(WITHER_SKELETON);
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
