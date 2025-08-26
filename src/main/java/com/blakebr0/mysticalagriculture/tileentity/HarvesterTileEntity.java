package com.blakebr0.mysticalagriculture.tileentity;

import com.agricraft.agricraft.api.crop.AgriCrop;
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
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.function.Consumer;

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
    private MachineUpgradeTier tier;
    private Direction direction;
    private int progress;
    private int fuelLeft;
    private int fuelItemValue;
    private int lastScanIndex = -1;
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
        this.lastScanIndex = tag.getInt("LastScanIndex");
        this.energy.deserializeNBT(tag.get("Energy"));
        this.upgradeInventory.deserializeNBT(tag.getCompound("UpgradeInventory"));
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putInt("Progress", this.progress);
        tag.putInt("FuelLeft", this.fuelLeft);
        tag.putInt("FuelItemValue", this.fuelItemValue);
        tag.putInt("LastScanIndex", this.lastScanIndex);
        tag.putInt("Energy", this.energy.getEnergyStored());
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
            tile.tier = tier;
            tile.direction = direction;

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

            Consumer<ItemStack> insert = drop -> {
                if (!drop.isEmpty()) {
                    tile.addItemToInventory(drop, level, nextPos);
                }
            };
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
                        insert.accept(drop);
                    }

                    level.setBlockAndUpdate(nextPos, crop.getStateForAge(0));

                    tile.energy.extractEnergy(tile.getFuelUsage(), false);
                } else {
                    tile.energy.extractEnergy(SCAN_FUEL_USAGE, false);
                }
            } else if (level.getBlockEntity(nextPos) instanceof AgriCrop agriCrop && agriCrop.hasPlant()) {
                // there's no seed removing as harvest only adds the plant's products
                if (agriCrop.harvest(insert, null)) {
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
            builder.setCanInsert((slot, stack) -> switch (slot) {
                case 0 -> FurnaceBlockEntity.isFuel(stack);
                default -> false;
            });
            builder.setCanExtract(slot -> switch (slot) {
                case 0 -> !FurnaceBlockEntity.isFuel(builder.getStackInSlot(slot));
                default -> true;
            });
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

    private BlockPos findNextPosition() {
        var range = tier != null ? BASE_RANGE + tier.getAddedRange() : BASE_RANGE;
        var size = range * 2 + 1;

        var index = this.lastScanIndex + 1;
        if (index >= (int) Math.pow(size, 2)) {
            index = 0;
        }

        this.lastScanIndex = index;

        var xOffset = (index % size) - range;
        var zOffset = (index / size) - range;

        var center = this.getBlockPos().relative(direction, range + 1);

        return switch (direction) {
            case NORTH -> new BlockPos(center.getX() + xOffset, center.getY(), center.getZ() - zOffset);
            case SOUTH -> new BlockPos(center.getX() - xOffset, center.getY(), center.getZ() + zOffset);
            case EAST -> new BlockPos(center.getX() + zOffset, center.getY(), center.getZ() + xOffset);
            case WEST -> new BlockPos(center.getX() - zOffset, center.getY(), center.getZ() - xOffset);
            default -> center;
        };
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
