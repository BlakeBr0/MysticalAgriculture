package com.blakebr0.mysticalagriculture.config;

import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class ModConfigs {
    public static final ModConfigSpec COMMON;

    public static final ModConfigSpec.DoubleValue INFERIUM_DROP_CHANCE;
    public static final ModConfigSpec.IntValue INFUSION_CRYSTAL_USES;
    public static final ModConfigSpec.IntValue GROWTH_ACCELERATOR_COOLDOWN;
    public static final ModConfigSpec.DoubleValue FERTILIZED_ESSENCE_DROP_CHANCE;
    public static final ModConfigSpec.BooleanValue SECONDARY_SEED_DROPS;
    public static final ModConfigSpec.BooleanValue REQUIRES_EFFECTIVE_FARMLAND;
    public static final ModConfigSpec.BooleanValue WITHER_DROPS_ESSENCE;
    public static final ModConfigSpec.BooleanValue WITHER_DROPS_COGNIZANT;
    public static final ModConfigSpec.BooleanValue DRAGON_DROPS_ESSENCE;
    public static final ModConfigSpec.BooleanValue DRAGON_DROPS_COGNIZANT;
    public static final ModConfigSpec.BooleanValue ESSENCE_FARMLAND_CONVERSION;
    public static final ModConfigSpec.BooleanValue SEED_CRAFTING_RECIPES;
    public static final ModConfigSpec.BooleanValue ENCHANTABLE_SUPREMIUM_TOOLS;
    public static final ModConfigSpec.BooleanValue ENCHANTABLE_SUPREMIUM_ARMOR;
    public static final ModConfigSpec.BooleanValue UNBREAKABLE_SUPREMIUM_ARMOR;
    public static final ModConfigSpec.BooleanValue FAKE_PLAYER_WATERING;
    public static final ModConfigSpec.BooleanValue AWAKENED_SUPREMIUM_SET_BONUS;

    public static final ModConfigSpec.BooleanValue GENERATE_PROSPERITY;
    public static final ModConfigSpec.BooleanValue GENERATE_INFERIUM;
    public static final ModConfigSpec.BooleanValue GENERATE_SOULSTONE;
    public static final ModConfigSpec.DoubleValue SOULIUM_ORE_CHANCE;

    // Common
    static {
        final var common = new ModConfigSpec.Builder();

        common.comment("General configuration options.").push("General");
        INFERIUM_DROP_CHANCE = common
                .comment("The percentage chance that a passive or hostile mob will drop an Inferium Essence when killed.")
                .defineInRange("inferiumDropChance", 0.2, 0.0, 1.0);
        INFUSION_CRYSTAL_USES = common
                .comment("The number of uses the basic Infusion Crystal should have.")
                .defineInRange("infusionCrystalUses", 1000, 10, Integer.MAX_VALUE);
        GROWTH_ACCELERATOR_COOLDOWN = common
                .comment("The amount of time in seconds between each Growth Accelerator growth tick.")
                .defineInRange("growthAcceleratorCooldown", 10, 1, Integer.MAX_VALUE);
        FERTILIZED_ESSENCE_DROP_CHANCE = common
                .comment("The percentage chance that harvesting a Resource Crop will drop a Fertilized Essence.")
                .defineInRange("fertilizedEssenceChance", 0.1, 0.0, 1.0);
        SECONDARY_SEED_DROPS = common
                .comment("Should crops have a chance of dropping a second seed when harvested?")
                .define("secondarySeedDrops", true);
        REQUIRES_EFFECTIVE_FARMLAND = common
                .comment("Should resource crops need to be planted on their effective Farmland to grow?")
                .define("requiresEffectiveFarmland", false);
        WITHER_DROPS_ESSENCE = common
                .comment("Should the Wither drop essence when killed with an essence weapon?")
                .define("witherDropsEssence", true);
        WITHER_DROPS_COGNIZANT = common
                .comment("Should the Wither drop Cognizant Dust when killed with a Mystical Enlightenment enchanted essence weapon?")
                .define("witherDropsCognizant", true);
        DRAGON_DROPS_ESSENCE = common
                .comment("Should the Ender Dragon drop essence when killed with an essence weapon?")
                .define("dragonDropsEssence", true);
        DRAGON_DROPS_COGNIZANT = common
                .comment("Should the Ender Dragon drop Cognizant Dust when killed with a Mystical Enlightenment enchanted essence weapon?")
                .define("dragonDropsCognizant", true);
        ESSENCE_FARMLAND_CONVERSION = common
                .comment("Should right-clicking on Farmland with an Essence create Essence Farmland?")
                .define("essenceFarmlandConversion", true);
        SEED_CRAFTING_RECIPES = common
                .comment("Should vanilla crafting recipes for seeds be generated?")
                .define("seedCraftingRecipes", false);
        ENCHANTABLE_SUPREMIUM_TOOLS = common
                .comment("Should Supremium & Awakened Supremium Tools be enchantable in an Enchanting Table?")
                .define("enchantableSupremiumTools", true);
        ENCHANTABLE_SUPREMIUM_ARMOR = common
                .comment("Should Supremium & Awakened Supremium Armor be enchantable in an Enchanting Table?")
                .define("enchantableSupremiumArmor", true);
        UNBREAKABLE_SUPREMIUM_ARMOR = common
                .comment("Should Supremium & Awakened Supremium Armor be unbreakable?")
                .define("unbreakableSupremiumArmor", false);
        FAKE_PLAYER_WATERING = common
                .comment("Should fake players be able to use watering cans?")
                .define("fakePlayerWatering", true);
        AWAKENED_SUPREMIUM_SET_BONUS = common
                .comment("Should wearing a full set of Awakened Supremium armor grant the Plant Growth AOE set bonus?")
                .define("awakenedSupremiumSetBonus", true);
        common.pop();

        common.comment("World generation options.").push("World");
        GENERATE_PROSPERITY = common
                .comment("Should Prosperity Ore generate in the world?")
                .define("generateProsperityOre", true);
        GENERATE_INFERIUM = common
                .comment("Should Inferium Ore generate in the world?")
                .define("generateInferiumOre", true);
        GENERATE_SOULSTONE = common
                .comment("Should Soulstone generate in the world?")
                .define("generateSoulstone", true);
        SOULIUM_ORE_CHANCE = common
                .comment("The percentage chance a Soulium Ore spawns in a Soulstone cluster.")
                .defineInRange("souliumOreChance", 0.05, 0, 1);
        common.pop();

        COMMON = common.build();
    }

    public static boolean isTheOneProbeInstalled() {
        return ModList.get().isLoaded("theoneprobe");
    }
}
