package com.blakebr0.mysticalagriculture.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemMeta;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.util.MystUtils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWateringCan extends ItemMeta implements IEnableable {
		
	private boolean water = false;
	private long ticks;
	
	public static ItemStack itemInferiumWateringCan;
	public static ItemStack itemPrudentiumWateringCan;
	public static ItemStack itemIntermediumWateringCan;
	public static ItemStack itemSuperiumWateringCan;
	public static ItemStack itemSupremiumWateringCan;;
	
	public ItemWateringCan(){
		super("ma.watering_can", MysticalAgriculture.REGISTRY);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.setMaxStackSize(1);
	}
	
	@Override
	public void init(){
		itemInferiumWateringCan = addItem(0, "inferium");
		itemPrudentiumWateringCan = addItem(1, "prudentium");
		itemIntermediumWateringCan = addItem(2, "intermedium");
		itemSuperiumWateringCan = addItem(3, "superium");
		itemSupremiumWateringCan = addItem(4, "supremium");
	}

	@Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected){
    	if(selected){
    		ticks++;
    		if(ticks % 4 == 0){
    			water = true;
    		}
    	}
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack){
        return EnumAction.NONE;
    }
	
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
    	ItemStack stack = player.getHeldItem(hand);
		if(!player.canPlayerEdit(pos.offset(facing), facing, stack)){
			return EnumActionResult.FAIL;
	    }
		
		if(!ModConfig.confFakePlayerWatering && player instanceof FakePlayer){
			return EnumActionResult.FAIL;
		}
		
		int range = stack.getMetadata();
		
    	Iterable<BlockPos> blocks = BlockPos.getAllInBox(pos.add(-range, -range, -range), pos.add(range, range, range));
    	for(BlockPos aoePos : blocks){
    		IBlockState aoeState = world.getBlockState(aoePos);
    		if(aoeState.getBlock() instanceof BlockFarmland){
    			int moisture =  aoeState.getValue(BlockFarmland.MOISTURE);
    			if(moisture < 7){
    				world.setBlockState(aoePos, aoeState.withProperty(BlockFarmland.MOISTURE, 7), 2);
    			}
    		}
    	}
		
		Random rand = new Random();
		for(int x = -range; x <= range; x++){
			for(int z = -range; z <= range; z++){
				double d0 = pos.add(x, 0, z).getX() + rand.nextFloat();
				double d1 = pos.add(x, 0, z).getY() + 1.0D;
				double d2 = pos.add(x, 0, z).getZ() + rand.nextFloat();
		      
				IBlockState state = world.getBlockState(pos);
				if((state.isFullCube()) || ((state.getBlock() instanceof BlockFarmland))){
					d1 += 0.3D;
				}
				world.spawnParticle(EnumParticleTypes.WATER_DROP, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[5]);
			}
		}
		
	    if(!world.isRemote && water){
	    	water = false;
	    	int chance = Utils.randInt(1, 100);
	    	int bonus = 4 * stack.getMetadata();
	        if(chance <= (40 + bonus)){
	        	for(BlockPos aoePos : blocks){
	        		Block plant = world.getBlockState(aoePos).getBlock();      		
	        		if(plant instanceof IGrowable || plant instanceof IPlantable || plant == Blocks.MYCELIUM || plant == Blocks.CHORUS_FLOWER){
	        			world.scheduleBlockUpdate(aoePos, plant, 0, 1000);
	        		}
	        	}
	        	return EnumActionResult.FAIL;
	        }
	    }
	    return EnumActionResult.FAIL;
    }
        
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced){
    	int meta = stack.getMetadata();
    	int range = (meta * 2 + 1);
    	tooltip.add(Tooltips.RANGE + MystUtils.getColorFromMeta(meta) + range + "x" + range);
    }

	@Override
	public boolean isEnabled(){
		return ModConfig.confWateringCans;
	}
}
