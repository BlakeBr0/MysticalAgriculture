package com.blakebr0.mysticalagriculture.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.iface.IRepairMaterial;
import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemPrudentiumArmor extends ItemArmor implements IRepairMaterial {
	
	private ItemStack repairMaterial;

	public ItemPrudentiumArmor(String name, ArmorMaterial material, int index, EntityEquipmentSlot slot){
		super(material, index, slot);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
	    this.setMaxStackSize(1);
	}
		
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add(Tooltips.DURABILITY + Colors.GREEN + damage);
		if(ModConfig.confSetBonuses){ tooltip.add(Tooltips.SET_BONUS + Colors.GREEN + Tooltips.WATER_BREATHING); }
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack){
		if(ModConfig.confSetBonuses && isFullSet(player)){
			if(player.isInWater()){
				player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 5, 0, true, false));
			}
		}
	}
	
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return OreDictionary.itemMatches(getRepairMaterial(), repair, false);
    }

	@Override
	public void setRepairMaterial(ItemStack stack){
		repairMaterial = stack;
	}

	@Override
	public ItemStack getRepairMaterial(){
		return repairMaterial;
	}
	
	public static boolean isFullSet(EntityPlayer player){		
		ItemStack head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		
		return !head.isEmpty() && head.getItem() instanceof ItemPrudentiumArmor && !chest.isEmpty() && chest.getItem() instanceof ItemPrudentiumArmor && !legs.isEmpty() && legs.getItem() instanceof ItemPrudentiumArmor && !feet.isEmpty() && feet.getItem() instanceof ItemPrudentiumArmor;
	}
}
