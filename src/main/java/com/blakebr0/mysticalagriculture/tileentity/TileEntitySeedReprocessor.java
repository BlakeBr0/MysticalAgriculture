package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.util.VanillaPacketDispatcher;
import com.blakebr0.mysticalagriculture.crafting.ReprocessorManager;
import com.blakebr0.mysticalagriculture.util.TileEntityUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
// TODO: I am genuinely curious how the fuck this class happened
public class TileEntitySeedReprocessor extends TileEntityUtil implements ISidedInventory, ITickable, ICapabilityProvider {
	
    private ItemStack input = ItemStack.EMPTY, processing = ItemStack.EMPTY, output = ItemStack.EMPTY;
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
    	if(input == null){
    		input = ItemStack.EMPTY;
    	}
        if(input != null || !input.isEmpty()){
            NBTTagCompound produce = new NBTTagCompound();
            input.writeToNBT(produce);
            tag.setTag("Input", produce);
        } else
            tag.removeTag("Input");
        if(processing != null || !processing.isEmpty()) {
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
        if(output != null || !output.isEmpty()) {
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
        this.input = new ItemStack(tag.getCompoundTag("Input"));
        this.processing = new ItemStack(tag.getCompoundTag("Processing"));
        this.output = new ItemStack(tag.getCompoundTag("Output"));
        if(!processing.isEmpty()) {
            this.target = ReprocessorManager.getPrice(processing);
            if(target != 0) {
                this.progress = tag.getInteger("Progress");
                if (tag.hasKey("Ingredient"))
                    this.ingredient = tag.getString("Ingredient");
            }
            else
                processing = ItemStack.EMPTY;
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
        
		if(world.isRemote)
			return;
        
        if(time_left <= 0){
	        if(!input.isEmpty() && (processing.isEmpty()) ){
	            if(!ReprocessorManager.getOutput(input).isEmpty() && (output.isEmpty() || ReprocessorManager.getOutput(input).isItemEqual(output))) {
	                if(processing.isEmpty()){
	                    processing = ReprocessorManager.getOutput(input);
	                    target = ReprocessorManager.getCost(input);
	                    ingredient = ReprocessorManager.getName(input);
	                    this.setTimeLeft(100);
	                }
	                if(ReprocessorManager.getOutput(input).isItemEqual(processing)) {
	                    int needed = target - progress;
	                    if(needed >= input.getCount()) {
	                        progress += input.getCount();
	                        input = ItemStack.EMPTY;
	                    }
	                    else {
	                        progress = target;
	                        input.shrink(needed);
	                    }
	                }
	                markDirty();
	                packet = true;
	            }
	        }
        }
        if(time_left <= 0){
            if (progress >= target && !processing.isEmpty() && (output.isEmpty() || (output.isItemEqual(processing) && output.getCount() + processing.getCount() <= output.getMaxStackSize()))) {
                if(output.isEmpty()){
                    output = processing.copy();
                }
                else if(output.isItemEqual(processing))
                    output.setCount(output.getCount() + processing.getCount());
                progress -= target;
                if(progress == 0) {
                    processing = ItemStack.EMPTY;
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
            if(input.isEmpty())
                return ItemStack.EMPTY;
            else {
                if(decrement < input.getCount()) {
                    ItemStack take = input.splitStack(decrement);
                    if(input.getCount() <= 0)
                        input = ItemStack.EMPTY;
                    return take;
                } else {
                    ItemStack take = input;
                    input = ItemStack.EMPTY;
                    return take;
                }
            }
        }
        else if (slot == 1){
            if(output.isEmpty())
                return ItemStack.EMPTY;
            else {
                if (decrement < output.getCount()) {
                    ItemStack take = output.splitStack(decrement);
                    if (output.getCount() <= 0)
                        output = ItemStack.EMPTY;
                    return take;
                } else {
                    ItemStack take = output;
                    output = ItemStack.EMPTY;
                    return take;
                }
            }
        }
        return ItemStack.EMPTY;
    }

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq((double)this.getPos().getX() + 0.5D, (double)this.getPos().getY() + 0.5D, (double)this.getPos().getZ() + 0.5D) <= 64.0D;
	}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack){
        if(stack.isEmpty())
            return false;
        if(slot == 0){
            if(processing.isEmpty())
                return true;
            if(ReprocessorManager.getOutput(stack).isEmpty())
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
	public ItemStack removeStackFromSlot(int index){
		return ItemStack.EMPTY;
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

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.UP ? new int[] { 0 } : side == EnumFacing.DOWN ? new int[] { 1 } : new int[] { 0, 1 };
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return index == 1;
	}

	public int getTimeLeft() {
		return time_left; 
	}

	public void setTimeLeft(int time_left) {
		this.time_left = time_left;
	}

	@Override
	public boolean isEmpty() {
		return false;
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
