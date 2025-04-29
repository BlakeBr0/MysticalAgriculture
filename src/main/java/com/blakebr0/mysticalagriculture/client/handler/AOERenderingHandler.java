package com.blakebr0.mysticalagriculture.client.handler;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureDataComponentTypes;
import com.blakebr0.mysticalagriculture.api.components.AOEOffsetComponent;
import com.blakebr0.mysticalagriculture.api.tinkering.AOEAugment;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import com.blakebr0.mysticalagriculture.augment.MiningAOEAugment;
import com.blakebr0.mysticalagriculture.network.payloads.UpdateAOEOffsetPayload;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

/**
 * This class handles rendering AOE area rendering and changing.
 */
public class AOERenderingHandler {
    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {
        var minecraft = Minecraft.getInstance();
        if (minecraft.level == null || minecraft.player == null)
            return;

        var window = minecraft.getWindow().getWindow();

        // do anything only if control is pressed
        if (!InputConstants.isKeyDown(window, InputConstants.KEY_RCONTROL) && !InputConstants.isKeyDown(window, InputConstants.KEY_LCONTROL))
            return;

        ItemStack mainHandItem = minecraft.player.getMainHandItem();
        List<Augment> augments = AugmentUtils.getAugments(mainHandItem);
        if (augments.isEmpty()) return;

        // Get range based on player action.
        int range = getRange(augments, minecraft.player.isCrouching());
        if (range == 0)
            return;

        var offset = mainHandItem.getOrDefault(MysticalAgricultureDataComponentTypes.AOE_OFFSET, AOEOffsetComponent.DEFAULT);

        final int horizontalOffset;
        final int verticalOffset;

        if (InputConstants.isKeyDown(minecraft.getWindow().getWindow(), InputConstants.KEY_LEFT)) {
            horizontalOffset = Mth.clamp(offset.horizontalOffset() - 1, -range, range);
            verticalOffset = offset.verticalOffset();
        } else if (InputConstants.isKeyDown(minecraft.getWindow().getWindow(), InputConstants.KEY_RIGHT)) {
            horizontalOffset = Mth.clamp(offset.horizontalOffset() + 1, -range, range);
            verticalOffset = offset.verticalOffset();
        } else if (InputConstants.isKeyDown(minecraft.getWindow().getWindow(), InputConstants.KEY_DOWN)) {
            horizontalOffset = offset.horizontalOffset();
            verticalOffset = Mth.clamp(offset.verticalOffset() - 1, -range, range);
        } else if (InputConstants.isKeyDown(minecraft.getWindow().getWindow(), InputConstants.KEY_UP)) {
            horizontalOffset = offset.horizontalOffset();
            verticalOffset = Mth.clamp(offset.verticalOffset() + 1, -range, range);
        } else {
            horizontalOffset = offset.horizontalOffset();
            verticalOffset = offset.verticalOffset();
        }

        PacketDistributor.sendToServer(new UpdateAOEOffsetPayload(new AOEOffsetComponent(horizontalOffset, verticalOffset)));
    }


    /**
     * This method gets that largest AOE augment size.
     * @param augments The list of augments.
     * @param crouching indicates that need tiling or pathing AOE upgrade.
     * @return the range
     */
    private static int getRange(List<Augment> augments, boolean crouching) {
        int range = 0;

        for (var augment : augments) {
            if (crouching && augment instanceof MiningAOEAugment) {
                // Skip mining augment if player is crouching
                continue;
            }

            if (augment instanceof AOEAugment aoeAugment) {
                range = Math.max(range, aoeAugment.getRange());
            }
        }

        return range;
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

        var window = Minecraft.getInstance().getWindow().getWindow();

        if (!InputConstants.isKeyDown(window, InputConstants.KEY_RCONTROL) && !InputConstants.isKeyDown(window, InputConstants.KEY_LCONTROL))
            return;

        ItemStack mainHandItem = player.getMainHandItem();
        List<Augment> augments = AugmentUtils.getAugments(mainHandItem);
        if (augments.isEmpty()) return;

        int range = getRange(augments, player.isCrouching());
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

        MiningAOEAugment.getAOEBlocks(mainHandItem, range, event.getTarget().getBlockPos(), direction, player).
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
