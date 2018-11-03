package com.blakebr0.mysticalagriculture.tileentity.reprocessor;

import com.blakebr0.mysticalagriculture.util.VanillaPacketDispatcher;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;

public class TilePrudentiumReprocessor extends TileEssenceReprocessor {

	@Override
	public int getOperationTime() {
		return 80;
	}
	
	@Override
	public int getFuelUsage() {
		return 2;
	}

	@Override
	public int getFuelCapacity() {
		return 25600;
	}
	
    @Override
    public void markDirty() {
    	super.markDirty();
    	VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
    }
    
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), -1, this.getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet) {
		this.readFromNBT(packet.getNbtCompound());
	}

	@Override
	public final NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
}
