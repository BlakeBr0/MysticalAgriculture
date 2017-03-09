package com.blakebr0.mysticalagriculture.blocks.crop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import vazkii.botania.api.item.IHornHarvestable;

@Optional.InterfaceList(value = {
        @Optional.Interface(modid = "Botania", iface = "vazkii.botania.api.item.IHornHarvestable")
})
public class BlockMysticalCrop extends BlockCrops implements IHornHarvestable {
	
    private static final AxisAlignedBB CROPS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
    private Item seed;
    private Item crop;
    
    public BlockMysticalCrop(String name){
    	super();
		this.setUnlocalizedName("ma." + name);
    	this.setRegistryName(name);
        this.setCreativeTab((CreativeTabs)null);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
    }
    
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand){
    	this.checkAndDropBlock(worldIn, pos, state);
    	int i = this.getAge(state);
        if(worldIn.getLightFromNeighbors(pos.up()) >= 9){
	    	if(i < this.getMaxAge()){
	    		float f = getGrowthChance(this, worldIn, pos);
	    		if (rand.nextInt((int)(35.0F / f) + 1) == 0) {
	    			worldIn.setBlockState(pos, this.withAge(i + 1), 2);
	    		}
	    	}
        }
    }
    
	protected boolean canSustainBush(IBlockState state){
		return state.getBlock() == Blocks.FARMLAND;
	}

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state){
        return false;
    }
            
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
    	return EnumPlantType.Crop;
    }
     
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return CROPS_AABB;
    }
        
    public Item setSeed(Item seed){
    	this.seed = seed;
    	return this.seed;
    }

    @Override
    protected Item getSeed(){
    	return this.seed;
    }
    
    public Item setCrop(Item crop){
    	this.crop = crop;
    	return this.crop;
    }
    
    @Override
    protected Item getCrop() {
    	return this.crop;
    }
    
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> drops = new ArrayList<ItemStack>();

        int age = state.getValue(AGE);
        Random rand = ((World) world).rand;

        int essence = 0;
        int fertilizer = 0;
        int seeds = 1;

        if(age == 7){
        	if(ModConfig.seed_chance > 0){
        		if(rand.nextInt(100 / ModConfig.seed_chance) > 0)
        			seeds = 1;
        		else
        			seeds = 2;
        	}
        	else seeds = 1;
        }
        
        if(age == 7){
        	if(ModConfig.fertilized_essence_chance > 0){
        		if(rand.nextInt(100 / ModConfig.fertilized_essence_chance) > 0)
        			fertilizer = 0;
        		else {
        			fertilizer = 1;
        		}
        	} 
        	else fertilizer = 0;
        }
        
        if(age == 7){
        	if(ModConfig.essence_chance > 0){
                if(rand.nextInt(100 / ModConfig.essence_chance) > 0)
                    essence = 1;
                else
                    essence = 2;        		
        	}
        	else essence = 1;
        }

        drops.add(new ItemStack(this.getSeed(), seeds, 0));
        if(essence > 0){ drops.add(new ItemStack(this.getCrop(), essence, 0)); }
        if(fertilizer > 0){ drops.add(new ItemStack(ModItems.itemFertilizedEssence, fertilizer, 0)); }
        return drops;
    }
    
    @Override
    public boolean canHornHarvest(World world, BlockPos blockPos, ItemStack stack, EnumHornType hornType) {
        return hornType.ordinal()==0 && isMaxAge(world.getBlockState(blockPos));
    }

    @Override
    public boolean hasSpecialHornHarvest(World world, BlockPos blockPos, ItemStack stack, EnumHornType hornType) {
        return hornType.ordinal()==0 && ModConfig.botania_horn_harvesting;
    }

    @Override
    public void harvestByHorn(World world, BlockPos blockPos, ItemStack stack, EnumHornType hornType) {
        if(hornType.ordinal()!=0) {
            return;
        }

        IBlockState state = world.getBlockState(blockPos);
        if(state.getValue(AGE) == 7) {
            List<ItemStack> drops = getDrops(world, blockPos, state, 0);
            for (ItemStack drop : drops) {
                if (drop != null && drop.getItem() != null) {
                    if (drop.getItem() == this.getSeed()) {
                        // getDrops simulates breaking the crop, we're just harvesting so drop one less seed
                        drop.stackSize -= 1;
                    }
                    if (drop.stackSize > 0) {
                        this.spawnAsEntity(world, blockPos, drop);
                    }
                }
            }
            world.setBlockState(blockPos, state.withProperty(AGE, 0), 2);
        }
    }
}