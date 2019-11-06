package com.blakebr0.mysticalagriculture.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfigs {
    public static final ForgeConfigSpec CLIENT;
    public static final ForgeConfigSpec COMMON;
    public static final ForgeConfigSpec SERVER;

    // Client
    static {
        final ForgeConfigSpec.Builder client = new ForgeConfigSpec.Builder();

        client.comment("General configuration options.").push("General");
        client.pop();

        CLIENT = client.build();
    }

    public static final ForgeConfigSpec.DoubleValue INFERIUM_DROP_CHANCE;
    public static final ForgeConfigSpec.IntValue INFUSION_CRYSTAL_USES;
    public static final ForgeConfigSpec.IntValue GROWTH_ACCELERATOR_COOLDOWN;
    public static final ForgeConfigSpec.DoubleValue FERTILIZED_ESSENCE_DROP_CHANCE;

    public static final ForgeConfigSpec.DoubleValue SOULIUM_ORE_CHANCE;

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
        common.pop();

        common.comment("World generation options.").push("World");
        SOULIUM_ORE_CHANCE = common
                .comment("The percentage chance a Soulium Ore spawns in a Soulstone cluster.")
                .translation("configGui.mysticalagriculture.soulium_ore_chance")
                .defineInRange("souliumOreChance", 0.05, 0, 1);
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
