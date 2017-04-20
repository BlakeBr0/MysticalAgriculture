package com.blakebr0.mysticalagriculture.items.tools.upgraded;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceSword;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSwordAOE extends ItemEssenceSword {
	    
	public ItemSwordAOE(String name, ToolMaterial material, Item repairMaterial, TextFormatting color){
		super(name, material, repairMaterial, color);
	}
		
	@Override
	@SideOnly(Side.CLIENT) // TODO: localize
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		tooltip.add("Durability: \u00A7cUnlimited");
		tooltip.add("Charm Slot: \u00A7cAttack AOE");
	}
	
	@Override
    public ItemStack getContainerItem(ItemStack itemstack){
        return new ItemStack(ModItems.itemCharmAttackAOE, 1, 0);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return ModConfig.confCharmReturn;
    }
	
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity){
    	if(player.getCooledAttackStrength(0.5F) >= 0.95F){
    		List<EntityLivingBase> entities = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, entity.getEntityBoundingBox().expand(1.5D, 0.25D, 1.5D));

            for(EntityLivingBase aoeEntity : entities) {
                if(aoeEntity != player && aoeEntity != entity && !player.isOnSameTeam(entity)) {
                    aoeEntity.knockBack(player, 0.4F, (double) MathHelper.sin(player.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(player.rotationYaw * 0.017453292F)));
                    aoeEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), 13F);
                }
            }

            player.worldObj.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
            player.spawnSweepParticles();
    	}
    	return super.onLeftClickEntity(stack, player, entity);
    }
}
