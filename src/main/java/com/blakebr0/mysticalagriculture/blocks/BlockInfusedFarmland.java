package com.blakebr0.mysticalagriculture.blocks;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.ArrayList;
import java.util.List;

public class BlockInfusedFarmland extends BlockFarmland implements IColored, IEssenceFarmland {
    public static final List<BlockInfusedFarmland> FARMLANDS = new ArrayList<>();
    private final int color;

    public BlockInfusedFarmland(String name, int color) {
        super(Properties.from(Blocks.FARMLAND));
        this.setRegistryName(name);
        this.color = color;

        FARMLANDS.add(this);
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing facing, IPlantable plantable) {
        EnumPlantType type = plantable.getPlantType(world, pos.offset(facing));
        return type == EnumPlantType.Crop || type == EnumPlantType.Plains;
    }

    @Override
    public int color(int index) {
        return this.color;
    }
}
