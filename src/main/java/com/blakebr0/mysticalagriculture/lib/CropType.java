package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ItemBase;
import com.blakebr0.mysticalagriculture.items.ItemSeed;
import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.util.IStringSerializable;

public class CropType {
	
	public static enum Type implements IStringSerializable {
		
		STONE("stone", ModConfig.stone_tier, ModConfig.stone_seeds),
		DIRT("dirt", ModConfig.dirt_tier, ModConfig.dirt_seeds),
		NATURE("nature", ModConfig.nature_tier, ModConfig.nature_seeds),
		WOOD("wood", ModConfig.wood_tier, ModConfig.wood_seeds),
		WATER("water", ModConfig.water_tier, ModConfig.water_seeds),
		ICE("ice", ModConfig.ice_tier, ModConfig.ice_seeds),
		FIRE("fire", ModConfig.fire_tier, ModConfig.fire_seeds),
		DYE("dye", ModConfig.dye_tier, ModConfig.dye_seeds),
		NETHER("nether", ModConfig.nether_tier, ModConfig.nether_seeds),
		COAL("coal", ModConfig.coal_tier, ModConfig.coal_seeds),
		IRON("iron", ModConfig.iron_tier, ModConfig.iron_seeds),
		NETHER_QUARTZ("nether_quartz", ModConfig.nether_quartz_tier, ModConfig.nether_quartz_seeds),
		GLOWSTONE("glowstone", ModConfig.glowstone_tier, ModConfig.glowstone_seeds),
		REDSTONE("redstone", ModConfig.redstone_tier, ModConfig.redstone_seeds),
		OBSIDIAN("obsidian", ModConfig.obsidian_tier, ModConfig.obsidian_seeds),
		GOLD("gold", ModConfig.gold_tier, ModConfig.gold_seeds),
		LAPIS_LAZULI("lapis_lazuli", ModConfig.lapis_lazuli_tier, ModConfig.lapis_lazuli_seeds),
		EXPERIENCE("experience", ModConfig.experience_tier, ModConfig.experience_seeds),
		DIAMOND("diamond", ModConfig.diamond_tier, ModConfig.diamond_seeds),
		EMERALD("emerald", ModConfig.emerald_tier, ModConfig.emerald_seeds),
		
		ZOMBIE("zombie", ModConfig.zombie_tier, ModConfig.zombie_seeds),
		PIG("pig", ModConfig.pig_tier, ModConfig.pig_seeds),
		CHICKEN("chicken", ModConfig.chicken_tier, ModConfig.chicken_seeds),
		COW("cow", ModConfig.cow_tier, ModConfig.cow_seeds),
		SHEEP("sheep", ModConfig.sheep_tier, ModConfig.sheep_seeds),
		SLIME("slime", ModConfig.slime_tier, ModConfig.slime_seeds),
		SKELETON("skeleton", ModConfig.skeleton_tier, ModConfig.skeleton_seeds),
		CREEPER("creeper", ModConfig.creeper_tier, ModConfig.creeper_seeds),
		SPIDER("spider", ModConfig.spider_tier, ModConfig.spider_seeds),
		RABBIT("rabbit", ModConfig.rabbit_tier, ModConfig.rabbit_seeds),
		GUARDIAN("guardian", ModConfig.guardian_tier, ModConfig.guardian_seeds),
		BLAZE("blaze", ModConfig.blaze_tier, ModConfig.blaze_seeds),
		GHAST("ghast", ModConfig.ghast_tier, ModConfig.ghast_seeds),
		ENDERMAN("enderman", ModConfig.enderman_tier, ModConfig.enderman_seeds),
		WITHER_SKELETON("wither_skeleton", ModConfig.wither_skeleton_tier, ModConfig.wither_skeleton_seeds),
		
		RUBBER("rubber", ModConfig.rubber_tier, ModConfig.rubber_seeds && ModChecker.RUBBER),
		ALUMINUM("aluminum", ModConfig.aluminum_tier, ModConfig.aluminum_seeds && ModChecker.ALUMINUM),
		COPPER("copper", ModConfig.copper_tier, ModConfig.copper_seeds && ModChecker.COPPER),
		TIN("tin", ModConfig.tin_tier, ModConfig.tin_seeds && ModChecker.TIN),
		BRONZE("bronze", ModConfig.bronze_tier, ModConfig.bronze_seeds && ModChecker.BRONZE),
		SILVER("silver", ModConfig.silver_tier, ModConfig.silver_seeds && ModChecker.SILVER),
		LEAD("lead", ModConfig.lead_tier, ModConfig.lead_seeds && ModChecker.LEAD),
		STEEL("steel", ModConfig.steel_tier, ModConfig.steel_seeds && ModChecker.STEEL),
		NICKEL("nickel", ModConfig.nickel_tier, ModConfig.nickel_seeds && ModChecker.NICKEL),
		CONSTANTAN("constantan", ModConfig.constantan_tier, ModConfig.constantan_seeds && ModChecker.CONSTANTAN),
		ELECTRUM("electrum", ModConfig.electrum_tier, ModConfig.electrum_seeds && ModChecker.ELECTRUM),
		INVAR("invar", ModConfig.invar_tier, ModConfig.invar_seeds && ModChecker.INVAR),
		PLATINUM("platinum", ModConfig.platinum_tier, ModConfig.platinum_seeds && ModChecker.PLATINUM),
		
		RUBY("ruby", ModConfig.ruby_tier, ModConfig.ruby_seeds && ModChecker.RUBY),
		SAPPHIRE("sapphire", ModConfig.sapphire_tier, ModConfig.sapphire_seeds && ModChecker.SAPPHIRE),
		PERIDOT("peridot", ModConfig.peridot_tier, ModConfig.peridot_seeds && ModChecker.PERIDOT),
		
