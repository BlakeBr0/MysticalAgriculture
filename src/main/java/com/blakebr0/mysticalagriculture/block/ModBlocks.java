package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseBlock;
import com.blakebr0.cucumber.block.BaseGlassBlock;
import com.blakebr0.cucumber.block.BaseSlabBlock;
import com.blakebr0.cucumber.block.BaseStairsBlock;
import com.blakebr0.cucumber.block.BaseWallBlock;
import com.blakebr0.cucumber.item.BaseBlockItem;
import com.blakebr0.mysticalagriculture.api.crop.CropTiers;
import com.blakebr0.mysticalagriculture.item.ModItems;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.ITEM_GROUP;

public class ModBlocks {
    public static final BaseBlock PROSPERITY_BLOCK = new BaseBlock("prosperity_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BaseBlock INFERIUM_BLOCK = new BaseBlock("inferium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BaseBlock PRUDENTIUM_BLOCK = new BaseBlock("prudentium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BaseBlock INTERMEDIUM_BLOCK = new BaseBlock("intermedium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BaseBlock IMPERIUM_BLOCK = new BaseBlock("imperium_block", Material.ROCK, SoundType.STONE, 4.0F, 5.0F);
    public static final BaseBlock SUPREMIUM_BLOCK = new BaseBlock("supremium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BaseBlock SOULIUM_BLOCK = new BaseBlock("soulium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BaseBlock PROSPERITY_INGOT_BLOCK = new BaseBlock("prosperity_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock INFERIUM_INGOT_BLOCK = new BaseBlock("inferium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock PRUDENTIUM_INGOT_BLOCK = new BaseBlock("prudentium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock INTERMEDIUM_INGOT_BLOCK = new BaseBlock("intermedium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock IMPERIUM_INGOT_BLOCK = new BaseBlock("imperium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock SUPREMIUM_INGOT_BLOCK = new BaseBlock("supremium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock SOULIUM_INGOT_BLOCK = new BaseBlock("soulium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock PROSPERITY_GEMSTONE_BLOCK = new BaseBlock("prosperity_gemstone_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock INFERIUM_GEMSTONE_BLOCK = new BaseBlock("inferium_gemstone_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock PRUDENTIUM_GEMSTONE_BLOCK = new BaseBlock("prudentium_gemstone_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock INTERMEDIUM_GEMSTONE_BLOCK = new BaseBlock("intermedium_gemstone_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock IMPERIUM_GEMSTONE_BLOCK = new BaseBlock("imperium_gemstone_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock SUPREMIUM_GEMSTONE_BLOCK = new BaseBlock("supremium_gemstone_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock SOULIUM_GEMSTONE_BLOCK = new BaseBlock("soulium_gemstone_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final InfusedFarmlandBlock INFERIUM_FARMLAND = new InfusedFarmlandBlock("inferium_farmland", CropTiers.ONE);
    public static final InfusedFarmlandBlock PRUDENTIUM_FARMLAND = new InfusedFarmlandBlock("prudentium_farmland", CropTiers.TWO);
    public static final InfusedFarmlandBlock INTERMEDIUM_FARMLAND = new InfusedFarmlandBlock("intermedium_farmland", CropTiers.THREE);
    public static final InfusedFarmlandBlock IMPERIUM_FARMLAND = new InfusedFarmlandBlock("imperium_farmland", CropTiers.FOUR);
    public static final InfusedFarmlandBlock SUPREMIUM_FARMLAND = new InfusedFarmlandBlock("supremium_farmland", CropTiers.FIVE);
    public static final GrowthAcceleratorBlock INFERIUM_GROWTH_ACCELERATOR = new GrowthAcceleratorBlock("inferium_growth_accelerator");
    public static final GrowthAcceleratorBlock PRUDENTIUM_GROWTH_ACCELERATOR = new GrowthAcceleratorBlock("prudentium_growth_accelerator");
    public static final GrowthAcceleratorBlock INTERMEDIUM_GROWTH_ACCELERATOR = new GrowthAcceleratorBlock("intermedium_growth_accelerator");
    public static final GrowthAcceleratorBlock IMPERIUM_GROWTH_ACCELERATOR = new GrowthAcceleratorBlock("imperium_growth_accelerator");
    public static final GrowthAcceleratorBlock SUPREMIUM_GROWTH_ACCELERATOR = new GrowthAcceleratorBlock("supremium_growth_accelerator");
    public static final BaseBlock SOULSTONE = new BaseBlock("soulstone", Material.ROCK, SoundType.STONE, 1.5F, 6.0F);
    public static final BaseBlock SOULSTONE_COBBLE = new BaseBlock("soulstone_cobble", Material.ROCK, SoundType.STONE, 2.0F, 6.0F);
    public static final BaseBlock SOULSTONE_BRICKS = new BaseBlock("soulstone_bricks", Material.ROCK, SoundType.STONE, 1.5F, 6.0F);
    public static final BaseBlock SOULSTONE_CRACKED_BRICKS = new BaseBlock("soulstone_cracked_bricks", Material.ROCK, SoundType.STONE, 1.5F, 6.0F);
    public static final BaseBlock SOULSTONE_CHISELED_BRICKS = new BaseBlock("soulstone_chiseled_bricks", Material.ROCK, SoundType.STONE, 1.5F, 6.0F);
    public static final BaseBlock SOULSTONE_SMOOTH = new BaseBlock("soulstone_smooth", Material.ROCK, SoundType.STONE, 1.5F, 6.0F);
    public static final BaseGlassBlock SOUL_GLASS = new BaseGlassBlock("soul_glass", Material.GLASS, SoundType.GLASS, 0.3F, 0.3F);
    public static final BaseSlabBlock SOULSTONE_SLAB = new BaseSlabBlock("soulstone_slab", SOULSTONE);
    public static final BaseSlabBlock SOULSTONE_COBBLE_SLAB = new BaseSlabBlock("soulstone_cobble_slab", SOULSTONE_COBBLE);
    public static final BaseSlabBlock SOULSTONE_BRICKS_SLAB = new BaseSlabBlock("soulstone_bricks_slab", SOULSTONE_BRICKS);
    public static final BaseSlabBlock SOULSTONE_SMOOTH_SLAB = new BaseSlabBlock("soulstone_smooth_slab", SOULSTONE_SMOOTH);
    public static final BaseStairsBlock SOULSTONE_STAIRS = new BaseStairsBlock("soulstone_stairs", SOULSTONE);
    public static final BaseStairsBlock SOULSTONE_COBBLE_STAIRS = new BaseStairsBlock("soulstone_cobble_stairs", SOULSTONE_COBBLE);
    public static final BaseStairsBlock SOULSTONE_BRICKS_STAIRS = new BaseStairsBlock("soulstone_bricks_stairs", SOULSTONE_BRICKS);
    public static final BaseWallBlock SOULSTONE_COBBLE_WALL = new BaseWallBlock("soulstone_cobble_wall", SOULSTONE_COBBLE);
    public static final BaseWallBlock SOULSTONE_BRICKS_WALL = new BaseWallBlock("soulstone_bricks_wall", SOULSTONE_BRICKS);
    public static final WitherproofBlock WITHERPROOF_BLOCK = new WitherproofBlock("witherproof_block");
    public static final WitherproofBlock WITHERPROOF_BRICKS = new WitherproofBlock("witherproof_bricks");
    public static final WitherproofGlassBlock WITHERPROOF_GLASS = new WitherproofGlassBlock("witherproof_glass");
    public static final InfusionPedestalBlock INFUSION_PEDESTAL = new InfusionPedestalBlock("infusion_pedestal");

    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        register(registry, PROSPERITY_BLOCK);
        register(registry, INFERIUM_BLOCK);
        register(registry, PRUDENTIUM_BLOCK);
        register(registry, INTERMEDIUM_BLOCK);
        register(registry, IMPERIUM_BLOCK);
        register(registry, SUPREMIUM_BLOCK);
        register(registry, SOULIUM_BLOCK);
        register(registry, PROSPERITY_INGOT_BLOCK);
        register(registry, INFERIUM_INGOT_BLOCK);
        register(registry, PRUDENTIUM_INGOT_BLOCK);
        register(registry, INTERMEDIUM_INGOT_BLOCK);
        register(registry, IMPERIUM_INGOT_BLOCK);
        register(registry, SUPREMIUM_INGOT_BLOCK);
        register(registry, SOULIUM_INGOT_BLOCK);
        register(registry, PROSPERITY_GEMSTONE_BLOCK);
        register(registry, INFERIUM_GEMSTONE_BLOCK);
        register(registry, PRUDENTIUM_GEMSTONE_BLOCK);
        register(registry, INTERMEDIUM_GEMSTONE_BLOCK);
        register(registry, IMPERIUM_GEMSTONE_BLOCK);
        register(registry, SUPREMIUM_GEMSTONE_BLOCK);
        register(registry, SOULIUM_GEMSTONE_BLOCK);
        register(registry, INFERIUM_FARMLAND);
        register(registry, PRUDENTIUM_FARMLAND);
        register(registry, INTERMEDIUM_FARMLAND);
        register(registry, IMPERIUM_FARMLAND);
        register(registry, SUPREMIUM_FARMLAND);
        register(registry, INFERIUM_GROWTH_ACCELERATOR);
        register(registry, PRUDENTIUM_GROWTH_ACCELERATOR);
        register(registry, INTERMEDIUM_GROWTH_ACCELERATOR);
        register(registry, IMPERIUM_GROWTH_ACCELERATOR);
        register(registry, SUPREMIUM_GROWTH_ACCELERATOR);
        register(registry, SOULSTONE);
        register(registry, SOULSTONE_COBBLE);
        register(registry, SOULSTONE_BRICKS);
        register(registry, SOULSTONE_CRACKED_BRICKS);
        register(registry, SOULSTONE_CHISELED_BRICKS);
        register(registry, SOULSTONE_SMOOTH);
        register(registry, SOUL_GLASS);
        register(registry, SOULSTONE_SLAB);
        register(registry, SOULSTONE_COBBLE_SLAB);
        register(registry, SOULSTONE_BRICKS_SLAB);
        register(registry, SOULSTONE_SMOOTH_SLAB);
        register(registry, SOULSTONE_STAIRS);
        register(registry, SOULSTONE_COBBLE_STAIRS);
        register(registry, SOULSTONE_BRICKS_STAIRS);
        register(registry, SOULSTONE_COBBLE_WALL);
        register(registry, SOULSTONE_BRICKS_WALL);
        register(registry, WITHERPROOF_BLOCK);
        register(registry, WITHERPROOF_BRICKS);
        register(registry, WITHERPROOF_GLASS);
        register(registry, INFUSION_PEDESTAL);

        CropRegistry.getInstance().allowRegistration();
        CropRegistry.getInstance().onRegisterBlocks(registry);
        CropRegistry.getInstance().denyRegistration();
    }

    private static void register(IForgeRegistry<Block> registry, Block block) {
        BaseBlockItem item = new BaseBlockItem(block, p -> p.group(ITEM_GROUP));
        register(registry, block, item);
    }

    private static void register(IForgeRegistry<Block> registry, Block block, BlockItem item) {
        registry.register(block);
        ModItems.ITEM_BLOCKS.add(item);
    }
}
