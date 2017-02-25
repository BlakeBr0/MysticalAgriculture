package com.blakebr0.mysticalagriculture.items.tools;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

public class ToolTools {
	
	/*
	 * Parts of the code used in this class is derived from
	 * Actually Additions, by Ellpeck (https://github.com/Ellpeck/ActuallyAdditions)
	 * Draconic Evolution, by brandon3055 (https://github.com/brandon3055/Draconic-Evolution)
	 * I do not claim to own or have created any of the code that came from those mods
	 */
	
    private static RayTraceResult getPosWithinReach(World world, EntityPlayer player, double distance, boolean p1, boolean p2, boolean p3){
        float f = player.rotationPitch;
        float f1 = player.rotationYaw;
        double d0 = player.posX;
        double d1 = player.posY + (double)player.getEyeHeight();
        double d2 = player.posZ;
        Vec3d vec = new Vec3d(d0, d1, d2);
        float f2 = MathHelper.cos(-f1 * 0.017453292F - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        float f6 = f3*f4;
        float f7 = f2*f4;
        Vec3d vec1 = vec.addVector((double)f6 * distance, (double)f5 * distance, (double)f7 * distance);
        return world.rayTraceBlocks(vec, vec1, p1, p2, p3);
    }

    public static RayTraceResult getBlockWithinReach(World world, EntityPlayer player){
        return getBlockWithinReach(world, player, false, true, false);
    }

    public static RayTraceResult getBlockWithinReach(World world, EntityPlayer player, boolean stopOnLiquids, boolean ignoreBlockWithoutBoundingBox, boolean returnLastUncollidableBlock){
        return getPosWithinReach(world, player, player instanceof EntityPlayerMP ? ((EntityPlayerMP)player).interactionManager.getBlockReachDistance() : 5.0D, stopOnLiquids, ignoreBlockWithoutBoundingBox, returnLastUncollidableBlock);
    }

    public static boolean breakBlocksAOE(ItemStack stack, World world, EntityPlayer player, BlockPos pos){
        if(world.isAirBlock(pos)){
            return false;
        }

        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if(!world.isRemote){
            world.playEvent(player, 2001, pos, Block.getStateId(state));
        } else {
            world.playEvent(2001, pos, Block.getStateId(state));
        }

        if(player.capabilities.isCreativeMode){
            block.onBlockHarvested(world, pos, state, player);
            if(block.removedByPlayer(state, world, pos, player, false)){
                block.onBlockDestroyedByPlayer(world, pos, state);
            }

            if(!world.isRemote){
                if(player instanceof EntityPlayerMP){
                    ((EntityPlayerMP)player).connection.sendPacket(new SPacketBlockChange(world, pos));
                }
            }
            return true;
        }

        stack.onBlockDestroyed(world, state, pos, player);

        if(!world.isRemote){
            if(player instanceof EntityPlayerMP){
                EntityPlayerMP mplayer = (EntityPlayerMP)player;

                int xp = ForgeHooks.onBlockBreakEvent(world, mplayer.interactionManager.getGameType(), mplayer, pos);
                if(xp == -1){
                    return false;
                }

                TileEntity tile = world.getTileEntity(pos);
                if(block.removedByPlayer(state, world, pos, player, true)){
                    block.onBlockDestroyedByPlayer(world, pos, state);
                    block.harvestBlock(world, player, pos, state, tile, stack);
                    block.dropXpOnBlockBreak(world, pos, xp);
                }

                mplayer.connection.sendPacket(new SPacketBlockChange(world, pos));
                return true;
            }
        } else {
            if(block.removedByPlayer(state, world, pos, player, true)){
                block.onBlockDestroyedByPlayer(world, pos, state);
            }

            Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, Minecraft.getMinecraft().objectMouseOver.sideHit));

            return true;
        }
        return false;
    }
}