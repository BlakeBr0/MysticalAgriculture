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
}
