package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSeed extends ItemSeeds {
	
	private Block crops;
	private int tier;
	
	public ItemSeed(String name, Block crops, int tier){
		super(crops, Blocks.FARMLAND);
		this.setUnlocalizedName("ma." + name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
        this.crops = crops;
        this.tier = tier;
	}
	
	public int getTier(){
		return this.tier;
	}
		
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
        switch(this.tier - 1){
        case 0:
        	tooltip.add(Tooltips.TIER + Colors.YELLOW + "1");
            break;
        case 1:
        	tooltip.add(Tooltips.TIER + Colors.GREEN + "2");
            break;
        case 2:
        	tooltip.add(Tooltips.TIER + Colors.GOLD + "3");
            break;
        case 3:
        	tooltip.add(Tooltips.TIER + Colors.AQUA + "4");
            break;
        case 4:
        	tooltip.add(Tooltips.TIER + Colors.RED + "5");
            break;
        }
    }
}