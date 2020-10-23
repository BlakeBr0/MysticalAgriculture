package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.api.registry.IMobSoulTypeRegistry;
import com.blakebr0.mysticalagriculture.api.soul.MobSoulType;
import com.google.common.collect.Sets;
import net.minecraft.util.ResourceLocation;

import java.util.Set;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.MOD_ID;

public final class ModMobSoulTypes {
    private static final Set<ResourceLocation> FISH_IDS = Sets.newHashSet(new ResourceLocation("minecraft:cod"), new ResourceLocation("minecraft:salmon"), new ResourceLocation("minecraft:tropical_fish"), new ResourceLocation("minecraft:pufferfish"));
    private static final Set<ResourceLocation> ZOMBIE_IDS = Sets.newHashSet(new ResourceLocation("minecraft:zombie"), new ResourceLocation("minecraft:zombie_villager"));
    private static final Set<ResourceLocation> SPIDER_IDS = Sets.newHashSet(new ResourceLocation("minecraft:spider"), new ResourceLocation("minecraft:cave_spider"));

    public static final MobSoulType PIG_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "pig"), new ResourceLocation("minecraft:pig"), 8, 15771042);
    public static final MobSoulType CHICKEN_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "chicken"), new ResourceLocation("minecraft:chicken"), 8, 10592673);
    public static final MobSoulType COW_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "cow"), new ResourceLocation("minecraft:cow"), 8, 4470310);
    public static final MobSoulType SHEEP_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "sheep"), new ResourceLocation("minecraft:sheep"), 8, 15198183);
    public static final MobSoulType SQUID_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "squid"), new ResourceLocation("minecraft:squid"), 6, 2243405);
    public static final MobSoulType FISH_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "fish"), FISH_IDS, "fish", 6, 12691306);
    public static final MobSoulType SLIME_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "slime"), new ResourceLocation("minecraft:slime"), 12, 5349438);
    public static final MobSoulType TURTLE_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "turtle"), new ResourceLocation("minecraft:turtle"), 6, 44975);
    public static final MobSoulType ZOMBIE_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "zombie"), ZOMBIE_IDS, "zombie", 10, 7969893);
    public static final MobSoulType SKELETON_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "skeleton"), new ResourceLocation("minecraft:skeleton"), 10, 12698049);
    public static final MobSoulType CREEPER_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "creeper"), new ResourceLocation("minecraft:creeper"), 10, 894731);
    public static final MobSoulType SPIDER_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "spider"), SPIDER_IDS, "spider", 10, 3419431);
    public static final MobSoulType RABBIT_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "rabbit"), new ResourceLocation("minecraft:rabbit"), 6, 10051392);
    public static final MobSoulType BLAZE_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "blaze"), new ResourceLocation("minecraft:blaze"), 10, 16167425);
    public static final MobSoulType GHAST_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "ghast"), new ResourceLocation("minecraft:ghast"), 4, 16382457);
    public static final MobSoulType ENDERMAN_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "enderman"), new ResourceLocation("minecraft:enderman"), 8, 1447446);
    public static final MobSoulType WITHER_SKELETON_SOUL_TYPE = new MobSoulType(new ResourceLocation(MOD_ID, "wither_skeleton"), new ResourceLocation("minecraft:wither_skeleton"), 8, 1315860);

    public static void onRegisterMobSoulTypes(IMobSoulTypeRegistry registry) {
        registry.register(PIG_SOUL_TYPE);
        registry.register(CHICKEN_SOUL_TYPE);
        registry.register(COW_SOUL_TYPE);
        registry.register(SHEEP_SOUL_TYPE);
        registry.register(SQUID_SOUL_TYPE);
        registry.register(FISH_SOUL_TYPE);
        registry.register(SLIME_SOUL_TYPE);
        registry.register(TURTLE_SOUL_TYPE);
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
}
