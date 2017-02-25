package com.blakebr0.mysticalagriculture.items.armor.superium;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.armor.sepremium.ItemSupremiumBoots;
import com.blakebr0.mysticalagriculture.items.armor.sepremium.ItemSupremiumChestplate;
import com.blakebr0.mysticalagriculture.items.armor.sepremium.ItemSupremiumHelmet;
import com.blakebr0.mysticalagriculture.items.armor.sepremium.ItemSupremiumLeggings;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSuperiumChestplate extends ItemArmor {

	public ItemSuperiumChestplate(String name, ArmorMaterial material, int index, EntityEquipmentSlot slot){
		super(material, index, slot);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	    this.setMaxStackSize(1);
	}
		
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add("Durability: \u00A7b" + damage);
		if(ModConfig.set_bonuses){ tooltip.add("Set Bonus:\u00A7b No Fall Damage"); }
		super.addInformation(stack, player, tooltip, advanced);
	}

	public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack){
		ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
		ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		if(ModConfig.set_bonuses && head != null && head.getItem() instanceof ItemSuperiumHelmet && chest != null && chest.getItem() instanceof ItemSuperiumChestplate && legs != null && legs.getItem() instanceof ItemSuperiumLeggings && feet != null && feet.getItem() instanceof ItemSuperiumBoots){
			if(entity.isInWater()){
				entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 5, 0, true, false));
			}
			entity.stepHeight = 1.0F;
			entity.fallDistance = 0;
		} else {
			entity.stepHeight = 0.5F;
		}
	}
	
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return repair.getItem() == ModItems.superium_ingot;
    }
}
