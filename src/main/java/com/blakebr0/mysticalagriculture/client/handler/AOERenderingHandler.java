package com.blakebr0.mysticalagriculture.client.handler;


import com.blakebr0.mysticalagriculture.api.MysticalAgricultureDataComponentTypes;
import com.blakebr0.mysticalagriculture.api.components.AOEOffsetComponent;
import com.blakebr0.mysticalagriculture.api.tinkering.AOEAugment;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import com.blakebr0.mysticalagriculture.augment.MiningAOEAugment;
import com.blakebr0.mysticalagriculture.network.payloads.UpdateAOEOffsetPayload;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import java.util.List;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import net.neoforged.neoforge.network.PacketDistributor;


/**
 * This class handles rendering AOE area rendering and changing.
 */
public class AOERenderingHandler
{
    @SubscribeEvent
    public void onKey(ClientTickEvent.Post event) {

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.level == null || minecraft.player == null) {
            return;
        }

        // Do anything only if control is pressed.
        if (!InputConstants.isKeyDown(minecraft.getWindow().getWindow(), InputConstants.KEY_RCONTROL) &&
            !InputConstants.isKeyDown(minecraft.getWindow().getWindow(), InputConstants.KEY_LCONTROL)) return;

        ItemStack mainHandItem = minecraft.player.getMainHandItem();
        List<Augment> augments = AugmentUtils.getAugments(mainHandItem);
        if (augments.isEmpty()) return;

        // Get range based on player action.
        int range = getRange(augments, minecraft.player.isCrouching());
        if (range == 0) return;

        AOEOffsetComponent offsetComponent =
            mainHandItem.getOrDefault(MysticalAgricultureDataComponentTypes.AOE_OFFSET,
                new AOEOffsetComponent(0, 0));

        final int horizontalOffset;
        final int verticalOffset;

        if (InputConstants.isKeyDown(minecraft.getWindow().getWindow(), InputConstants.KEY_LEFT))
        {
            horizontalOffset = Mth.clamp(offsetComponent.horizontalOffset() - 1, -range, range);
            verticalOffset = offsetComponent.verticalOffset();
        }
        else if (InputConstants.isKeyDown(minecraft.getWindow().getWindow(), InputConstants.KEY_RIGHT))
        {
            horizontalOffset = Mth.clamp(offsetComponent.horizontalOffset() + 1, -range, range);
            verticalOffset = offsetComponent.verticalOffset();
        }
        else if (InputConstants.isKeyDown(minecraft.getWindow().getWindow(), InputConstants.KEY_DOWN))
        {
            horizontalOffset = offsetComponent.horizontalOffset();
            verticalOffset = Mth.clamp(offsetComponent.verticalOffset() - 1, -range, range);
        }
        else if (InputConstants.isKeyDown(minecraft.getWindow().getWindow(), InputConstants.KEY_UP))
        {
            horizontalOffset = offsetComponent.horizontalOffset();
            verticalOffset = Mth.clamp(offsetComponent.verticalOffset() + 1, -range, range);
        }
        else
        {
            horizontalOffset = offsetComponent.horizontalOffset();
            verticalOffset = offsetComponent.verticalOffset();
        }

        PacketDistributor.sendToServer(new UpdateAOEOffsetPayload(new AOEOffsetComponent(horizontalOffset, verticalOffset)));
    }


    /**
     * This method gets that largest AOE augment size.
     * @param augmentComponents The list of augments.
     * @param crouching indicates that need tiling or pathing AOE upgrade.
     * @return integer that
     */
    private static int getRange(List<Augment> augmentComponents, boolean crouching)
    {
        int range = 0;

        for (Augment augment : augmentComponents)
        {
            if (crouching && augment instanceof MiningAOEAugment)
            {
                // Skip mining augment if player is crouching
                continue;
            }

            if (augment instanceof AOEAugment aoeAugment)
            {
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
    public void onBlockHighlight(RenderHighlightEvent.Block event)
    {
        if (!(event.getCamera().getEntity() instanceof Player player)) return;
        if (!InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), InputConstants.KEY_RCONTROL) &&
            !InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), InputConstants.KEY_LCONTROL)) return;

        ItemStack mainHandItem = player.getMainHandItem();
        List<Augment> augments = AugmentUtils.getAugments(mainHandItem);
        if (augments.isEmpty()) return;

        int range = getRange(augments, player.isCrouching());
        if (range == 0) return;

        // Work on rendering
        PoseStack poseStack = event.getPoseStack();
        Camera camera = event.getCamera();
        Vec3 cameraPos = camera.getPosition();

        poseStack.pushPose();
        poseStack.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        VertexConsumer buffer = event.getMultiBufferSource().getBuffer(RenderType.lines());

        // Define the color and alpha for the outline (green in this case)
        float red = player.isCrouching() ? 1.0F : 0.0F;
        float green = player.isCrouching() ? 0.5F : 1.0F;
        float blue = 0.0F;
        float alpha = 0.7F;

        Direction direction = player.isCrouching() ? Direction.UP : event.getTarget().getDirection();

        // Iterate through each block position and render its outline
        MiningAOEAugment.getAOEBlocks(mainHandItem, range, event.getTarget().getBlockPos(), direction, player).
            forEach(blockPos ->
            {
                BlockState blockState = player.level().getBlockState(blockPos);

                // Do not render air block outline
                if (blockState.isAir()) return;

                // Contract the block box to the face player is looking at
                AABB box = new AABB(blockPos).contract(
                    -direction.getStepX(),
                    -direction.getStepY(),
                    -direction.getStepZ()
                );

                // Render outline.
                LevelRenderer.renderLineBox(poseStack, buffer, box, red, green, blue, alpha);
            });

        poseStack.popPose();
    }
}
