package com.blakebr0.mysticalagriculture.items.tools;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.iface.IRepairMaterial;
import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.util.NBTHelper;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemEssenceShears extends ItemShears implements IRepairMaterial {

	public ItemStack repairMaterial;
	public TextFormatting color;
	
	public ItemEssenceShears(String name, ToolMaterial material, TextFormatting color){
		this.setUnlocalizedName("ma." + name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.setMaxDamage(material.getMaxUses());
		this.color = color;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add(Tooltips.DURABILITY + color + (damage > -1 ? damage : Tooltips.UNLIMITED));
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
	
    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity, EnumHand hand){
    	if(itemstack.getItem() == ModItems.itemSupremiumShears){
        	NBTTagCompound tag = NBTHelper.getDataMap(itemstack);
        	if(tag.hasKey(ToolType.TOOL_TYPE)){
        		if(tag.getInteger(ToolType.TOOL_TYPE) == ToolType.RAINBOW.getIndex()){
        	        if(entity.getEntityWorld().isRemote){
        	            return false;
        	        }
        	        
        	        if(entity instanceof IShearable){
        	            IShearable target = (IShearable)entity;
        	            BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
        	            if(target.isShearable(itemstack, entity.getEntityWorld(), pos)){
        	                List<ItemStack> drops = target.onSheared(itemstack, entity.getEntityWorld(), pos, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemstack));

        	                Random rand = new Random();
        	                for(ItemStack stack : drops){
        	                	if(Block.getBlockFromItem(stack.getItem()) == Blocks.WOOL){
        	                		stack = new ItemStack(stack.getItem(), 1, Utils.randInt(0, 15));
        	                	}
        	                    EntityItem ent = entity.entityDropItem(stack, 1.0F);
        	                    ent.motionY += rand.nextFloat() * 0.05F;
        	                    ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
        	                    ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
        	                }
        	                itemstack.damageItem(1, entity);
        	            }
        	            return true;
        	        }
        	        return false;
        		} else if(tag.getInteger(ToolType.TOOL_TYPE) == ToolType.SHEARING_AOE.getIndex()){
        	        if(entity.getEntityWorld().isRemote){
        	            return false;
        	        }
        	        
        	        List<EntityLivingBase> entities = player.getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, entity.getEntityBoundingBox().grow(1.5D, 0.25D, 1.5D));
        	        
        	        for(EntityLivingBase aoeEntity : entities){
        	        	shear(itemstack, player, aoeEntity, hand);
        	        }
        		}
        	}
    	}
    	return super.itemInteractionForEntity(itemstack, player, entity, hand);
    }
    
    private boolean shear(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity, EnumHand hand){
        if(entity instanceof IShearable){
            IShearable target = (IShearable)entity;
            BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
            if(target.isShearable(itemstack, entity.getEntityWorld(), pos)){
                List<ItemStack> drops = target.onSheared(itemstack, entity.getEntityWorld(), pos, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemstack));

                Random rand = new Random();
                for(ItemStack stack : drops){
                    EntityItem ent = entity.entityDropItem(stack, 1.0F);
                    ent.motionY += rand.nextFloat() * 0.05F;
                    ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                    ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                }
                itemstack.damageItem(1, entity);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player){
        if(player.getEntityWorld().isRemote || player.capabilities.isCreativeMode){
            return false;
        }
        
        if(itemstack.getItem() != ModItems.itemSupremiumShears){
        	return super.onBlockStartBreak(itemstack, pos, player);
        }
        
        NBTTagCompound tag = NBTHelper.getDataMap(itemstack);
        if(!tag.hasKey(ToolType.TOOL_TYPE)){
        	return super.onBlockStartBreak(itemstack, pos, player);
        } else if(!(tag.getInteger(ToolType.TOOL_TYPE) == ToolType.SHEARING_AOE.getIndex())){
    		return super.onBlockStartBreak(itemstack, pos, player);
    	}
        
        Iterable<BlockPos> blocks = pos.getAllInBox(pos.add(1, 1, 1), pos.add(-1, -1, -1));      
        
        for(BlockPos aoePos : blocks){
            Block block = player.getEntityWorld().getBlockState(aoePos).getBlock();
            if(block instanceof IShearable){
                IShearable target = (IShearable)block;
                if(target.isShearable(itemstack, player.getEntityWorld(), aoePos)){
                    List<ItemStack> drops = target.onSheared(itemstack, player.getEntityWorld(), aoePos, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemstack));
                    Random rand = new Random();

                    for(ItemStack stack : drops){
                        float f = 0.7F;
                        double d  = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d1 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d2 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        EntityItem entityitem = new EntityItem(player.getEntityWorld(), (double)aoePos.getX() + d, (double)aoePos.getY() + d1, (double)aoePos.getZ() + d2, stack);
                        entityitem.setDefaultPickupDelay();
                        player.getEntityWorld().spawnEntity(entityitem);
                        player.getEntityWorld().destroyBlock(aoePos, false);
                    }

                    itemstack.damageItem(1, player);
                    player.addStat(StatList.getBlockStats(block));
                }
            }
        }
        return false;
    }
}
