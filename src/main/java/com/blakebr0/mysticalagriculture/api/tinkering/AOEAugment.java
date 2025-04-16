package com.blakebr0.mysticalagriculture.api.tinkering;


import com.blakebr0.mysticalagriculture.api.MysticalAgricultureDataComponentTypes;
import com.blakebr0.mysticalagriculture.api.components.AOEOffsetComponent;
import java.util.EnumSet;
import java.util.stream.Stream;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


/**
 * This class adds range variable to Augment object.
 */
public abstract class AOEAugment extends Augment
{
    protected final int range;

    public AOEAugment(ResourceLocation id,
        int tier,
        EnumSet<AugmentType> types,
        int primaryColor,
        int secondaryColor,
        int range)
    {
        super(id, tier, types, primaryColor, secondaryColor);
        this.range = range;
    }


    public int getRange()
    {
        return this.range;
    }


    /**
     * This method returns a stream of blocks that should be affected by AOE augment.
     * @return the stream of block positions that are affected by AOE.
     */
    public static Stream<BlockPos> getAOEBlocks(ItemStack stack, int radius, BlockPos pos, Direction side, Player player)
    {
        int xRange = radius;
        int yRange = radius;
        int zRange = 0;

        int horizontalOffset = 0;
        int verticalOffset = 0;

        if (stack.has(MysticalAgricultureDataComponentTypes.AOE_OFFSET))
        {
            AOEOffsetComponent aoeOffsetComponent = stack.getOrDefault(
                MysticalAgricultureDataComponentTypes.AOE_OFFSET,
                new AOEOffsetComponent(0, 0));

            horizontalOffset = Mth.clamp(aoeOffsetComponent.horizontalOffset(), -radius, radius);
            verticalOffset = Mth.clamp(aoeOffsetComponent.verticalOffset(), -radius, radius);
        }

        BlockPos offsetPos = pos;

        if (side == Direction.DOWN || side == Direction.UP) {
            zRange = radius;
            yRange = 0;

            // move offset position
            offsetPos = offsetPos.relative(player.getDirection().getClockWise(), horizontalOffset);
            offsetPos = offsetPos.relative(
                player.getDirection(),
                verticalOffset);
        }
        else
        {
            // move offset position
            offsetPos = offsetPos.relative(side.getCounterClockWise(), horizontalOffset);
            offsetPos = offsetPos.relative(Direction.UP, verticalOffset);
        }

        if (side == Direction.WEST || side == Direction.EAST) {
            xRange = 0;
            zRange = radius;
        }

        return BlockPos.betweenClosedStream(
            offsetPos.offset(-xRange, -yRange, -zRange),
            offsetPos.offset(xRange, yRange, zRange));
    }
}
