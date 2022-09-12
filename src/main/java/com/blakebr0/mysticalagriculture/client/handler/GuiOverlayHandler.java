package com.blakebr0.mysticalagriculture.client.handler;

import com.blakebr0.mysticalagriculture.tileentity.AwakeningAltarTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.EssenceVesselTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.InfusionAltarTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
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
}
