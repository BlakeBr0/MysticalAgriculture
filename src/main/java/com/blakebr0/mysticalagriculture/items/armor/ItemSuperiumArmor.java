package com.blakebr0.mysticalagriculture.items.armor;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;

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
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSuperiumArmor extends ItemArmor {

	public ItemSuperiumArmor(String name, ArmorMaterial material, int index, EntityEquipmentSlot slot){
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
	}

	public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack){
		ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
		ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		if(ModConfig.set_bonuses && head != null && head.getItem() instanceof ItemSuperiumArmor && chest != null && chest.getItem() instanceof ItemSuperiumArmor && legs != null && legs.getItem() instanceof ItemSuperiumArmor && feet != null && feet.getItem() instanceof ItemSuperiumArmor){
			if(entity.isInWater()){
				entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 5, 0, true, false));
			}
			entity.fallDistance = 0;
		}
	}
	
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return repair.getItem() == ModItems.superium_ingot;
    }
    
    public static class abilityHandler {
    	
    	public static List<String> playersWithSet = new ArrayList<String>();
    	
    	public static String playerKey(EntityPlayer player) {
    		return player.getGameProfile().getName() +":"+ player.worldObj.isRemote;
    	}
    	
    	public static boolean playerHasSet(EntityPlayer entity) {
    		ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
    		ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
    		ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
    		ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);	
    		
    		return head != null && head.getItem() instanceof ItemSuperiumArmor && chest != null && chest.getItem() instanceof ItemSuperiumArmor && legs != null && legs.getItem() instanceof ItemSuperiumArmor && feet != null && feet.getItem() instanceof ItemSuperiumArmor;
    	}
    	
    	@SubscribeEvent
    	public void updatePlayerAbilityStatus(LivingUpdateEvent event) {
    		if(event.getEntityLiving() instanceof EntityPlayer) {
    			EntityPlayer player = (EntityPlayer)event.getEntityLiving();
    			String key = playerKey(player);

    			Boolean hasSet = playerHasSet(player);
    			if(playersWithSet.contains(key) && ModConfig.set_bonuses){
    				if(hasSet){
    					player.stepHeight = 1.0F;
    				} else {
    					player.stepHeight = 0.5F;
    					playersWithSet.remove(key);
    				}
    			} else if(hasSet) {
    				playersWithSet.add(key);
    			}
    		}
    	}
    }
}
