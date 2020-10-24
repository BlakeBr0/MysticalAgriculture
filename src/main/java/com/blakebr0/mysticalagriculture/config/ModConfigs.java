package com.blakebr0.mysticalagriculture.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class ModConfigs {
    public static final ForgeConfigSpec CLIENT;
    public static final ForgeConfigSpec COMMON;
    public static final ForgeConfigSpec SERVER;

    public static final ForgeConfigSpec.BooleanValue ANIMATED_GROWTH_ACCELERATORS;

    // Client
    static {
        final ForgeConfigSpec.Builder client = new ForgeConfigSpec.Builder();

        client.comment("General configuration options.").push("General");
        ANIMATED_GROWTH_ACCELERATORS = client
                .comment("Should Growth Accelerators use animated textures?")
                .translation("configGui.mysticalagriculture.animated_growth_accelerators")
                .define("animatedGrowthAccelerators", true);
        client.pop();

        CLIENT = client.build();
    }

    public static final ForgeConfigSpec.DoubleValue INFERIUM_DROP_CHANCE;
    public static final ForgeConfigSpec.IntValue INFUSION_CRYSTAL_USES;
    public static final ForgeConfigSpec.IntValue GROWTH_ACCELERATOR_COOLDOWN;
    public static final ForgeConfigSpec.DoubleValue FERTILIZED_ESSENCE_DROP_CHANCE;
    public static final ForgeConfigSpec.BooleanValue SECONDARY_SEED_DROPS;

    public static final ForgeConfigSpec.DoubleValue SOULIUM_ORE_CHANCE;
    public static final ForgeConfigSpec.BooleanValue GENERATE_PROSPERITY;
    public static final ForgeConfigSpec.IntValue PROSPERITY_SPAWN_RATE;
    public static final ForgeConfigSpec.IntValue PROSPERITY_SPAWN_SIZE;
    public static final ForgeConfigSpec.IntValue PROSPERITY_SPAWN_HEIGHT;
    public static final ForgeConfigSpec.BooleanValue GENERATE_INFERIUM;
    public static final ForgeConfigSpec.IntValue INFERIUM_SPAWN_RATE;
    public static final ForgeConfigSpec.IntValue INFERIUM_SPAWN_SIZE;
    public static final ForgeConfigSpec.IntValue INFERIUM_SPAWN_HEIGHT;
    public static final ForgeConfigSpec.BooleanValue GENERATE_SOULSTONE;
    public static final ForgeConfigSpec.IntValue SOULSTONE_SPAWN_RATE;
    public static final ForgeConfigSpec.IntValue SOULSTONE_SPAWN_SIZE;
    public static final ForgeConfigSpec.IntValue SOULSTONE_SPAWN_HEIGHT;

    // Common
    static {
        final ForgeConfigSpec.Builder common = new ForgeConfigSpec.Builder();

        common.comment("General configuration options.").push("General");
        INFERIUM_DROP_CHANCE = common
                .comment("The percentage chance that a passive or hostile mob will drop an Inferium Essence when killed.")
                .translation("configGui.mysticalagriculture.inferium_chance")
                .defineInRange("inferiumDropChance", 0.2, 0.0, 1.0);
        INFUSION_CRYSTAL_USES = common
                .comment("The amount of uses the basic Infusion Crystal should have.")
                .translation("configGui.mysticalagriculture.infusion_crystal_uses")
                .defineInRange("infusionCrystalUses", 1000, 10, Integer.MAX_VALUE);
        GROWTH_ACCELERATOR_COOLDOWN = common
                .comment("The amount of time in seconds between each Growth Accelerator growth tick.")
                .translation("configGui.mysticalagriculture.growth_accelerator_cooldown")
                .defineInRange("growthAcceleratorCooldown", 10, 1, Integer.MAX_VALUE);
        FERTILIZED_ESSENCE_DROP_CHANCE = common
                .comment("The percentage chance that harvesting a Resource Crop will drop a Fertilized Essence.")
                .translation("configGui.mysticalagriculture.fertilized_essence_chance")
                .defineInRange("fertilizedEssenceChance", 0.1, 0.0, 1.0);
        SECONDARY_SEED_DROPS = common
                .comment("Should crops have a chance of dropping a second seed when harvested?")
                .translation("configGui.mysticalagriculture.secondary_seed_drops")
                .define("secondarySeedDrops", true);
        common.pop();

        common.comment("World generation options.").push("World");
        SOULIUM_ORE_CHANCE = common
                .comment("The percentage chance a Soulium Ore spawns in a Soulstone cluster.")
                .translation("configGui.mysticalagriculture.soulium_ore_chance")
                .defineInRange("souliumOreChance", 0.05, 0, 1);
        GENERATE_PROSPERITY = common
                .comment("Should Prosperity Ore generate in the world?")
                .translation("configGui.mysticalagriculture.generate_prosperity")
                .define("generateProsperityOre", true);
        PROSPERITY_SPAWN_RATE = common
                .comment("How many Prosperity Ore veins should spawn in a chunk.")
                .translation("configGui.mysticalagriculture.prosperity_spawn_rate")
                .defineInRange("prosperitySpawnRate", 12, 0, Integer.MAX_VALUE);
        PROSPERITY_SPAWN_SIZE = common
                .comment("The maximum amount of Prosperity Ore blocks that can spawn in a vein.")
                .translation("configGui.mysticalagriculture.prosperity_spawn_size")
                .defineInRange("prosperitySpawnSize", 8, 0, Integer.MAX_VALUE);
        PROSPERITY_SPAWN_HEIGHT = common
                .comment("The maximum Y level that Prosperity Ore veins can spawn.")
                .translation("configGui.mysticalagriculture.prosperity_spawn_height")
                .defineInRange("prosperitySpawnHeight", 50, 0, 256);
        GENERATE_INFERIUM = common
                .comment("Should Inferium Ore generate in the world?")
                .translation("configGui.mysticalagriculture.generate_inferium")
                .define("generateInferiumOre", true);
        INFERIUM_SPAWN_RATE = common
                .comment("How many Inferium Ore veins should spawn in a chunk.")
                .translation("configGui.mysticalagriculture.inferium_spawn_rate")
                .defineInRange("inferiumSpawnRate", 16, 0, Integer.MAX_VALUE);
        INFERIUM_SPAWN_SIZE = common
                .comment("The maximum amount of Inferium Ore blocks that can spawn in a vein.")
                .translation("configGui.mysticalagriculture.inferium_spawn_size")
                .defineInRange("inferiumSpawnSize", 8, 0, Integer.MAX_VALUE);
        INFERIUM_SPAWN_HEIGHT = common
                .comment("The maximum Y level that Inferium Ore veins can spawn.")
                .translation("configGui.mysticalagriculture.inferium_spawn_height")
                .defineInRange("inferiumSpawnHeight", 50, 0, 256);
        GENERATE_SOULSTONE = common
                .comment("How many Soulstone veins should spawn in a chunk.")
                .translation("configGui.mysticalagriculture.generate_soulstone")
                .define("generateSoulstone", true);
        SOULSTONE_SPAWN_RATE = common
                .comment("How many Soulstone veins should spawn in a chunk.")
                .translation("configGui.mysticalagriculture.soulstone_spawn_rate")
                .defineInRange("soulstoneSpawnRate", 4, 0, Integer.MAX_VALUE);
        SOULSTONE_SPAWN_SIZE = common
                .comment("The maximum amount of Soulstone blocks that can spawn in a vein.")
                .translation("configGui.mysticalagriculture.soulstone_spawn_size")
                .defineInRange("soulstoneSpawnSize", 64, 0, Integer.MAX_VALUE);
        SOULSTONE_SPAWN_HEIGHT = common
                .comment("The maximum Y level that Soulstone veins can spawn.")
                .translation("configGui.mysticalagriculture.inferium_spawn_height")
                .defineInRange("inferiumSpawnHeight", 128, 0, 128);
        common.pop();

        COMMON = common.build();
    }

    public static final ForgeConfigSpec.BooleanValue SEED_CRAFTING_RECIPES;

    // Server
    static {
        final ForgeConfigSpec.Builder server = new ForgeConfigSpec.Builder();

        SEED_CRAFTING_RECIPES = server
                .comment("Should vanilla crafting recipes for seeds be generated?")
                .translation("configGui.mysticalagriculture.seed_crafting_recipes")
                .define("seedCraftingRecipes", false);

        SERVER = server.build();
    }
}
