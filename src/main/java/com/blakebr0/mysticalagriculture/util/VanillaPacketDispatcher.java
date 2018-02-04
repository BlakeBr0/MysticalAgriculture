package com.blakebr0.mysticalagriculture.util;

import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class VanillaPacketDispatcher {

    public static void dispatchTEToNearbyPlayers(TileEntity tile){
        World world = tile.getWorld();
        List players = world.playerEntities;
        for(Object player : players){
            if(player instanceof EntityPlayerMP){
                EntityPlayerMP mp = (EntityPlayerMP) player;
                if(pointDistancePlane(mp.posX, mp.posZ, tile.getPos().getX() + 0.5, tile.getPos().getZ() + 0.5) < 64){
                    ((EntityPlayerMP) player).connection.sendPacket(tile.getUpdatePacket());
                }
            }
        }
    }

    public static void dispatchTEToNearbyPlayers(World world, int x, int y, int z){
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
        if(tile != null)
            dispatchTEToNearbyPlayers(tile);
    }

    public static float pointDistancePlane(double x1, double y1, double x2, double y2){
        return (float) Math.hypot(x1 - x2, y1 - y2);
    }
}
