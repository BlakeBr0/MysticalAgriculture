package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.blakebr0.mysticalagriculture.api.util.ExperienceCapsuleUtils;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.item.ModItems;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;

import java.util.Arrays;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.MOD_ID;

public class ModCrops {
    private static final CropTextures ELEMENTAL_CROP_TEXTURES = new CropTextures(CropTextures.FLOWER_INGOT_BLANK, CropTextures.ESSENCE_FLAME_BLANK);
    private static final CropTextures ROCK_CROP_TEXTURES = new CropTextures(CropTextures.FLOWER_ROCK_BLANK, CropTextures.ESSENCE_ROCK_BLANK);
    private static final CropTextures INGOT_CROP_TEXTURES = new CropTextures(CropTextures.FLOWER_INGOT_BLANK, CropTextures.ESSENCE_INGOT_BLANK);
    private static final CropTextures DUST_CROP_TEXTURES = new CropTextures(CropTextures.FLOWER_DUST_BLANK, CropTextures.ESSENCE_DUST_BLANK);

    public static final Crop AIR = new Crop(new ResourceLocation(MOD_ID, "air"), CropTier.ELEMENTAL, CropType.RESOURCE, ELEMENTAL_CROP_TEXTURES, 0xDAD64D, LazyIngredient.item("mysticalagriculture:air_agglomeratio"));
    public static final Crop EARTH = new Crop(new ResourceLocation(MOD_ID, "earth"), CropTier.ELEMENTAL, CropType.RESOURCE, ELEMENTAL_CROP_TEXTURES, 0x54DA4D, LazyIngredient.item("mysticalagriculture:earth_agglomeratio"));
    public static final Crop WATER = new Crop(new ResourceLocation(MOD_ID, "water"), CropTier.ELEMENTAL, CropType.RESOURCE, ELEMENTAL_CROP_TEXTURES, 0x4D7EDA, LazyIngredient.item("mysticalagriculture:water_agglomeratio"));
    public static final Crop FIRE = new Crop(new ResourceLocation(MOD_ID, "fire"), CropTier.ELEMENTAL, CropType.RESOURCE, ELEMENTAL_CROP_TEXTURES, 0xDA4D4D, LazyIngredient.item("mysticalagriculture:fire_agglomeratio"));

