package com.blakebr0.mysticalagriculture.blocks;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.lib.IModelHelper;
import com.blakebr0.mysticalagriculture.registry.MysticalRegistry;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class BlockIngotStorage extends BlockBase implements IModelHelper {

    public static final PropertyEnum<Type> VARIANT = PropertyEnum.<Type>create("variant", Type.class);
	
	public BlockIngotStorage(){
		super("ingot_storage", Material.IRON, SoundType.METAL, 5.0F, 8.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, Type.BASE_ESSENCE));
	}

    @Override
    public void init(){
    	MysticalRegistry.addOre(new ItemStack(this, 1, 0), "blockBaseEssence");
    	MysticalRegistry.addOre(new ItemStack(this, 1, 1), "blockInferium");
    	MysticalRegistry.addOre(new ItemStack(this, 1, 2), "blockPrudentium");
    	MysticalRegistry.addOre(new ItemStack(this, 1, 3), "blockIntermedium");
    	MysticalRegistry.addOre(new ItemStack(this, 1, 4), "blockSuperium");
    	MysticalRegistry.addOre(new ItemStack(this, 1, 5), "blockSupremium");
    	MysticalRegistry.addOre(new ItemStack(this, 1, 6), "blockSoulium");
    }

    @Override
    public int damageDropped(IBlockState state){
        return ((Type)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> stacks){
        for(Type type : Type.values()){
            stacks.add(new ItemStack(this, 1, type.getMetadata()));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void initModels(){
    	for(Type type : Type.values()){
        	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), type.getMetadata(), new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + getUnlocalizedName().substring(8) + "_" + type.byMetadata(type.getMetadata()).getName()));
    	}
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(VARIANT, Type.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state){
        return ((Type)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] { VARIANT });
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
		
		Type(int meta, String name){
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
