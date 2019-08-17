package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.block.InfusedFarmlandBlock;
import com.blakebr0.mysticalagriculture.item.ModItems;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ColorHandler {
    @SubscribeEvent
    public void onBlockColors(ColorHandlerEvent.Block event) {
        BlockColors colors = event.getBlockColors();

        colors.register(new IColored.BlockColors(), InfusedFarmlandBlock.FARMLANDS.toArray(new InfusedFarmlandBlock[0]));

        CropRegistry.getInstance().getRegisteredCrops().forEach(crop -> {
            if (crop.isFlowerColored())
                colors.register((state, world, pos, tint) -> crop.getFlowerColor(), crop.getCrop());
        });
    }

    @SubscribeEvent
    public void onItemColors(ColorHandlerEvent.Item event) {
        ItemColors colors = event.getItemColors();

        colors.register(new IColored.ItemBlockColors(), InfusedFarmlandBlock.FARMLANDS.toArray(new InfusedFarmlandBlock[0]));
        colors.register((stack, tint) -> {
            float damage = (float) (stack.getMaxDamage() - stack.getDamage()) / stack.getMaxDamage();
            return Utils.saturate(0x00D9D9, damage);
        }, ModItems.INFUSION_CRYSTAL);

        CropRegistry.getInstance().getRegisteredCrops().forEach(crop -> {
            if (crop.isEssenceColored())
                colors.register((stack, tint) -> crop.getEssenceColor(), crop.getEssence());
            if (crop.isSeedColored())
                colors.register((stack, tint) -> crop.getSeedColor(), crop.getSeeds());
        });
    }
}
