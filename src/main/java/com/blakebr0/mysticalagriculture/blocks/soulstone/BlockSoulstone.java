package com.blakebr0.mysticalagriculture.blocks.soulstone;

import com.blakebr0.cucumber.iface.IModelHelper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.BlockBase;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class BlockSoulstone extends BlockBase implements IModelHelper {
	
    public static final PropertyEnum<Type> VARIANT = PropertyEnum.<Type>create("variant", Type.class);

    public BlockSoulstone(){
        super("soulstone", Material.ROCK, SoundType.STONE, 1.5F, 10.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, Type.SMOOTH));
    }

    @Override
    public int damageDropped(IBlockState state){
    	if(state.getBlock().getMetaFromState(state) == 0){
    		return Type.COBBLED.getMetadata();
    	}
        return ((Type)state.getValue(VARIANT)).getMetadata();
    }
    
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    	 Item item = Item.getItemFromBlock(this);
         return new ItemStack(item, 1, getMetaFromState(state));
    }
    
    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> stacks){
        for(Type type : Type.values()){
            stacks.add(new ItemStack(this, 1, type.getMetadata()));
        }
    }
    
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
		
		SMOOTH(0, "smooth"),
		COBBLED(1, "cobbled"),
		POLISHED(2, "polished"),
		BRICK(3, "brick"),
		CRACKED(4, "cracked"),
		CHISELED(5, "chiseled"),
		SLAB_BLOCK(6, "slab_block");
		
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
