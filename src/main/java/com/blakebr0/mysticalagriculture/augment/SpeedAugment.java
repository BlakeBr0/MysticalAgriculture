package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.helper.ColorHelper;
import com.blakebr0.mysticalagriculture.api.lib.AbilityCache;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.EnumSet;

public class SpeedAugment extends Augment {
    private final int amplifier;

    public SpeedAugment(ResourceLocation id, int tier, int amplifier) {
        super(id, tier, EnumSet.of(AugmentType.LEGGINGS), getColor(0xAD524D, tier), getColor(0x240805, tier));
        this.amplifier = amplifier;
    }

    @Override
    public void onPlayerTick(World world, PlayerEntity player, AbilityCache cache) {
        boolean flying = player.abilities.isFlying;
        boolean swimming = player.isSwimming();
        boolean inWater = player.isInWater();

        if (player.isOnGround() || flying || swimming || inWater) {
            boolean sneaking = player.isCrouching();
            boolean sprinting = player.isSprinting();

            float speed = 0.1F
                    * (flying ? 0.6F : 1.0F)
                    * (sneaking ? 0.1F : 1.0F)
                    * (!sprinting ? 0.6F : 1.2F)
                    * (inWater ? 0.5F : 1.0F)
                    * (swimming ? 0.8F : 1.0F)
                    * this.amplifier;

            if (player.moveForward > 0F) {
                player.moveRelative(1F, new Vector3d(0F, 0F, speed));
            } else if (player.moveForward < 0F) {
                player.moveRelative(1F, new Vector3d(0F, 0F, -speed * 0.3F));
            }

            if (player.moveStrafing != 0F) {
                player.moveRelative(1F, new Vector3d(speed * 0.5F * Math.signum(player.moveStrafing), 0F, 0F));
            }
        }
    }

    private static int getColor(int color, int tier) {
        return ColorHelper.saturate(color, Math.min((float) tier / 4, 1));
    }
}
