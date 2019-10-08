package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.UseAction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class WateringCanItem extends BaseItem {
    public WateringCanItem(Function<Properties, Properties> properties) {
        super(properties.compose(p -> p.maxStackSize(1)));
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            ItemStack stack = new ItemStack(this);
            NBTHelper.setBoolean(stack, "Water", false);
            items.add(stack);
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        ItemStack stack = context.getItem();
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        if (player != null && !NBTHelper.getBoolean(stack, "Water")) {
            RayTraceResult result = rayTrace(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
            if (result.getType() == RayTraceResult.Type.MISS)
                return ActionResultType.FAIL;

            BlockPos pos = new BlockPos(result.getHitVec());
            BlockState state = world.getBlockState(pos);
            if (state.getMaterial() == Material.WATER) {
                NBTHelper.setBoolean(stack, "Water", true);
                return ActionResultType.FAIL;
            }
        }

        return this.doWater(stack, world, player, context.getPos(), context.getFace());
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
        if (NBTHelper.getBoolean(stack, "Water")) {
            tooltip.add(ModTooltips.FILLED.build());
        } else {
            tooltip.add(ModTooltips.EMPTY.build());
        }
    }

    private ActionResultType doWater(ItemStack stack, World world, PlayerEntity player, BlockPos pos, Direction direction) {
        if (player == null)
            return ActionResultType.FAIL;

        if (!player.canPlayerEdit(pos.offset(direction), direction, stack))
            return ActionResultType.FAIL;

        if (!NBTHelper.getBoolean(stack, "Water"))
            return ActionResultType.FAIL;

        Stream<BlockPos> blocks = BlockPos.getAllInBox(pos.add(-1, -1, -1), pos.add(1, 1, 1));
        blocks.forEach(aoePos -> {
            BlockState aoeState = world.getBlockState(aoePos);
            if (aoeState.getBlock() instanceof FarmlandBlock) {
                int moisture = aoeState.get(FarmlandBlock.MOISTURE);
                if (moisture < 7) {
                    world.setBlockState(aoePos, aoeState.with(FarmlandBlock.MOISTURE, 7), 2);
                }
            }
        });

        Random rand = new Random();
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                double d0 = pos.add(x, 0, z).getX() + rand.nextFloat();
                double d1 = pos.add(x, 0, z).getY() + 1.0D;
                double d2 = pos.add(x, 0, z).getZ() + rand.nextFloat();

                BlockState state = world.getBlockState(pos);
                if (state.isSolid() || state.getBlock() instanceof FarmlandBlock) {
                    d1 += 0.3D;
                }

                world.addParticle(ParticleTypes.RAIN, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }

        if (!world.isRemote()) {
            if (Math.random() <= 25) {
                blocks = BlockPos.getAllInBox(pos.add(-3, -3, -3), pos.add(3, 3, 3));
                blocks.forEach(aoePos -> {
                    Block plantBlock = world.getBlockState(aoePos).getBlock();
                    if (plantBlock instanceof IGrowable || plantBlock instanceof IPlantable || plantBlock == Blocks.MYCELIUM || plantBlock == Blocks.CHORUS_FLOWER) {
                        world.getPendingBlockTicks().scheduleTick(aoePos, plantBlock, 0);
                    }
                });

                return ActionResultType.FAIL;
            }
        }

        return ActionResultType.FAIL;
    }
}
