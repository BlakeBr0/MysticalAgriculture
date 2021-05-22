package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.helper.BlockHelper;
import com.blakebr0.cucumber.helper.ColorHelper;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.util.EnumSet;

public class MiningAOEAugment extends Augment {
    private final int range;

    public MiningAOEAugment(ResourceLocation id, int tier, int range) {
        super(id, tier, EnumSet.of(AugmentType.PICKAXE, AugmentType.AXE, AugmentType.SHOVEL), getColor(0xD5FFF6, tier), getColor(0x0EBABD, tier));
        this.range = range;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, PlayerEntity player) {
        World world = player.getEntityWorld();
        BlockRayTraceResult trace = BlockHelper.rayTraceBlocks(world, player);
        int side = trace.getFace().ordinal();

        return !harvest(stack, this.range, world, pos, side, player);
    }

    private static boolean harvest(ItemStack stack, int radius, World world, BlockPos pos, int side, PlayerEntity player) {
        if (player.isCrouching())
            radius = 0;

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

        BlockState state = world.getBlockState(pos);
        float hardness = state.getBlockHardness(world, pos);

        if (!tryHarvestBlock(world, pos, false, stack, player))
            return false;

        if (radius > 0 && hardness >= 0.2F && canHarvestBlock(stack, state)) {
            BlockPos.getAllInBox(pos.add(-xRange, -yRange, -zRange), pos.add(xRange, yRange, zRange)).forEach(aoePos -> {
                if (aoePos != pos) {
                    BlockState aoeState = world.getBlockState(aoePos);
                    if (!aoeState.hasTileEntity() && aoeState.getBlockHardness(world, aoePos) <= hardness + 5.0F) {
                        if (canHarvestBlock(stack, aoeState)) {
                            tryHarvestBlock(world, aoePos, true, stack, player);
                        }
                    }
                }
            });
        }

        return true;
    }

    private static boolean tryHarvestBlock(World world, BlockPos pos, boolean extra, ItemStack stack, PlayerEntity player) {
        BlockState state = world.getBlockState(pos);
        float hardness = state.getBlockHardness(world, pos);
        boolean harvest = (ForgeHooks.canHarvestBlock(state, player, world, pos) || stack.canHarvestBlock(state)) && (!extra || stack.getDestroySpeed(state) > 1.0F);

        if (hardness >= 0.0F && (!extra || harvest))
            return BlockHelper.breakBlocksAOE(stack, world, player, pos, !extra);

        return false;
    }

    private static boolean canHarvestBlock(ItemStack stack, BlockState state) {
        return stack.canHarvestBlock(state) || (!state.getRequiresTool() && stack.getDestroySpeed(state) > 1.0F);
    }

    private static int getColor(int color, int tier) {
        return ColorHelper.saturate(color, Math.min((float) tier / 5, 1));
    }
}
