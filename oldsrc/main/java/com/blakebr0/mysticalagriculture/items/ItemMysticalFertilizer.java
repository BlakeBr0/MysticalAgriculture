package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import com.blakebr0.mysticalagriculture.blocks.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.blocks.BlockTier1InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.BlockTier2InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.BlockTier3InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.BlockTier4InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.BlockTier5InferiumCrop;
import com.blakebr0.mysticalagriculture.config.ModConfig;

import akka.actor.FSM.State;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMysticalFertilizer extends BaseItem {

	public ItemMysticalFertilizer(String name){
		super(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		tooltip.add("Instantly grows a Resource Crop.");
		super.addInformation(stack, player, tooltip, advanced);
	}
	
    public static boolean applyFertilizer(ItemStack stack, World worldIn, BlockPos target, EntityPlayer player){
        IBlockState iblockstate = worldIn.getBlockState(target);

        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, worldIn, target, iblockstate, stack);
        if(hook != 0) return hook > 0;

    	if(iblockstate.getBlock() instanceof BlockMysticalCrop || iblockstate.getBlock() instanceof BlockTier1InferiumCrop || iblockstate.getBlock() instanceof BlockTier2InferiumCrop || iblockstate.getBlock() instanceof BlockTier3InferiumCrop || iblockstate.getBlock() instanceof BlockTier4InferiumCrop || iblockstate.getBlock() instanceof BlockTier5InferiumCrop){
            IGrowable igrowable = (IGrowable)iblockstate.getBlock();

            if(igrowable.canGrow(worldIn, target, iblockstate, worldIn.isRemote)){
                if(!worldIn.isRemote){
                	worldIn.setBlockState(target, iblockstate.withProperty(BlockCrops.AGE, 7));
                    --stack.stackSize;
                }
                return true;
            }
        }
        return false;
    }
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z){
		if(!playerIn.canPlayerEdit(pos.offset(facing), facing, stack)){
			return EnumActionResult.FAIL;
		}

		if(applyFertilizer(stack, world, pos, playerIn)){
			if (!world.isRemote){
				world.playEvent(2005, pos, 0);
			}
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}
}