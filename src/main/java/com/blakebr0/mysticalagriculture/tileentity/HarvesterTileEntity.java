package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.energy.DynamicEnergyStorage;
import com.blakebr0.cucumber.helper.CropHelper;
import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.block.HarvesterBlock;
import com.blakebr0.mysticalagriculture.container.HarvesterContainer;
import com.blakebr0.mysticalagriculture.container.inventory.UpgradeItemStackHandler;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.IUpgradeableMachine;
import com.blakebr0.mysticalagriculture.util.MachineUpgradeTier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.ArrayList;
import java.util.List;

public class HarvesterTileEntity extends BaseInventoryTileEntity implements MenuProvider, IUpgradeableMachine {
    private static final int FUEL_TICK_MULTIPLIER = 20;
    public static final int OPERATION_TIME = 100;
    public static final int FUEL_USAGE = 40;
    public static final int SCAN_FUEL_USAGE = 10;
    public static final int FUEL_CAPACITY = 80000;
    public static final int BASE_RANGE = 1;

    private final BaseItemStackHandler inventory;
    private final UpgradeItemStackHandler upgradeInventory;
    private final DynamicEnergyStorage energy;
    private final LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(this::getEnergy);
    private List<BlockPos> positions;
    private BlockPos lastPosition = BlockPos.ZERO;
    private MachineUpgradeTier tier;
    private Direction direction;
    private int progress;
    private int fuelLeft;
    private int fuelItemValue;
    private boolean isRunning;

