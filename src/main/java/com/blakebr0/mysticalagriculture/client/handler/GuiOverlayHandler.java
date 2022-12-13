package com.blakebr0.mysticalagriculture.client.handler;

import com.blakebr0.cucumber.helper.ColorHelper;
import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.block.InfusedFarmlandBlock;
import com.blakebr0.mysticalagriculture.crafting.recipe.AwakeningRecipe;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import com.blakebr0.mysticalagriculture.registry.AugmentRegistry;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.blakebr0.mysticalagriculture.tileentity.AwakeningAltarTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.EssenceVesselTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.InfusionAltarTileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class GuiOverlayHandler {
    private static final IGuiOverlay ALTAR_OVERLAY = (gui, matrix, partialTicks, width, height) -> {
        var mc = Minecraft.getInstance();

        if (mc.level == null)
            return;

        if (mc.hitResult instanceof BlockHitResult result) {
            var pos = result.getBlockPos();
            var tile = mc.level.getBlockEntity(pos);
            var stack = ItemStack.EMPTY;

            if (tile instanceof InfusionAltarTileEntity altar) {
                var recipe = altar.getActiveRecipe();
                if (recipe != null) {
                    stack = recipe.getResultItem();
                }
            }

            if (tile instanceof AwakeningAltarTileEntity altar) {
                var recipe = altar.getActiveRecipe();
                if (recipe != null) {
                    stack = recipe.getResultItem();

                    drawEssenceRequirements(matrix, recipe, altar);
                }
            }

            if (!stack.isEmpty()) {
                int x = mc.getWindow().getGuiScaledWidth() / 2 - 11;
                int y = mc.getWindow().getGuiScaledHeight() / 2 - 8;

                mc.getItemRenderer().renderAndDecorateItem(stack, x + 26, y);
                mc.getItemRenderer().renderGuiItemDecorations(mc.font, stack, x + 26, y);
                mc.font.drawShadow(matrix, stack.getHoverName(), x + 48, y + 5, 16383998);
            }
        }
    };

    private static final IGuiOverlay ESSENCE_VESSEL_OVERLAY = (gui, matrix, partialTicks, width, height) -> {
        var mc = Minecraft.getInstance();

        if (mc.level == null)
            return;

        if (mc.hitResult instanceof BlockHitResult result) {
            var pos = result.getBlockPos();
            var tile = mc.level.getBlockEntity(pos);

            if (tile instanceof EssenceVesselTileEntity vessel) {
                var stack = vessel.getInventory().getStackInSlot(0);

                if (!stack.isEmpty()) {
                    int x = mc.getWindow().getGuiScaledWidth() / 2 - 11;
                    int y = mc.getWindow().getGuiScaledHeight() / 2 - 8;

                    mc.getItemRenderer().renderAndDecorateItem(stack, x + 26, y);
                    mc.getItemRenderer().renderGuiItemDecorations(mc.font, stack, x + 26, y);
                    mc.font.drawShadow(matrix, stack.getHoverName(), x + 48, y + 5, 16383998);
                }
            }
        }
    };

    @SubscribeEvent
    public void onRegisterGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.CROSSHAIR.id(), "altar_overlay", ALTAR_OVERLAY);
        event.registerAbove(VanillaGuiOverlay.CROSSHAIR.id(), "essence_vessel_overlay", ESSENCE_VESSEL_OVERLAY);
    }

    private static void drawEssenceRequirements(PoseStack matrix, AwakeningRecipe recipe, AwakeningAltarTileEntity altar) {
        var mc = Minecraft.getInstance();

        int x = mc.getWindow().getGuiScaledWidth() / 2 - 11;
        int y = mc.getWindow().getGuiScaledHeight() / 2 - 4;
        var lineHeight = mc.font.lineHeight + 6;

        var requirements = recipe.getEssenceRequirements();
        var vessels = altar.getEssenceVessels();

        var hasMissingEssences = false;
        var xOffset = 0;

        for (var vessel : vessels) {
            var stack = vessel.getInventory().getStackInSlot(0);

            if (stack.is(ModCrops.AIR.getEssenceItem()) && stack.getCount() < requirements.air()) {
                mc.getItemRenderer().renderAndDecorateItem(stack, x + 26 + xOffset, y + 2 * lineHeight);
                mc.font.drawShadow(matrix, getEssenceDisplayName(stack, requirements.air()), x + 48 + xOffset, y + 5 + 2 * lineHeight, 16383998);
                xOffset += 56;
                hasMissingEssences = true;
            }

            if (stack.is(ModCrops.EARTH.getEssenceItem()) && stack.getCount() < requirements.earth()) {
                mc.getItemRenderer().renderAndDecorateItem(stack, x + 26 + xOffset, y + 2 * lineHeight);
                mc.font.drawShadow(matrix, getEssenceDisplayName(stack, requirements.earth()), x + 48 + xOffset, y + 5 + 2 * lineHeight, 16383998);
                xOffset += 56;
                hasMissingEssences = true;
            }

            if (stack.is(ModCrops.WATER.getEssenceItem()) && stack.getCount() < requirements.water()) {
                mc.getItemRenderer().renderAndDecorateItem(stack, x + 26 + xOffset, y + 2 * lineHeight);
                mc.font.drawShadow(matrix, getEssenceDisplayName(stack, requirements.water()), x + 48 + xOffset, y + 5 + 2 * lineHeight, 16383998);
                xOffset += 56;
                hasMissingEssences = true;
            }

            if (stack.is(ModCrops.FIRE.getEssenceItem()) && stack.getCount() < requirements.fire()) {
                mc.getItemRenderer().renderAndDecorateItem(stack, x + 26 + xOffset, y + 2 * lineHeight);
                mc.font.drawShadow(matrix, getEssenceDisplayName(stack, requirements.fire()), x + 48 + xOffset, y + 5 + 2 * lineHeight, 16383998);
                xOffset += 56;
                hasMissingEssences = true;
            }
        }

        if (hasMissingEssences) {
            mc.font.drawShadow(matrix, ModTooltips.MISSING_ESSENCES.buildString(), x + 28, y + 5 + lineHeight, 16383998);
        }
    }

    private static String getEssenceDisplayName(ItemStack stack, int required) {
        return stack.getCount() + "/" + required;
    }

    public static final class ColorHandler {
        @SubscribeEvent
        public void onBlockColors(RegisterColorHandlersEvent.Block event) {
            event.register(new IColored.BlockColors(), InfusedFarmlandBlock.FARMLANDS.toArray(new InfusedFarmlandBlock[0]));

            CropRegistry.getInstance().getCrops().forEach(crop -> {
                if (crop.isFlowerColored() && crop.getCropBlock() != null)
                    event.register((state, world, pos, tint) -> crop.getFlowerColor(), crop.getCropBlock());
            });
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

            CropRegistry.getInstance().getCrops().forEach(crop -> {
                if (crop.isEssenceColored() && crop.getEssenceItem() != null)
                    event.register((stack, tint) -> crop.getEssenceColor(), crop.getEssenceItem());
                if (crop.isSeedColored() && crop.getSeedsItem() != null)
                    event.register((stack, tint) -> crop.getSeedColor(), crop.getSeedsItem());
            });

            AugmentRegistry.getInstance().getAugments().forEach(augment -> {
                if (augment.getItem() != null)
                    event.register((stack, tint) -> tint == 0 ? augment.getSecondaryColor() : tint == 1 ? augment.getPrimaryColor() : -1, augment.getItem());
            });
        }
    }
}
