package com.blakebr0.mysticalagriculture.items.armor.prudentium;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.armor.sepremium.ItemSupremiumBoots;
import com.blakebr0.mysticalagriculture.items.armor.sepremium.ItemSupremiumChestplate;
import com.blakebr0.mysticalagriculture.items.armor.sepremium.ItemSupremiumHelmet;
import com.blakebr0.mysticalagriculture.items.armor.sepremium.ItemSupremiumLeggings;

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

public class ItemPrudentiumHelmet extends ItemArmor {

	public ItemPrudentiumHelmet(String name, ArmorMaterial material, int index, EntityEquipmentSlot slot){
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
		tooltip.add("Durability: \u00A7a" + damage);
		if(ModConfig.set_bonuses){ tooltip.add("Set Bonus: \u00A7aWater Breathing"); }
		super.addInformation(stack, player, tooltip, advanced);
	}

	public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack){
		ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
		ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		if(ModConfig.set_bonuses && head != null && head.getItem() instanceof ItemPrudentiumHelmet && chest != null && chest.getItem() instanceof ItemPrudentiumChestplate && legs != null && legs.getItem() instanceof ItemPrudentiumLeggings && feet != null && feet.getItem() instanceof ItemPrudentiumBoots){
			if(entity.isInWater()){
				entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 5, 0, true, false));
			}
		}
	}
	
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return repair.getItem() == ModItems.prudentium_ingot;
    }
}
