package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.ArrayList;
import java.util.List;

public class BlockInfusedFarmland extends BlockFarmland implements IColored, IEssenceFarmland {
    public static final List<BlockInfusedFarmland> FARMLANDS = new ArrayList<>();
    private final CropTier tier;

    public BlockInfusedFarmland(String name, CropTier tier) {
        super(Properties.from(Blocks.FARMLAND));
        this.setRegistryName(name);
        this.tier = tier;

        FARMLANDS.add(this);
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing facing, IPlantable plantable) {
        EnumPlantType type = plantable.getPlantType(world, pos.offset(facing));
        return type == EnumPlantType.Crop || type == EnumPlantType.Plains;
    }

    @Override
    public void getDrops(IBlockState state, NonNullList<ItemStack> drops, World world, BlockPos pos, int fortune) {
        super.getDrops(state, drops, world, pos, fortune);

        if (world.getRandom().nextInt(100) < 25)
            drops.add(new ItemStack(this.tier.getEssence(), 1));
    }

    @Override
    public int color(int index) {
        return this.tier.getColor();
    }
}
