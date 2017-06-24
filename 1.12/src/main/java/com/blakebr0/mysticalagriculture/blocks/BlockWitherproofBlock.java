package com.blakebr0.mysticalagriculture.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

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
	
	public BlockWitherproofBlock(){
		super(Material.ROCK);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName("ma.witherproof_block");
		this.setHardness(24.0F);
		this.setResistance(2000.0F);
		this.setHarvestLevel("pickaxe", 1);
	} 

	@Override
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity){
		return !(entity instanceof EntityWither) && !(entity instanceof EntityWitherSkull);
	}

	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion){
	}

	@Override
	public boolean canDropFromExplosion(Explosion explosion){
		return false;
	}
	  
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		tooltip.add(Tooltips.BLAST_RESISTANT);
	}
}
