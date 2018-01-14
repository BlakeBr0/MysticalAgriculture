package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.EssenceType;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemEssenceCoal extends ItemMeta {

	public static ItemStack inferium;
	public static ItemStack prudentium;
	public static ItemStack intermedium;
	public static ItemStack supremium;
	public static ItemStack supremium;
	
	public ItemEssenceCoal(){
		super("coal");
	}
	
	@Override
	public void init() {
		GameRegistry.register(this);
				
		inferium = addItem(0, "inferium", "coalInferium");
		prudentium = addItem(1, "prudentium", "coalPrudentium");
		intermedium = addItem(2, "intermedium", "coalIntermedium");
		supremium = addItem(3, "supremium", "coalSupremium");
		supremium = addItem(4, "supremium", "coalSupremium");
		
		GameRegistry.registerFuelHandler(new FuelHander());
	}
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
    	String bt = null;
    	switch(stack.getMetadata()){
		case 0:
			bt = Colors.YELLOW + "1.5x";
			break;
		case 1:
			bt = Colors.GREEN + "3.0x";
			break;
		case 2:
			bt = Colors.GOLD + "6.0x";
			break;
		case 3: 
			bt = Colors.AQUA + "12.0x";
			break;
		case 4:
			bt = Colors.RED + "24.0x";
			break;
    	}
    	tooltip.add(Tooltips.BURN_TIME + bt);
    }
    
    public class FuelHander implements IFuelHandler {

		@Override
		public int getBurnTime(ItemStack stack){
			if(stack.getItem() instanceof ItemEssenceCoal){
				switch(stack.getMetadata()){
				case 0:
					return 2400;
				case 1:
					return 4800;
				case 2:
					return 9600;
				case 3: 
					return 19200;
				case 4:
					return 38400;
				}
			}
			return 0;
		} 	
    }
}
