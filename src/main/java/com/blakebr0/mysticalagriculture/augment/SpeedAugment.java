package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.helper.ColorHelper;
import com.blakebr0.mysticalagriculture.api.lib.AbilityCache;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class SpeedAugment extends Augment {
    private final int amplifier;

    public SpeedAugment(ResourceLocation id, int tier, int amplifier) {
        super(id, tier, EnumSet.of(AugmentType.LEGGINGS), getColor(0xAD524D, tier), getColor(0x240805, tier));
        this.amplifier = amplifier;
    }

    @Override
    public void onPlayerTick(Level level, Player player, AbilityCache cache) {
        var flying = player.getAbilities().flying;
        var swimming = player.isSwimming();
        var inWater = player.isInWater();

        if (player.onGround() || flying || swimming || inWater) {
            var sneaking = player.isCrouching();
            var sprinting = player.isSprinting();

            float speed = 0.1F
                    * (flying ? 0.6F : 1.0F)
                    * (sneaking ? 0.1F : 1.0F)
                    * (!sprinting ? 0.6F : 1.2F)
                    * (inWater ? 0.5F : 1.0F)
                    * (swimming ? 0.8F : 1.0F)
                    * this.amplifier;

            if (player.zza > 0F) {
                player.moveRelative(1F, new Vec3(0F, 0F, speed));
            } else if (player.zza < 0F) {
                player.moveRelative(1F, new Vec3(0F, 0F, -speed * 0.3F));
            }

            if (player.xxa != 0F) {
                player.moveRelative(1F, new Vec3(speed * 0.5F * Math.signum(player.xxa), 0F, 0F));
            }
        }
    }

    private static int getColor(int color, int tier) {
        return ColorHelper.saturate(color, Math.min((float) tier / 4, 1));
    }
}
