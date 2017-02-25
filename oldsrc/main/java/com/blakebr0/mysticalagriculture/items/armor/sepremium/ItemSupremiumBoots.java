package com.blakebr0.mysticalagriculture.items.armor.sepremium;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSupremiumBoots extends ItemArmor {
	public ItemSupremiumBoots(String name, ArmorMaterial material, int index, EntityEquipmentSlot slot){
		super(material, index, slot);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	    this.setMaxStackSize(1);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		if(stack.getItem() == ModItems.supremium_boots){
			int damage = stack.getMaxDamage() - stack.getItemDamage();
			tooltip.add("Durability: \u00A7c" + damage);
			if(ModConfig.supremium_flight){ tooltip.add("Set Bonus: \u00A7cFlight"); }
			tooltip.add("Charm Slot: \u00A7c\u00A7oEmpty");
			super.addInformation(stack, player, tooltip, advanced);
		}
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
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
	
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return repair.getItem() == ModItems.supremium_ingot;
    }
}