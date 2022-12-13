package com.blakebr0.mysticalagriculture.client.handler;

import com.blakebr0.cucumber.helper.ColorHelper;
import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.block.InfusedFarmlandBlock;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import com.blakebr0.mysticalagriculture.registry.AugmentRegistry;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ColorHandler {
    @SubscribeEvent
    public void onBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register(new IColored.BlockColors(), InfusedFarmlandBlock.FARMLANDS.toArray(new InfusedFarmlandBlock[0]));

        for (var crop : CropRegistry.getInstance().getCrops()) {
            if (crop.isFlowerColored() && crop.getCropBlock() != null)
                event.register((state, world, pos, tint) -> crop.getFlowerColor(), crop.getCropBlock());
        }
    }

    @SubscribeEvent
    public void onItemColors(RegisterColorHandlersEvent.Item event) {
        event.register(new IColored.ItemBlockColors(), InfusedFarmlandBlock.FARMLANDS.toArray(new InfusedFarmlandBlock[0]));
        event.register((stack, tint) -> {
            float damage = (float) (stack.getMaxDamage() - stack.getDamageValue()) / stack.getMaxDamage();
            return ColorHelper.saturate(0x00D9D9, damage);
        }, ModItems.INFUSION_CRYSTAL.get());

        event.register((stack, tint) -> ModCrops.AIR.getEssenceColor(), ModItems.AIR_AGGLOMERATIO.get());
        event.register((stack, tint) -> ModCrops.EARTH.getEssenceColor(), ModItems.EARTH_AGGLOMERATIO.get());
        event.register((stack, tint) -> ModCrops.WATER.getEssenceColor(), ModItems.WATER_AGGLOMERATIO.get());
        event.register((stack, tint) -> ModCrops.FIRE.getEssenceColor(), ModItems.FIRE_AGGLOMERATIO.get());

        event.register((stack, tint) -> {
            var type = MobSoulUtils.getType(stack);
            return tint == 1 && type != null ? type.getColor() : -1;
        }, ModItems.SOUL_JAR.get());

        for (var crop : CropRegistry.getInstance().getCrops()) {
            if (crop.isEssenceColored() && crop.getEssenceItem() != null)
                event.register((stack, tint) -> crop.getEssenceColor(), crop.getEssenceItem());
            if (crop.isSeedColored() && crop.getSeedsItem() != null)
                event.register((stack, tint) -> crop.getSeedColor(), crop.getSeedsItem());
        }

        for (var augment : AugmentRegistry.getInstance().getAugments()) {
            if (augment.getItem() != null)
                event.register((stack, tint) -> tint == 0 ? augment.getSecondaryColor() : tint == 1 ? augment.getPrimaryColor() : -1, augment.getItem());
        }
    }
}
