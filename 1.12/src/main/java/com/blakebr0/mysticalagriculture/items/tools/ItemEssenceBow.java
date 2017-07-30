package com.blakebr0.mysticalagriculture.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.iface.IRepairMaterial;
import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.util.NBTHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemEssenceBow extends ItemBow implements IRepairMaterial {

	public ItemStack repairMaterial;
	public ToolMaterial toolMaterial;
	public float damage;
	public float drawSpeed;
	public TextFormatting color;
	
	public ItemEssenceBow(String name, ToolMaterial material, float drawSpeed, TextFormatting color){
		this.setUnlocalizedName("ma." + name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.toolMaterial = material;
		this.damage = material.getDamageVsEntity() / 4;
		this.drawSpeed = drawSpeed;
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
                    return !itemstack.isEmpty() && itemstack.getItem() instanceof ItemEssenceBow ? (float)(stack.getMaxItemUseDuration() - entity.getItemInUseCount()) * getDrawSpeed(stack) / 20.0F : 0.0F;
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
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add(Tooltips.DURABILITY + color + (damage > -1 ? damage : Tooltips.UNLIMITED));
		tooltip.add(Tooltips.DAMAGE + color + "+" + this.damage);
		tooltip.add(Tooltips.DRAW_SPEED + color +  "+" + (int)(this.drawSpeed * 100) + "%");
		if(OreDictionary.itemMatches(getRepairMaterial(), ModItems.itemCrafting.itemSupremiumIngot, false)){
			NBTTagCompound tag = NBTHelper.getDataMap(stack);
			if(tag.hasKey(ToolType.TOOL_TYPE)){
				tooltip.add(Tooltips.CHARM_SLOT + Colors.RED + ToolType.byIndex(tag.getInteger(ToolType.TOOL_TYPE)).getLocalizedName());
			} else {
				tooltip.add(Tooltips.CHARM_SLOT + Colors.RED + Tooltips.EMPTY);
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
	
	public float getDrawSpeed(ItemStack stack){
		float multi = 1.0F;
		if(stack.getItem() == ModItems.itemSupremiumBow){
			NBTTagCompound tag = NBTHelper.getDataMap(stack);
			if(tag.hasKey(ToolType.TOOL_TYPE)){
				if(tag.getInteger(ToolType.TOOL_TYPE) == ToolType.QUICK_DRAW.getIndex()){
					multi = 2.0F;
				} else if(tag.getInteger(ToolType.TOOL_TYPE) == ToolType.TRIPLE_SHOT.getIndex()){
					multi = 0.5F;
				}
			}
		}
		return (this.drawSpeed * multi) + 1.0f;
	}
	
	protected ItemStack findAmmo(EntityPlayer player){
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
			return ItemStack.EMPTY;
		}
	}
	
	public int getArrowsShot(ItemStack stack){
		if(stack.getItem() == ModItems.itemSupremiumBow){
			NBTTagCompound tag = NBTHelper.getDataMap(stack);
			if(tag.hasKey(ToolType.TOOL_TYPE)){
				if(tag.getInteger(ToolType.TOOL_TYPE) == ToolType.TRIPLE_SHOT.getIndex()){
					return 3;
				}
			}
		}
		return 1;
	}
	
	@Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft){
        if(entityLiving instanceof EntityPlayer){
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = this.findAmmo(entityplayer);

            int i = (int)((this.getMaxItemUseDuration(stack) - timeLeft) * getDrawSpeed(stack));
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, world, (EntityPlayer)entityLiving, i, !itemstack.isEmpty() || flag);
            if(i < 0) return;
            int ts = getArrowsShot(stack);
            for(int xd = 0; xd < ts; xd++){
                if(!itemstack.isEmpty() || flag){
                    if(itemstack.isEmpty()){
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
