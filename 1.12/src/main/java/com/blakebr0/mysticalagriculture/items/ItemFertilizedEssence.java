package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockInferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFertilizedEssence extends Item {

	public ItemFertilizedEssence(){
		super();
		this.setUnlocalizedName("ma.fertilized_essence");
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		tooltip.add(Tooltips.FERTILIZED_ESSENCE);
		int chance = ModConfig.confFertilizedEssenceChance;
		if(ModConfig.confFertilizedEssenceChance > 0){ tooltip.add(Tooltips.DROP_CHANCE + Colors.LIGHT_PURPLE + chance + "%"); }
	}
	
    public static boolean applyFertilizer(ItemStack stack, World worldIn, BlockPos target, EntityPlayer player, EnumHand hand){
        IBlockState iblockstate = worldIn.getBlockState(target);

        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, worldIn, target, iblockstate, stack, hand);
        if(hook != 0) return hook > 0;

        if(iblockstate.getBlock() instanceof IGrowable){
            IGrowable igrowable = (IGrowable)iblockstate.getBlock();

            if(igrowable.canGrow(worldIn, target, iblockstate, worldIn.isRemote)){
                if(!worldIn.isRemote){
                    if(igrowable.canUseBonemeal(worldIn, worldIn.rand, target, iblockstate) || iblockstate.getBlock() instanceof BlockMysticalCrop || iblockstate.getBlock() instanceof BlockInferiumCrop){
                    	igrowable.grow(worldIn, worldIn.rand, target, iblockstate);
                    }
                    stack.shrink(1);
                }
                return true;
            }
        }
        return false;
    }
	
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        ItemStack stack = player.getHeldItem(hand);

        if(!player.canPlayerEdit(pos.offset(facing), facing, stack)){
            return EnumActionResult.FAIL;
        } else {
        	if (applyFertilizer(stack, world, pos, player, hand)){
        		if (!world.isRemote){
        			world.playEvent(2005, pos, 0);
        		}
        		return EnumActionResult.SUCCESS;
        	}
        }
        return EnumActionResult.PASS;
    }
}