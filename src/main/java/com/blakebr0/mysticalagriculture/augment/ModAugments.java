package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.mysticalagriculture.api.registry.IAugmentRegistry;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import net.minecraft.util.ResourceLocation;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.MOD_ID;

public class ModAugments {
    public static final IAugment ABSORPTION_I = new AbsorptionAugment(new ResourceLocation(MOD_ID, "absorption_i"), 1, 0);
    public static final IAugment HEALTH_BOOST_I = new HealthBoostAugment(new ResourceLocation(MOD_ID, "health_boost_i"), 1, 1);

    public static final IAugment NIGHT_VISION = new NightVisionAugment(new ResourceLocation(MOD_ID, "night_vision"), 2);
    public static final IAugment WATER_BREATHING = new WaterBreathingAugment(new ResourceLocation(MOD_ID, "water_breathing"), 2);
    public static final IAugment ABSORPTION_II = new AbsorptionAugment(new ResourceLocation(MOD_ID, "absorption_ii"), 2,  1);
    public static final IAugment JUMP_BOOST_I = new JumpBoostAugment(new ResourceLocation(MOD_ID, "jump_boost_i"), 2, 0);
    public static final IAugment HEALTH_BOOST_II = new HealthBoostAugment(new ResourceLocation(MOD_ID, "health_boost_ii"), 2, 2);
    public static final IAugment SPEED_I = new SpeedAugment(new ResourceLocation(MOD_ID, "speed_i"), 2, 1);
    public static final IAugment MINING_AOU = new MiningAOEAugment(new ResourceLocation(MOD_ID, "mining_aoe_i"), 2);

    public static final IAugment ABSORPTION_III = new AbsorptionAugment(new ResourceLocation(MOD_ID, "absorption_iii"), 3, 2);
    public static final IAugment FIRE_RESISTANCE = new FireResistanceAugment(new ResourceLocation(MOD_ID, "fire_resistance"), 3);
    public static final IAugment JUMP_BOOST_II = new JumpBoostAugment(new ResourceLocation(MOD_ID, "jump_boost_ii"), 3, 1);
    public static final IAugment STEP_ASSIST = new StepAssistAugment(new ResourceLocation(MOD_ID, "step_assist"), 3);
    public static final IAugment HEALTH_BOOST_III = new HealthBoostAugment(new ResourceLocation(MOD_ID, "health_boost_iii"), 3, 3);
    public static final IAugment STRENGTH_I = new StrengthAugment(new ResourceLocation(MOD_ID, "strength_i"), 3, 1);
    public static final IAugment SPEED_II = new SpeedAugment(new ResourceLocation(MOD_ID, "speed_ii"), 3, 2);
    public static final IAugment NO_FALL_DAMAGE = new NoFallDamageAugment(new ResourceLocation(MOD_ID, "no_fall_damage"), 3);

    public static final IAugment ABSORPTION_IV = new AbsorptionAugment(new ResourceLocation(MOD_ID, "absorption_iv"), 4, 3);
    public static final IAugment POISON_RESISTANCE = new PoisonResistanceAugment(new ResourceLocation(MOD_ID, "poison_resistance"), 4);
    public static final IAugment JUMP_BOOST_III = new JumpBoostAugment(new ResourceLocation(MOD_ID, "jump_boost_iii"), 4, 2);
    public static final IAugment HEALTH_BOOST_IV = new HealthBoostAugment(new ResourceLocation(MOD_ID, "health_boost_iv"), 4, 4);
    public static final IAugment STRENGTH_II = new StrengthAugment(new ResourceLocation(MOD_ID, "strength_ii"), 4, 2);
    public static final IAugment SPEED_III = new SpeedAugment(new ResourceLocation(MOD_ID, "speed_iii"), 4, 3);

    public static final IAugment ABSORPTION_V = new AbsorptionAugment(new ResourceLocation(MOD_ID, "absorption_v"), 5, 4);
    public static final IAugment WITHER_RESISTANCE = new WitherResistanceAugment(new ResourceLocation(MOD_ID, "wither_resistance"), 5);
    public static final IAugment HEALTH_BOOST_V = new HealthBoostAugment(new ResourceLocation(MOD_ID, "health_boost_v"), 5, 5);
    public static final IAugment STRENGTH_III = new StrengthAugment(new ResourceLocation(MOD_ID, "strength_iii"), 5, 4);
    public static final IAugment FLIGHT = new FlightAugment(new ResourceLocation(MOD_ID, "flight"), 5);

    public static void onRegisterAugments(IAugmentRegistry registry) {
        registry.register(ABSORPTION_I);
        registry.register(HEALTH_BOOST_I);

        registry.register(NIGHT_VISION);
        registry.register(WATER_BREATHING);
        registry.register(ABSORPTION_II);
        registry.register(JUMP_BOOST_I);
        registry.register(HEALTH_BOOST_II);
        registry.register(SPEED_I);
        registry.register(MINING_AOU);

        registry.register(ABSORPTION_III);
        registry.register(FIRE_RESISTANCE);
        registry.register(JUMP_BOOST_II);
        registry.register(STEP_ASSIST);
        registry.register(HEALTH_BOOST_III);
        registry.register(STRENGTH_I);
        registry.register(SPEED_II);
        registry.register(NO_FALL_DAMAGE);

        registry.register(ABSORPTION_IV);
        registry.register(POISON_RESISTANCE);
        registry.register(JUMP_BOOST_III);
        registry.register(HEALTH_BOOST_IV);
        registry.register(STRENGTH_II);
        registry.register(SPEED_III);

        registry.register(ABSORPTION_V);
        registry.register(WITHER_RESISTANCE);
        registry.register(HEALTH_BOOST_V);
        registry.register(STRENGTH_III);
        registry.register(FLIGHT);
    }
}
