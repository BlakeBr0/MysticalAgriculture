package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.blocks.ItemBlockEssenceCoal;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class CoalBlockFuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack stack){
		if(stack.getItem() instanceof ItemBlockEssenceCoal){
			switch(stack.getMetadata()){
			case 0:
				return 21600;
			case 1:
				return 43200;
			case 2:
				return 86400;
			case 3: 
				return 172800;
			case 4:
				return 345600;
			}
		}
		return 0;
	} 	
}
