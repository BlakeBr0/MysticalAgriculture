package com.blakebr0.mysticalagriculture.blocks.crop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.Optional;
import vazkii.botania.api.item.IHornHarvestable;
import vazkii.botania.api.item.IHornHarvestable.EnumHornType;

@Optional.InterfaceList(value = {
        @Optional.Interface(modid = "Botania", iface = "vazkii.botania.api.item.IHornHarvestable")
})
public class BlockTier5InferiumCrop extends BlockCrops implements IHornHarvestable {

    private static final AxisAlignedBB CROPS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);

    public BlockTier5InferiumCrop(String name) {
    	super();
		this.setUnlocalizedName("ma." + name);
    	this.setRegistryName(name);
        this.setCreativeTab((CreativeTabs)null);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
    }
    
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
    	this.checkAndDropBlock(world, pos, state);
    	int i = this.getAge(state);
        if(world.getLightFromNeighbors(pos.up()) >= 9){
	    	if(i < this.getMaxAge()){
	    		float f = getGrowthChance(this, world, pos);
	    		if(rand.nextInt((int)(35.0F / f) + 1) == 0) {
	    			world.setBlockState(pos, this.withAge(i + 1), 2);
	    		}
	    	}
        }
    }
    
	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock() == Blocks.FARMLAND;
	}

    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
    	return EnumPlantType.Crop;
    }
    
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return CROPS_AABB;
    }
    
    @Override
    protected Item getSeed(){
    	return ModItems.itemTier5InferiumSeeds;
    }
    
    @Override
    protected Item getCrop(){
    	return ModItems.itemInferiumEssence;
    }
    
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> drops = new ArrayList<ItemStack>();

        int age = state.getValue(AGE);
        Random rand = ((World) world).rand;

        int essence = 0;
        int seeds = 1;

        if(age == 7){
        	if(ModConfig.confSeedChance > 0){
        		if(rand.nextInt(100 / ModConfig.confSeedChance) > 0){
        			seeds = 1;
        		} else {
        			seeds = 2;
        		}		
        	} else {
        		seeds = 1;
        	}
        }
        
        if(age == 7){
        	if(ModConfig.confEssenceChance > 0){
                if(rand.nextInt(100 / ModConfig.confEssenceChance) > 0){
                	essence = 5;
                } else {
                	essence = 6;
                }          		
        	} else {
        		essence = 5;
        	}
        }

        drops.add(new ItemStack(this.getSeed(), seeds, 0));
        if(essence > 0){ drops.add(new ItemStack(this.getCrop(), essence, 0)); }
        return drops;
    }
    
    @Override
    public boolean canHornHarvest(World world, BlockPos pos, ItemStack stack, EnumHornType hornType) {
        return hornType.ordinal() == 0 && isMaxAge(world.getBlockState(pos));
    }

    @Override
    public boolean hasSpecialHornHarvest(World world, BlockPos pos, ItemStack stack, EnumHornType hornType) {
        return hornType.ordinal() == 0 && ModConfig.confBotaniaHornHarvesting;
    }

    @Override
    public void harvestByHorn(World world, BlockPos pos, ItemStack stack, EnumHornType hornType){
        if(hornType.ordinal() != 0){
            return;
        }

        IBlockState state = world.getBlockState(pos);
        if(state.getValue(AGE) == 7){
            List<ItemStack> drops = getDrops(world, pos, state, 0);
            for(ItemStack drop : drops){
                if(drop != null && drop.getItem() != null){
                    if(drop.getItem() == this.getSeed()){
                        drop.stackSize -= 1;
                    }
                    if(drop.stackSize > 0){
                        this.spawnAsEntity(world, pos, drop);
                    }
                }
            }
            world.setBlockState(pos, state.withProperty(AGE, 0), 2);
        }
    }
}