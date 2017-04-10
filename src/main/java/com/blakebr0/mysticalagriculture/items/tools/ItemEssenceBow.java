package com.blakebr0.mysticalagriculture.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.items.ModItems;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEssenceBow extends ItemBow {

	public Item repairMaterial;
	public ToolMaterial toolMaterial;
	public float damage;
	public float drawSpeed;
	public TextFormatting color;
	
	public ItemEssenceBow(String name, ToolMaterial material, float drawSpeed, Item repairMaterial, TextFormatting color){
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.toolMaterial = material;
		this.damage = material.getDamageVsEntity() / 4;
		this.drawSpeed = drawSpeed;
		this.repairMaterial = repairMaterial;
		this.color = color;
		this.setMaxStackSize(1);
		this.setMaxDamage(material.getMaxUses());
		
        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter(){
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity){
                if(entity == null){
                    return 0.0F;
                } else {
                    ItemStack itemstack = entity.getActiveItemStack();
                    return itemstack != null && itemstack.getItem() instanceof ItemEssenceBow ? (float)(stack.getMaxItemUseDuration() - entity.getItemInUseCount()) * getDrawSpeed() / 20.0F : 0.0F;
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter(){
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity){
                return entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add("Durability: " + color + (damage > -1 ? damage : "Unlimited"));
		tooltip.add("Damage: " + color + "+" + this.damage);
		tooltip.add("Draw Speed: " + color +  "+" + (int)(this.drawSpeed * 100) + "%");
		if(repairMaterial == ModItems.itemSupremiumIngot){
			tooltip.add("Charm Slot: \u00A7c\u00A7oEmpty");
		}
	}
	
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return repair.getItem() == repairMaterial;
    }
	
	public float getDrawSpeed(){
		return this.drawSpeed + 1.0f;
	}
	
	private ItemStack findAmmo(EntityPlayer player){
		if(this.isArrow(player.getHeldItem(EnumHand.OFF_HAND))){
			return player.getHeldItem(EnumHand.OFF_HAND);
		} else if(this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND))){
	            return player.getHeldItem(EnumHand.MAIN_HAND);
		} else {
			for(int i = 0; i < player.inventory.getSizeInventory(); ++i){
				ItemStack itemstack = player.inventory.getStackInSlot(i);
				if(this.isArrow(itemstack)){
					return itemstack;
				}
			}
			return null;
		}
	}
	
	@Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft){
        if(entityLiving instanceof EntityPlayer){
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = this.findAmmo(entityplayer);

            int i = (int)((this.getMaxItemUseDuration(stack) - timeLeft) * getDrawSpeed());
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, world, (EntityPlayer)entityLiving, i, itemstack != null || flag);
            if(i < 0) return;

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

                        world.spawnEntityInWorld(entityarrow);
                    }

                    world.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if(!flag1){
                        --itemstack.stackSize;

                        if(itemstack.stackSize == 0){
                            entityplayer.inventory.deleteStack(itemstack);
                        }
                    }
                    entityplayer.addStat(StatList.getObjectUseStats(this));
                }
            }
        }
    }
}