    public static final Crop INFERIUM = new Crop(new ResourceLocation(MOD_ID, "inferium"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.EMPTY);
    public static final Crop STONE = new Crop(new ResourceLocation(MOD_ID, "stone"), CropTier.ONE, CropType.RESOURCE, ROCK_CROP_TEXTURES, 0x7F7F7F, LazyIngredient.tag("forge:stone"));
    public static final Crop DIRT = new Crop(new ResourceLocation(MOD_ID, "dirt"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.item("minecraft:dirt"));
    public static final Crop WOOD = new Crop(new ResourceLocation(MOD_ID, "wood"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.tag("minecraft:logs"));
    public static final Crop ICE = new Crop(new ResourceLocation(MOD_ID, "ice"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.item("minecraft:ice"));

    public static final Crop NATURE = new Crop(new ResourceLocation(MOD_ID, "nature"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:nature_agglomeratio"));
    public static final Crop DYE = new Crop(new ResourceLocation(MOD_ID, "dye"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:dye_agglomeratio"));
    public static final Crop NETHER = new Crop(new ResourceLocation(MOD_ID, "nether"), CropTier.TWO, CropType.RESOURCE, ROCK_CROP_TEXTURES, 0x723232, LazyIngredient.item("mysticalagriculture:nether_agglomeratio"));
    public static final Crop COAL = new Crop(new ResourceLocation(MOD_ID, "coal"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("minecraft:coal"));
    public static final Crop CORAL = new Crop(new ResourceLocation(MOD_ID, "coral"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:coral_agglomeratio"));
    public static final Crop HONEY = new Crop(new ResourceLocation(MOD_ID, "honey"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:honey_agglomeratio"));
    public static final Crop PIG = new Crop(new ResourceLocation(MOD_ID, "pig"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.PIG_SOUL_TYPE)));
    public static final Crop CHICKEN = new Crop(new ResourceLocation(MOD_ID, "chicken"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.CHICKEN_SOUL_TYPE)));
    public static final Crop COW = new Crop(new ResourceLocation(MOD_ID, "cow"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.COW_SOUL_TYPE)));
    public static final Crop SHEEP = new Crop(new ResourceLocation(MOD_ID, "sheep"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.SHEEP_SOUL_TYPE)));
    public static final Crop SQUID = new Crop(new ResourceLocation(MOD_ID, "squid"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.SQUID_SOUL_TYPE)));
    public static final Crop FISH = new Crop(new ResourceLocation(MOD_ID, "fish"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.FISH_SOUL_TYPE)));
    public static final Crop SLIME = new Crop(new ResourceLocation(MOD_ID, "slime"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.SLIME_SOUL_TYPE)));
    public static final Crop TURTLE = new Crop(new ResourceLocation(MOD_ID, "turtle"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.TURTLE_SOUL_TYPE)));

    public static final Crop IRON = new Crop(new ResourceLocation(MOD_ID, "iron"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/iron"));
    public static final Crop NETHER_QUARTZ = new Crop(new ResourceLocation(MOD_ID, "nether_quartz"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:gems/quartz"));
    public static final Crop GLOWSTONE = new Crop(new ResourceLocation(MOD_ID, "glowstone"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:dusts/glowstone"));
    public static final Crop REDSTONE = new Crop(new ResourceLocation(MOD_ID, "redstone"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:dusts/redstone"));
    public static final Crop OBSIDIAN = new Crop(new ResourceLocation(MOD_ID, "obsidian"), CropTier.THREE, CropType.RESOURCE, ROCK_CROP_TEXTURES, 0x271E3D, LazyIngredient.item("minecraft:obsidian"));
    public static final Crop PRISMARINE = new Crop(new ResourceLocation(MOD_ID, "prismarine"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:prismarine_agglomeratio"));
    public static final Crop ZOMBIE = new Crop(new ResourceLocation(MOD_ID, "zombie"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.ZOMBIE_SOUL_TYPE)));
    public static final Crop SKELETON = new Crop(new ResourceLocation(MOD_ID, "skeleton"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.SKELETON_SOUL_TYPE)));
    public static final Crop CREEPER = new Crop(new ResourceLocation(MOD_ID, "creeper"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.CREEPER_SOUL_TYPE)));
    public static final Crop SPIDER = new Crop(new ResourceLocation(MOD_ID, "spider"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.SPIDER_SOUL_TYPE)));
    public static final Crop RABBIT = new Crop(new ResourceLocation(MOD_ID, "rabbit"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.RABBIT_SOUL_TYPE)));

    public static final Crop GOLD = new Crop(new ResourceLocation(MOD_ID, "gold"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/gold"));
    public static final Crop LAPIS_LAZULI = new Crop(new ResourceLocation(MOD_ID, "lapis_lazuli"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:gems/lapis"));
    public static final Crop END = new Crop(new ResourceLocation(MOD_ID, "end"), CropTier.FOUR, CropType.RESOURCE, ROCK_CROP_TEXTURES, 0xEEF6B4, LazyIngredient.item("mysticalagriculture:end_agglomeratio"));
    public static final Crop EXPERIENCE = new Crop(new ResourceLocation(MOD_ID, "experience"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:experience_capsule", ExperienceCapsuleUtils.makeTag(ExperienceCapsuleUtils.MAX_XP_POINTS)));
    public static final Crop BLAZE = new Crop(new ResourceLocation(MOD_ID, "blaze"), CropTier.FOUR, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.BLAZE_SOUL_TYPE)));
    public static final Crop GHAST = new Crop(new ResourceLocation(MOD_ID, "ghast"), CropTier.FOUR, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.GHAST_SOUL_TYPE)));
    public static final Crop ENDERMAN = new Crop(new ResourceLocation(MOD_ID, "enderman"), CropTier.FOUR, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.ENDERMAN_SOUL_TYPE)));

    public static final Crop DIAMOND = new Crop(new ResourceLocation(MOD_ID, "diamond"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:gems/diamond"));
    public static final Crop EMERALD = new Crop(new ResourceLocation(MOD_ID, "emerald"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:gems/emerald"));
    public static final Crop WITHER_SKELETON = new Crop(new ResourceLocation(MOD_ID, "wither_skeleton"), CropTier.FIVE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.WITHER_SKELETON_SOUL_TYPE)));

    // COMMON
    public static final Crop RUBBER = new Crop(new ResourceLocation(MOD_ID, "rubber"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("forge:rubber"));
    public static final Crop SILICON = new Crop(new ResourceLocation(MOD_ID, "silicon"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("forge:silicon"));
    public static final Crop SULFUR = new Crop(new ResourceLocation(MOD_ID, "sulfur"), CropTier.TWO, CropType.RESOURCE, DUST_CROP_TEXTURES, 0xFDDC4B, LazyIngredient.tag("forge:dusts/sulfur"));
    public static final Crop ALUMINUM = new Crop(new ResourceLocation(MOD_ID, "aluminum"), CropTier.TWO, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0xA4A6B1, LazyIngredient.tag("forge:ingots/aluminum"));
    public static final Crop COPPER = new Crop(new ResourceLocation(MOD_ID, "copper"), CropTier.TWO, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0xF48702, LazyIngredient.tag("forge:ingots/copper"));
    public static final Crop SALTPETER = new Crop(new ResourceLocation(MOD_ID, "saltpeter"), CropTier.TWO, CropType.RESOURCE, DUST_CROP_TEXTURES, 0xC9BEBD, LazyIngredient.tag("forge:dusts/saltpeter"));
    public static final Crop TIN = new Crop(new ResourceLocation(MOD_ID, "tin"), CropTier.THREE, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0x9ABDD6, LazyIngredient.tag("forge:ingots/tin"));
    public static final Crop BRONZE = new Crop(new ResourceLocation(MOD_ID, "bronze"), CropTier.THREE, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0xC98C52, LazyIngredient.tag("forge:ingots/bronze"));
    public static final Crop ZINC = new Crop(new ResourceLocation(MOD_ID, "zinc"), CropTier.THREE, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0xE9EBE7, LazyIngredient.tag("forge:ingots/zinc"));
    public static final Crop BRASS = new Crop(new ResourceLocation(MOD_ID, "brass"), CropTier.THREE, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0xDAAA4C, LazyIngredient.tag("forge:ingots/brass"));
    public static final Crop SILVER = new Crop(new ResourceLocation(MOD_ID, "silver"), CropTier.THREE, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0xA9DBE5, LazyIngredient.tag("forge:ingots/silver"));
    public static final Crop LEAD = new Crop(new ResourceLocation(MOD_ID, "lead"), CropTier.THREE, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0x677193, LazyIngredient.tag("forge:ingots/lead"));
    public static final Crop GRAPHITE = new Crop(new ResourceLocation(MOD_ID, "graphite"), CropTier.THREE, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0x545454, LazyIngredient.tag("forge:ingots/graphite"));
    public static final Crop STEEL = new Crop(new ResourceLocation(MOD_ID, "steel"), CropTier.FOUR, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0x737373, LazyIngredient.tag("forge:ingots/steel"));
    public static final Crop NICKEL = new Crop(new ResourceLocation(MOD_ID, "nickel"), CropTier.FOUR, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0xD8CC93, LazyIngredient.tag("forge:ingots/nickel"));
    public static final Crop CONSTANTAN = new Crop(new ResourceLocation(MOD_ID, "constantan"), CropTier.FOUR, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0xD6A04A, LazyIngredient.tag("forge:ingots/constantan"));
    public static final Crop ELECTRUM = new Crop(new ResourceLocation(MOD_ID, "electrum"), CropTier.FOUR, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0xD5BB4F, LazyIngredient.tag("forge:ingots/electrum"));
    public static final Crop INVAR = new Crop(new ResourceLocation(MOD_ID, "invar"), CropTier.FOUR, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0xADB7B2, LazyIngredient.tag("forge:ingots/invar"));
    public static final Crop MITHRIL = new Crop(new ResourceLocation(MOD_ID, "mithril"), CropTier.FOUR, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0x659ABB, LazyIngredient.tag("forge:ingots/mithril"));
    public static final Crop TUNGSTEN = new Crop(new ResourceLocation(MOD_ID, "tungsten"), CropTier.FOUR, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0x616669, LazyIngredient.tag("forge:ingots/tungsten"));
    public static final Crop TITANIUM = new Crop(new ResourceLocation(MOD_ID, "titanium"), CropTier.FOUR, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0xD0D1DA, LazyIngredient.tag("forge:ingots/titanium"));
    public static final Crop URANIUM = new Crop(new ResourceLocation(MOD_ID, "uranium"), CropTier.FOUR, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0x60AE11, LazyIngredient.tag("forge:ingots/uranium"));
    public static final Crop CHROME = new Crop(new ResourceLocation(MOD_ID, "chrome"), CropTier.FOUR, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0xCDB9BD, LazyIngredient.tag("forge:ingots/chrome"));
    public static final Crop PLATINUM = new Crop(new ResourceLocation(MOD_ID, "platinum"), CropTier.TWO, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0x6FEAEF, LazyIngredient.tag("forge:ingots/platinum"));
    public static final Crop IRIDIUM = new Crop(new ResourceLocation(MOD_ID, "iridium"), CropTier.TWO, CropType.RESOURCE, INGOT_CROP_TEXTURES, 0xC7C5DC, LazyIngredient.tag("forge:ingots/iridium"));

    // BOTANIA
    public static final Crop MYSTICAL_FLOWER = new Crop(new ResourceLocation(MOD_ID, "mystical_flower"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:mystical_flower_agglomeratio"));
    public static final Crop MANASTEEL = new Crop(new ResourceLocation(MOD_ID, "manasteel"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("botania:manasteel_ingot"));
    public static final Crop ELEMENTIUM = new Crop(new ResourceLocation(MOD_ID, "elementium"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("botania:elementium_ingot"));
    public static final Crop TERRASTEEL = new Crop(new ResourceLocation(MOD_ID, "terrasteel"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("botania:terrasteel_ingot"));

    // MEKANISM
    public static final Crop OSMIUM = new Crop(new ResourceLocation(MOD_ID, "osmium"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/osmium"));
    public static final Crop REFINED_GLOWSTONE = new Crop(new ResourceLocation(MOD_ID, "refined_glowstone"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/refined_glowstone"));
    public static final Crop REFINED_OBSIDIAN = new Crop(new ResourceLocation(MOD_ID, "refined_obsidian"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/refined_obsidian"));

    // QUARK
    public static final Crop ENDER_BIOTITE = new Crop(new ResourceLocation(MOD_ID, "ender_biotite"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("quark:biotite"));

    // REFINED STORAGE
    public static final Crop QUARTZ_ENRICHED_IRON = new Crop(new ResourceLocation(MOD_ID, "quartz_enriched_iron"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("refinedstorage:quartz_enriched_iron"));

    public static void onRegisterCrops(ICropRegistry registry) {
        INFERIUM.setCrop(ModBlocks.INFERIUM_CROP)
                .setEssence(ModItems.INFERIUM_ESSENCE);

        registry.register(AIR);
        registry.register(EARTH);
        registry.register(WATER);
        registry.register(FIRE);

        registry.register(INFERIUM);
        registry.register(STONE);
        registry.register(DIRT);
        registry.register(WOOD);
        registry.register(ICE);

        registry.register(NATURE);
        registry.register(DYE);
        registry.register(NETHER);
        registry.register(COAL);
        registry.register(CORAL);
        registry.register(HONEY);
        registry.register(PIG);
        registry.register(CHICKEN);
        registry.register(COW);
        registry.register(SHEEP);
        registry.register(SQUID);
        registry.register(FISH);
        registry.register(SLIME);
        registry.register(TURTLE);

        registry.register(IRON);
        registry.register(NETHER_QUARTZ);
        registry.register(GLOWSTONE);
        registry.register(REDSTONE);
        registry.register(OBSIDIAN);
        registry.register(PRISMARINE);
        registry.register(ZOMBIE);
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

        // COMMON
        registry.register(RUBBER);
        registry.register(SILICON);
        registry.register(SULFUR);
        registry.register(ALUMINUM);
        registry.register(COPPER);
        registry.register(SALTPETER);
        registry.register(TIN);
        registry.register(BRONZE);
        registry.register(ZINC);
        registry.register(BRASS);
        registry.register(SILVER);
        registry.register(LEAD);
        registry.register(GRAPHITE);
        registry.register(STEEL);
        registry.register(NICKEL);
        registry.register(CONSTANTAN);
        registry.register(ELECTRUM);
        registry.register(INVAR);
        registry.register(MITHRIL);
        registry.register(TUNGSTEN);
        registry.register(TITANIUM);
        registry.register(URANIUM);
        registry.register(CHROME);
        registry.register(PLATINUM);
        registry.register(IRIDIUM);

        // BOTANIA
        registry.register(withRequiredMods(MYSTICAL_FLOWER, "botania"));
        registry.register(withRequiredMods(MANASTEEL, "botania"));
        registry.register(withRequiredMods(ELEMENTIUM, "botania"));
        registry.register(withRequiredMods(TERRASTEEL, "botania"));

        // MEKANISM
        registry.register(withRequiredMods(OSMIUM, "mekanism"));
        registry.register(withRequiredMods(REFINED_GLOWSTONE, "mekanism"));
        registry.register(withRequiredMods(REFINED_OBSIDIAN, "mekanism"));

        // QUARK
        registry.register(withRequiredMods(ENDER_BIOTITE, "quark"));

        // REFINED STORAGE
        registry.register(withRequiredMods(QUARTZ_ENRICHED_IRON, "refinedstorage"));
    }

    private static ICrop withRequiredMods(ICrop crop, String... mods) {
        // if (true) return crop;

        boolean enabled = Arrays.stream(mods).anyMatch(ModList.get()::isLoaded);
        return crop.setEnabled(enabled);
    }
}
