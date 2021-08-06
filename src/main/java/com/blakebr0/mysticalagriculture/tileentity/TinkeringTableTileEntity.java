package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugmentGetter;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import com.blakebr0.mysticalagriculture.container.TinkeringTableContainer;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class TinkeringTableTileEntity extends BaseInventoryTileEntity implements MenuProvider {
    private final BaseItemStackHandler inventory = new BaseItemStackHandler(7, () -> {
        if (this.getLevel() != null && !this.getLevel().isClientSide()) {
            this.markDirtyAndDispatch();
        }
    });

    public TinkeringTableTileEntity() {
        super(ModTileEntities.TINKERING_TABLE.get());
        this.inventory.setDefaultSlotLimit(1);
        this.inventory.setSlotValidator(this::canInsertStack);
    }

    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public Component getDisplayName() {
        return Localizable.of("container.mysticalagriculture.tinkering_table").build();
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player player) {
        return TinkeringTableContainer.create(windowId, playerInventory, this::isUsableByPlayer, this.inventory);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return LazyOptional.empty();
    }

    private boolean canInsertStack(int slot, ItemStack stack) {
        Item item = stack.getItem();
        switch (slot) {
            case 0: return item instanceof ITinkerable;
            case 1:
            case 2: return item instanceof IAugmentGetter;
            case 3: return item == ModCrops.AIR.getEssence();
            case 4: return item == ModCrops.EARTH.getEssence();
            case 5: return item == ModCrops.WATER.getEssence();
            case 6: return item == ModCrops.FIRE.getEssence();
            default: return true;
        }
    }
}
