package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.helper.ColorHelper;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class AttackAOEAugment extends Augment {
    private final int amplifier;

    public AttackAOEAugment(ResourceLocation id, int tier, int amplifier) {
        super(id, tier, EnumSet.of(AugmentType.SWORD), getColor(0xFF0000, tier), getColor(0x700000, tier));
        this.amplifier = amplifier;
    }

    @Override
    public boolean onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player) {
            if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                var entities = player.getCommandSenderWorld().getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(1.5D * this.amplifier, 0.25D * this.amplifier, 1.5D * this.amplifier));

                for (var aoeEntity : entities) {
                    if (aoeEntity != player && aoeEntity != target && !player.isAlliedTo(target)) {
                        aoeEntity.knockback(0.4F, Mth.sin(player.getYRot() * 0.017453292F), -Mth.cos(player.getYRot() * 0.017453292F));
                        aoeEntity.hurt(DamageSource.playerAttack(player), 13.0F); // TODO: 1.16: should this damage value be hardcoded?
                    }
                }

                player.getCommandSenderWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, player.getSoundSource(), 1.0F, 1.0F);
                player.sweepAttack();
            }

            return true;
        }

        return false;
    }

    private static int getColor(int color, int tier) {
        return ColorHelper.saturate(color, Math.min((float) tier / 5, 1));
    }
}
