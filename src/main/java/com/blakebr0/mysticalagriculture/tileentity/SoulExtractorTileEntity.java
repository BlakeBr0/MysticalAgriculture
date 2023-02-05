package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.energy.DynamicEnergyStorage;
import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.inventory.SidedItemStackHandlerWrapper;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.container.SoulExtractorContainer;
import com.blakebr0.mysticalagriculture.container.inventory.UpgradeItemStackHandler;
import com.blakebr0.mysticalagriculture.crafting.recipe.SoulExtractionRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.item.SoulJarItem;
import com.blakebr0.mysticalagriculture.util.IUpgradeableMachine;
import com.blakebr0.mysticalagriculture.util.MachineUpgradeTier;
import com.blakebr0.mysticalagriculture.util.RecipeIngredientCache;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandlerModifiable;

public class SoulExtractorTileEntity extends BaseInventoryTileEntity implements MenuProvider, IUpgradeableMachine {
    private static final int FUEL_TICK_MULTIPLIER = 20;
    public static final int OPERATION_TIME = 100;
    public static final int FUEL_USAGE = 40;
    public static final int FUEL_CAPACITY = 80000;

    private final BaseItemStackHandler inventory;
    private final UpgradeItemStackHandler upgradeInventory;
    private final DynamicEnergyStorage energy;
    private final LazyOptional<IItemHandlerModifiable>[] inventoryCapabilities;
    private final LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(this::getEnergy);
    private int progress;
    private int fuelLeft;
    private int fuelItemValue;
    private SoulExtractionRecipe recipe;
    private MachineUpgradeTier tier;

