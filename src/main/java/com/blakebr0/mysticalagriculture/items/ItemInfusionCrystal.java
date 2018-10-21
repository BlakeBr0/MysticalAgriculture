package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemInfusionCrystal extends Item {
	
	public ItemInfusionCrystal(){
		super();
		this.setUnlocalizedName("ma.infusion_crystal");
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
		this.setMaxStackSize(1);
		this.setMaxDamage(ModConfig.confCrystalDurability - 1);
		this.setNoRepair();
	}
	
    @Override
    public ItemStack getContainerItem(ItemStack itemstack){
        ItemStack stack = itemstack.copy();

        stack.setItemDamage(stack.getItemDamage() + 1);
        stack.setCount(1);

        return stack;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
    	int damage = stack.getMaxDamage() - stack.getItemDamage() + 1;
    	tooltip.add(Tooltips.USES_LEFT + Colors.RED + damage);
    }
}
