package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.item.ModItems;
import net.minecraft.util.ResourceLocation;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.MOD_ID;

public class ModCorePlugin implements IMysticalAgriculturePlugin {
    private static final CropTextures ELEMENTAL_CROP_TEXTURES = new CropTextures(CropTextures.FLOWER_INGOT_BLANK, CropTextures.ESSENCE_FLAME_BLANK);
    private static final CropTextures ROCK_CROP_TEXTURES = new CropTextures(CropTextures.FLOWER_ROCK_BLANK, CropTextures.ESSENCE_ROCK_BLANK);

    public static final Crop AIR = new Crop(new ResourceLocation(MOD_ID, "air"), CropTier.ELEMENTAL, CropType.RESOURCE, ELEMENTAL_CROP_TEXTURES, 0xDAD64D, LazyIngredient.EMPTY);
    public static final Crop EARTH = new Crop(new ResourceLocation(MOD_ID, "earth"), CropTier.ELEMENTAL, CropType.RESOURCE, ELEMENTAL_CROP_TEXTURES, 0x54DA4D, LazyIngredient.EMPTY);
    public static final Crop WATER = new Crop(new ResourceLocation(MOD_ID, "water"), CropTier.ELEMENTAL, CropType.RESOURCE, ELEMENTAL_CROP_TEXTURES, 0x4D7EDA, LazyIngredient.EMPTY);
    public static final Crop FIRE = new Crop(new ResourceLocation(MOD_ID, "fire"), CropTier.ELEMENTAL, CropType.RESOURCE, ELEMENTAL_CROP_TEXTURES, 0xDA4D4D, LazyIngredient.EMPTY);

