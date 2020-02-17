package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.util.Utils;
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
import java.util.Map;

public class PathingAOEAugment extends Augment {
    private static final Map<Block, BlockState> PATH_LOOKUP = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.GRASS_PATH.getDefaultState()));
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

        ItemStack stack = context.getItem();
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        Direction direction = context.getFace();
        Hand hand = context.getHand();
        if (!this.tryPath(stack, player, world, pos, direction, hand) && !player.isCrouching())
            return false;

        if (player.isCrouching()) {
            BlockPos.getAllInBox(pos.add(-this.range, 0, -this.range), pos.add(this.range, 0, this.range)).forEach(aoePos -> {
                this.tryPath(stack, player, world, aoePos, direction, hand);
            });
        }

        return true;
    }

    private boolean tryPath(ItemStack stack, PlayerEntity player, World world, BlockPos pos, Direction direction, Hand hand) {
        if (direction != Direction.DOWN && world.isAirBlock(pos.up())) {
            BlockState state = PATH_LOOKUP.get(world.getBlockState(pos).getBlock());
            if (state != null) {
                world.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isRemote()) {
                    world.setBlockState(pos, state, 11);
                    if (player != null) {
                        stack.damageItem(1, player, (entity) -> {
                            entity.sendBreakAnimation(hand);
                        });
                    }
                }

                return true;
            }
        }

        return false;
    }

    private static int getColor(int color, int tier) {
        return Utils.saturate(color, Math.min((float) tier / 5, 1));
    }
}
