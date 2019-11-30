package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.EnumSet;

public class JumpBoostAugment extends Augment {
    private final int amplifier;

    public JumpBoostAugment(ResourceLocation id, int tier, int amplifier) {
        super(id, tier, EnumSet.of(AugmentType.BOOTS), getColor(0xFAFAFA, tier), getColor(0x458FAB, tier));
        this.amplifier = amplifier;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 5, this.amplifier, true, false));
    }

    public static int getColor(int color, int tier) {
        return Utils.saturate(color, Math.min((float) tier / 4, 1));
    }
}
