package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.api.registry.IAugmentRegistry;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import com.blakebr0.mysticalagriculture.augment.AbsorptionAugment;
import com.blakebr0.mysticalagriculture.augment.AttackAOEAugment;
import com.blakebr0.mysticalagriculture.augment.FireResistanceAugment;
import com.blakebr0.mysticalagriculture.augment.FlightAugment;
import com.blakebr0.mysticalagriculture.augment.HealthBoostAugment;
import com.blakebr0.mysticalagriculture.augment.JumpBoostAugment;
import com.blakebr0.mysticalagriculture.augment.MiningAOEAugment;
import com.blakebr0.mysticalagriculture.augment.MiningFatigueResistanceAugment;
import com.blakebr0.mysticalagriculture.augment.NightVisionAugment;
import com.blakebr0.mysticalagriculture.augment.NoFallDamageAugment;
import com.blakebr0.mysticalagriculture.augment.PathingAOEAugment;
import com.blakebr0.mysticalagriculture.augment.PoisonResistanceAugment;
import com.blakebr0.mysticalagriculture.augment.SpeedAugment;
import com.blakebr0.mysticalagriculture.augment.StepAssistAugment;
import com.blakebr0.mysticalagriculture.augment.StrengthAugment;
import com.blakebr0.mysticalagriculture.augment.TillingAOEAugment;
import com.blakebr0.mysticalagriculture.augment.WaterBreathingAugment;
import com.blakebr0.mysticalagriculture.augment.WitherResistanceAugment;
import net.minecraft.resources.ResourceLocation;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.MOD_ID;

public final class ModAugments {
    public static final IAugment ABSORPTION_I = new AbsorptionAugment(new ResourceLocation(MOD_ID, "absorption_i"), 1, 0);
    public static final IAugment HEALTH_BOOST_I = new HealthBoostAugment(new ResourceLocation(MOD_ID, "health_boost_i"), 1, 1);
    public static final IAugment PATHING_AOE_I = new PathingAOEAugment(new ResourceLocation(MOD_ID, "pathing_aoe_i"), 1, 1);

    public static final IAugment NIGHT_VISION = new NightVisionAugment(new ResourceLocation(MOD_ID, "night_vision"), 2);
    public static final IAugment WATER_BREATHING = new WaterBreathingAugment(new ResourceLocation(MOD_ID, "water_breathing"), 2);
    public static final IAugment ABSORPTION_II = new AbsorptionAugment(new ResourceLocation(MOD_ID, "absorption_ii"), 2,  1);
    public static final IAugment JUMP_BOOST_I = new JumpBoostAugment(new ResourceLocation(MOD_ID, "jump_boost_i"), 2, 0);
    public static final IAugment HEALTH_BOOST_II = new HealthBoostAugment(new ResourceLocation(MOD_ID, "health_boost_ii"), 2, 2);
    public static final IAugment SPEED_I = new SpeedAugment(new ResourceLocation(MOD_ID, "speed_i"), 2, 1);
    public static final IAugment MINING_AOE_I = new MiningAOEAugment(new ResourceLocation(MOD_ID, "mining_aoe_i"), 2, 1);
    public static final IAugment TILLING_AOE_I = new TillingAOEAugment(new ResourceLocation(MOD_ID, "tilling_aoe_i"), 2, 1);
    public static final IAugment PATHING_AOE_II = new PathingAOEAugment(new ResourceLocation(MOD_ID, "pathing_aoe_ii"), 2, 2);

    public static final IAugment ABSORPTION_III = new AbsorptionAugment(new ResourceLocation(MOD_ID, "absorption_iii"), 3, 2);
    public static final IAugment FIRE_RESISTANCE = new FireResistanceAugment(new ResourceLocation(MOD_ID, "fire_resistance"), 3);
    public static final IAugment JUMP_BOOST_II = new JumpBoostAugment(new ResourceLocation(MOD_ID, "jump_boost_ii"), 3, 1);
    public static final IAugment STEP_ASSIST = new StepAssistAugment(new ResourceLocation(MOD_ID, "step_assist"), 3);
    public static final IAugment HEALTH_BOOST_III = new HealthBoostAugment(new ResourceLocation(MOD_ID, "health_boost_iii"), 3, 3);
    public static final IAugment STRENGTH_I = new StrengthAugment(new ResourceLocation(MOD_ID, "strength_i"), 3, 1);
    public static final IAugment SPEED_II = new SpeedAugment(new ResourceLocation(MOD_ID, "speed_ii"), 3, 2);
    public static final IAugment NO_FALL_DAMAGE = new NoFallDamageAugment(new ResourceLocation(MOD_ID, "no_fall_damage"), 3);
    public static final IAugment MINING_AOE_II = new MiningAOEAugment(new ResourceLocation(MOD_ID, "mining_aoe_ii"), 3, 2);
    public static final IAugment ATTACK_AOE_I = new AttackAOEAugment(new ResourceLocation(MOD_ID, "attack_aoe_i"), 3, 1);
    public static final IAugment TILLING_AOE_II = new TillingAOEAugment(new ResourceLocation(MOD_ID, "tilling_aoe_ii"), 3, 2);
    public static final IAugment PATHING_AOE_III = new PathingAOEAugment(new ResourceLocation(MOD_ID, "pathing_aoe_iii"), 3, 3);

