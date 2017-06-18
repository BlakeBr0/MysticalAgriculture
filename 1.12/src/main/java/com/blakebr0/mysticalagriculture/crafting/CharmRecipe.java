package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;
import com.blakebr0.mysticalagriculture.util.NBTHelper;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class CharmRecipe extends ShapelessOreRecipe {
    
    private final Item resultItem;
    private final int resultMeta;
    
    public CharmRecipe(ItemStack result, Object... recipe){
        super(new ResourceLocation("", ""), result, recipe);
        this.resultItem = result.getItem();
        this.resultMeta = result.getItemDamage();
        result.getEnchantmentTagList();
    }
    
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting){
        NBTTagCompound tags = null;
        
        ItemStack result = new ItemStack((Item) this.resultItem, 1, this.resultMeta);
        
        ItemStack slotStack;
        for(int i = 0; i < inventoryCrafting.getSizeInventory(); i++){
            slotStack = inventoryCrafting.getStackInSlot(i);
            if(!slotStack.isEmpty() && slotStack.getItem() != null){
     //           if(slotStack.getItem() instanceof ItemSupremiumArmor){
                    tags = (NBTTagCompound) NBTHelper.getDataMap(slotStack).copy();
	 				int newDamage = MathHelper.clamp(slotStack.getItemDamage(), 0, result.getMaxDamage());
	 				result.setItemDamage(newDamage);
	 				break;
       //         }
            }
        }
        
        if(tags != null){
            result.setTagCompound(tags);
        }
        
        return result;
    }
}