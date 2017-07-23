package com.blakebr0.mysticalagriculture.items;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.cucumber.item.ItemMeta;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemChunk extends ItemMeta {
	
	private static CropType.Type type;
	
	public static ItemStack itemTier1MobChunk;
	public static ItemStack itemTier2MobChunk;
	public static ItemStack itemTier3MobChunk;
	public static ItemStack itemTier4MobChunk;
	public static ItemStack itemTier5MobChunk;
	
	public static ItemStack itemExperienceChunk;
	public static ItemStack itemZombieChunk;
	public static ItemStack itemPigChunk;
	public static ItemStack itemChickenChunk;
	public static ItemStack itemCowChunk;
	public static ItemStack itemSheepChunk;
	public static ItemStack itemSlimeChunk;
	public static ItemStack itemSkeletonChunk;
	public static ItemStack itemCreeperChunk;
	public static ItemStack itemSpiderChunk;
	public static ItemStack itemRabbitChunk;
	public static ItemStack itemGuardianChunk;
	public static ItemStack itemBlazeChunk;
	public static ItemStack itemGhastChunk;
	public static ItemStack itemEndermanChunk;
	public static ItemStack itemWitherSkeletonChunk;
	public static ItemStack itemBlizzChunk;
	public static ItemStack itemBlitzChunk;
	public static ItemStack itemBasalzChunk;
	
	public ItemChunk(){
		super("ma.chunk", MysticalAgriculture.REGISTRY);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}
	
	@Override
	public void init(){
		itemTier1MobChunk = addItem(0, "tier1_mob_chunk");
		itemTier2MobChunk = addItem(1, "tier2_mob_chunk");
		itemTier3MobChunk = addItem(2, "tier3_mob_chunk");
		itemTier4MobChunk = addItem(3, "tier4_mob_chunk");
		itemTier5MobChunk = addItem(4, "tier5_mob_chunk");
		
		itemExperienceChunk = addItem(5, "experience_chunk", true, Utils.asList(Tooltips.DROP_CHANCE + Colors.RED + "10%"));
		itemZombieChunk = addItem(6, "zombie_chunk", type.ZOMBIE.isEnabled(), getChunkTooltip(type.ZOMBIE.getTier()));
		itemPigChunk = addItem(7, "pig_chunk", type.PIG.isEnabled(), getChunkTooltip(type.PIG.getTier()));
		itemChickenChunk = addItem(8, "chicken_chunk", type.CHICKEN.isEnabled(), getChunkTooltip(type.CHICKEN.getTier()));
		itemCowChunk = addItem(9, "cow_chunk", type.COW.isEnabled(), getChunkTooltip(type.COW.getTier()));
		itemSheepChunk = addItem(10, "sheep_chunk", type.SHEEP.isEnabled(), getChunkTooltip(type.SHEEP.getTier()));
		itemSlimeChunk = addItem(11, "slime_chunk", type.SLIME.isEnabled(), getChunkTooltip(type.SLIME.getTier()));
		itemSkeletonChunk = addItem(12, "skeleton_chunk", type.SKELETON.isEnabled(), getChunkTooltip(type.SKELETON.getTier()));
		itemCreeperChunk = addItem(13, "creeper_chunk", type.CREEPER.isEnabled(), getChunkTooltip(type.CREEPER.getTier()));
		itemSpiderChunk = addItem(14, "spider_chunk", type.SPIDER.isEnabled(), getChunkTooltip(type.SPIDER.getTier()));
		itemRabbitChunk = addItem(15, "rabbit_chunk", type.RABBIT.isEnabled(), getChunkTooltip(type.RABBIT.getTier()));
		itemGuardianChunk = addItem(16, "guardian_chunk", type.GUARDIAN.isEnabled(), getChunkTooltip(type.GUARDIAN.getTier()));
		itemBlazeChunk = addItem(17, "blaze_chunk", type.BLAZE.isEnabled(), getChunkTooltip(type.BLAZE.getTier()));
		itemGhastChunk = addItem(18, "ghast_chunk", type.GHAST.isEnabled(), getChunkTooltip(type.GHAST.getTier()));
		itemEndermanChunk = addItem(19, "enderman_chunk", type.ENDERMAN.isEnabled(), getChunkTooltip(type.ENDERMAN.getTier()));
		itemWitherSkeletonChunk = addItem(20, "wither_skeleton_chunk", type.WITHER_SKELETON.isEnabled(), getChunkTooltip(type.WITHER_SKELETON.getTier()));
		itemBlizzChunk = addItem(21, "blizz_chunk", type.BLIZZ.isEnabled(), getChunkTooltip(type.BLIZZ.getTier()));
		itemBlitzChunk = addItem(22, "blitz_chunk", type.BLITZ.isEnabled(), getChunkTooltip(type.BLITZ.getTier()));
		itemBasalzChunk = addItem(23, "basalz_chunk", type.BASALZ.isEnabled(), getChunkTooltip(type.BASALZ.getTier()));
	}
	
	private List<String> getChunkTooltip(int tier){
		List<String> tooltip = new ArrayList<>();
		switch(tier - 1){
        case 0:
        	tooltip.add(Tooltips.TIER + Colors.YELLOW + "1");
        	tooltip.add(Tooltips.DROP_CHANCE + Colors.YELLOW + "30%");
            break;
        case 1:
        	tooltip.add(Tooltips.TIER + Colors.GREEN + "2");
    		tooltip.add(Tooltips.DROP_CHANCE + Colors.GREEN + "25%");
            break;
        case 2:
        	tooltip.add(Tooltips.TIER + Colors.GOLD + "3");
        	tooltip.add(Tooltips.DROP_CHANCE + Colors.GOLD + "20%");
            break;
        case 3:
        	tooltip.add(Tooltips.TIER + Colors.AQUA + "4");
        	tooltip.add(Tooltips.DROP_CHANCE + Colors.AQUA + "15%");
            break;
        case 4:
        	tooltip.add(Tooltips.TIER + Colors.RED + "5");
        	tooltip.add(Tooltips.DROP_CHANCE + Colors.RED + "10%");
            break;
		}
		if(ModConfig.confCraftableChunks){
			tooltip.add(Tooltips.CRAFTABLE);
		}
		return tooltip;
	}
}