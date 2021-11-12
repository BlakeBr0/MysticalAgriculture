package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.helper.ColorHelper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.lib.AbilityCache;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import com.blakebr0.mysticalagriculture.registry.AugmentRegistry;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.UUID;

public class HealthBoostAugment extends Augment {
    private static final UUID ATTRIBUTE_ID = UUID.fromString("e04addf9-0fe8-4498-b5a8-45e5201cd76d");
    private final int amplifier;

    public HealthBoostAugment(ResourceLocation id, int tier, int amplifier) {
        super(id, tier, EnumSet.of(AugmentType.ARMOR), getColor(0xC6223B, tier), getColor(0x3B0402, tier));
        this.amplifier = amplifier;
    }

    @Override
    public void onPlayerTick(World world, PlayerEntity player, AbilityCache cache) {
        if (!cache.isCached(this, player)) {
            ModifiableAttributeInstance health = player.getAttribute(Attributes.MAX_HEALTH);
            if (health == null)
                return;

            int boost = 4 * this.amplifier;

            AttributeModifier modifier = health.getModifier(ATTRIBUTE_ID);
            if (modifier != null) {
                if (boost < modifier.getAmount())
                    return;

                health.removeModifier(modifier);

                cache.getCachedAbilities(player).forEach(c -> {
                    IAugment augment = AugmentRegistry.getInstance().getAugmentById(new ResourceLocation(c));

                    if (augment instanceof HealthBoostAugment && cache.isCached(augment, player)) {
                        cache.removeQuietly(c, player);
                    }
                });
            }

            health.addPermanentModifier(new AttributeModifier(ATTRIBUTE_ID, MysticalAgriculture.MOD_ID + ":health_boost_augment", boost, AttributeModifier.Operation.ADDITION));

            cache.add(this, player, () -> {
                float max = player.getMaxHealth() - boost;

                if (player.getHealth() > max) {
                    player.setHealth(max);
                }

                health.removeModifier(ATTRIBUTE_ID);
            });
        }
    }

    private static int getColor(int color, int tier) {
        return ColorHelper.saturate(color, Math.min((float) tier / 5, 1));
    }
}
