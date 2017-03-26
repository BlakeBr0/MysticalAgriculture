package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.mysticalagriculture.crafting.TinkeringTableManager;
import com.blakebr0.mysticalagriculture.tileentity.TileEntityTinkeringTable;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraftforge.items.ItemStackHandler;

public class TinkeringTableStackHandler extends ItemStackHandler {

    public InventoryCrafting crafting;
    private TileEntityTinkeringTable tile;

    public TinkeringTableStackHandler(int size, TileEntityTinkeringTable tile){
        super(size);
        this.tile = tile;
    }

    @Override
    protected void onContentsChanged(int slot) {
        if(crafting != null)
            tile.setResult(TinkeringTableManager.getInstance().findMatchingRecipe(this.crafting, tile.getWorld()));
        super.onContentsChanged(slot);
    }
}