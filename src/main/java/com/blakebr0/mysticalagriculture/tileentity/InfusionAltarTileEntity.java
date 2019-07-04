package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.tileentity.BaseTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class InfusionAltarTileEntity extends BaseTileEntity implements ISidedInventory {
    private static final int[] SLOTS = { 0, 1 };
    private NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    private LazyOptional<? extends IItemHandler> wrapper = LazyOptional.of(() -> new SidedInvWrapper(this, Direction.DOWN));

    public InfusionAltarTileEntity() {
        super(ModTileEntities.INFUSION_ALTAR);
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return this.inventory.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return this.inventory.get(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int count) {
        return ItemStackHelper.getAndSplit(this.inventory, i, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int i) {
        return ItemStackHelper.getAndRemove(this.inventory, i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack stack) {
        this.inventory.set(i, stack);
        this.markDirty();
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        if (this.world.getTileEntity(this.pos) != this) {
            return false;
        } else {
            return !(player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) > 64.0D);
        }
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (!this.removed && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return this.wrapper.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return SLOTS;
    }

    @Override
    public boolean canInsertItem(int i, ItemStack stack, Direction direction) {
        return i == 0 && this.getStackInSlot(0).getCount() < this.getInventoryStackLimit() && this.getStackInSlot(1).isEmpty();
    }

    @Override
    public boolean canExtractItem(int i, ItemStack stack, Direction direction) {
        return i == 1;
    }

    @Override
    public void read(CompoundNBT tag) {
        super.read(tag);
        ItemStackHelper.loadAllItems(tag, this.inventory);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        super.write(tag);
        ItemStackHelper.saveAllItems(tag, this.inventory);
        return tag;
    }
}
