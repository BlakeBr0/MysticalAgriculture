package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockInferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.config.ModConfig;

import akka.actor.FSM.State;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMysticalFertilizer extends Item {

	public ItemMysticalFertilizer(String name){
		super();
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List tooltip, ITooltipFlag advanced){
		tooltip.add(new TextComponentTranslation("tooltip.ma.mystical_fertilizer").getFormattedText());
	}
	
    public static boolean applyFertilizer(ItemStack stack, World world, BlockPos pos, EntityPlayer player, EnumHand hand){
        IBlockState state = world.getBlockState(pos);

        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, world, pos, state, stack, hand);
        if(hook != 0) return hook > 0;

    	if(state.getBlock() instanceof IGrowable){
            IGrowable growable = (IGrowable)state.getBlock();

            if(growable.canGrow(world, pos, state, world.isRemote)){
                if(!world.isRemote){
                	if(growable.canUseBonemeal(world, world.rand, pos, state) || growable instanceof BlockMysticalCrop || growable instanceof BlockInferiumCrop){
                		if(growable instanceof BlockCrops){
                			BlockCrops crop = (BlockCrops)state.getBlock();
                			world.setBlockState(pos, crop.withAge(crop.getMaxAge()), 2);
                		} else {
                			growable.grow(world, world.rand, pos, state);
                		}
                	}
                	stack.shrink(1);
                }
                return true;
            }
    	}
    	return false;
    }
    
    @Override
    public EnumActionResult onItemUse(EntityPlayer stack, World playerIn, BlockPos worldIn, EnumHand pos, EnumFacing hand, float facing, float hitX, float hitY){
        ItemStack itemstack = stack.getHeldItem(pos);

        if(!stack.canPlayerEdit(worldIn.offset(hand), hand, itemstack)){
            return EnumActionResult.FAIL;
        } else {
        	if (applyFertilizer(itemstack, playerIn, worldIn, stack, pos)){
        		if (!playerIn.isRemote){
        			playerIn.playEvent(2005, worldIn, 0);
        		}
        		return EnumActionResult.SUCCESS;
        	}
        }
        return EnumActionResult.PASS;
    }
}