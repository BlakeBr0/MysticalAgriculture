package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNugget extends ItemBase {

	public ItemNugget(){
		super("nugget");
		this.setHasSubtypes(true);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> stacks) {
        for(Type type : Type.values()){
            stacks.add(new ItemStack(item, 1, type.getMetadata()));
        }
	}
	
    @Override
    public String getUnlocalizedName(ItemStack stack){
        return super.getUnlocalizedName() + "_" + Type.byMetadata(stack.getMetadata()).getName();
    }
    
    @SideOnly(Side.CLIENT)
    public void initModels(){
    	for(Type type : Type.values()){
        	ModelLoader.setCustomModelResourceLocation(this, type.getMetadata(), new ModelResourceLocation(getRegistryName().toString() + "_" + type.byMetadata(type.getMetadata()).getName()));
    	}
    }
    
	public static enum Type implements IStringSerializable {
		
		BASE_ESSENCE(0, "base_essence"),
		INFERIUM(1, "inferium"),
		PRUDENTIUM(2, "prudentium"),
		INTERMEDIUM(3, "intermedium"),
		SUPERIUM(4, "superium"),
		SUPREMIUM(5, "supremium"),
		SOULIUM(6, "soulium");

        private static final Type[] META_LOOKUP = new Type[values().length];		
		private final int meta;
		private final String name;
		
		private Type(int meta, String name){
			this.meta = meta;
			this.name = name;
		}
				
		public int getMetadata(){
			return this.meta;
		}
		
		@Override
		public String getName(){
			return this.name;
		}
		
        public static Type byMetadata(int meta){
            if(meta < 0 || meta >= META_LOOKUP.length){
                meta = 0;
            }
            return META_LOOKUP[meta];
        }

        static {
            for(Type type : values()){
                META_LOOKUP[type.getMetadata()] = type;
            }
        }
	}
}
