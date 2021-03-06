package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.helper.ColorHelper;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;

public class PathingAOEAugment extends Augment {
    private static final Map<Block, BlockState> PATH_LOOKUP = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.GRASS_PATH.defaultBlockState()));
    private final int range;

    public PathingAOEAugment(ResourceLocation id, int tier, int range) {
        super(id, tier, EnumSet.of(AugmentType.SHOVEL), getColor(0xAA8D4A, tier), getColor(0x856B3A, tier));
        this.range = range;
    }

    @Override
    public boolean onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player == null)
            return false;

        ItemStack stack = context.getItemInHand();
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        Hand hand = context.getHand();

        boolean playedSound = false;

        if (tryPath(stack, player, world, pos, direction, hand)) {
            world.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

            playedSound = true;

            if (!player.isCrouching())
                return false;
        }

        if (player.isCrouching()) {
            Iterator<BlockPos> positions = BlockPos.betweenClosedStream(pos.offset(-this.range, 0, -this.range), pos.offset(this.range, 0, this.range)).iterator();

            while (positions.hasNext()) {
                BlockPos aoePos = positions.next();

                if (tryPath(stack, player, world, aoePos, direction, hand) && !playedSound) {
                    world.playSound(player, pos, SoundEvents.HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    playedSound = true;
                }
            }
        }

        return true;
    }

    private static boolean tryPath(ItemStack stack, PlayerEntity player, World world, BlockPos pos, Direction direction, Hand hand) {
        if (direction != Direction.DOWN && world.isEmptyBlock(pos.above())) {
            BlockState state = PATH_LOOKUP.get(world.getBlockState(pos).getBlock());
            if (state != null) {
                if (!world.isClientSide()) {
                    world.setBlock(pos, state, 11);
                    if (player != null) {
                        stack.hurtAndBreak(1, player, (entity) -> {
                            entity.broadcastBreakEvent(hand);
                        });
                    }
                }

                return true;
            }
        }

        return false;
    }

    private static int getColor(int color, int tier) {
        return ColorHelper.saturate(color, Math.min((float) tier / 5, 1));
    }
}
