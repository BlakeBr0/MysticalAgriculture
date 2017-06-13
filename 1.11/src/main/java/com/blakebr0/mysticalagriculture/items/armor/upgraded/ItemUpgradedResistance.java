package com.blakebr0.mysticalagriculture.items.armor.upgraded;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemUpgradedResistance extends ItemSupremiumArmor {

	public ItemUpgradedResistance(String name, ArmorMaterial material, int index, EntityEquipmentSlot slot){
		super(name, material, index, slot);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	    this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT) // TODO: localize
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add("Durability: \u00A7c" + damage);
		if(ModConfig.confSupremiumFlight){ tooltip.add("Set Bonus: \u00A7cFlight"); }
		tooltip.add("Charm Slot: \u00A7cResistance");
	}
	
	@Override
    public ItemStack getContainerItem(ItemStack itemstack){
        return new ItemStack(ModItems.itemCharmResistance, 1, 0);
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
    	player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 5, 0, true, false));
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return repair.getItem() == ModItems.itemSupremiumIngot;
    }
}
