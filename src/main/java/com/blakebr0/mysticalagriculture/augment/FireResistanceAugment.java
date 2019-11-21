package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import com.blakebr0.mysticalagriculture.item.AugmentItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.EnumSet;

public class FireResistanceAugment implements IAugment {
    private static final EnumSet<AugmentType> TYPES = EnumSet.of(AugmentType.ARMOR);
    private static final ResourceLocation ID = new ResourceLocation(MysticalAgriculture.MOD_ID, "fire_resistance");
    private static final RegistryObject<AugmentItem> ITEM = RegistryObject.of(new ResourceLocation(MysticalAgriculture.MOD_ID, "fire_resistance_augment"), ForgeRegistries.ITEMS);

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public EnumSet<AugmentType> getAugmentTypes() {
        return TYPES;
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public Item getItem() {
        return ITEM.get();
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200, 0));
    }
}
