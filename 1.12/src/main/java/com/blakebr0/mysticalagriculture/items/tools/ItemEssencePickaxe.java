package com.blakebr0.mysticalagriculture.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.IRepairMaterial;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.util.NBTHelper;
import com.blakebr0.mysticalagriculture.util.ToolTools;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemEssencePickaxe extends ItemPickaxe implements IRepairMaterial {
	    
	public ItemStack repairMaterial;
	public TextFormatting color;
	
	public ItemEssencePickaxe(String name, ToolMaterial material, TextFormatting color){
		super(material);
		this.setUnlocalizedName("ma." + name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
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
	
	@Override // TODO: upgraded supremium stuff container items
    public ItemStack getContainerItem(ItemStack stack){
		NBTTagCompound tag = NBTHelper.getDataMap(stack);
		int type = tag.getInteger(ToolType.TOOL_TYPE);
		if(type == ToolType.MINERS_VISION.getIndex()){
			return ModItems.itemCharm.itemCharmMinersVision;
		} else if(type == ToolType.MINING_AOE.getIndex()){
			return ModItems.itemCharm.itemCharmMiningAOE;
		}
        return ItemStack.EMPTY;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return ModConfig.confCharmReturn;
    }
	
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
		ItemStack stack = player.getHeldItem(hand);
		if(stack.getItem() == ModItems.itemSupremiumPickaxe){
			NBTTagCompound tag = NBTHelper.getDataMap(stack);
			if(tag.hasKey(ToolType.TOOL_TYPE)){
				if(tag.getInteger(ToolType.TOOL_TYPE) == ToolType.MINERS_VISION.getIndex()){
			    	ItemStack torch = new ItemStack(ModBlocks.blockMinersTorch);
			    	if(torch.onItemUse(player, world, pos, hand, side, hitX, hitY, hitZ) != EnumActionResult.FAIL){
			    		return EnumActionResult.SUCCESS;
			    	}
				}
			}
		}
        return EnumActionResult.FAIL;
    }
	
	@Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player){
		if(stack.getItem() == ModItems.itemSupremiumPickaxe){
			NBTTagCompound tag = NBTHelper.getDataMap(stack);
			if(tag.hasKey(ToolType.TOOL_TYPE)){
				if(tag.getInteger(ToolType.TOOL_TYPE) == ToolType.MINING_AOE.getIndex()){
			        boolean blocks = false;
		            RayTraceResult ray = ToolTools.getBlockWithinReach(player.getEntityWorld(), player);
		            if(ray != null){
		                int side = ray.sideHit.ordinal();
		                blocks = this.harvest(stack, 1, player.getEntityWorld(), pos, side, player);
		            }
		            return blocks;
				}
			}
		}
		return super.onBlockStartBreak(stack, pos, player);
    }

    public boolean harvest(ItemStack stack, int radius, World world, BlockPos pos, int side, EntityPlayer player){
        int xRange = radius;
        int yRange = radius;
        int zRange = 0;

        if(side == 0 || side == 1){
            zRange = radius;
            yRange = 0;
        }
        
        if(side == 4 || side == 5){
            xRange = 0;
            zRange = radius;
        }
        
        IBlockState state = world.getBlockState(pos);
        float hardness = state.getBlockHardness(world, pos);
        
        if(!canHarvest(world, pos, false, stack, player)){
        	return false;
        }
        
        if(radius > 0 && hardness >= 0.2F && state.getBlock().isToolEffective("pickaxe", state)){
        	Iterable<BlockPos> blocks = BlockPos.getAllInBox(pos.add(-xRange, -yRange, -zRange), pos.add(xRange, yRange, zRange));
        	for(BlockPos aoePos : blocks){
        		if(aoePos != pos){
        			IBlockState aoeState = world.getBlockState(aoePos);
        			if(aoeState.getBlockHardness(world, aoePos) <= hardness + 5.0F){
        				if(aoeState.getBlock().isToolEffective("pickaxe", aoeState)){
        					canHarvest(world, aoePos, true, stack, player);
        				}   
        			} else {
        				return false;
        			}
        		}
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
}