    public HarvesterTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.HARVESTER.get(), pos, state);
        this.inventory = createInventoryHandler(this::setChangedFast);
        this.upgradeInventory = new UpgradeItemStackHandler();
        this.energy = new DynamicEnergyStorage(FUEL_CAPACITY, this::setChangedFast);
    }

    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public Component getDisplayName() {
        return Localizable.of("container.mysticalagriculture.harvester").build();
    }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return HarvesterContainer.create(i, inventory, this.inventory, this.upgradeInventory, this.getBlockPos());
    }

    @Override
    public UpgradeItemStackHandler getUpgradeInventory() {
        return this.upgradeInventory;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        this.progress = tag.getInt("Progress");
        this.fuelLeft = tag.getInt("FuelLeft");
        this.fuelItemValue = tag.getInt("FuelItemValue");
        this.energy.deserializeNBT(tag.get("Energy"));
        this.lastPosition = BlockPos.of(tag.getLong("LastPosition"));
        this.upgradeInventory.deserializeNBT(tag.getCompound("UpgradeInventory"));
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putInt("Progress", this.progress);
        tag.putInt("FuelLeft", this.fuelLeft);
        tag.putInt("FuelItemValue", this.fuelItemValue);
        tag.putInt("Energy", this.energy.getEnergyStored());
        tag.putLong("LastPosition", this.lastPosition.asLong());
        tag.put("UpgradeInventory", this.upgradeInventory.serializeNBT());
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (!this.isRemoved()) {
            if (cap == ForgeCapabilities.ENERGY) {
                return ForgeCapabilities.ENERGY.orEmpty(cap, this.energyCapability);
            }
        }

        return super.getCapability(cap, side);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, HarvesterTileEntity tile) {
        if (tile.energy.getEnergyStored() < tile.energy.getMaxEnergyStored()) {
            var fuel = tile.inventory.getStackInSlot(0);

            if (tile.fuelLeft <= 0 && !fuel.isEmpty()) {
                tile.fuelItemValue = ForgeHooks.getBurnTime(fuel, null);

                if (tile.fuelItemValue > 0) {
                    tile.fuelLeft = tile.fuelItemValue *= FUEL_TICK_MULTIPLIER;
                    tile.inventory.setStackInSlot(0, StackHelper.shrink(fuel, 1, true));

                    tile.setChangedFast();
                }
            }

            if (tile.fuelLeft > 0) {
                var fuelPerTick = Math.min(Math.min(tile.fuelLeft, tile.getFuelUsage() * 2), tile.energy.getMaxEnergyStored() - tile.energy.getEnergyStored());

                tile.fuelLeft -= tile.energy.receiveEnergy(fuelPerTick, false);

                if (tile.fuelLeft <= 0)
                    tile.fuelItemValue = 0;

                tile.setChangedFast();
            }
        }

        var tier = tile.getMachineTier();
        var direction = state.getValue(HarvesterBlock.FACING);

        if (tier != tile.tier || direction != tile.direction) {
            var range = tier != null ? BASE_RANGE + tier.getAddedRange() : BASE_RANGE;
            var center = pos.relative(direction, range + 1);

            tile.tier = tier;
            tile.direction = direction;
            tile.positions = getWorkingArea(center, range, direction);

            if (tier == null) {
                tile.energy.resetMaxEnergyStorage();
            } else {
                tile.energy.setMaxEnergyStorage((int) (FUEL_CAPACITY * tier.getFuelCapacityMultiplier()));
            }

            tile.setChangedFast();
        }

        var isDisabled = level.hasNeighborSignal(tile.getBlockPos());
        var operationTime = tile.getOperationTime();

        if (tile.progress > operationTime && !isDisabled) {
            var nextPos = tile.findNextPosition();
            var cropState = level.getBlockState(nextPos);
            var block = cropState.getBlock();

            if (block instanceof CropBlock crop) {
                var seed = CropHelper.getSeedsItem(block);
                if (seed != null && crop.isMaxAge(cropState)) {
                    var drops = Block.getDrops(cropState, (ServerLevel) level, nextPos, tile);

                    for (var drop : drops) {
                        var item = drop.getItem();

                        if (!drop.isEmpty() && item == seed) {
                            drop.shrink(1);
                            break;
                        }
                    }

                    for (var drop : drops) {
                        if (!drop.isEmpty()) {
                            tile.addItemToInventory(drop, level, nextPos);
                        }
                    }

                    level.setBlockAndUpdate(nextPos, crop.getStateForAge(0));

                    tile.energy.extractEnergy(tile.getFuelUsage(), false);
                } else {
                    tile.energy.extractEnergy(SCAN_FUEL_USAGE, false);
                }
            }

            tile.progress = 0;

            tile.setChangedFast();
        }

        var wasRunning = tile.isRunning;

        if (!isDisabled && tile.energy.getEnergyStored() >= tile.getFuelUsage()) {
            tile.progress++;
            tile.isRunning = true;
            tile.setChangedFast();
        } else {
            tile.isRunning = false;
        }

        if (wasRunning != tile.isRunning) {
            level.setBlock(pos, state.setValue(HarvesterBlock.RUNNING, tile.isRunning), 3);

            tile.setChangedFast();
        }

        tile.dispatchIfChanged();
    }

    public static BaseItemStackHandler createInventoryHandler() {
        return createInventoryHandler(null);
    }

    public static BaseItemStackHandler createInventoryHandler(Runnable onContentsChanged) {
        return BaseItemStackHandler.create(16, onContentsChanged, builder -> {
            builder.setCanInsert((slot, stack) -> slot == 0 && ForgeHooks.getBurnTime(stack, null) > 0);
            builder.setCanExtract(slot -> slot > 0);
        });
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

    private static List<BlockPos> getWorkingArea(BlockPos center, int range, Direction direction) {
        var positions = new ArrayList<BlockPos>();

        switch (direction) {
            case NORTH -> {
                for (int x = -range; x < range + 1; x++) {
                    for (int z = -range; z < range + 1; z++) {
                        positions.add(new BlockPos(center.getX() + x, center.getY(), center.getZ() + z));
                    }
                }
            }
            case SOUTH -> {
                for (int x = range; x > -range - 1; x--) {
                    for (int z = range; z > -range - 1; z--) {
                        positions.add(new BlockPos(center.getX() + x, center.getY(), center.getZ() + z));
                    }
                }
            }
            case EAST -> {
                for (int z = -range; z < range + 1; z++) {
                    for (int x = range; x > -range - 1; x--) {
                        positions.add(new BlockPos(center.getX() + x, center.getY(), center.getZ() + z));
                    }
                }
            }
            case WEST -> {
                for (int z = range; z > -range - 1; z--) {
                    for (int x = -range; x < range + 1; x++) {
                        positions.add(new BlockPos(center.getX() + x, center.getY(), center.getZ() + z));
                    }
                }
            }
        }

        return positions;
    }

    private BlockPos findNextPosition() {
        if (this.lastPosition == null) {
            this.lastPosition = this.positions.get(0);
            return this.lastPosition;
        }

        var index = this.positions.indexOf(this.lastPosition);
        if (index == -1 || index >= this.positions.size() - 1) {
            this.lastPosition = this.positions.get(0);
            return this.lastPosition;
        }

        this.lastPosition = this.positions.get(index + 1);

        return this.lastPosition;
    }

    private void addItemToInventory(ItemStack stack, Level level, BlockPos pos) {
        var remaining = stack.getCount();
        for (int i = 1; i < this.inventory.getSlots(); i++) {
            var stackInSlot = this.inventory.getStackInSlot(i);

            if (stackInSlot.isEmpty()) {
                this.inventory.setStackInSlot(i, stack.copy());
                return;
            }

            if (StackHelper.areStacksEqual(stackInSlot, stack)) {
                var insertSize = Math.min(remaining, stackInSlot.getMaxStackSize() - stackInSlot.getCount());

                this.inventory.setStackInSlot(i, StackHelper.grow(stackInSlot, insertSize));

                remaining -= insertSize;
            }

            if (remaining == 0)
                return;
        }

        Block.popResource(level, pos, StackHelper.withSize(stack, remaining, false));
    }
}
