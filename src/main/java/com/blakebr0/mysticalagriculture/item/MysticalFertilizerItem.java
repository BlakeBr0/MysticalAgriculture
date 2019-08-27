package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SaplingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;
import java.util.function.Function;

public class MysticalFertilizerItem extends BaseItem {
    public MysticalFertilizerItem(Function<Properties, Properties> properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        ItemStack stack = context.getItem();
        BlockPos pos = context.getPos();
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        Direction direction = context.getFace();

        if (player == null || !player.canPlayerEdit(pos.offset(direction), direction, stack)) {
            return ActionResultType.FAIL;
        } else {
            if (applyFertilizer(stack, world, pos, player)) {
                if (!world.isRemote()){
                    world.playEvent(Constants.WorldEvents.BONEMEAL_PARTICLES, pos, 0);
                }

                return ActionResultType.SUCCESS;
            }
        }

        return ActionResultType.PASS;
    }

    public static boolean applyFertilizer(ItemStack stack, World world, BlockPos pos, PlayerEntity player){
        BlockState state = world.getBlockState(pos);

        int hook = ForgeEventFactory.onApplyBonemeal(player, world, pos, state, stack);
        if (hook != 0) return hook > 0;

        if (state.getBlock() instanceof IGrowable) {
            IGrowable growable = (IGrowable) state.getBlock();

            if (growable.canGrow(world, pos, state, world.isRemote())) {
                if (!world.isRemote()) {
                    Random rand = world.getRandom();
                    if (growable.canUseBonemeal(world, rand, pos, state) || growable instanceof ICropGetter || growable instanceof SaplingBlock) {
                        if (growable instanceof CropsBlock) {
                            CropsBlock crop = (CropsBlock) state.getBlock();
                            world.setBlockState(pos, crop.withAge(crop.getMaxAge()), 2);
                        } else if (growable instanceof SaplingBlock) {
                            if (!ForgeEventFactory.saplingGrowTree(world, rand, pos))
                                return false;

                            ((SaplingBlock) growable).tree.spawn(world, pos, state, rand);
                        } else {
                            growable.grow(world, rand, pos, state);
                        }
                    }

                    stack.shrink(1);
                }

                return true;
            }
        }

        return false;
    }
}
