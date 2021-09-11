package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.FurnaceTier;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class EssenceFurnaceTileEntity extends AbstractFurnaceBlockEntity {
    public EssenceFurnaceTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, RecipeType.SMELTING);
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

    // TODO: how would I do this now
//    @Override
//    protected int getTotalCookTime(Level level, RecipeType<? extends AbstractCookingRecipe> recipeType, Container container) {
//        return (int) (super.getTotalCookTime(level, recipeType, container) * this.getTier().getCookTimeMultiplier());
//    }

    public abstract FurnaceTier getTier();

    public static class Inferium extends EssenceFurnaceTileEntity {
        public Inferium(BlockPos pos, BlockState state) {
            super(ModTileEntities.INFERIUM_FURNACE.get(), pos, state);
        }

        @Override
        public FurnaceTier getTier() {
            return FurnaceTier.INFERIUM;
        }
    }

    public static class Prudentium extends EssenceFurnaceTileEntity {
        public Prudentium(BlockPos pos, BlockState state) {
            super(ModTileEntities.PRUDENTIUM_FURNACE.get(), pos, state);
        }

        @Override
        public FurnaceTier getTier() {
            return FurnaceTier.PRUDENTIUM;
        }
    }

    public static class Tertium extends EssenceFurnaceTileEntity {
        public Tertium(BlockPos pos, BlockState state) {
            super(ModTileEntities.TERTIUM_FURNACE.get(), pos, state);
        }

        @Override
        public FurnaceTier getTier() {
            return FurnaceTier.TERTIUM;
        }
    }

    public static class Imperium extends EssenceFurnaceTileEntity {
        public Imperium(BlockPos pos, BlockState state) {
            super(ModTileEntities.IMPERIUM_FURNACE.get(), pos, state);
        }

        @Override
        public FurnaceTier getTier() {
            return FurnaceTier.IMPERIUM;
        }
    }

    public static class Supremium extends EssenceFurnaceTileEntity {
        public Supremium(BlockPos pos, BlockState state) {
            super(ModTileEntities.SUPREMIUM_FURNACE.get(), pos, state);
        }

        @Override
        public FurnaceTier getTier() {
            return FurnaceTier.SUPREMIUM;
        }
    }
}
