package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import java.util.ArrayList;
import java.util.List;

public class InfusedFarmlandBlock extends FarmlandBlock implements IColored, IEssenceFarmland {
    public static final List<InfusedFarmlandBlock> FARMLANDS = new ArrayList<>();
    private final CropTier tier;

    public InfusedFarmlandBlock(String name, CropTier tier) {
        super(Properties.from(Blocks.FARMLAND));
        this.setRegistryName(name);
        this.tier = tier;

        FARMLANDS.add(this);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction direction, IPlantable plantable) {
        PlantType type = plantable.getPlantType(world, pos.offset(direction));
        return type == PlantType.Crop || type == PlantType.Plains;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);
        if (builder.func_216018_a().getRandom().nextInt(100) < 25)
            drops.add(new ItemStack(this.tier.getEssence(), 1));

        return drops;
    }

    @Override
    public int color(int index) {
        return this.tier.getColor();
    }
}
