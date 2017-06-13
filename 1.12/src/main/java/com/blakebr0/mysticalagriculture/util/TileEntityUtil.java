package com.blakebr0.mysticalagriculture.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityUtil extends TileEntity {

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		writeCustomNBT(tag);
		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		readCustomNBT(tag);
	}

	public NBTTagCompound writeCustomNBT(NBTTagCompound tag){ return tag; }
	public void readCustomNBT(NBTTagCompound tag){}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket(){
		NBTTagCompound tag = new NBTTagCompound();
		writeCustomNBT(tag);
		return new SPacketUpdateTileEntity(pos, -1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet){
		readCustomNBT(packet.getNbtCompound());
	}

}
