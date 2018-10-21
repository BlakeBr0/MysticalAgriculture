package com.blakebr0.mysticalagriculture.blocks;

import javax.annotation.Nonnull;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSoulGlassPane extends BlockPane {

	public BlockSoulGlassPane(){
		super(Material.GLASS, false);
		this.setUnlocalizedName("ma.soul_glass_pane");
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
		this.setHardness(0.3F);
		this.setSoundType(SoundType.GLASS);
	}
	
	@Nonnull
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer(){
		return BlockRenderLayer.TRANSLUCENT;
	}

}
