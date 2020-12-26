package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.lib.PluginConfig;
import com.blakebr0.mysticalagriculture.api.registry.IAugmentRegistry;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.blakebr0.mysticalagriculture.api.registry.IMobSoulTypeRegistry;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.init.ModItems;
import net.minecraft.block.FarmlandBlock;

public final class ModCorePlugin implements IMysticalAgriculturePlugin {
    @Override
    public void configure(PluginConfig config) {
        config.setModId(MysticalAgriculture.MOD_ID);
        config.disableDynamicSeedCraftingRecipes();
        config.disableDynamicSeedInfusionRecipes();
        config.disableDynamicSeedReprocessingRecipes();
    }

    @Override
    public void onRegisterCrops(ICropRegistry registry) {
        ModCrops.onRegisterCrops(registry);
    }

    @Override
    public void onPostRegisterCrops(ICropRegistry registry) {
        CropTier.ELEMENTAL.setFarmland(() -> (FarmlandBlock) ModBlocks.INFERIUM_FARMLAND.get()).setEssence(ModItems.INFERIUM_ESSENCE);
        CropTier.ONE.setFarmland(() -> (FarmlandBlock) ModBlocks.INFERIUM_FARMLAND.get()).setEssence(ModItems.INFERIUM_ESSENCE);
        CropTier.TWO.setFarmland(() -> (FarmlandBlock) ModBlocks.PRUDENTIUM_FARMLAND.get()).setEssence(ModItems.PRUDENTIUM_ESSENCE);
        CropTier.THREE.setFarmland(() -> (FarmlandBlock) ModBlocks.TERTIUM_FARMLAND.get()).setEssence(ModItems.TERTIUM_ESSENCE);
        CropTier.FOUR.setFarmland(() -> (FarmlandBlock) ModBlocks.IMPERIUM_FARMLAND.get()).setEssence(ModItems.IMPERIUM_ESSENCE);
        CropTier.FIVE.setFarmland(() -> (FarmlandBlock) ModBlocks.SUPREMIUM_FARMLAND.get()).setEssence(ModItems.SUPREMIUM_ESSENCE);

        CropType.RESOURCE.setCraftingSeed(ModItems.PROSPERITY_SEED_BASE);
        CropType.MOB.setCraftingSeed(ModItems.SOULIUM_SEED_BASE);
    }

    @Override
    public void onRegisterMobSoulTypes(IMobSoulTypeRegistry registry) {
        ModMobSoulTypes.onRegisterMobSoulTypes(registry);
    }

    @Override
    public void onRegisterAugments(IAugmentRegistry registry) {
        ModAugments.onRegisterAugments(registry);
    }
}
