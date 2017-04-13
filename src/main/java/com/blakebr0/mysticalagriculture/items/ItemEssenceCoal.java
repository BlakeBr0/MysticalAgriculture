package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
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

public class ItemEssenceCoal extends ItemBase {

	public ItemEssenceCoal(){
		super("coal");
		this.setHasSubtypes(true);
		GameRegistry.registerFuelHandler(new FuelHander());
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> stacks) {
        for(EssenceType.Type type : EssenceType.Type.values()){
            stacks.add(new ItemStack(item, 1, type.getMetadata()));
        }
	}
	
    @Override
    public String getUnlocalizedName(ItemStack stack){
        return super.getUnlocalizedName() + "_" + EssenceType.Type.byMetadata(stack.getMetadata()).getName();
    }
    
    @SideOnly(Side.CLIENT)
    public void initModels(){
    	for(EssenceType.Type type : EssenceType.Type.values()){
        	ModelLoader.setCustomModelResourceLocation(this, type.getMetadata(), new ModelResourceLocation(getRegistryName().toString() + "_" + type.byMetadata(type.getMetadata()).getName()));
    	}
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
    	String mat = "";
    	switch(stack.getMetadata()){
		case 0:
			mat = Tooltips.INFERIUM;
			break;
		case 1:
			mat = Tooltips.PRUDENTIUM;
			break;
		case 2:
			mat = Tooltips.INTERMEDIUM;
			break;
		case 3: 
			mat = Tooltips.SUPERIUM;
			break;
		case 4:
			mat = Tooltips.SUPREMIUM;
			break;
    	}
		tooltip.add(Tooltips.MATERIAL + " " + mat);
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
