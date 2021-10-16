package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.FurnaceTier;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;

public abstract class EssenceFurnaceTileEntity extends AbstractFurnaceTileEntity {
    public EssenceFurnaceTileEntity(TileEntityType<?> type) {
        super(type, IRecipeType.SMELTING);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return Localizable.of(String.format("container.mysticalagriculture.%s_furnace", this.getTier().getName())).build();
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new FurnaceContainer(id, player, this, this.dataAccess);
    }

    @Override
    protected int getBurnDuration(ItemStack stack) {
        return (int) (super.getBurnDuration(stack) * this.getTier().getBurnTimeMultiplier());
    }

    @Override
    protected int getTotalCookTime() {
        return (int) (super.getTotalCookTime() * this.getTier().getCookTimeMultiplier());
    }

    public abstract FurnaceTier getTier();

    public static class Inferium extends EssenceFurnaceTileEntity {
        public Inferium() {
            super(ModTileEntities.INFERIUM_FURNACE.get());
        }

        @Override
        public FurnaceTier getTier() {
            return FurnaceTier.INFERIUM;
        }
    }

    public static class Prudentium extends EssenceFurnaceTileEntity {
        public Prudentium() {
            super(ModTileEntities.PRUDENTIUM_FURNACE.get());
        }

        @Override
        public FurnaceTier getTier() {
            return FurnaceTier.PRUDENTIUM;
        }
    }

    public static class Tertium extends EssenceFurnaceTileEntity {
        public Tertium() {
            super(ModTileEntities.TERTIUM_FURNACE.get());
        }

        @Override
        public FurnaceTier getTier() {
            return FurnaceTier.TERTIUM;
        }
    }

    public static class Imperium extends EssenceFurnaceTileEntity {
        public Imperium() {
            super(ModTileEntities.IMPERIUM_FURNACE.get());
        }

        @Override
        public FurnaceTier getTier() {
            return FurnaceTier.IMPERIUM;
        }
    }

    public static class Supremium extends EssenceFurnaceTileEntity {
        public Supremium() {
            super(ModTileEntities.SUPREMIUM_FURNACE.get());
        }

        @Override
        public FurnaceTier getTier() {
            return FurnaceTier.SUPREMIUM;
        }
    }
}