		SIGNALUM("signalum", ModConfig.signalum_tier, ModConfig.signalum_seeds && ModChecker.SIGNALUM),
		LUMIUM("lumium", ModConfig.lumium_tier, ModConfig.lumium_seeds && ModChecker.LUMIUM),
		ENDERIUM("enderium", ModConfig.enderium_tier, ModConfig.enderium_seeds && ModChecker.ENDERIUM),
		
		ALUMINUM_BRASS("aluminum_brass", ModConfig.aluminum_brass_tier, ModConfig.aluminum_brass_seeds && ModChecker.TINKERS),
		KNIGHTSLIME("knightslime", ModConfig.knightslime_tier, ModConfig.knightslime_seeds && ModChecker.TINKERS),
		ARDITE("ardite", ModConfig.ardite_tier, ModConfig.ardite_seeds && ModChecker.TINKERS),
		COBALT("cobalt", ModConfig.cobalt_tier, ModConfig.cobalt_seeds && ModChecker.TINKERS),
		MANYULLYN("manyullyn", ModConfig.manyullyn_tier, ModConfig.manyullyn_seeds && ModChecker.TINKERS),
		
		ELECTRICAL_STEEL("electrical_steel", ModConfig.electrical_steel_tier, ModConfig.electrical_steel_seeds && ModChecker.ENDERIO),
		REDSTONE_ALLOY("redstone_alloy", ModConfig.redstone_alloy_tier, ModConfig.redstone_alloy_seeds && ModChecker.ENDERIO),
		CONDUCTIVE_IRON("conductive_iron", ModConfig.conductive_iron_tier, ModConfig.conductive_iron_seeds && ModChecker.ENDERIO),
		SOULARIUM("soularium", ModConfig.soularium_tier, ModConfig.soularium_seeds && ModChecker.ENDERIO),
		DARK_STEEL("dark_steel", ModConfig.dark_steel_tier, ModConfig.dark_steel_seeds && ModChecker.ENDERIO),
		PULSATING_IRON("pulsating_iron", ModConfig.pulsating_iron_tier, ModConfig.pulsating_iron_seeds && ModChecker.ENDERIO),
		ENERGETIC_ALLOY("energetic_alloy", ModConfig.energetic_alloy_tier, ModConfig.energetic_alloy_seeds && ModChecker.ENDERIO),
		VIBRANT_ALLOY("vibrant_alloy", ModConfig.vibrant_alloy_tier, ModConfig.vibrant_alloy_seeds && ModChecker.ENDERIO),
		
		MYSTICAL_FLOWER("mystical_flower", ModConfig.mystical_flower_tier, ModConfig.mystical_flower_seeds && ModChecker.BOTANIA),
		MANASTEEL("manasteel", ModConfig.manasteel_tier, ModConfig.manasteel_seeds && ModChecker.BOTANIA),
		TERRASTEEL("terrasteel", ModConfig.terrasteel_tier, ModConfig.terrasteel_seeds && ModChecker.BOTANIA),
		
		OSMIUM("osmium", ModConfig.osmium_tier, ModConfig.osmium_seeds && ModChecker.MEKANISM),
		REFINED_OBSIDIAN("refined_obsidian", ModConfig.refined_obsidian_tier, ModConfig.refined_obsidian_seeds && ModChecker.MEKANISM),
		
		MARBLE("marble", ModConfig.marble_tier, ModConfig.marble_seeds && ModChecker.CHISEL),
		LIMESTONE("limestone", ModConfig.limestone_tier, ModConfig.limestone_seeds && ModChecker.CHISEL),
		BASALT("basalt", ModConfig.basalt_tier, ModConfig.basalt_seeds && ModChecker.CHISEL),
		
		DRACONIUM("draconium", ModConfig.draconium_tier, ModConfig.draconium_seeds && ModChecker.DRACONIC),
		
		YELLORIUM("yellorium", ModConfig.yellorium_tier, ModConfig.yellorium_seeds && ModChecker.BIGREACTORS),
		
		CERTUS_QUARTZ("certus_quartz", ModConfig.certus_quartz_tier, ModConfig.certus_quartz_seeds && ModChecker.AE2),
		FLUIX("fluix", ModConfig.fluix_tier, ModConfig.fluix_seeds && ModChecker.AE2),
		
		QUARTZ_ENRICHED_IRON("quartz_enriched_iron", ModConfig.quartz_enriched_iron_tier, ModConfig.quartz_enriched_iron_seeds && ModChecker.RS);
				
		private final String name;
		private final int tier;
		private final boolean enabled;
		private final BlockMysticalCrop plant;
		private final ItemBase crop;
		private final ItemSeed seed;
		
		Type(String name, int tier, boolean enabled){
			this.name = name;
			this.tier = tier;
			this.enabled = enabled;
			this.plant = new BlockMysticalCrop(getName() + "_crop");
			this.crop = new ItemBase(getName() + "_essence");
			this.seed = new ItemSeed(getName() + "_seeds", getPlant(), getTier());
			getPlant().setCrop(getCrop());
			getPlant().setSeed(getSeed());
		}

		@Override
		public String getName(){
			return this.name;
		}
		
		public int getTier(){
			return this.tier;
		}
		
		public boolean isEnabled(){
			return this.enabled;
		}
		
		public BlockMysticalCrop getPlant(){
			return this.plant;
		}
		
		public ItemBase getCrop(){
			return this.crop;
		}
		
		public ItemSeed getSeed(){
			return this.seed;
		}
	}
}
