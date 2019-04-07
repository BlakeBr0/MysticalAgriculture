package com.blakebr0.mysticalagriculture.blocks;

import com.blakebr0.cucumber.iface.IColored;
import net.minecraft.block.BlockFarmland;
import net.minecraft.init.Blocks;

import java.util.ArrayList;
import java.util.List;

public class BlockInfusedFarmland extends BlockFarmland implements IColored {

    public static final List<BlockInfusedFarmland> FARMLANDS = new ArrayList<>();
    private final int color;

    public BlockInfusedFarmland(String name, int color) {
        super(Properties.from(Blocks.FARMLAND));
        this.setRegistryName(name);
        this.color = color;

        FARMLANDS.add(this);
    }

    @Override
    public int color(int index) {
        return this.color;
    }
}
