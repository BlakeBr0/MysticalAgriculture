package com.blakebr0.mysticalagriculture.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWitherproofBlock extends Block {
	
	public BlockWitherproofBlock(String name, Material material, SoundType sound, float hardness, float resistance, String tool, int level) {
		super(material);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.setSoundType(sound);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setHarvestLevel(tool, level);
	} 
	
	@Override
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity){
		return !(entity instanceof EntityWither) && !(entity instanceof EntityWitherSkull);
	}

	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion){
	}

	@Override
	public boolean canDropFromExplosion(Explosion p_149659_1_){
		return false;
	}
	  
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		tooltip.add("Blast Resistant");
	}
}
