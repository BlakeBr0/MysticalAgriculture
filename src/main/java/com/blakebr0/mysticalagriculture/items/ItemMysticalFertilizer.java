package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier1InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier2InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier3InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier4InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier5InferiumCrop;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMysticalFertilizer extends ItemBase {

	public ItemMysticalFertilizer(String name){
		super(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		tooltip.add(new TextComponentTranslation("tooltip.ma.mystical_fertilizer").getFormattedText());
	}
	
    public static boolean applyFertilizer(ItemStack stack, World world, BlockPos pos, EntityPlayer player){
        IBlockState state = world.getBlockState(pos);

        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, world, pos, state, stack);
        if(hook != 0) return hook > 0;

    	if(state.getBlock() instanceof IGrowable){
            IGrowable growable = (IGrowable)state.getBlock();

            if(growable.canGrow(world, pos, state, world.isRemote)){
                if(!world.isRemote){
                	if(growable.canUseBonemeal(world, world.rand, pos, state) || growable instanceof BlockMysticalCrop || growable instanceof BlockTier1InferiumCrop || growable instanceof BlockTier2InferiumCrop || growable instanceof BlockTier3InferiumCrop || growable instanceof BlockTier4InferiumCrop || growable instanceof BlockTier5InferiumCrop){
                		if(growable instanceof BlockCrops){
                			BlockCrops crop = (BlockCrops)state.getBlock();
                			world.setBlockState(pos, crop.withAge(crop.getMaxAge()), 2);
                		} else {
                			growable.grow(world, world.rand, pos, state);
                		}
                	}
                	--stack.stackSize;
                }
                return true;
            }
    	}
    	return false;
    }
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z){
		if(!player.canPlayerEdit(pos.offset(facing), facing, stack)){
			return EnumActionResult.FAIL;
		}

		if(applyFertilizer(stack, world, pos, player)){
			if(!world.isRemote){
				world.playEvent(2005, pos, 0);
			}
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}
}