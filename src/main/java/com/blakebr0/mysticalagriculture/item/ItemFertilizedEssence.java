package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.function.Function;

public class ItemFertilizedEssence extends ItemBase {
    public ItemFertilizedEssence(String name, Function<Properties, Properties> properties) {
        super(name, properties);
    }

    @Override
    public EnumActionResult onItemUse(ItemUseContext context) {
        ItemStack stack = context.getItem();
        BlockPos pos = context.getPos();
        EntityPlayer player = context.getPlayer();
        World world = context.getWorld();
        EnumFacing facing = context.getFace();

        if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
            return EnumActionResult.FAIL;
        } else {
            if (applyFertilizer(stack, world, pos, player)) {
                if (!world.isRemote){
                    world.playEvent(2005, pos, 0);
                }

                return EnumActionResult.SUCCESS;
            }
        }

        return EnumActionResult.PASS;
    }

    public static boolean applyFertilizer(ItemStack stack, World world, BlockPos pos, EntityPlayer player){
        IBlockState state = world.getBlockState(pos);

        int hook = ForgeEventFactory.onApplyBonemeal(player, world, pos, state, stack);
        if (hook != 0) return hook > 0;

        if (state.getBlock() instanceof IGrowable) {
            IGrowable growable = (IGrowable) state.getBlock();

            if (growable.canGrow(world, pos, state, world.isRemote)) {
                if (!world.isRemote) {
                    if (growable.canUseBonemeal(world, world.getRandom(), pos, state) || growable instanceof ICropGetter) {
                        growable.grow(world, world.getRandom(), pos, state);
                    }

                    stack.shrink(1);
                }

                return true;
            }
        }

        return false;
    }
}