    public static final IAugment ABSORPTION_IV = new AbsorptionAugment(new ResourceLocation(MOD_ID, "absorption_iv"), 4, 3);
    public static final IAugment POISON_RESISTANCE = new PoisonResistanceAugment(new ResourceLocation(MOD_ID, "poison_resistance"), 4);
    public static final IAugment JUMP_BOOST_III = new JumpBoostAugment(new ResourceLocation(MOD_ID, "jump_boost_iii"), 4, 2);
    public static final IAugment HEALTH_BOOST_IV = new HealthBoostAugment(new ResourceLocation(MOD_ID, "health_boost_iv"), 4, 4);
    public static final IAugment STRENGTH_II = new StrengthAugment(new ResourceLocation(MOD_ID, "strength_ii"), 4, 2);
    public static final IAugment SPEED_III = new SpeedAugment(new ResourceLocation(MOD_ID, "speed_iii"), 4, 3);
    public static final IAugment MINING_AOE_III = new MiningAOEAugment(new ResourceLocation(MOD_ID, "mining_aoe_iii"), 4, 3);
    public static final IAugment ATTACK_AOE_II = new AttackAOEAugment(new ResourceLocation(MOD_ID, "attack_aoe_ii"), 4, 2);
    public static final IAugment TILLING_AOE_III = new TillingAOEAugment(new ResourceLocation(MOD_ID, "tilling_aoe_iii"), 4, 3);
    public static final IAugment PATHING_AOE_IV = new PathingAOEAugment(new ResourceLocation(MOD_ID, "pathing_aoe_iv"), 4, 4);
    public static final IAugment MINING_FATIGUE_RESISTANCE = new MiningFatigueResistanceAugment(new ResourceLocation(MOD_ID, "mining_fatigue_resistance"), 4);

    public static final IAugment ABSORPTION_V = new AbsorptionAugment(new ResourceLocation(MOD_ID, "absorption_v"), 5, 4);
    public static final IAugment WITHER_RESISTANCE = new WitherResistanceAugment(new ResourceLocation(MOD_ID, "wither_resistance"), 5);
    public static final IAugment HEALTH_BOOST_V = new HealthBoostAugment(new ResourceLocation(MOD_ID, "health_boost_v"), 5, 5);
    public static final IAugment STRENGTH_III = new StrengthAugment(new ResourceLocation(MOD_ID, "strength_iii"), 5, 4);
    public static final IAugment FLIGHT = new FlightAugment(new ResourceLocation(MOD_ID, "flight"), 5);
    public static final IAugment MINING_AOE_IV = new MiningAOEAugment(new ResourceLocation(MOD_ID, "mining_aoe_iv"), 5, 4);
    public static final IAugment ATTACK_AOE_III = new AttackAOEAugment(new ResourceLocation(MOD_ID, "attack_aoe_iii"), 5, 3);
    public static final IAugment TILLING_AOE_IV = new TillingAOEAugment(new ResourceLocation(MOD_ID, "tilling_aoe_iv"), 5, 4);

    public static void onRegisterAugments(IAugmentRegistry registry) {
        registry.register(ABSORPTION_I);
        registry.register(HEALTH_BOOST_I);
        registry.register(PATHING_AOE_I);

        registry.register(NIGHT_VISION);
        registry.register(WATER_BREATHING);
        registry.register(ABSORPTION_II);
        registry.register(JUMP_BOOST_I);
        registry.register(HEALTH_BOOST_II);
        registry.register(SPEED_I);
        registry.register(MINING_AOE_I);
        registry.register(TILLING_AOE_I);
        registry.register(PATHING_AOE_II);

        registry.register(ABSORPTION_III);
        registry.register(FIRE_RESISTANCE);
        registry.register(JUMP_BOOST_II);
        registry.register(STEP_ASSIST);
        registry.register(HEALTH_BOOST_III);
        registry.register(STRENGTH_I);
        registry.register(SPEED_II);
        registry.register(NO_FALL_DAMAGE);
        registry.register(MINING_AOE_II);
        registry.register(ATTACK_AOE_I);
        registry.register(TILLING_AOE_II);
        registry.register(PATHING_AOE_III);

        registry.register(ABSORPTION_IV);
        registry.register(POISON_RESISTANCE);
        registry.register(JUMP_BOOST_III);
        registry.register(HEALTH_BOOST_IV);
        registry.register(STRENGTH_II);
        registry.register(SPEED_III);
        registry.register(MINING_AOE_III);
        registry.register(ATTACK_AOE_II);
        registry.register(TILLING_AOE_III);
        registry.register(PATHING_AOE_IV);
        registry.register(MINING_FATIGUE_RESISTANCE);

        registry.register(ABSORPTION_V);
        registry.register(WITHER_RESISTANCE);
        registry.register(HEALTH_BOOST_V);
        registry.register(STRENGTH_III);
        registry.register(FLIGHT);
        registry.register(MINING_AOE_IV);
        registry.register(ATTACK_AOE_III);
        registry.register(TILLING_AOE_IV);
    }
}
