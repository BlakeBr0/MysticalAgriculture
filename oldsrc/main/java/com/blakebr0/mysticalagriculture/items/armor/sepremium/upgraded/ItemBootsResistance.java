package com.blakebr0.mysticalagriculture.items.armor.sepremium.upgraded;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.armor.sepremium.ItemSupremiumBoots;
import com.blakebr0.mysticalagriculture.items.armor.sepremium.ItemSupremiumChestplate;
import com.blakebr0.mysticalagriculture.items.armor.sepremium.ItemSupremiumHelmet;
import com.blakebr0.mysticalagriculture.items.armor.sepremium.ItemSupremiumLeggings;
import com.blakebr0.mysticalagriculture.lib.ModToolMaterials;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBootsResistance extends ItemSupremiumBoots {

	public ItemBootsResistance(String name, ArmorMaterial material, int index, EntityEquipmentSlot slot){
		super(name, material, index, slot);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	    this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add("Durability: \u00A7c" + damage);
		if(ModConfig.supremium_flight){ tooltip.add("Set Bonus: \u00A7cFlight"); }
		tooltip.add("Charm Slot: \u00A7cResistance");
		super.addInformation(stack, player, tooltip, advanced);
	}
	
	@Override
    public ItemStack getContainerItem(ItemStack itemstack){
        return new ItemStack(ModItems.charm_resistance, 1, 0);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return ModConfig.charm_return;
    }

	public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack){
		ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
		ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		if(head != null && head.getItem() instanceof ItemSupremiumHelmet && chest != null && chest.getItem() instanceof ItemSupremiumChestplate && legs != null && legs.getItem() instanceof ItemSupremiumLeggings && feet != null && feet.getItem() instanceof ItemSupremiumBoots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
			if(ModConfig.set_bonuses){
				if(entity.isInWater()){
					entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 5, 0, true, false));
				}
				entity.stepHeight = 1.0F;
				entity.fallDistance = 0;
			}
			if(ModConfig.supremium_flight){
				entity.capabilities.allowFlying = true;
			}
		} else {
			entity.stepHeight = 0.5F;
			entity.capabilities.isFlying = false;
			entity.capabilities.allowFlying = false;
		}
		if(entity instanceof EntityLivingBase){
			entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 5, 0, true, false));
		}
	}

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return repair.getItem() == ModItems.supremium_ingot;
    }
}
