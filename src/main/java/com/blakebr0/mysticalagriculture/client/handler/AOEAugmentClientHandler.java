package com.blakebr0.mysticalagriculture.client.handler;

import com.blakebr0.mysticalagriculture.api.tinkering.AOEAugment;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import com.blakebr0.mysticalagriculture.network.payloads.UpdateAOEAugmentOffsetPayload;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.BlockOutlineRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.core.Direction;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.CustomBlockOutlineRenderer;
import net.neoforged.neoforge.client.event.ExtractBlockOutlineRenderStateEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public final class AOEAugmentClientHandler {
    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {
        var minecraft = Minecraft.getInstance();
        if (minecraft.level == null || minecraft.player == null)
            return;

        var stack = minecraft.player.getMainHandItem();
        var item = stack.getItem();
        if (!(item instanceof ITinkerable))
            return;

        var window = minecraft.getWindow();

        // do anything only if control is pressed
        if (!InputConstants.isKeyDown(window, InputConstants.KEY_RCONTROL) && !InputConstants.isKeyDown(window, InputConstants.KEY_LCONTROL))
            return;

        int range = AugmentUtils.getMaxAOEAugmentRange(stack);
        if (range == 0)
            return;

        int horizontalOffsetChange = 0;
        int verticalOffsetChange = 0;

        if (InputConstants.isKeyDown(window, InputConstants.KEY_LEFT)) {
            horizontalOffsetChange = -1;
        } else if (InputConstants.isKeyDown(window, InputConstants.KEY_RIGHT)) {
            horizontalOffsetChange = 1;
        } else if (InputConstants.isKeyDown(window, InputConstants.KEY_DOWN)) {
            verticalOffsetChange = -1;
        } else if (InputConstants.isKeyDown(window, InputConstants.KEY_UP)) {
            verticalOffsetChange = 1;
        }

        if (horizontalOffsetChange != 0 || verticalOffsetChange != 0) {
            ClientPacketDistributor.sendToServer(new UpdateAOEAugmentOffsetPayload(horizontalOffsetChange, verticalOffsetChange));
        }
    }

    /**
     * This method highlights selected blocks that will be affected by the AOE effect.
     *
     * @param event The block highlight rendering event.
     */
    @SubscribeEvent
    public void onBlockHighlight(ExtractBlockOutlineRenderStateEvent event) {
        var camera = event.getCamera();
        var entity = camera.entity();

        if (!(entity instanceof Player player))
            return;

        var stack = player.getMainHandItem();
        var item = stack.getItem();
        if (!(item instanceof ITinkerable))
            return;

        var window = Minecraft.getInstance().getWindow();

        if (!InputConstants.isKeyDown(window, InputConstants.KEY_RCONTROL) && !InputConstants.isKeyDown(window, InputConstants.KEY_LCONTROL))
            return;

        int range = AugmentUtils.getMaxAOEAugmentRange(stack);
        if (range == 0)
            return;

        var hitResult = event.getHitResult();

        event.addCustomRenderer(new AOEOutlineRenderer(player, stack, range, hitResult));
    }

    record AOEOutlineRenderer(Player player, ItemStack stack, int range,
                              BlockHitResult hitResult) implements CustomBlockOutlineRenderer {
        @Override
        public boolean render(BlockOutlineRenderState renderState, SubmitNodeCollector submitNodeCollector, PoseStack poseStack, LevelRenderState levelRenderState) {
            float red = player.isCrouching() ? 1.0F : 0.0F;
            float green = player.isCrouching() ? 0.5F : 1.0F;
            float blue = 0.0F;
            float alpha = 0.7F;

            var direction = player.isCrouching() ? Direction.UP : hitResult.getDirection();
            var camPos = levelRenderState.cameraRenderState.pos;
            var width = Minecraft.getInstance().gameRenderer.gameRenderState().windowRenderState.appropriateLineWidth;
            int color = ARGB.colorFromFloat(alpha, red, green, blue);

            AOEAugment.getAOEBlocks(stack, range, hitResult.getBlockPos(), direction, player).forEach(aoePos -> {
                var state = player.level().getBlockState(aoePos);
                if (state.isAir())
                    return;

                poseStack.pushPose();
                poseStack.translate(aoePos.getX() - camPos.x, aoePos.getY() - camPos.y, aoePos.getZ() - camPos.z);
                submitNodeCollector.submitShapeOutline(poseStack, Shapes.block(), RenderTypes.lines(), color, width, renderState.isTranslucent());
                poseStack.popPose();
            });

            return true;
        }
    }
}