    public static final Crop STONE = new Crop(new ResourceLocation(MOD_ID, "stone"), CropTier.ONE, CropType.RESOURCE, ROCK_CROP_TEXTURES, 0x7F7F7F, LazyIngredient.tag("forge:stone"));
    public static final Crop DIRT = new Crop(new ResourceLocation(MOD_ID, "dirt"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.item("minecraft:dirt"));
    public static final Crop WOOD = new Crop(new ResourceLocation(MOD_ID, "wood"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.tag("minecraft:logs"));
    public static final Crop ICE = new Crop(new ResourceLocation(MOD_ID, "ice"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.item("minecraft:ice"));
    public static final Crop ZOMBIE = new Crop(new ResourceLocation(MOD_ID, "zombie"), CropTier.ONE, CropType.MOB, LazyIngredient.EMPTY);

    public static final Crop NATURE = new Crop(new ResourceLocation(MOD_ID, "nature"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.EMPTY);
    public static final Crop DYE = new Crop(new ResourceLocation(MOD_ID, "dye"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.EMPTY);
    public static final Crop NETHER = new Crop(new ResourceLocation(MOD_ID, "nether"), CropTier.TWO, CropType.RESOURCE, ROCK_CROP_TEXTURES, 0x723232, LazyIngredient.EMPTY);
    public static final Crop COAL = new Crop(new ResourceLocation(MOD_ID, "coal"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("minecraft:coal"));
    public static final Crop PIG = new Crop(new ResourceLocation(MOD_ID, "pig"), CropTier.TWO, CropType.MOB, LazyIngredient.EMPTY);
    public static final Crop CHICKEN = new Crop(new ResourceLocation(MOD_ID, "chicken"), CropTier.TWO, CropType.MOB, LazyIngredient.EMPTY);
    public static final Crop COW = new Crop(new ResourceLocation(MOD_ID, "cow"), CropTier.TWO, CropType.MOB, LazyIngredient.EMPTY);
    public static final Crop SHEEP = new Crop(new ResourceLocation(MOD_ID, "sheep"), CropTier.TWO, CropType.MOB, LazyIngredient.EMPTY);
    public static final Crop SLIME = new Crop(new ResourceLocation(MOD_ID, "slime"), CropTier.TWO, CropType.MOB, LazyIngredient.EMPTY);

    public static final Crop IRON = new Crop(new ResourceLocation(MOD_ID, "iron"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/iron"));
    public static final Crop NETHER_QUARTZ = new Crop(new ResourceLocation(MOD_ID, "nether_quartz"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:gems/quartz"));
    public static final Crop GLOWSTONE = new Crop(new ResourceLocation(MOD_ID, "glowstone"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:dusts/glowstone"));
    public static final Crop REDSTONE = new Crop(new ResourceLocation(MOD_ID, "redstone"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:dusts/redstone"));
    public static final Crop OBSIDIAN = new Crop(new ResourceLocation(MOD_ID, "obsidian"), CropTier.THREE, CropType.RESOURCE, ROCK_CROP_TEXTURES, 0x271E3D, LazyIngredient.item("minecraft:obsidian"));
    public static final Crop PRISMARINE = new Crop(new ResourceLocation(MOD_ID, "prismarine"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.EMPTY);
    public static final Crop SKELETON = new Crop(new ResourceLocation(MOD_ID, "skeleton"), CropTier.THREE, CropType.MOB, LazyIngredient.EMPTY);
    public static final Crop CREEPER = new Crop(new ResourceLocation(MOD_ID, "creeper"), CropTier.THREE, CropType.MOB, LazyIngredient.EMPTY);
    public static final Crop SPIDER = new Crop(new ResourceLocation(MOD_ID, "spider"), CropTier.THREE, CropType.MOB, LazyIngredient.EMPTY);
    public static final Crop RABBIT = new Crop(new ResourceLocation(MOD_ID, "rabbit"), CropTier.THREE, CropType.MOB, LazyIngredient.EMPTY);

    public static final Crop GOLD = new Crop(new ResourceLocation(MOD_ID, "gold"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/gold"));
    public static final Crop LAPIS_LAZULI = new Crop(new ResourceLocation(MOD_ID, "lapis_lazuli"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:gems/lapis"));
    public static final Crop END = new Crop(new ResourceLocation(MOD_ID, "end"), CropTier.FOUR, CropType.RESOURCE, ROCK_CROP_TEXTURES, 0xEEF6B4, LazyIngredient.EMPTY);
    public static final Crop EXPERIENCE = new Crop(new ResourceLocation(MOD_ID, "experience"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.EMPTY);
    public static final Crop BLAZE = new Crop(new ResourceLocation(MOD_ID, "blaze"), CropTier.FOUR, CropType.MOB, LazyIngredient.EMPTY);
    public static final Crop GHAST = new Crop(new ResourceLocation(MOD_ID, "ghast"), CropTier.FOUR, CropType.MOB, LazyIngredient.EMPTY);
    public static final Crop ENDERMAN = new Crop(new ResourceLocation(MOD_ID, "enderman"), CropTier.FOUR, CropType.MOB, LazyIngredient.EMPTY);

    public static final Crop DIAMOND = new Crop(new ResourceLocation(MOD_ID, "diamond"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:gems/diamond"));
    public static final Crop EMERALD = new Crop(new ResourceLocation(MOD_ID, "emerald"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:gems/emerald"));
    public static final Crop WITHER_SKELETON = new Crop(new ResourceLocation(MOD_ID, "wither_skeleton"), CropTier.FIVE, CropType.MOB, LazyIngredient.EMPTY);

    @Override
    public void onRegisterCrops(ICropRegistry registry) {
        registry.register(AIR);
        registry.register(EARTH);
        registry.register(WATER);
        registry.register(FIRE);

        registry.register(STONE);
        registry.register(DIRT);
        registry.register(WOOD);
        registry.register(ICE);
        registry.register(ZOMBIE);

        registry.register(NATURE);
        registry.register(DYE);
        registry.register(NETHER);
        registry.register(COAL);
        registry.register(PIG);
        registry.register(CHICKEN);
        registry.register(COW);
        registry.register(SHEEP);
        registry.register(SLIME);

        registry.register(IRON);
        registry.register(NETHER_QUARTZ);
        registry.register(GLOWSTONE);
        registry.register(REDSTONE);
        registry.register(OBSIDIAN);
        registry.register(PRISMARINE);
        registry.register(SKELETON);
        registry.register(CREEPER);
        registry.register(SPIDER);
        registry.register(RABBIT);

        registry.register(GOLD);
        registry.register(LAPIS_LAZULI);
        registry.register(END);
        registry.register(EXPERIENCE);
        registry.register(BLAZE);
        registry.register(GHAST);
        registry.register(ENDERMAN);

        registry.register(DIAMOND);
        registry.register(EMERALD);
        registry.register(WITHER_SKELETON);
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
