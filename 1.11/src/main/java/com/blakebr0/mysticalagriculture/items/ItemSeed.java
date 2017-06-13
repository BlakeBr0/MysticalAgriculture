package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSeed extends ItemSeeds {
	
	private Block crops;
	public int tier;
	
	public ItemSeed(String name, Block crops, int tier){
		super(crops, Blocks.FARMLAND);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
        this.crops = crops;
        this.tier = tier;
	}
		
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
        switch(this.tier - 1){
        case 0:
        	tooltip.add("Tier: \u00A7e1");
            break;
        case 1:
        	tooltip.add("Tier: \u00A7a2");
            break;
        case 2:
        	tooltip.add("Tier: \u00A763");
            break;
        case 3:
        	tooltip.add("Tier: \u00A7b4");
            break;
        case 4:
        	tooltip.add("Tier: \u00A7c5");
            break;
        }
    }
}