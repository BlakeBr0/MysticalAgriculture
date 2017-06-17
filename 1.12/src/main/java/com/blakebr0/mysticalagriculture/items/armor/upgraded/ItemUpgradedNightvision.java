package com.blakebr0.mysticalagriculture.items.armor.upgraded;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemUpgradedNightvision extends ItemSupremiumArmor {

	public ItemUpgradedNightvision(String name, ArmorMaterial material, int index, EntityEquipmentSlot slot){
		super(name, material, index, slot);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	    this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add(Tooltips.DURABILITY + Colors.RED + damage);
		if(ModConfig.confSupremiumFlight){ tooltip.add(Tooltips.SET_BONUS + Colors.RED + Tooltips.FLIGHT); }
		tooltip.add(Tooltips.CHARM_SLOT + Colors.RED + Tooltips.NIGHT_VISION);
	}
	
	@Override
    public ItemStack getContainerItem(ItemStack itemstack){
        return ModItems.itemCharm.itemCharmNightvision;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return ModConfig.confCharmReturn;
    }

    @Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack){
    	if(ModConfig.confSetBonuses && ItemSupremiumArmor.isFullSet(player)){
    		if(player.isInWater()){
    			player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 5, 0, true, false));
    		}
    		player.fallDistance = 0;
    	}
    	player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, false));
	}

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return repair == ModItems.itemCrafting.itemSupremiumIngot;
    }
}
