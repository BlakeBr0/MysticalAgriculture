package com.blakebr0.mysticalagriculture.crafting;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;

import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;
import com.blakebr0.mysticalagriculture.util.NBTHelper;

import java.util.*;

public class TinkeringTableRecipe implements IRecipe {

    private ItemStack output = null;
    private Object[] input = null;
    public int width = 0;
    public int height = 0;
    private boolean mirrored = false;
    private Item resultItem;
    private int resultMeta;

    public TinkeringTableRecipe(Block result, Object... recipe){
        this(new ItemStack(result), recipe);
    }

    public TinkeringTableRecipe(Item result, Object... recipe){
        this(new ItemStack(result), recipe);
    }

    public TinkeringTableRecipe(ItemStack result, Object... recipe){
        output = result.copy();
        this.resultItem = result.getItem();
        this.resultMeta = result.getItemDamage();
        result.getEnchantmentTagList();

        String shape = "";
        int idx = 0;

        if(recipe[idx] instanceof Boolean){
            mirrored = (Boolean)recipe[idx];
            if(recipe[idx + 1] instanceof Object[]){
                recipe = (Object[])recipe[idx + 1];
            } else {
                idx = 1;
            }
        }

        if(recipe[idx] instanceof String[]){
            String[] parts = ((String[])recipe[idx++]);

            for(String s : parts){
                width = s.length();
                shape += s;
            }

            height = parts.length;
        } else {
            while(recipe[idx] instanceof String){
                String s = (String)recipe[idx++];
                shape += s;
                width = s.length();
                height++;
            }
        }

        if(width * height != shape.length()){
            String ret = "Invalid Tinkering Table recipe: ";
            for(Object tmp : recipe){
                ret += tmp + ", ";
            }
            ret += output;
            throw new RuntimeException(ret);
        }

        HashMap<Character, Object> itemMap = new HashMap<Character, Object>();

        for(; idx < recipe.length; idx += 2) {
            Character chr = (Character)recipe[idx];
            Object in = recipe[idx + 1];

            if(in instanceof ItemStack){
                itemMap.put(chr, ((ItemStack)in).copy());
            } else if(in instanceof Item){
                itemMap.put(chr, new ItemStack((Item)in));
            } else if(in instanceof Block){
                itemMap.put(chr, new ItemStack((Block)in, 1, OreDictionary.WILDCARD_VALUE));
            } else if(in instanceof String){
                itemMap.put(chr, OreDictionary.getOres((String)in));
            } else {
                String ret = "Invalid Tinkering Table recipe: ";
                for(Object tmp : recipe) {
                    ret += tmp + ", ";
                }
                ret += output;
                throw new RuntimeException(ret);
            }
        }

        input = new Object[width * height];
        int x = 0;
        for(char chr : shape.toCharArray()){
            input[x++] = itemMap.get(chr);
        }
    }

    TinkeringTableRecipe(ShapedRecipes recipe, Map<ItemStack, String> replacements){
        output = recipe.getRecipeOutput();
        width = recipe.recipeWidth;
        height = recipe.recipeHeight;

        input = new Object[recipe.recipeItems.length];

        for(int i = 0; i < input.length; i++) {
            ItemStack ingred = recipe.recipeItems[i];

            if (ingred == null) continue;

            input[i] = recipe.recipeItems[i];

            for(Map.Entry<ItemStack, String> replace : replacements.entrySet()){
                if(OreDictionary.itemMatches(replace.getKey(), ingred, true)){
                    input[i] = OreDictionary.getOres(replace.getValue());
                    break;
                }
            }
        }
    }

    public TinkeringTableRecipe(ItemStack result, Object[] ingredients, int wid, int hei) {
        width = wid;
        height = hei;
        output = result;
        input = ingredients;
        this.resultItem = result.getItem();
        this.resultMeta = result.getItemDamage();
        result.getEnchantmentTagList();
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        NBTTagCompound tags = null;
        
        ItemStack result = new ItemStack((Item)this.resultItem, 1, this.resultMeta);
        
        ItemStack slotStack;
        for(int i = 0; i < inventoryCrafting.getSizeInventory(); i++){
            slotStack = inventoryCrafting.getStackInSlot(i);
            if(slotStack != null && slotStack.getItem() != null){
                if(slotStack.getItem() instanceof ItemSupremiumArmor){
                    tags = (NBTTagCompound) NBTHelper.getDataMap(slotStack).copy();
	 				int newDamage = MathHelper.clamp_int(slotStack.getItemDamage(), 0, result.getMaxDamage());
	 				result.setItemDamage(newDamage);
	 				break;
                }
            }
        }
        
        if(tags != null){
            result.setTagCompound(tags);
        }
        
        return result;
    }

    @Override
    public int getRecipeSize() {
        return input.length;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    @Override
    public ItemStack[] getRemainingItems(InventoryCrafting inv){
        return ForgeHooks.defaultRecipeGetRemainingItems(inv);
    }

    @Override
    public boolean matches(@Nonnull InventoryCrafting inv,@Nonnull World world) {
        for (int x = 0; x <= 3 - width; x++) {
            for (int y = 0; y <= 3 - height; ++y) {
                if(checkMatch(inv, x, y, false)) {
                    return true;
                }

                if(mirrored && checkMatch(inv, x, y, true)) {
                    return true;
                }
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    private boolean checkMatch(InventoryCrafting inv, int startX, int startY, boolean mirror){
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                int subX = x - startX;
                int subY = y - startY;
                Object target = null;

                if(subX >= 0 && subY >= 0 && subX < width && subY < height){
                    if (mirror) {
                        target = input[width - subX - 1 + subY * width];
                    } else {
                        target = input[subX + subY * width];
                    }
                }

                ItemStack slot = inv.getStackInRowAndColumn(x, y);

                if(target instanceof ItemStack){
                    if (!OreDictionary.itemMatches((ItemStack) target, slot, false)) {
                        return false;
                    }
                } else if(target instanceof List){
                    boolean matched = false;

                    Iterator<ItemStack> itr = ((List<ItemStack>) target).iterator();
                    while(itr.hasNext() && !matched){
                        matched = OreDictionary.itemMatches(itr.next(), slot, false);
                    }

                    if(!matched){
                        return false;
                    }
                } else if(target == null && slot != null){
                    return false;
                }
            }
        }

        return true;
    }

    public TinkeringTableRecipe setMirrored(boolean mirror) {
        mirrored = mirror;
        return this;
    }

    public Object[] getInput() {
        return this.input;
    }
}