package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.iface.IRepairMaterial;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.armor.ArmorType;
import com.blakebr0.mysticalagriculture.items.tools.ToolType;
import com.blakebr0.mysticalagriculture.util.NBTHelper;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class UpgradeRecipe extends ShapedOreRecipe {

    private final Item resultItem;
    private final int resultMeta;
    private final int type;

    public UpgradeRecipe(ItemStack result, int type, Object... recipe){
		super(new ResourceLocation("", ""), result, recipe);
		this.resultItem = result.getItem();
		this.resultMeta = result.getItemDamage();
		this.type = type;
		this.setMirrored(false);
	}

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting){        
        ItemStack result = new ItemStack((Item)this.resultItem, 1, this.resultMeta);
        NBTTagCompound tag = new NBTTagCompound();
        ItemStack slotStack;
        for(int i = 0; i < inventoryCrafting.getSizeInventory(); i++){
            slotStack = inventoryCrafting.getStackInSlot(i);
            if(!StackHelper.isNull(slotStack) && slotStack.getItem() != null){
            	if(slotStack.getItem() == result.getItem()){
            		tag = NBTHelper.getDataMap(slotStack).copy();
            		int newDamage = MathHelper.clamp(slotStack.getItemDamage(), 0, result.getMaxDamage());
            		result.setItemDamage(newDamage);
            		break;
            	}
            }
        }
        if(this.resultItem instanceof ItemArmor){
        	tag.setInteger(ArmorType.ARMOR_TYPE, type);
        } else {
        	tag.setInteger(ToolType.TOOL_TYPE, type);
        }
    	result.setTagCompound(tag);
        return result;
    }
    
    @Override
    public ItemStack getRecipeOutput(){
        ItemStack result = new ItemStack((Item)this.resultItem, 1, this.resultMeta);
        NBTTagCompound tag = NBTHelper.getDataMap(result);
        if(this.resultItem instanceof ItemArmor){
        	tag.setInteger(ArmorType.ARMOR_TYPE, type);
        } else {
        	tag.setInteger(ToolType.TOOL_TYPE, type);
        }
    	result.setTagCompound(tag);
    	return result;
    }
    
    @Override
    public boolean matches(InventoryCrafting inv, World world){
        for(int x = 0; x <= MAX_CRAFT_GRID_WIDTH - width; x++){
            for(int y = 0; y <= MAX_CRAFT_GRID_HEIGHT - height; ++y){
            	ItemStack stack = inv.getStackInRowAndColumn(x, y);
            	if(!stack.isEmpty() && stack.hasTagCompound()){
            		if(stack.getTagCompound().hasKey(ArmorType.ARMOR_TYPE)){
            			return false;
            		} else {
                        if(checkMatch(inv, x, y, false)){
                            return true;
                        }
            		}
            	} else {
                    if(checkMatch(inv, x, y, false)){
                        return true;
                    }
            	}
            }
        }
        return false;
    }
}