package com.blakebr0.mysticalagriculture.blocks.ore;

import java.util.Random;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.blocks.BlockBase;
import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.items.ModItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockProsperityOre extends BlockBase {

	public BlockProsperityOre(String name, Material material, SoundType sound, float hardness, float resistance, String tool, int level){
		super(name, material, sound, hardness, resistance, tool, level);
	}

    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return ModItems.itemProsperityShard;
    }

    public int quantityDropped(Random random){
    	int amount = 0;
    	if(this == ModBlocks.blockProsperityOre){
    		amount = 2 + random.nextInt(2);
    	}
    	if(this == ModBlocks.blockProsperityOreNether){
    		amount = 3 + random.nextInt(2);
    	}
    	if(this == ModBlocks.blockProsperityOreEnd){
    		amount = 4 + random.nextInt(2);
    	}
        return amount; 
    }

    public int quantityDroppedWithBonus(int fortune, Random random){
        if(fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune)){
            int i = random.nextInt(fortune + 2) - 1;

            if(i < 1){
                i = 1;
            }
            return this.quantityDropped(random) * (i);
        } else {
            return this.quantityDropped(random);
        }
    }

    public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune){
        super.dropBlockAsItemWithChance(world, pos, state, chance, fortune);
    }
    
    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune){
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if(this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)){
            int i = 0;

            if(this == ModBlocks.blockProsperityOre){
                i = MathHelper.getRandomIntegerInRange(rand, 1, 3);
            }
            if(this == ModBlocks.blockProsperityOreNether){
                i = MathHelper.getRandomIntegerInRange(rand, 1, 4);
            }
            if(this == ModBlocks.blockProsperityOreEnd){
                i = MathHelper.getRandomIntegerInRange(rand, 1, 5);
            }
            return i;
        }
        return 0;
    }

    public ItemStack getItem(World world, BlockPos pos, IBlockState state){
        return new ItemStack(this);
    }

    public int damageDropped(IBlockState state){
        return 0;
    }
}
