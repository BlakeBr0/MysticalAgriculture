package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.block.InfusedFarmlandBlock;
import com.blakebr0.mysticalagriculture.item.ModItems;
import com.blakebr0.mysticalagriculture.lib.ModCorePlugin;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import com.blakebr0.mysticalagriculture.registry.AugmentRegistry;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Items;

public class ColorHandler {
    public static void onClientSetup() {
        Minecraft minecraft = Minecraft.getInstance();
        BlockColors blockColors = minecraft.getBlockColors();
        ItemColors itemColors = minecraft.getItemColors();

        onBlockColors(blockColors);
        onItemColors(itemColors);
    }

    private static void onBlockColors(BlockColors colors) {
        colors.register(new IColored.BlockColors(), InfusedFarmlandBlock.FARMLANDS.toArray(new InfusedFarmlandBlock[0]));

        CropRegistry.getInstance().getCrops().forEach(crop -> {
            if (crop.isFlowerColored() && crop.getCrop() != null)
                colors.register((state, world, pos, tint) -> crop.getFlowerColor(), crop.getCrop());
        });
    }

    private static void onItemColors(ItemColors colors) {
        colors.register(new IColored.ItemBlockColors(), InfusedFarmlandBlock.FARMLANDS.toArray(new InfusedFarmlandBlock[0]));
        colors.register((stack, tint) -> {
            float damage = (float) (stack.getMaxDamage() - stack.getDamage()) / stack.getMaxDamage();
            return Utils.saturate(0x00D9D9, damage);
        }, ModItems.INFUSION_CRYSTAL.get());

        colors.register((stack, tint) -> ModCrops.AIR.getEssenceColor(), ModItems.AIR_AGGLOMERATIO.get());
        colors.register((stack, tint) -> ModCrops.EARTH.getEssenceColor(), ModItems.EARTH_AGGLOMERATIO.get());
        colors.register((stack, tint) -> ModCrops.WATER.getEssenceColor(), ModItems.WATER_AGGLOMERATIO.get());
        colors.register((stack, tint) -> ModCrops.FIRE.getEssenceColor(), ModItems.FIRE_AGGLOMERATIO.get());

        colors.register((stack, tint) -> {
            IMobSoulType type = MobSoulUtils.getType(stack);
            return tint == 1 && type != null ? type.getColor() : -1;
        }, ModItems.SOUL_JAR.get());

        CropRegistry.getInstance().getCrops().forEach(crop -> {
            if (crop.isEssenceColored() && crop.getEssence() != null)
                colors.register((stack, tint) -> crop.getEssenceColor(), crop.getEssence());
            if (crop.isSeedColored() && crop.getSeeds() != null)
                colors.register((stack, tint) -> crop.getSeedColor(), crop.getSeeds());
        });

        AugmentRegistry.getInstance().getAugments().forEach(augment -> {
            if (augment.getItem() != null)
                colors.register((stack, tint) -> tint == 0 ? augment.getSecondaryColor() : tint == 1 ? augment.getPrimaryColor() : -1, augment.getItem());
        });
    }
}
