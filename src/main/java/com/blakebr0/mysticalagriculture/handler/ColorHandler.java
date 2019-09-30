package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import com.blakebr0.mysticalagriculture.api.soul.MobSoulUtils;
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

        CropRegistry.getInstance().getCrops().forEach(crop -> {
            if (crop.isFlowerColored() && crop.getCrop() != null)
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
        }, ModItems.INFUSION_CRYSTAL::get);
//
        colors.register((stack, tint) -> {
            IMobSoulType type = MobSoulUtils.getType(stack);
            return tint == 1 ? type != null ? type.getColor() : -1 : -1;
        }, ModItems.SOUL_JAR::get);

        CropRegistry.getInstance().getCrops().forEach(crop -> {
            if (crop.isEssenceColored() && crop.getEssence() != null)
                colors.register((stack, tint) -> crop.getEssenceColor(), crop.getEssence());
            if (crop.isSeedColored() && crop.getSeeds() != null)
                colors.register((stack, tint) -> crop.getSeedColor(), crop.getSeeds());
        });
    }
}
