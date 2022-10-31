package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.block.HarvesterBlock;
import com.blakebr0.mysticalagriculture.container.HarvesterContainer;
import com.blakebr0.mysticalagriculture.container.inventory.UpgradeItemStackHandler;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.DynamicEnergyStorage;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HarvesterTileEntity extends BaseInventoryTileEntity implements MenuProvider, IUpgradeableMachine {
    private static final Method GET_SEED;
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
    private int progress;
    private int fuelLeft;
    private int fuelItemValue;
    private int oldEnergy;
    private List<BlockPos> positions;
    private BlockPos lastPosition = BlockPos.ZERO;
    private MachineUpgradeTier tier;
    private Direction direction;

    static {
        GET_SEED = ObfuscationReflectionHelper.findMethod(CropBlock.class, "m_6404_");
    }

    public HarvesterTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.HARVESTER.get(), pos, state);
        this.inventory = createInventoryHandler(this::markDirtyAndDispatch);
        this.upgradeInventory = new UpgradeItemStackHandler();
        this.energy = new DynamicEnergyStorage(FUEL_CAPACITY);
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
        return HarvesterContainer.create(i, inventory, this::isUsableByPlayer, this.inventory, this.upgradeInventory, this.getBlockPos());
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
            if (cap == CapabilityEnergy.ENERGY) {
                return CapabilityEnergy.ENERGY.orEmpty(cap, this.energyCapability);
            }
        }

        return super.getCapability(cap, side);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, HarvesterTileEntity tile) {
        var mark = false;

        if (tile.energy.getEnergyStored() < tile.energy.getMaxEnergyStored()) {
            var fuel = tile.inventory.getStackInSlot(0);

            if (tile.fuelLeft <= 0 && !fuel.isEmpty()) {
                tile.fuelItemValue = ForgeHooks.getBurnTime(fuel, null);

                if (tile.fuelItemValue > 0) {
                    tile.fuelLeft = tile.fuelItemValue *= FUEL_TICK_MULTIPLIER;
                    tile.inventory.extractItemSuper(0, 1, false);

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

        var tier = tile.getMachineTier();
        var direction = state.getValue(HarvesterBlock.FACING);

        if (tier != tile.tier || direction != tile.direction) {
            var range = tier != null ? BASE_RANGE + tier.getAddedRange() : BASE_RANGE;
            var center = pos.relative(direction, range + 1);

            tile.tier = tier;
            tile.direction = direction;
            tile.positions = getWorkingArea(center, range);

            if (tier == null) {
                tile.energy.resetMaxEnergyStorage();
            } else {
                tile.energy.setMaxEnergyStorage((int) (FUEL_CAPACITY * tier.getFuelCapacityMultiplier()));
            }

            mark = true;
        }

        var operationTime = tile.getOperationTime();

        if (tile.progress > operationTime && !level.hasNeighborSignal(tile.getBlockPos())) {
            var nextPos = tile.findNextPosition();
            var cropState = level.getBlockState(nextPos);
            var block = cropState.getBlock();

            if (block instanceof CropBlock crop) {
                var seed = getSeed(block);
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

            mark = true;
        }

        if (tile.energy.getEnergyStored() >= tile.getFuelUsage()) {
            tile.progress++;

            mark = true;
        }

        if (tile.oldEnergy != tile.energy.getEnergyStored()) {
            tile.oldEnergy = tile.energy.getEnergyStored();

            mark = true;
        }

        if (mark) {
            tile.markDirtyAndDispatch();
        }
    }

    public static BaseItemStackHandler createInventoryHandler(Runnable onContentsChanged) {
        var inventory = new BaseItemStackHandler(16, onContentsChanged);
        inventory.setCanExtract(slot -> slot > 0);
        inventory.setSlotValidator((slot, stack) -> slot > 0 || slot == 0 && ForgeHooks.getBurnTime(stack, null) > 0);
        return inventory;
    }

    public EnergyStorage getEnergy() {
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

    private static List<BlockPos> getWorkingArea(BlockPos center, int range) {
        var positions = new ArrayList<BlockPos>();

        for (int x = -range; x < range + 1; x++) {
            for (int z = -range; z < range + 1; z++) {
                positions.add(new BlockPos(center.getX() + x, center.getY(), center.getZ() + z));
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
        var remaining = stack;
        for (int i = 1; i < this.inventory.getSlots(); i++) {
            remaining = this.inventory.insertItemSuper(i, remaining, false);
            if (remaining.isEmpty())
                return;
        }

        if (!remaining.isEmpty()) {
            Block.popResource(level, pos, remaining);
        }
    }

    private static Item getSeed(Block block) {
        try {
            return (Item) GET_SEED.invoke(block);
        } catch (Exception e) {
            MysticalAgriculture.LOGGER.error("Unable to get seed from crop {}", e.getLocalizedMessage());
        }

        return null;
    }
}
