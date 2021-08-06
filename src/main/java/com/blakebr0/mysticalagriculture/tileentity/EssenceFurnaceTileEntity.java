package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.block.EssenceFurnaceBlock;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.network.chat.Component;

public abstract class EssenceFurnaceTileEntity extends AbstractFurnaceBlockEntity {
    public EssenceFurnaceTileEntity(BlockEntityType<?> type) {
        super(type, RecipeType.SMELTING);
    }

    @Override
    protected Component getDefaultName() {
        return Localizable.of(String.format("container.mysticalagriculture.%s_furnace", this.getTier().getName())).build();
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new FurnaceMenu(id, player, this, this.dataAccess);
    }

    @Override
    protected int getBurnDuration(ItemStack stack) {
        return (int) (super.getBurnDuration(stack) * this.getTier().getBurnTimeMultiplier());
    }

    @Override
    protected int getTotalCookTime() {
        return (int) (super.getTotalCookTime() * this.getTier().getCookTimeMultiplier());
    }

    public abstract EssenceFurnaceBlock.FurnaceTier getTier();

    public static class Inferium extends EssenceFurnaceTileEntity {
        public Inferium() {
            super(ModTileEntities.INFERIUM_FURNACE.get());
        }

        @Override
        public EssenceFurnaceBlock.FurnaceTier getTier() {
            return EssenceFurnaceBlock.FurnaceTier.INFERIUM;
        }
    }

    public static class Prudentium extends EssenceFurnaceTileEntity {
        public Prudentium() {
            super(ModTileEntities.PRUDENTIUM_FURNACE.get());
        }

        @Override
        public EssenceFurnaceBlock.FurnaceTier getTier() {
            return EssenceFurnaceBlock.FurnaceTier.PRUDENTIUM;
        }
    }

    public static class Tertium extends EssenceFurnaceTileEntity {
        public Tertium() {
            super(ModTileEntities.TERTIUM_FURNACE.get());
        }

        @Override
        public EssenceFurnaceBlock.FurnaceTier getTier() {
            return EssenceFurnaceBlock.FurnaceTier.TERTIUM;
        }
    }

    public static class Imperium extends EssenceFurnaceTileEntity {
        public Imperium() {
            super(ModTileEntities.IMPERIUM_FURNACE.get());
        }

        @Override
        public EssenceFurnaceBlock.FurnaceTier getTier() {
            return EssenceFurnaceBlock.FurnaceTier.IMPERIUM;
        }
    }

    public static class Supremium extends EssenceFurnaceTileEntity {
        public Supremium() {
            super(ModTileEntities.SUPREMIUM_FURNACE.get());
        }

        @Override
        public EssenceFurnaceBlock.FurnaceTier getTier() {
            return EssenceFurnaceBlock.FurnaceTier.SUPREMIUM;
        }
    }
}