    public SoulExtractorTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.SOUL_EXTRACTOR.get(), pos, state);
        this.inventory = createInventoryHandler(this::markDirtyAndDispatch);
        this.upgradeInventory = new UpgradeItemStackHandler();
        this.energy = new DynamicEnergyStorage(FUEL_CAPACITY, this::markDirtyAndDispatch);
        this.inventoryCapabilities = SidedItemStackHandlerWrapper.create(this.inventory, new Direction[] { Direction.UP, Direction.DOWN, Direction.NORTH }, this::canInsertStackSided, this::canExtractStackSided);
    }

    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        this.progress = tag.getInt("Progress");
        this.fuelLeft = tag.getInt("FuelLeft");
        this.fuelItemValue = tag.getInt("FuelItemValue");
        this.energy.deserializeNBT(tag.get("Energy"));
        this.upgradeInventory.deserializeNBT(tag.getCompound("UpgradeInventory"));
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putInt("Progress", this.progress);
        tag.putInt("FuelLeft", this.fuelLeft);
        tag.putInt("FuelItemValue", this.fuelItemValue);
        tag.putInt("Energy", this.energy.getEnergyStored());
        tag.put("UpgradeInventory", this.upgradeInventory.serializeNBT());
    }

    @Override
    public Component getDisplayName() {
        return Localizable.of("container.mysticalagriculture.soul_extractor").build();
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return SoulExtractorContainer.create(id, playerInventory, this.inventory, this.upgradeInventory, this.getBlockPos());
    }

    @Override
    public UpgradeItemStackHandler getUpgradeInventory() {
        return this.upgradeInventory;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (!this.isRemoved()) {
            if (cap == ForgeCapabilities.ENERGY) {
                return ForgeCapabilities.ENERGY.orEmpty(cap, this.energyCapability);
            }

            if (side != null && cap == ForgeCapabilities.ITEM_HANDLER) {
                if (side == Direction.UP) {
                    return this.inventoryCapabilities[0].cast();
                } else if (side == Direction.DOWN) {
                    return this.inventoryCapabilities[1].cast();
                } else {
                    return this.inventoryCapabilities[2].cast();
                }
            }
        }

        return super.getCapability(cap, side);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SoulExtractorTileEntity tile) {
        var mark = false;

        if (tile.energy.getEnergyStored() < tile.energy.getMaxEnergyStored()) {
            var fuel = tile.inventory.getStackInSlot(1);

            if (tile.fuelLeft <= 0 && !fuel.isEmpty()) {
                tile.fuelItemValue = ForgeHooks.getBurnTime(fuel, null);

                if (tile.fuelItemValue > 0) {
                    tile.fuelLeft = tile.fuelItemValue *= FUEL_TICK_MULTIPLIER;
                    tile.inventory.setStackInSlot(1, StackHelper.shrink(tile.inventory.getStackInSlot(1), 1, false));

                    mark = true;
                }
            }

            if (tile.fuelLeft > 0) {
                var fuelPerTick = Math.min(Math.min(tile.fuelLeft, tile.getFuelUsage() * 2), tile.energy.getMaxEnergyStored() - tile.energy.getEnergyStored());

                tile.fuelLeft -= tile.energy.receiveEnergy(fuelPerTick, false);

                if (tile.fuelLeft <= 0)
                    tile.fuelItemValue = 0;

                mark = true;
            }
        }

        if (tile.recipe == null || !tile.recipe.matches(tile.inventory)) {
            var recipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.SOUL_EXTRACTION.get(), tile.inventory.toIInventory(), level).orElse(null);
            tile.recipe = recipe instanceof SoulExtractionRecipe ? (SoulExtractionRecipe) recipe : null;
        }

        var tier = tile.getMachineTier();

        if (tier != tile.tier) {
            tile.tier = tier;

            if (tier == null) {
                tile.energy.resetMaxEnergyStorage();
            } else {
                tile.energy.setMaxEnergyStorage((int) (FUEL_CAPACITY * tier.getFuelCapacityMultiplier()));
            }

            mark = true;
        }

        if (tile.recipe != null) {
            if (tile.energy.getEnergyStored() >= tile.getFuelUsage()) {
                tile.progress++;
                tile.energy.extractEnergy(tile.getFuelUsage(), false);

                if (tile.progress >= tile.getOperationTime()) {
                    tile.inventory.setStackInSlot(0, StackHelper.shrink(tile.inventory.getStackInSlot(1), 1, false));
                    tile.inventory.setStackInSlot(2, tile.recipe.assemble(tile.inventory));

                    tile.progress = 0;
                }

                mark = true;
            }
        } else {
            if (tile.progress > 0) {
                tile.progress = 0;

                mark = true;
            }
        }

        if (mark) {
            tile.markDirtyAndDispatch();
        }
    }

    public static BaseItemStackHandler createInventoryHandler() {
        return createInventoryHandler(null);
    }

    public static BaseItemStackHandler createInventoryHandler(Runnable onContentsChanged) {
        return BaseItemStackHandler.create(3, onContentsChanged);
    }

    public DynamicEnergyStorage getEnergy() {
        return this.energy;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getOperationTime() {
        if (this.tier == null)
            return OPERATION_TIME;

        return (int) (OPERATION_TIME * this.tier.getOperationTimeMultiplier());
    }

    public int getFuelLeft() {
        return this.fuelLeft;
    }

    public int getFuelItemValue() {
        return this.fuelItemValue;
    }

    public int getFuelUsage() {
        if (this.tier == null)
            return FUEL_USAGE;

        return (int) (FUEL_USAGE * this.tier.getFuelUsageMultiplier());
    }

    private boolean canInsertStackSided(int slot, ItemStack stack, Direction direction) {
        if (direction == null)
            return true;
        if (slot == 0 && direction == Direction.UP)
            return RecipeIngredientCache.INSTANCE.isValidInput(stack, ModRecipeTypes.SOUL_EXTRACTION.get());
        if (slot == 1 && direction == Direction.NORTH)
            return FurnaceBlockEntity.isFuel(stack);
        if (slot == 2 && direction == Direction.NORTH)
            return stack.getItem() instanceof SoulJarItem;

        return false;
    }

    private boolean canExtractStackSided(int slot, Direction direction) {
        if (slot == 2 && direction == Direction.DOWN) {
            var stack = this.inventory.getStackInSlot(2);
            return stack.getItem() instanceof SoulJarItem && MobSoulUtils.isJarFull(stack);
        }

        return false;
    }
}
