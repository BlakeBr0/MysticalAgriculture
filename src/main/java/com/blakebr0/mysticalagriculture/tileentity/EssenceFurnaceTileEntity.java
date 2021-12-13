package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.FurnaceTier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
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

    public void setItem(int slot, ItemStack stack) {
        ItemStack itemstack = this.items.get(slot);
        boolean flag = !stack.isEmpty() && stack.sameItem(itemstack) && ItemStack.tagMatches(stack, itemstack);
        this.items.set(slot, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        if (slot == 0 && !flag) {
            this.cookingTotalTime = (int) (getTotalCookTime(level, this.recipeType, this) * this.getTier().getCookTimeMultiplier());
            this.cookingProgress = 0;
            this.setChanged();
        }

    }

    protected boolean canBurn(Recipe<?> recipe, NonNullList<ItemStack> items, int maxStackSize) {
        if (!items.get(0).isEmpty() && recipe != null) {
            ItemStack itemstack = ((Recipe<WorldlyContainer>) recipe).assemble(this);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = items.get(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.sameItem(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= maxStackSize && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }

    protected boolean burn(Recipe<?> recipe, NonNullList<ItemStack> items, int maxStackSize) {
        if (recipe != null && this.canBurn(recipe, items, maxStackSize)) {
            ItemStack itemstack = items.get(0);
            ItemStack itemstack1 = ((Recipe<WorldlyContainer>) recipe).assemble(this);
            ItemStack itemstack2 = items.get(2);
            if (itemstack2.isEmpty()) {
                items.set(2, itemstack1.copy());
            } else if (itemstack2.is(itemstack1.getItem())) {
                itemstack2.grow(itemstack1.getCount());
            }

            if (itemstack.is(Blocks.WET_SPONGE.asItem()) && !items.get(1).isEmpty() && items.get(1).is(Items.BUCKET)) {
                items.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemstack.shrink(1);
            return true;
        } else {
            return false;
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, EssenceFurnaceTileEntity tile) {
        var flag = tile.isLit();
        var flag1 = false;

        if (tile.isLit()) {
            --tile.litTime;
        }

        var stack = tile.items.get(1);
        if (tile.isLit() || !stack.isEmpty() && !tile.items.get(0).isEmpty()) {
            Recipe<?> recipe = level.getRecipeManager().getRecipeFor((RecipeType<AbstractCookingRecipe>)tile.recipeType, tile, level).orElse(null);
            int i = tile.getMaxStackSize();
            if (!tile.isLit() && tile.canBurn(recipe, tile.items, i)) {
                tile.litTime = tile.getBurnDuration(stack);
                tile.litDuration = tile.litTime;
                if (tile.isLit()) {
                    flag1 = true;
                    if (stack.hasContainerItem())
                        tile.items.set(1, stack.getContainerItem());
                    else
                    if (!stack.isEmpty()) {
                        stack.shrink(1);

                        if (stack.isEmpty()) {
                            tile.items.set(1, stack.getContainerItem());
                        }
                    }
                }
            }

            if (tile.isLit() && tile.canBurn(recipe, tile.items, i)) {
                ++tile.cookingProgress;
                if (tile.cookingProgress == tile.cookingTotalTime) {
                    tile.cookingProgress = 0;
                    tile.cookingTotalTime = (int) (getTotalCookTime(level, tile.recipeType, tile) * tile.getTier().getCookTimeMultiplier());
                    if (tile.burn(recipe, tile.items, i)) {
                        tile.setRecipeUsed(recipe);
                    }

                }
            } else {
                tile.cookingProgress = 0;
            }
        } else if (!tile.isLit() && tile.cookingProgress > 0) {
            tile.cookingProgress = Mth.clamp(tile.cookingProgress - 2, 0, tile.cookingTotalTime);
        }

        if (flag != tile.isLit()) {
            flag1 = true;
            state = state.setValue(AbstractFurnaceBlock.LIT, tile.isLit());
            level.setBlock(pos, state, 3);
        }

        if (flag1) {
            setChanged(level, pos, state);
        }
    }

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
