package com.blakebr0.mysticalagriculture.lib;

import net.minecraft.util.IStringSerializable;

public class EssenceType {

	public static enum Type implements IStringSerializable {	
	
		INFERIUM(0, "inferium"),
		PRUDENTIUM(1, "prudentium"),
		INTERMEDIUM(2, "intermedium"),
		SUPREMIUM(3, "supremium"),
		SUPREMIUM(4, "supremium");
		
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
