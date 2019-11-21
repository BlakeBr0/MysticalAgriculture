package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.blakebr0.mysticalagriculture.api.registry.IAugmentRegistry;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.blakebr0.mysticalagriculture.api.registry.IMobSoulTypeRegistry;
import com.blakebr0.mysticalagriculture.api.soul.MobSoulType;
import com.blakebr0.mysticalagriculture.api.util.ExperienceCapsuleUtils;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.augment.ModAugments;
import com.blakebr0.mysticalagriculture.block.InferiumCropBlock;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.item.ModItems;
import com.google.common.collect.Sets;
import net.minecraft.util.ResourceLocation;

import java.util.Set;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.MOD_ID;

public class ModCorePlugin implements IMysticalAgriculturePlugin {
    private static final CropTextures ELEMENTAL_CROP_TEXTURES = new CropTextures(CropTextures.FLOWER_INGOT_BLANK, CropTextures.ESSENCE_FLAME_BLANK);
    private static final CropTextures ROCK_CROP_TEXTURES = new CropTextures(CropTextures.FLOWER_ROCK_BLANK, CropTextures.ESSENCE_ROCK_BLANK);
    private static final Set<ResourceLocation> FISH_IDS = Sets.newHashSet(new ResourceLocation("minecraft:cod"), new ResourceLocation("minecraft:salmon"), new ResourceLocation("minecraft:tropical_fish"), new ResourceLocation("minecraft:pufferfish"));

