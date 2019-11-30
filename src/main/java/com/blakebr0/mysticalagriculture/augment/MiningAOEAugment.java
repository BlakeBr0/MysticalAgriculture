package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.util.ToolTools;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ToolType;

import java.util.EnumSet;

public class MiningAOEAugment extends Augment {
    public MiningAOEAugment(ResourceLocation id, int tier) {
        super(id, tier, EnumSet.of(AugmentType.PICKAXE, AugmentType.AXE, AugmentType.SHOVEL), 0x123432, 0x122223);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, PlayerEntity player) {
        boolean blocks = false;
        float f = player.rotationPitch;
        float f1 = player.rotationYaw;
        Vec3d vec3d = player.getEyePosition(1.0F);
        float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
        float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = player.getAttribute(PlayerEntity.REACH_DISTANCE).getValue();;
        Vec3d vec3d1 = vec3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        BlockRayTraceResult ray = player.getEntityWorld().rayTraceBlocks(new RayTraceContext(vec3d, vec3d1, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, player));
            int side = ((BlockRayTraceResult) ray).getFace().ordinal();
            blocks = this.harvest(stack, 1, player.getEntityWorld(), pos, side, player);
        return blocks;
    }

    private boolean harvest(ItemStack stack, int radius, World world, BlockPos pos, int side, PlayerEntity player) {
        int xRange = radius;
        int yRange = radius;
        int zRange = 0;

        if(side == 0 || side == 1){
            zRange = radius;
            yRange = 0;
        }

        if(side == 4 || side == 5){
            xRange = 0;
            zRange = radius;
        }

        BlockState state = world.getBlockState(pos);
        float hardness = state.getBlockHardness(world, pos);

        if(!canHarvest(world, pos, false, stack, player)){
            return false;
        }

        if(radius > 0 && hardness >= 0.2F && state.getBlock().getHarvestTool(state) == null || state.getBlock().isToolEffective(state, ToolType.PICKAXE)){
            BlockPos.getAllInBox(pos.add(-xRange, -yRange, -zRange), pos.add(xRange, yRange, zRange)).forEach(aoePos -> {
                if(aoePos != pos){
                    BlockState aoeState = world.getBlockState(aoePos);
                    if(aoeState.getBlockHardness(world, aoePos) <= hardness + 5.0F){
                        if(aoeState.getBlock().isToolEffective(state, ToolType.PICKAXE)){
                            canHarvest(world, aoePos, true, stack, player);
                        }
                    } else {
//                        returrn false;
                    }
                }
            });
        }
        return true;
    }

    private boolean canHarvest(World world, BlockPos pos, boolean extra, ItemStack stack, PlayerEntity player){
        BlockState state = world.getBlockState(pos);
        float hardness = state.getBlockHardness(world, pos);
        boolean harvest = (ForgeHooks.canHarvestBlock(state, player, world, pos) || stack.getItem().canHarvestBlock(stack, state)) && (!extra || stack.getItem().getDestroySpeed(stack, world.getBlockState(pos)) > 1.0F);
        if(hardness >= 0.0F && (!extra || harvest)){
            return ToolTools.breakBlocksAOE(stack, world, player, pos);
        }
        return false;
    }
}
