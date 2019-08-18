package com.blakebr0.mysticalagriculture.tileentity.furnace;

import com.blakebr0.cucumber.lib.Localizable;
import com.blakebr0.mysticalagriculture.block.EssenceFurnaceBlock;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;

public abstract class EssenceFurnaceTileEntity extends AbstractFurnaceTileEntity {
    public EssenceFurnaceTileEntity(TileEntityType<?> type, IRecipeType<? extends AbstractCookingRecipe> recipeType) {
        super(type, recipeType);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return Localizable.of(String.format("container.mysticalagriculture.%s_furnace", this.getTier().getName())).build();
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new FurnaceContainer(id, player, this, this.furnaceData);
    }

    @Override
    protected int getBurnTime(ItemStack stack) {
        return (int) (super.getBurnTime(stack) * this.getTier().getBurnTimeMultiplier());
    }

    @Override
    protected int func_214005_h() {
        return (int) (super.func_214005_h() * this.getTier().getCookTimeMultiplier());
    }

    public abstract EssenceFurnaceBlock.FurnaceTier getTier();
}
