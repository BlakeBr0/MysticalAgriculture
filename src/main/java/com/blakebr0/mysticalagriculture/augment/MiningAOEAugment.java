package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.helper.BlockHelper;
import com.blakebr0.cucumber.helper.ColorHelper;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;

import java.util.EnumSet;

public class MiningAOEAugment extends Augment {
    private final int range;

    public MiningAOEAugment(ResourceLocation id, int tier, int range) {
        super(id, tier, EnumSet.of(AugmentType.PICKAXE, AugmentType.AXE, AugmentType.SHOVEL), getColor(0xD5FFF6, tier), getColor(0x0EBABD, tier));
        this.range = range;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, Player player) {
        var level = player.getLevel();
        if (level.isClientSide())
            return false;

        if (player.isShiftKeyDown())
            return false;

        var trace = BlockHelper.rayTraceBlocks(level, player);
        int side = trace.getDirection().ordinal();

        harvestAOEBlocks(stack, this.range, level, pos, side, player);

        return false;
    }

    private static void harvestAOEBlocks(ItemStack stack, int radius, Level level, BlockPos pos, int side, Player player) {
        int xRange = radius;
        int yRange = radius;
        int zRange = 0;

        if (side == 0 || side == 1) {
            zRange = radius;
            yRange = 0;
        }

        if (side == 4 || side == 5) {
            xRange = 0;
            zRange = radius;
        }

        var state = level.getBlockState(pos);
        float hardness = state.getDestroySpeed(level, pos);

        if (radius > 0 && hardness >= 0.2F && canHarvestBlock(stack, state)) {
            BlockPos.betweenClosedStream(pos.offset(-xRange, -yRange, -zRange), pos.offset(xRange, yRange, zRange)).forEach(aoePos -> {
                if (aoePos != pos) {
                    var aoeState = level.getBlockState(aoePos);

                    if (level.getBlockEntity(aoePos) == null && aoeState.getDestroySpeed(level, aoePos) <= hardness + 5.0F) {
                        if (canHarvestBlock(stack, aoeState)) {
                            tryHarvestBlock(level, aoePos, true, stack, player);
                        }
                    }
                }
            });
        }
    }

    private static boolean tryHarvestBlock(Level world, BlockPos pos, boolean extra, ItemStack stack, Player player) {
        var state = world.getBlockState(pos);
        float hardness = state.getDestroySpeed(world, pos);
        var harvest = (ForgeHooks.isCorrectToolForDrops(state, player) || stack.isCorrectToolForDrops(state)) && (!extra || stack.getDestroySpeed(state) > 1.0F);

        if (hardness >= 0.0F && (!extra || harvest))
            return BlockHelper.breakBlocksAOE(stack, world, player, pos, !extra);

        return false;
    }

    private static boolean canHarvestBlock(ItemStack stack, BlockState state) {
        return stack.isCorrectToolForDrops(state) || (!state.requiresCorrectToolForDrops() && stack.getDestroySpeed(state) > 1.0F);
    }

    private static int getColor(int color, int tier) {
        return ColorHelper.saturate(color, Math.min((float) tier / 5, 1));
    }
}
