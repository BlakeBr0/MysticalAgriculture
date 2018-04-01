package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockInferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMysticalFertilizer extends Item implements IEnableable {

	public ItemMysticalFertilizer(){
		super();
		this.setUnlocalizedName("ma.mystical_fertilizer");
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, new Bootstrap.BehaviorDispenseOptional() {
            protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
                this.successful = true;
                World world = source.getWorld();
                BlockPos blockpos = source.getBlockPos().offset((EnumFacing)source.getBlockState().getValue(BlockDispenser.FACING));

                if (ItemMysticalFertilizer.applyFertilizer(stack, world, blockpos)) {
                	if (!world.isRemote) {
                		world.playEvent(2005, blockpos, 0);
                	}
                } else {
                	this.successful = false;
                }

                return stack;
            }
        });
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		tooltip.add(Tooltips.MYSTICAL_FERTILIZER);
	}
	
    public static boolean applyFertilizer(ItemStack stack, World worldIn, BlockPos target) {
        if (worldIn instanceof net.minecraft.world.WorldServer)
            return applyFertilizer(stack, worldIn, target, net.minecraftforge.common.util.FakePlayerFactory.getMinecraft((net.minecraft.world.WorldServer)worldIn), null);
        return false;
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

	@Override
	public boolean isEnabled(){
		return ModConfig.confMysticalFertilizer;
	}
}