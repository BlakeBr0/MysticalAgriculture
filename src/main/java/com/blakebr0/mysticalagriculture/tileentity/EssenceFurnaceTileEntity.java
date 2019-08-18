package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.lib.Localizable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;

public class EssenceFurnaceTileEntity extends AbstractFurnaceTileEntity {
    public EssenceFurnaceTileEntity() {
        super(ModTileEntities.ESSENCE_FURNACE, IRecipeType.SMELTING);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return Localizable.of("container.furnace").build();
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new FurnaceContainer(id, player, this, this.furnaceData);
    }
}
