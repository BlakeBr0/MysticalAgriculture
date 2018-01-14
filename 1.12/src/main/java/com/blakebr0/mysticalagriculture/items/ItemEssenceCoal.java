package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.item.ItemMeta;
import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEssenceCoal extends ItemMeta implements IEnableable {

	public static ItemStack itemInferiumCoal;
	public static ItemStack itemPrudentiumCoal;
	public static ItemStack itemIntermediumCoal;
	public static ItemStack itemSupremiumCoal;
	public static ItemStack itemSupremiumCoal;
	
	public ItemEssenceCoal(){
		super("ma.coal", MysticalAgriculture.REGISTRY);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}
	
	@Override
	public void init(){
		itemInferiumCoal = addItem(0, "inferium", "coalInferium");
		itemPrudentiumCoal = addItem(1, "prudentium", "coalPrudentium");
		itemIntermediumCoal = addItem(2, "intermedium", "coalIntermedium");
		itemSupremiumCoal = addItem(3, "supremium", "coalSupremium");
		itemSupremiumCoal = addItem(4, "supremium", "coalSupremium");
		
		GameRegistry.registerFuelHandler(new FuelHandler());
	}
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced){
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
    
    public class FuelHandler implements IFuelHandler {

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

	@Override
	public boolean isEnabled(){
		return ModConfig.confEssenceCoal;
	}
}
