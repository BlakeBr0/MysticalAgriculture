package com.blakebr0.mysticalagriculture.tileentity;

import java.util.ArrayList;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.crafting.ReprocessorManager;
import com.blakebr0.mysticalagriculture.util.TileEntityUtil;
import com.blakebr0.mysticalagriculture.util.VanillaPacketDispatcher;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class TileEntitySeedReprocessor extends TileEntityUtil implements ISidedInventory, ITickable {
	
    private ItemStack input, processing, output;
    private int facing = 2;
    private int progress = 0;
    private int target = 0;
    private int time_left;
    private String ingredient;

    private int packetCount;
    private boolean packet;

    private static final int[] top = new int[]{0};
    private static final int[] sides = new int[]{1};
    
    public boolean timeLeft(){
    	return this.getTimeLeft() > 0;
    }
    
    @Override
    public NBTTagCompound writeCustomNBT(NBTTagCompound tag){
    	tag.setInteger("TimeLeft", this.time_left);
        tag.setShort("Facing", (short) this.facing);
        if(input != null) {
            NBTTagCompound produce = new NBTTagCompound();
            input.writeToNBT(produce);
            tag.setTag("Input", produce);
        }
        else
            tag.removeTag("Input");
        if(processing != null) {
            NBTTagCompound produce = new NBTTagCompound();
            processing.writeToNBT(produce);
            tag.setTag("Processing", produce);
            tag.setInteger("Progress", this.progress);
            if(ingredient != null)
                tag.setString("Ingredient", this.ingredient);
            else
                tag.removeTag("Ingredient");
        }
        else {
            tag.removeTag("Processing");
            tag.removeTag("Progress");
            tag.removeTag("Target");
            tag.removeTag("Ingredient");
        }
        if(output != null) {
            NBTTagCompound produce = new NBTTagCompound();
            output.writeToNBT(produce);
            tag.setTag("Output", produce);
        }
        else
            tag.removeTag("Output");
        return tag;
    }
    
    @Override
    public void readCustomNBT(NBTTagCompound tag){
    	this.time_left = tag.getInteger("TimeLeft");
        this.input = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Input"));
        this.processing = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Processing"));
        this.output = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Output"));
        if(processing != null) {
            this.target = ReprocessorManager.getPrice(processing);
            if(target != 0) {
                this.progress = tag.getInteger("Progress");
                if (tag.hasKey("Ingredient"))
                    this.ingredient = tag.getString("Ingredient");
            }
            else
                processing = null;
        }
        else {
            progress = 0;
            target = 0;
            ingredient = null;
        }
        this.facing = tag.getShort("Facing");
    }
    
    @Override
    public void update(){
        if(packetCount > 0){
            packetCount--;
        }
        
        if(time_left > 0)
        	time_left--;
        
		if(worldObj.isRemote)
			return;
        
        if(time_left <= 0){
	        if(input != null && (processing == null) ){
	            if(ReprocessorManager.getOutput(input) != null && (output == null || ReprocessorManager.getOutput(input).isItemEqual(output))) {
	                if(processing == null){
	                    processing = ReprocessorManager.getOutput(input);
	                    target = ReprocessorManager.getCost(input);
	                    ingredient = ReprocessorManager.getName(input);
	                    this.setTimeLeft(100);
	                }
	                if(ReprocessorManager.getOutput(input).isItemEqual(processing)) {
	                    int needed = target - progress;
	                    if(needed >= input.stackSize) {
	                        progress += input.stackSize;
	                        input = null;
	                    }
	                    else {
	                        progress = target;
	                        input.stackSize -= needed;
	                    }
	                }
	                markDirty();
	                packet = true;
	            }
	        }
        }
        if(time_left <= 0){
            if (progress >= target && processing != null && (output == null || (output.isItemEqual(processing) && output.stackSize + processing.stackSize <= output.getMaxStackSize()))) {
                if(output == null){
                    output = processing.copy();
                }
                else if(output.isItemEqual(processing))
                    output.stackSize+= processing.stackSize;
                progress -= target;
                if(progress == 0) {
                    processing = null;
                    ingredient = null;
                }
                markDirty();
                packet = true;
            }
        }

        if(packet && packetCount <= 0) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
            packetCount = 10;
            packet = false;
        }
    }

    public int getFacing(){
        return facing;
    }

    public void setFacing(int dir){
        facing = dir;
    }

    public int getProgress(){
        return progress;
    }

    public int getTarget(){
        return target;
    }

    public String getIngredient(){
        return ingredient;
    }
    
    @Override
    public int getSizeInventory()
    {
        return 2;
    }

    @Override
    public ItemStack getStackInSlot(int slot){
        if(slot == 0){
            return input;
        } else {
            return output;
        }
    }

    @Override
    public ItemStack decrStackSize(int slot, int decrement){
        if(slot == 0) {
            if (input == null)
                return null;
            else {
                if (decrement < input.stackSize) {
                    ItemStack take = input.splitStack(decrement);
                    if (input.stackSize <= 0)
                        input = null;
                    return take;
                } else {
                    ItemStack take = input;
                    input = null;
                    return take;
                }
            }
        }
        else if (slot == 1){
            if (output == null)
                return null;
            else {
                if (decrement < output.stackSize) {
                    ItemStack take = output.splitStack(decrement);
                    if (output.stackSize <= 0)
                        output = null;
                    return take;
                } else {
                    ItemStack take = output;
                    output = null;
                    return take;
                }
            }
        }
        return null;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.getPos()) == this && player.getDistanceSq((double)this.getPos().getX() + 0.5D, (double)this.getPos().getY() + 0.5D, (double)this.getPos().getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack){
        if(stack == null)
            return false;
        if(slot == 0){
            if(processing == null)
                return true;
            if(ReprocessorManager.getOutput(stack) == null)
                return false;
            if(ReprocessorManager.getOutput(stack).isItemEqual(processing))
                return true;
        }
        return false;
    }

    @Override
    public int getInventoryStackLimit(){
        return 64;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack){
        if(slot == 0)
            input = stack;
        else if(slot == 1)
            output = stack;
    }

    public boolean canInsertItem(int slot, ItemStack stack, int side){
        return isItemValidForSlot(slot, stack);
    }

    public boolean canExtractItem(int slot, ItemStack stack, int side){
        if(slot == 1 && side != 1)
            return true;
        return false;
    }

    @Override
	public ItemStack removeStackFromSlot(int index) {
		return null;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

    public int[] getSlotsForFace(EnumFacing side){
        return side == EnumFacing.DOWN ? sides : (side == EnumFacing.UP ? top : sides);
    }

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return true;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return true;
	}

	public int getTimeLeft() {
		return time_left;
	}

	public void setTimeLeft(int time_left) {
		this.time_left = time_left;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing side) {
		return this.getCapability(capability, side) != null;
	}
	
    @Override
    public <T> T getCapability(Capability<T> capability, net.minecraft.util.EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
        	return (T) new SidedInvWrapper(this, facing);
        }
        return super.getCapability(capability, facing);
    }
}
