package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMasterInfusionCrystal extends ItemBase {
	
	public ItemMasterInfusionCrystal(String name){
		super(name);
		this.setMaxStackSize(1);
	}

    @Override
    public ItemStack getContainerItem(ItemStack stack){
        ItemStack item = stack.copy();
        item.stackSize = 1;
        return item;
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
    	tooltip.add(Tooltips.USES_LEFT + "\u00A7cUnlimited");
    }
}
