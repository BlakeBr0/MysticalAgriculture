package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.helper.ColorHelper;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class LuckAugment extends Augment {
    private final int amplifier;

    public LuckAugment(ResourceLocation id, int tier, int amplifier) {
        super(id, tier, EnumSet.of(AugmentType.ARMOR), getColor(0x3FDC00, tier), getColor(0x339900, tier));
        this.amplifier = amplifier;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.LUCK, 5, this.amplifier, true, false));
    }

    private static int getColor(int color, int tier) {
        return ColorHelper.saturate(color, Math.min((float) tier / 3, 1));
    }
}
