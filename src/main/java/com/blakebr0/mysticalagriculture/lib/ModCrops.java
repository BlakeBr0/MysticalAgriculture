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
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.init.ModItems;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;

import java.util.Arrays;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.MOD_ID;

public final class ModCrops {
    private static final boolean DEBUG = false;

    public static final Crop AIR = new Crop(new ResourceLocation(MOD_ID, "air"), CropTier.ELEMENTAL, CropType.RESOURCE, CropTextures.ELEMENTAL_CROP_TEXTURES, 0xDAD64D, LazyIngredient.item("mysticalagriculture:air_agglomeratio"));
    public static final Crop EARTH = new Crop(new ResourceLocation(MOD_ID, "earth"), CropTier.ELEMENTAL, CropType.RESOURCE, CropTextures.ELEMENTAL_CROP_TEXTURES, 0x54DA4D, LazyIngredient.item("mysticalagriculture:earth_agglomeratio"));
    public static final Crop WATER = new Crop(new ResourceLocation(MOD_ID, "water"), CropTier.ELEMENTAL, CropType.RESOURCE, CropTextures.ELEMENTAL_CROP_TEXTURES, 0x4D7EDA, LazyIngredient.item("mysticalagriculture:water_agglomeratio"));
    public static final Crop FIRE = new Crop(new ResourceLocation(MOD_ID, "fire"), CropTier.ELEMENTAL, CropType.RESOURCE, CropTextures.ELEMENTAL_CROP_TEXTURES, 0xDA4D4D, LazyIngredient.item("mysticalagriculture:fire_agglomeratio"));

