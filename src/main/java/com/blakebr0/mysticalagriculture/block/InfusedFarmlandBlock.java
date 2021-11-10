package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InfusedFarmlandBlock extends FarmlandBlock implements IColored, IEssenceFarmland {
    public static final List<InfusedFarmlandBlock> FARMLANDS = new ArrayList<>();
    private final CropTier tier;

    public InfusedFarmlandBlock(CropTier tier) {
        super(Properties.copy(Blocks.FARMLAND));
        this.tier = tier;

        FARMLANDS.add(this);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction direction, IPlantable plantable) {
        PlantType type = plantable.getPlantType(world, pos.relative(direction));
        return type == PlantType.CROP || type == PlantType.PLAINS;
    }

    @Override
    public void fallOn(World world, BlockPos pos, Entity entity, float fallDistance) {
        entity.causeFallDamage(fallDistance, 1.0F);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int moisture = state.getValue(MOISTURE);

        if (!isNearWater(world, pos) && !world.isRainingAt(pos.above())) {
            if (moisture > 0) {
                world.setBlock(pos, state.setValue(MOISTURE, moisture - 1), 2);
            }
        } else if (moisture < 7) {
            world.setBlock(pos, state.setValue(MOISTURE, 7), 2);
        }
    }

    // TODO: Convert to proper loot table json
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = new ArrayList<>();
        ItemStack stack = builder.getOptionalParameter(LootParameters.TOOL);

        if (stack != null && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0) {
            drops.add(new ItemStack(this));
        } else {
            drops.add(new ItemStack(Blocks.DIRT));
            if (builder.getLevel().getRandom().nextInt(100) < 25)
                drops.add(new ItemStack(this.tier.getEssence(), 1));
        }

        return drops;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(ModTooltips.TIER.args(this.tier.getDisplayName()).build());
    }

    @Override
    public int getColor(int index) {
        return this.tier.getColor();
    }

    @Override
    public CropTier getTier() {
        return this.tier;
    }
}
