package com.blakebr0.mysticalagriculture.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.iface.IRepairMaterial;
import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.util.NBTHelper;
import com.blakebr0.mysticalagriculture.util.ToolTools;
import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemEssenceSickle extends ItemBase implements IRepairMaterial {
	
	public int range;
	public ToolMaterial toolMaterial;
	public ItemStack repairMaterial;
	public TextFormatting color;
	
	public ItemEssenceSickle(String name, int range, ToolMaterial material, TextFormatting color){
		super("ma." + name);
		this.setMaxStackSize(1);
		this.setMaxDamage(material.getMaxUses());
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
		this.range = range;
		this.toolMaterial = material;
		this.color = color;
	}
	
	public int getRange(ItemStack stack){
		if(stack.getItem() == ModItems.itemSupremiumSickle){
        	NBTTagCompound tag = NBTHelper.getDataMap(stack);
        	if(tag.hasKey(ToolType.TOOL_TYPE)){
        		if(tag.getInteger(ToolType.TOOL_TYPE) == ToolType.REAPING_AOE.getIndex()){
        			return this.range + 1;
        		}
        	}
		}
		return this.range;
	}
	
	@Override
    public float getStrVsBlock(ItemStack stack, IBlockState state){
        return (state.getMaterial() == Material.LEAVES || state.getMaterial() == Material.PLANTS || state.getMaterial() == Material.VINE) ? (this.toolMaterial.getEfficiencyOnProperMaterial() / 2) : super.getStrVsBlock(stack, state);
	}

	@Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player){
		this.harvest(stack, getRange(stack), player.getEntityWorld(), pos, player);
        return false;
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
	
    public boolean harvest(ItemStack stack, int radius, World world, BlockPos pos, EntityPlayer player){
        
        IBlockState state = world.getBlockState(pos);
        float hardness = state.getBlockHardness(world, pos);
        
        if(!canHarvest(world, pos, false, stack, player) || !(state.getMaterial() == Material.LEAVES || state.getMaterial() == Material.PLANTS || state.getMaterial() == Material.VINE)){
            stack.damageItem(1, player);
        	return false;
        }
                
        if(radius > 0){        	
        	int used = 0;
        	Iterable<BlockPos> blocks = BlockPos.getAllInBox(pos.add(-radius, -radius, -radius), pos.add(radius, radius, radius));
        	for(BlockPos aoePos : blocks){
        		if(aoePos != pos){
        			IBlockState aoeState = world.getBlockState(aoePos);
        			if(aoeState.getBlockHardness(world, aoePos) <= hardness + 5.0F){
        				if(aoeState.getMaterial() == Material.LEAVES || aoeState.getMaterial() == Material.PLANTS || aoeState.getMaterial() == Material.VINE){
        					int usesLeft = stack.getMaxDamage() - stack.getItemDamage() + 1;
        					if(used < usesLeft || stack.getMaxDamage() == -1){
            					if(canHarvest(world, aoePos, true, stack, player)){
            						if(aoeState.getBlockHardness(world, aoePos) <= 0.0F){
            							if(Utils.randInt(1, 3) == 1){
            								used++;
            							}
            						} else {
                						used++;
            						}
            					}
        					}
        				}
        			} else {
        				return false;
        			}
        		}
            }
        	if(used > 0 && !player.capabilities.isCreativeMode){
        		stack.damageItem(used, player);
        	}
        }
        return true;
    }	
    
    private boolean canHarvest(World world, BlockPos pos, boolean extra, ItemStack stack, EntityPlayer player){
        IBlockState state = world.getBlockState(pos);
        float hardness = state.getBlockHardness(world, pos);
        Block block = state.getBlock();
        boolean harvest = (ForgeHooks.canHarvestBlock(block, player, world, pos) || this.canHarvestBlock(state, stack)) && (!extra || this.getStrVsBlock(stack, world.getBlockState(pos)) > 1.0F);
        if(hardness >= 0.0F && (!extra || harvest)){
        	return ToolTools.breakBlocksAOE(stack, world, player, pos);
        }
        return false;
    }
    
    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot){
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if(equipmentSlot == EntityEquipmentSlot.MAINHAND){
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.toolMaterial.getDamageVsEntity(), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.5D, 0));
        }

        return multimap;
    }
}