    public static final Crop INFERIUM = new Crop(new ResourceLocation(MOD_ID, "inferium"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.EMPTY);
    public static final Crop STONE = new Crop(new ResourceLocation(MOD_ID, "stone"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.tag("forge:stone"));
    public static final Crop DIRT = new Crop(new ResourceLocation(MOD_ID, "dirt"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.item("minecraft:dirt"));
    public static final Crop WOOD = new Crop(new ResourceLocation(MOD_ID, "wood"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.tag("minecraft:logs"));
    public static final Crop ICE = new Crop(new ResourceLocation(MOD_ID, "ice"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.item("minecraft:ice"));

    public static final Crop NATURE = new Crop(new ResourceLocation(MOD_ID, "nature"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:nature_agglomeratio"));
    public static final Crop DYE = new Crop(new ResourceLocation(MOD_ID, "dye"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:dye_agglomeratio"));
    public static final Crop NETHER = new Crop(new ResourceLocation(MOD_ID, "nether"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:nether_agglomeratio"));
    public static final Crop COAL = new Crop(new ResourceLocation(MOD_ID, "coal"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("minecraft:coal"));
    public static final Crop CORAL = new Crop(new ResourceLocation(MOD_ID, "coral"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:coral_agglomeratio"));
    public static final Crop HONEY = new Crop(new ResourceLocation(MOD_ID, "honey"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:honey_agglomeratio"));
    public static final Crop PIG = new Crop(new ResourceLocation(MOD_ID, "pig"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.PIG)));
    public static final Crop CHICKEN = new Crop(new ResourceLocation(MOD_ID, "chicken"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.CHICKEN)));
    public static final Crop COW = new Crop(new ResourceLocation(MOD_ID, "cow"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.COW)));
    public static final Crop SHEEP = new Crop(new ResourceLocation(MOD_ID, "sheep"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.SHEEP)));
    public static final Crop SQUID = new Crop(new ResourceLocation(MOD_ID, "squid"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.SQUID)));
    public static final Crop FISH = new Crop(new ResourceLocation(MOD_ID, "fish"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.FISH)));
    public static final Crop SLIME = new Crop(new ResourceLocation(MOD_ID, "slime"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.SLIME)));
    public static final Crop TURTLE = new Crop(new ResourceLocation(MOD_ID, "turtle"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.TURTLE)));

    public static final Crop IRON = new Crop(new ResourceLocation(MOD_ID, "iron"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/iron"));
    public static final Crop NETHER_QUARTZ = new Crop(new ResourceLocation(MOD_ID, "nether_quartz"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:gems/quartz"));
    public static final Crop GLOWSTONE = new Crop(new ResourceLocation(MOD_ID, "glowstone"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:dusts/glowstone"));
    public static final Crop REDSTONE = new Crop(new ResourceLocation(MOD_ID, "redstone"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:dusts/redstone"));
    public static final Crop OBSIDIAN = new Crop(new ResourceLocation(MOD_ID, "obsidian"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("minecraft:obsidian"));
    public static final Crop PRISMARINE = new Crop(new ResourceLocation(MOD_ID, "prismarine"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:prismarine_agglomeratio"));
    public static final Crop ZOMBIE = new Crop(new ResourceLocation(MOD_ID, "zombie"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.ZOMBIE)));
    public static final Crop SKELETON = new Crop(new ResourceLocation(MOD_ID, "skeleton"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.SKELETON)));
    public static final Crop CREEPER = new Crop(new ResourceLocation(MOD_ID, "creeper"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.CREEPER)));
    public static final Crop SPIDER = new Crop(new ResourceLocation(MOD_ID, "spider"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.SPIDER)));
    public static final Crop RABBIT = new Crop(new ResourceLocation(MOD_ID, "rabbit"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.RABBIT)));

    public static final Crop GOLD = new Crop(new ResourceLocation(MOD_ID, "gold"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/gold"));
    public static final Crop LAPIS_LAZULI = new Crop(new ResourceLocation(MOD_ID, "lapis_lazuli"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:gems/lapis"));
    public static final Crop END = new Crop(new ResourceLocation(MOD_ID, "end"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:end_agglomeratio"));
    public static final Crop EXPERIENCE = new Crop(new ResourceLocation(MOD_ID, "experience"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:experience_capsule", ExperienceCapsuleUtils.makeTag(ExperienceCapsuleUtils.MAX_XP_POINTS)));
    public static final Crop BLAZE = new Crop(new ResourceLocation(MOD_ID, "blaze"), CropTier.FOUR, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.BLAZE)));
    public static final Crop GHAST = new Crop(new ResourceLocation(MOD_ID, "ghast"), CropTier.FOUR, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.GHAST)));
    public static final Crop ENDERMAN = new Crop(new ResourceLocation(MOD_ID, "enderman"), CropTier.FOUR, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.ENDERMAN)));

    public static final Crop DIAMOND = new Crop(new ResourceLocation(MOD_ID, "diamond"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:gems/diamond"));
    public static final Crop EMERALD = new Crop(new ResourceLocation(MOD_ID, "emerald"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:gems/emerald"));
    public static final Crop NETHERITE = new Crop(new ResourceLocation(MOD_ID, "netherite"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("minecraft:netherite_ingot"));
    public static final Crop WITHER_SKELETON = new Crop(new ResourceLocation(MOD_ID, "wither_skeleton"), CropTier.FIVE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.WITHER)));

    // COMMON
    public static final Crop RUBBER = new Crop(new ResourceLocation(MOD_ID, "rubber"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("forge:rubber"));
    public static final Crop SILICON = new Crop(new ResourceLocation(MOD_ID, "silicon"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("forge:silicon"));
    public static final Crop SULFUR = new Crop(new ResourceLocation(MOD_ID, "sulfur"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("forge:dusts/sulfur"));
    public static final Crop ALUMINUM = new Crop(new ResourceLocation(MOD_ID, "aluminum"), CropTier.TWO, CropType.RESOURCE, CropTextures.INGOT_CROP_TEXTURES, 0xA4A6B1, LazyIngredient.tag("forge:ingots/aluminum"));
    public static final Crop COPPER = new Crop(new ResourceLocation(MOD_ID, "copper"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/copper"));
    public static final Crop SALTPETER = new Crop(new ResourceLocation(MOD_ID, "saltpeter"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("forge:dusts/saltpeter"));
    public static final Crop TIN = new Crop(new ResourceLocation(MOD_ID, "tin"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/tin"));
    public static final Crop BRONZE = new Crop(new ResourceLocation(MOD_ID, "bronze"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/bronze"));
    public static final Crop ZINC = new Crop(new ResourceLocation(MOD_ID, "zinc"), CropTier.THREE, CropType.RESOURCE, CropTextures.INGOT_CROP_TEXTURES, 0xE9EBE7, LazyIngredient.tag("forge:ingots/zinc"));
    public static final Crop BRASS = new Crop(new ResourceLocation(MOD_ID, "brass"), CropTier.THREE, CropType.RESOURCE, CropTextures.INGOT_CROP_TEXTURES, 0xDAAA4C, LazyIngredient.tag("forge:ingots/brass"));
    public static final Crop SILVER = new Crop(new ResourceLocation(MOD_ID, "silver"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/silver"));
    public static final Crop LEAD = new Crop(new ResourceLocation(MOD_ID, "lead"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/lead"));
    public static final Crop GRAPHITE = new Crop(new ResourceLocation(MOD_ID, "graphite"), CropTier.THREE, CropType.RESOURCE, CropTextures.INGOT_CROP_TEXTURES, 0x545454, LazyIngredient.tag("forge:ingots/graphite"));
    public static final Crop STEEL = new Crop(new ResourceLocation(MOD_ID, "steel"), CropTier.FOUR, CropType.RESOURCE, CropTextures.INGOT_CROP_TEXTURES, 0x737373, LazyIngredient.tag("forge:ingots/steel"));
    public static final Crop NICKEL = new Crop(new ResourceLocation(MOD_ID, "nickel"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/nickel"));
    public static final Crop CONSTANTAN = new Crop(new ResourceLocation(MOD_ID, "constantan"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/constantan"));
    public static final Crop ELECTRUM = new Crop(new ResourceLocation(MOD_ID, "electrum"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/electrum"));
    public static final Crop INVAR = new Crop(new ResourceLocation(MOD_ID, "invar"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/invar"));
    public static final Crop MITHRIL = new Crop(new ResourceLocation(MOD_ID, "mithril"), CropTier.FOUR, CropType.RESOURCE, CropTextures.INGOT_CROP_TEXTURES, 0x659ABB, LazyIngredient.tag("forge:ingots/mithril"));
    public static final Crop TUNGSTEN = new Crop(new ResourceLocation(MOD_ID, "tungsten"), CropTier.FOUR, CropType.RESOURCE, CropTextures.INGOT_CROP_TEXTURES, 0x616669, LazyIngredient.tag("forge:ingots/tungsten"));
    public static final Crop TITANIUM = new Crop(new ResourceLocation(MOD_ID, "titanium"), CropTier.FOUR, CropType.RESOURCE, CropTextures.INGOT_CROP_TEXTURES, 0xD0D1DA, LazyIngredient.tag("forge:ingots/titanium"));
    public static final Crop URANIUM = new Crop(new ResourceLocation(MOD_ID, "uranium"), CropTier.FOUR, CropType.RESOURCE, CropTextures.INGOT_CROP_TEXTURES, 0x60AE11, LazyIngredient.tag("forge:ingots/uranium"));
    public static final Crop CHROME = new Crop(new ResourceLocation(MOD_ID, "chrome"), CropTier.FOUR, CropType.RESOURCE, CropTextures.INGOT_CROP_TEXTURES, 0xCDB9BD, LazyIngredient.tag("forge:ingots/chrome"));
    public static final Crop PLATINUM = new Crop(new ResourceLocation(MOD_ID, "platinum"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/platinum"));
    public static final Crop IRIDIUM = new Crop(new ResourceLocation(MOD_ID, "iridium"), CropTier.FIVE, CropType.RESOURCE, CropTextures.INGOT_CROP_TEXTURES, 0xC7C5DC, LazyIngredient.tag("forge:ingots/iridium"));

    // GEMS
    public static final Crop APATITE = new Crop(new ResourceLocation(MOD_ID, "apatite"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("forge:gems/apatite"));
    public static final Crop RUBY = new Crop(new ResourceLocation(MOD_ID, "ruby"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:gems/ruby"));
    public static final Crop SAPPHIRE = new Crop(new ResourceLocation(MOD_ID, "sapphire"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:gems/sapphire"));

    // THERMAL SERIES
    public static final Crop BLIZZ = new Crop(new ResourceLocation(MOD_ID, "blizz"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.BLIZZ)));
    public static final Crop BLITZ = new Crop(new ResourceLocation(MOD_ID, "blitz"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.BLITZ)));
    public static final Crop BASALZ = new Crop(new ResourceLocation(MOD_ID, "basalz"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ModMobSoulTypes.BASALZ)));
    public static final Crop SIGNALUM = new Crop(new ResourceLocation(MOD_ID, "signalum"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/signalum"));
    public static final Crop LUMIUM = new Crop(new ResourceLocation(MOD_ID, "lumium"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/lumium"));
    public static final Crop ENDERIUM = new Crop(new ResourceLocation(MOD_ID, "enderium"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/enderium"));

    // IMMERSIVE ENGINEERING
    public static final Crop HOP_GRAPHITE = new Crop(new ResourceLocation(MOD_ID, "hop_graphite"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/hop_graphite"));

    // TINKERS' CONSTRUCT
    public static final Crop TINKERS_BRONZE = new Crop(new ResourceLocation(MOD_ID, "tinkers_bronze"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/silicon_bronze"));
    public static final Crop SLIMESTEEL = new Crop(new ResourceLocation(MOD_ID, "slimesteel"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/slimesteel"));
    public static final Crop PIG_IRON = new Crop(new ResourceLocation(MOD_ID, "pig_iron"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/pig_iron"));
    public static final Crop COBALT = new Crop(new ResourceLocation(MOD_ID, "cobalt"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/cobalt"));
    public static final Crop ROSE_GOLD = new Crop(new ResourceLocation(MOD_ID, "rose_gold"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/rose_gold"));
    public static final Crop MANYULLYN = new Crop(new ResourceLocation(MOD_ID, "manyullyn"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/manyullyn"));
    public static final Crop QUEENS_SLIME = new Crop(new ResourceLocation(MOD_ID, "queens_slime"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/queens_slime"));
    public static final Crop HEPATIZON = new Crop(new ResourceLocation(MOD_ID, "hepatizon"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/hepatizon"));

    // BOTANIA
    public static final Crop MYSTICAL_FLOWER = new Crop(new ResourceLocation(MOD_ID, "mystical_flower"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:mystical_flower_agglomeratio"));
    public static final Crop MANASTEEL = new Crop(new ResourceLocation(MOD_ID, "manasteel"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("botania:manasteel_ingot"));
    public static final Crop ELEMENTIUM = new Crop(new ResourceLocation(MOD_ID, "elementium"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("botania:elementium_ingot"));
    public static final Crop TERRASTEEL = new Crop(new ResourceLocation(MOD_ID, "terrasteel"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("botania:terrasteel_ingot"));

    // MEKANISM
    public static final Crop OSMIUM = new Crop(new ResourceLocation(MOD_ID, "osmium"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/osmium"));
    public static final Crop FLUORITE = new Crop(new ResourceLocation(MOD_ID, "fluorite"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:gems/fluorite"));
    public static final Crop REFINED_GLOWSTONE = new Crop(new ResourceLocation(MOD_ID, "refined_glowstone"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/refined_glowstone"));
    public static final Crop REFINED_OBSIDIAN = new Crop(new ResourceLocation(MOD_ID, "refined_obsidian"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/refined_obsidian"));

    // CHISEL
    public static final Crop MARBLE = new Crop(new ResourceLocation(MOD_ID, "marble"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("chisel:marble"));
    public static final Crop LIMESTONE = new Crop(new ResourceLocation(MOD_ID, "limestone"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("chisel:limestone"));
    public static final Crop BASALT = new Crop(new ResourceLocation(MOD_ID, "basalt"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("chisel:basalt"));

    // TWILIGHT FOREST
    public static final Crop STEELEAF = new Crop(new ResourceLocation(MOD_ID, "steeleaf"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("twilightforest:steeleaf_ingot"));
    public static final Crop IRONWOOD = new Crop(new ResourceLocation(MOD_ID, "ironwood"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("twilightforest:ironwood_ingot"));
    public static final Crop KNIGHTMETAL = new Crop(new ResourceLocation(MOD_ID, "knightmetal"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("twilightforest:knightmetal_ingot"));
    public static final Crop FIERY_INGOT = new Crop(new ResourceLocation(MOD_ID, "fiery_ingot"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("twilightforest:fiery_ingot"));

    // QUARK
    public static final Crop ENDER_BIOTITE = new Crop(new ResourceLocation(MOD_ID, "ender_biotite"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("quark:biotite"));

    // ASTRAL SORCERY
    public static final Crop AQUAMARINE = new Crop(new ResourceLocation(MOD_ID, "aquamarine"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("astralsorcery:aquamarine"));
    public static final Crop STARMETAL = new Crop(new ResourceLocation(MOD_ID, "starmetal"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("astralsorcery:starmetal_ingot"));
    public static final Crop ROCK_CRYSTAL = new Crop(new ResourceLocation(MOD_ID, "rock_crystal"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("astralsorcery:rock_crystal"));

    // PNEUMATICCRAFT
    public static final Crop COMPRESSED_IRON = new Crop(new ResourceLocation(MOD_ID, "compressed_iron"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/compressed_iron"));

    // EXTREME REACTORS 2
    public static final Crop YELLORIUM = new Crop(new ResourceLocation(MOD_ID, "yellorium"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/yellorium"));
    public static final Crop CYANITE = new Crop(new ResourceLocation(MOD_ID, "cyanite"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/cyanite"));

    // APPLIED ENERGISTICS 2
    public static final Crop SKY_STONE = new Crop(new ResourceLocation(MOD_ID, "sky_stone"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("appliedenergistics2:sky_stone_block"));
    public static final Crop CERTUS_QUARTZ = new Crop(new ResourceLocation(MOD_ID, "certus_quartz"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("appliedenergistics2:crystals/certus_quartz"));
    public static final Crop FLUIX = new Crop(new ResourceLocation(MOD_ID, "fluix"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("appliedenergistics2:crystals/fluix"));

    // REFINED STORAGE
    public static final Crop QUARTZ_ENRICHED_IRON = new Crop(new ResourceLocation(MOD_ID, "quartz_enriched_iron"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("refinedstorage:quartz_enriched_iron"));

    // POWAH
    public static final Crop ENERGIZED_STEEL = new Crop(new ResourceLocation(MOD_ID, "energized_steel"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("powah:steel_energized"));
    public static final Crop BLAZING_CRYSTAL = new Crop(new ResourceLocation(MOD_ID, "blazing_crystal"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("powah:crystal_blazing"));
    public static final Crop NIOTIC_CRYSTAL = new Crop(new ResourceLocation(MOD_ID, "niotic_crystal"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("powah:crystal_niotic"));
    public static final Crop SPIRITED_CRYSTAL = new Crop(new ResourceLocation(MOD_ID, "spirited_crystal"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("powah:crystal_spirited"));
    public static final Crop URANINITE = new Crop(new ResourceLocation(MOD_ID, "uraninite"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("powah:uraninite"));

    public static void onRegisterCrops(ICropRegistry registry) {
        INFERIUM.setCrop(() -> (CropBlock) ModBlocks.INFERIUM_CROP.get())
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
        registry.register(NETHERITE);
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

        // GEMS
        registry.register(APATITE);
        registry.register(RUBY);
        registry.register(SAPPHIRE);

        // THERMAL SERIES
        registry.register(withRequiredMods(BLIZZ, "thermal"));
        registry.register(withRequiredMods(BLITZ, "thermal"));
        registry.register(withRequiredMods(BASALZ, "thermal"));
        registry.register(withRequiredMods(SIGNALUM, "thermal"));
        registry.register(withRequiredMods(LUMIUM, "thermal"));
        registry.register(withRequiredMods(ENDERIUM, "thermal"));

        // IMMERSIVE ENGINEERING
        registry.register(withRequiredMods(HOP_GRAPHITE, "immersiveengineering"));

        // TINKERS' CONSTRUCT
        registry.register(withRequiredMods(TINKERS_BRONZE, "tconstruct"));
        registry.register(withRequiredMods(SLIMESTEEL, "tconstruct"));
        registry.register(withRequiredMods(PIG_IRON, "tconstruct"));
        registry.register(withRequiredMods(COBALT, "tconstruct"));
        registry.register(withRequiredMods(ROSE_GOLD, "tconstruct"));
        registry.register(withRequiredMods(MANYULLYN, "tconstruct"));
        registry.register(withRequiredMods(QUEENS_SLIME, "tconstruct"));
        registry.register(withRequiredMods(HEPATIZON, "tconstruct"));

        // BOTANIA
        registry.register(withRequiredMods(MYSTICAL_FLOWER, "botania"));
        registry.register(withRequiredMods(MANASTEEL, "botania"));
        registry.register(withRequiredMods(ELEMENTIUM, "botania"));
        registry.register(withRequiredMods(TERRASTEEL, "botania"));

        // MEKANISM
        registry.register(withRequiredMods(OSMIUM, "mekanism"));
        registry.register(withRequiredMods(FLUORITE, "mekanism"));
        registry.register(withRequiredMods(REFINED_GLOWSTONE, "mekanism"));
        registry.register(withRequiredMods(REFINED_OBSIDIAN, "mekanism"));

        // CHISEL
        registry.register(withRequiredMods(MARBLE, "chisel", "quark", "astralsorcery"));
        registry.register(withRequiredMods(LIMESTONE, "chisel"));
        registry.register(withRequiredMods(BASALT, "chisel"));

        // TWILIGHT FOREST
        registry.register(withRequiredMods(STEELEAF, "twilightforest"));
        registry.register(withRequiredMods(IRONWOOD, "twilightforest"));
        registry.register(withRequiredMods(KNIGHTMETAL, "twilightforest"));
        registry.register(withRequiredMods(FIERY_INGOT, "twilightforest"));

        // QUARK
        registry.register(withRequiredMods(ENDER_BIOTITE, "quark"));

        // ASTRAL SORCERY
        registry.register(withRequiredMods(AQUAMARINE, "astralsorcery"));
        registry.register(withRequiredMods(STARMETAL, "astralsorcery"));
        registry.register(withRequiredMods(ROCK_CRYSTAL, "astralsorcery"));

        // PNEUMATICCRAFT
        registry.register(withRequiredMods(COMPRESSED_IRON, "pneumaticcraft"));

        // EXTREME REACTORS 2
        registry.register(withRequiredMods(YELLORIUM, "bigreactors"));
        registry.register(withRequiredMods(CYANITE, "bigreactors"));

        // APPLIED ENERGISTICS 2
        registry.register(withRequiredMods(SKY_STONE, "appliedenergistics2"));
        registry.register(withRequiredMods(CERTUS_QUARTZ, "appliedenergistics2"));
        registry.register(withRequiredMods(FLUIX, "appliedenergistics2"));

        // REFINED STORAGE
        registry.register(withRequiredMods(QUARTZ_ENRICHED_IRON, "refinedstorage"));

        // POWAH
        registry.register(withRequiredMods(ENERGIZED_STEEL, "powah"));
        registry.register(withRequiredMods(BLAZING_CRYSTAL, "powah"));
        registry.register(withRequiredMods(NIOTIC_CRYSTAL, "powah"));
        registry.register(withRequiredMods(SPIRITED_CRYSTAL, "powah"));
        registry.register(withRequiredMods(URANINITE, "powah"));
    }

    private static ICrop withRequiredMods(ICrop crop, String... mods) {
        if (DEBUG) return crop;
        
        boolean enabled = Arrays.stream(mods).anyMatch(ModList.get()::isLoaded);
        return crop.setEnabled(enabled);
    }
}
