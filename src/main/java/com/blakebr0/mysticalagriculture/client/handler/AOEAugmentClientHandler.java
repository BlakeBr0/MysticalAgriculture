package com.blakebr0.mysticalagriculture.client.handler;

import com.blakebr0.mysticalagriculture.api.tinkering.AOEAugment;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import com.blakebr0.mysticalagriculture.network.payloads.UpdateAOEAugmentOffsetPayload;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import net.neoforged.neoforge.network.PacketDistributor;

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

        var window = minecraft.getWindow().getWindow();

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
            PacketDistributor.sendToServer(new UpdateAOEAugmentOffsetPayload(horizontalOffsetChange, verticalOffsetChange));
        }
    }

    /**
     * This method highlights selected blocks that will be affected by AOE effect.
     * @param event The block highlight rendering event.
     */
    @SubscribeEvent
    public void onBlockHighlight(RenderHighlightEvent.Block event) {
        var camera = event.getCamera();
        var entity = camera.getEntity();

        if (!(entity instanceof Player player))
            return;

        var stack = player.getMainHandItem();
        var item = stack.getItem();
        if (!(item instanceof ITinkerable))
            return;

        var window = Minecraft.getInstance().getWindow().getWindow();

        if (!InputConstants.isKeyDown(window, InputConstants.KEY_RCONTROL) && !InputConstants.isKeyDown(window, InputConstants.KEY_LCONTROL))
            return;

        int range = AugmentUtils.getMaxAOEAugmentRange(stack);
        if (range == 0)
            return;

        var poseStack = event.getPoseStack();
        var cameraPos = camera.getPosition();

        poseStack.pushPose();
        poseStack.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);

        var buffer = event.getMultiBufferSource().getBuffer(RenderType.lines());

        float red = player.isCrouching() ? 1.0F : 0.0F;
        float green = player.isCrouching() ? 0.5F : 1.0F;
        float blue = 0.0F;
        float alpha = 0.7F;

        var direction = player.isCrouching() ? Direction.UP : event.getTarget().getDirection();

        AOEAugment.getAOEBlocks(stack, range, event.getTarget().getBlockPos(), direction, player).
            forEach(aoePos -> {
                var state = player.level().getBlockState(aoePos);
                if (state.isAir())
                    return;

                // Contract the block box to the face player is looking at
                var box = new AABB(aoePos).contract(
                    -direction.getStepX(),
                    -direction.getStepY(),
                    -direction.getStepZ()
                );

                LevelRenderer.renderLineBox(poseStack, buffer, box, red, green, blue, alpha);
            });

        poseStack.popPose();
    }
}