    public static final MobSoulType PIG_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "pig"), new ResourceLocation("minecraft:pig"), 10, 15771042);
    public static final MobSoulType CHICKEN_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "chicken"), new ResourceLocation("minecraft:chicken"), 10, 10592673);
    public static final MobSoulType COW_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "cow"), new ResourceLocation("minecraft:cow"), 10, 4470310);
    public static final MobSoulType SHEEP_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "sheep"), new ResourceLocation("minecraft:sheep"), 10, 15198183);
    public static final MobSoulType SQUID_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "squid"), new ResourceLocation("minecraft:squid"), 10, 2243405);
    public static final MobSoulType FISH_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "fish"), FISH_IDS, "fish", 10, 12691306);
    public static final MobSoulType SLIME_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "slime"), new ResourceLocation("minecraft:slime"), 10, 5349438);
    public static final MobSoulType ZOMBIE_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "zombie"), new ResourceLocation("minecraft:zombie"), 10, 7969893);
    public static final MobSoulType SKELETON_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "skeleton"), new ResourceLocation("minecraft:skeleton"), 10, 12698049);
    public static final MobSoulType CREEPER_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "creeper"), new ResourceLocation("minecraft:creeper"), 10, 894731);
    public static final MobSoulType SPIDER_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "spider"), new ResourceLocation("minecraft:spider"), 10, 3419431);
    public static final MobSoulType RABBIT_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "rabbit"), new ResourceLocation("minecraft:rabbit"), 10, 10051392);
    public static final MobSoulType BLAZE_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "blaze"), new ResourceLocation("minecraft:blaze"), 10, 16167425);
    public static final MobSoulType GHAST_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "ghast"), new ResourceLocation("minecraft:ghast"), 10, 16382457);
    public static final MobSoulType ENDERMAN_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "enderman"), new ResourceLocation("minecraft:enderman"), 10, 1447446);
    public static final MobSoulType WITHER_SKELETON_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "wither_skeleton"), new ResourceLocation("minecraft:wither_skeleton"), 10, 1315860);

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
    public static final Crop PIG = new Crop(new ResourceLocation(MOD_ID, "pig"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(PIG_SOUL_TYPE)));
    public static final Crop CHICKEN = new Crop(new ResourceLocation(MOD_ID, "chicken"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(CHICKEN_SOUL_TYPE)));
    public static final Crop COW = new Crop(new ResourceLocation(MOD_ID, "cow"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(COW_SOUL_TYPE)));
    public static final Crop SHEEP = new Crop(new ResourceLocation(MOD_ID, "sheep"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(SHEEP_SOUL_TYPE)));
    public static final Crop SQUID = new Crop(new ResourceLocation(MOD_ID, "squid"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(SQUID_SOUL_TYPE)));
    public static final Crop FISH = new Crop(new ResourceLocation(MOD_ID, "fish"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(FISH_SOUL_TYPE)));
    public static final Crop SLIME = new Crop(new ResourceLocation(MOD_ID, "slime"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(SLIME_SOUL_TYPE)));

    public static final Crop IRON = new Crop(new ResourceLocation(MOD_ID, "iron"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/iron"));
    public static final Crop NETHER_QUARTZ = new Crop(new ResourceLocation(MOD_ID, "nether_quartz"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:gems/quartz"));
    public static final Crop GLOWSTONE = new Crop(new ResourceLocation(MOD_ID, "glowstone"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:dusts/glowstone"));
    public static final Crop REDSTONE = new Crop(new ResourceLocation(MOD_ID, "redstone"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("forge:dusts/redstone"));
    public static final Crop OBSIDIAN = new Crop(new ResourceLocation(MOD_ID, "obsidian"), CropTier.THREE, CropType.RESOURCE, ROCK_CROP_TEXTURES, 0x271E3D, LazyIngredient.item("minecraft:obsidian"));
    public static final Crop PRISMARINE = new Crop(new ResourceLocation(MOD_ID, "prismarine"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:prismarine_agglomeratio"));
    public static final Crop ZOMBIE = new Crop(new ResourceLocation(MOD_ID, "zombie"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ZOMBIE_SOUL_TYPE)));
    public static final Crop SKELETON = new Crop(new ResourceLocation(MOD_ID, "skeleton"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(SKELETON_SOUL_TYPE)));
    public static final Crop CREEPER = new Crop(new ResourceLocation(MOD_ID, "creeper"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(CREEPER_SOUL_TYPE)));
    public static final Crop SPIDER = new Crop(new ResourceLocation(MOD_ID, "spider"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(SPIDER_SOUL_TYPE)));
    public static final Crop RABBIT = new Crop(new ResourceLocation(MOD_ID, "rabbit"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(RABBIT_SOUL_TYPE)));

    public static final Crop GOLD = new Crop(new ResourceLocation(MOD_ID, "gold"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:ingots/gold"));
    public static final Crop LAPIS_LAZULI = new Crop(new ResourceLocation(MOD_ID, "lapis_lazuli"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("forge:gems/lapis"));
    public static final Crop END = new Crop(new ResourceLocation(MOD_ID, "end"), CropTier.FOUR, CropType.RESOURCE, ROCK_CROP_TEXTURES, 0xEEF6B4, LazyIngredient.item("mysticalagriculture:end_agglomeratio"));
    public static final Crop EXPERIENCE = new Crop(new ResourceLocation(MOD_ID, "experience"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:experience_capsule", ExperienceCapsuleUtils.makeTag(ExperienceCapsuleUtils.MAX_XP_POINTS)));
    public static final Crop BLAZE = new Crop(new ResourceLocation(MOD_ID, "blaze"), CropTier.FOUR, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(BLAZE_SOUL_TYPE)));
    public static final Crop GHAST = new Crop(new ResourceLocation(MOD_ID, "ghast"), CropTier.FOUR, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(GHAST_SOUL_TYPE)));
    public static final Crop ENDERMAN = new Crop(new ResourceLocation(MOD_ID, "enderman"), CropTier.FOUR, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(ENDERMAN_SOUL_TYPE)));

    public static final Crop DIAMOND = new Crop(new ResourceLocation(MOD_ID, "diamond"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:gems/diamond"));
    public static final Crop EMERALD = new Crop(new ResourceLocation(MOD_ID, "emerald"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("forge:gems/emerald"));
    public static final Crop WITHER_SKELETON = new Crop(new ResourceLocation(MOD_ID, "wither_skeleton"), CropTier.FIVE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeTag(WITHER_SKELETON_SOUL_TYPE)));

    @Override
    public void onRegisterCrops(ICropRegistry registry) {
        InferiumCropBlock inferiumCropBlock = new InferiumCropBlock(INFERIUM);
        INFERIUM.setCrop(() -> inferiumCropBlock, true)
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
        registry.register(PIG);
        registry.register(CHICKEN);
        registry.register(COW);
        registry.register(SHEEP);
        registry.register(SQUID);
        registry.register(FISH);
        registry.register(SLIME);

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
    }

    @Override
    public void onRegisterMobSoulTypes(IMobSoulTypeRegistry registry) {
        registry.register(PIG_SOUL_TYPE);
        registry.register(CHICKEN_SOUL_TYPE);
        registry.register(COW_SOUL_TYPE);
        registry.register(SHEEP_SOUL_TYPE);
        registry.register(SQUID_SOUL_TYPE);
        registry.register(FISH_SOUL_TYPE);
        registry.register(SLIME_SOUL_TYPE);
        registry.register(ZOMBIE_SOUL_TYPE);
        registry.register(SKELETON_SOUL_TYPE);
        registry.register(CREEPER_SOUL_TYPE);
        registry.register(SPIDER_SOUL_TYPE);
        registry.register(RABBIT_SOUL_TYPE);
        registry.register(BLAZE_SOUL_TYPE);
        registry.register(GHAST_SOUL_TYPE);
        registry.register(ENDERMAN_SOUL_TYPE);
        registry.register(WITHER_SKELETON_SOUL_TYPE);
    }

    @Override
    public void onRegisterAugments(IAugmentRegistry registry) {
        ModAugments.onRegisterAugments(registry);
    }

    public static void onCommonSetup() {
        CropTier.ELEMENTAL.setFarmland(ModBlocks.INFERIUM_FARMLAND.get()).setEssence(ModItems.INFERIUM_ESSENCE.get());
        CropTier.ONE.setFarmland(ModBlocks.INFERIUM_FARMLAND.get()).setEssence(ModItems.INFERIUM_ESSENCE.get());
        CropTier.TWO.setFarmland(ModBlocks.PRUDENTIUM_FARMLAND.get()).setEssence(ModItems.PRUDENTIUM_ESSENCE.get());
        CropTier.THREE.setFarmland(ModBlocks.TERTIUM_FARMLAND.get()).setEssence(ModItems.TERTIUM_ESSENCE.get());
        CropTier.FOUR.setFarmland(ModBlocks.IMPERIUM_FARMLAND.get()).setEssence(ModItems.IMPERIUM_ESSENCE.get());
        CropTier.FIVE.setFarmland(ModBlocks.SUPREMIUM_FARMLAND.get()).setEssence(ModItems.SUPREMIUM_ESSENCE.get());

        CropType.RESOURCE.setCraftingSeed(ModItems.PROSPERITY_SEED_BASE.get());
        CropType.MOB.setCraftingSeed(ModItems.SOULIUM_SEED_BASE.get());
    }
}
