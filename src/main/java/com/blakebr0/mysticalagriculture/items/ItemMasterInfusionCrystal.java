package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMasterInfusionCrystal extends Item {
	
	public ItemMasterInfusionCrystal(String name){
		super();
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.setMaxStackSize(1);
	}

    @Override
    public ItemStack getContainerItem(ItemStack itemstack){
        ItemStack stack = itemstack.copy();
        stack.stackSize = 1;
        return stack;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return true;
    }
    
    @Override
    public boolean hasEffect(ItemStack stack){
    	return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
    	tooltip.add("Uses Left: \u00A7cUnlimited");
    }
}
