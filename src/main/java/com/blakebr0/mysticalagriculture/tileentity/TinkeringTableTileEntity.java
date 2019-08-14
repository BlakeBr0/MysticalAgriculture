package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.lib.Localizable;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.mysticalagriculture.container.TinkeringTableContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;

public class TinkeringTableTileEntity extends BaseInventoryTileEntity implements INamedContainerProvider {
    private final BaseItemStackHandler inventory = new BaseItemStackHandler(1, this::markDirtyAndDispatch);

    public TinkeringTableTileEntity() {
        super(ModTileEntities.TINKERING_TABLE);
        this.inventory.addSlotLimit(0, 1);
    }

    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public ITextComponent getDisplayName() {
        return Localizable.of("container.mysticalagriculture.tinkering_table").build();
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return TinkeringTableContainer.create(windowId, playerInventory, this.getInventory());
    }
}
