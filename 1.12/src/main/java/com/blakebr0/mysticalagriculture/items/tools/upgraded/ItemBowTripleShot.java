package com.blakebr0.mysticalagriculture.items.tools.upgraded;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceBow;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBowTripleShot extends ItemEssenceBow {

	public ItemBowTripleShot(String name, ToolMaterial material, float drawSpeed, TextFormatting color){
		super(name, material, drawSpeed, color);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add(Tooltips.DURABILITY + color + (damage > -1 ? damage : Tooltips.UNLIMITED));
		tooltip.add(Tooltips.DAMAGE + color + "+" + this.damage);
		tooltip.add(Tooltips.DRAW_SPEED + color +  "+" + (int)(this.drawSpeed * 100) + "%");
		tooltip.add(Tooltips.CHARM_SLOT + Colors.RED + Tooltips.TRIPLE_SHOT);
	}

	@Override
    public ItemStack getContainerItem(ItemStack itemstack){
        return ModItems.itemCharm.itemCharmTripleShot;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return ModConfig.confCharmReturn;
    }
    
	@Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft){
        if(entityLiving instanceof EntityPlayer){
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = this.findAmmo(entityplayer);

            int i = (int)((this.getMaxItemUseDuration(stack) - timeLeft) * getDrawSpeed(stack));
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, world, (EntityPlayer)entityLiving, i, itemstack != null || flag);
            if(i < 0) return;
            
            for(int xd = 0; xd < 3; xd++){
                if(itemstack != null || flag){
                    if(itemstack == null){
                        itemstack = new ItemStack(Items.ARROW);
                    }

                    float f = getArrowVelocity(i);

                    if((double)f >= 0.1D){
                        boolean flag1 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow ? ((ItemArrow)itemstack.getItem()).isInfinite(itemstack, stack, entityplayer) : false);

                        if(!world.isRemote){
                            ItemArrow itemarrow = (ItemArrow)((ItemArrow)(itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW));
                            EntityArrow entityarrow = itemarrow.createArrow(world, itemstack, entityplayer);
                            entityarrow.setAim(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);
                            entityarrow.setDamage(entityarrow.getDamage() + this.damage);

                            if(f >= 1.0F){
                                entityarrow.setIsCritical(true);
                            }
                            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                            if(j > 0){
                                entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
                            }

                            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
                            if(k > 0){
                                entityarrow.setKnockbackStrength(k);
                            }

                            if(EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0){
                                entityarrow.setFire(100);
                            }

                            stack.damageItem(1, entityplayer);

                            if(flag1){
                                entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                            }
                            world.spawnEntity(entityarrow);
                        }

                        world.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                        if(!flag1){
                            itemstack.shrink(1);;

                            if(itemstack.getCount() == 0){
                                entityplayer.inventory.deleteStack(itemstack);
                            }
                        }
                        entityplayer.addStat(StatList.getObjectUseStats(this));
                    }
                }
            }
        }
	}
}
