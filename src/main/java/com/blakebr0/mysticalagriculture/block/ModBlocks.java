package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseBlock;
import com.blakebr0.cucumber.block.BaseGlassBlock;
import com.blakebr0.cucumber.block.BaseOreBlock;
import com.blakebr0.cucumber.block.BaseSlabBlock;
import com.blakebr0.cucumber.block.BaseStairsBlock;
import com.blakebr0.cucumber.block.BaseWallBlock;
import com.blakebr0.cucumber.item.BaseBlockItem;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.item.ModItems;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.ITEM_GROUP;

public class ModBlocks {
    public static final BaseBlock PROSPERITY_BLOCK = new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BaseBlock INFERIUM_BLOCK = new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BaseBlock PRUDENTIUM_BLOCK = new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BaseBlock INTERMEDIUM_BLOCK = new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BaseBlock IMPERIUM_BLOCK = new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 5.0F);
    public static final BaseBlock SUPREMIUM_BLOCK = new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BaseBlock SOULIUM_BLOCK = new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BaseBlock PROSPERITY_INGOT_BLOCK = new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock INFERIUM_INGOT_BLOCK = new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock PRUDENTIUM_INGOT_BLOCK = new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock INTERMEDIUM_INGOT_BLOCK = new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock IMPERIUM_INGOT_BLOCK = new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock SUPREMIUM_INGOT_BLOCK = new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock SOULIUM_INGOT_BLOCK = new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock PROSPERITY_GEMSTONE_BLOCK = new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock INFERIUM_GEMSTONE_BLOCK = new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock PRUDENTIUM_GEMSTONE_BLOCK = new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock INTERMEDIUM_GEMSTONE_BLOCK = new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock IMPERIUM_GEMSTONE_BLOCK = new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock SUPREMIUM_GEMSTONE_BLOCK = new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BaseBlock SOULIUM_GEMSTONE_BLOCK = new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final InfusedFarmlandBlock INFERIUM_FARMLAND = new InfusedFarmlandBlock(CropTier.ONE);
    public static final InfusedFarmlandBlock PRUDENTIUM_FARMLAND = new InfusedFarmlandBlock(CropTier.TWO);
    public static final InfusedFarmlandBlock INTERMEDIUM_FARMLAND = new InfusedFarmlandBlock(CropTier.THREE);
    public static final InfusedFarmlandBlock IMPERIUM_FARMLAND = new InfusedFarmlandBlock(CropTier.FOUR);
    public static final InfusedFarmlandBlock SUPREMIUM_FARMLAND = new InfusedFarmlandBlock(CropTier.FIVE);
    public static final GrowthAcceleratorBlock INFERIUM_GROWTH_ACCELERATOR = new GrowthAcceleratorBlock();
    public static final GrowthAcceleratorBlock PRUDENTIUM_GROWTH_ACCELERATOR = new GrowthAcceleratorBlock();
    public static final GrowthAcceleratorBlock INTERMEDIUM_GROWTH_ACCELERATOR = new GrowthAcceleratorBlock();
    public static final GrowthAcceleratorBlock IMPERIUM_GROWTH_ACCELERATOR = new GrowthAcceleratorBlock();
    public static final GrowthAcceleratorBlock SUPREMIUM_GROWTH_ACCELERATOR = new GrowthAcceleratorBlock();
    public static final EssenceFurnaceBlock INFERIUM_FURNACE = new EssenceFurnaceBlock();
    public static final EssenceFurnaceBlock PRUDENTIUM_FURNACE = new EssenceFurnaceBlock();
    public static final EssenceFurnaceBlock INTERMEDIUM_FURNACE = new EssenceFurnaceBlock();
    public static final EssenceFurnaceBlock IMPERIUM_FURNACE = new EssenceFurnaceBlock();
    public static final EssenceFurnaceBlock SUPREMIUM_FURNACE = new EssenceFurnaceBlock();
    public static final BaseOreBlock PROSPERITY_ORE = new BaseOreBlock(Material.ROCK, SoundType.STONE, 3.0F, 3.0F, 2, 5);
    public static final BaseOreBlock INFERIUM_ORE = new BaseOreBlock(Material.ROCK, SoundType.STONE, 3.0F, 3.0F, 2, 5);
    public static final BaseOreBlock SOULIUM_ORE = new BaseOreBlock(Material.ROCK, SoundType.STONE, 3.0F, 3.0F, 3, 7);
    public static final BaseBlock SOULSTONE = new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F);
    public static final BaseBlock SOULSTONE_COBBLE = new BaseBlock(Material.ROCK, SoundType.STONE, 2.0F, 6.0F);
    public static final BaseBlock SOULSTONE_BRICKS = new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F);
    public static final BaseBlock SOULSTONE_CRACKED_BRICKS = new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F);
    public static final BaseBlock SOULSTONE_CHISELED_BRICKS = new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F);
    public static final BaseBlock SOULSTONE_SMOOTH = new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F);
    public static final BaseGlassBlock SOUL_GLASS = new BaseGlassBlock(Material.GLASS, SoundType.GLASS, 0.3F, 0.3F);
    public static final BaseSlabBlock SOULSTONE_SLAB = new BaseSlabBlock(SOULSTONE);
    public static final BaseSlabBlock SOULSTONE_COBBLE_SLAB = new BaseSlabBlock(SOULSTONE_COBBLE);
    public static final BaseSlabBlock SOULSTONE_BRICKS_SLAB = new BaseSlabBlock(SOULSTONE_BRICKS);
    public static final BaseSlabBlock SOULSTONE_SMOOTH_SLAB = new BaseSlabBlock(SOULSTONE_SMOOTH);
    public static final BaseStairsBlock SOULSTONE_STAIRS = new BaseStairsBlock(SOULSTONE);
    public static final BaseStairsBlock SOULSTONE_COBBLE_STAIRS = new BaseStairsBlock(SOULSTONE_COBBLE);
    public static final BaseStairsBlock SOULSTONE_BRICKS_STAIRS = new BaseStairsBlock(SOULSTONE_BRICKS);
    public static final BaseWallBlock SOULSTONE_COBBLE_WALL = new BaseWallBlock(SOULSTONE_COBBLE);
    public static final BaseWallBlock SOULSTONE_BRICKS_WALL = new BaseWallBlock(SOULSTONE_BRICKS);
    public static final WitherproofBlock WITHERPROOF_BLOCK = new WitherproofBlock();
    public static final WitherproofBlock WITHERPROOF_BRICKS = new WitherproofBlock();
    public static final WitherproofGlassBlock WITHERPROOF_GLASS = new WitherproofGlassBlock();
    public static final InfusionPedestalBlock INFUSION_PEDESTAL = new InfusionPedestalBlock();
    public static final InfusionAltarBlock INFUSION_ALTAR = new InfusionAltarBlock();
    public static final TinkeringTableBlock TINKERING_TABLE = new TinkeringTableBlock();

    @SubscribeEvent
    public void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        register(registry, PROSPERITY_BLOCK.setRegistryName("prosperity_block"));
        register(registry, INFERIUM_BLOCK.setRegistryName("inferium_block"));
        register(registry, PRUDENTIUM_BLOCK.setRegistryName("prudentium_block"));
        register(registry, INTERMEDIUM_BLOCK.setRegistryName("intermedium_block"));
        register(registry, IMPERIUM_BLOCK.setRegistryName("imperium_block"));
        register(registry, SUPREMIUM_BLOCK.setRegistryName("supremium_block"));
        register(registry, SOULIUM_BLOCK.setRegistryName("soulium_block"));
        register(registry, PROSPERITY_INGOT_BLOCK.setRegistryName("prosperity_ingot_block"));
        register(registry, INFERIUM_INGOT_BLOCK.setRegistryName("inferium_ingot_block"));
        register(registry, PRUDENTIUM_INGOT_BLOCK.setRegistryName("prudentium_ingot_block"));
        register(registry, INTERMEDIUM_INGOT_BLOCK.setRegistryName("intermedium_ingot_block"));
        register(registry, IMPERIUM_INGOT_BLOCK.setRegistryName("imperium_ingot_block"));
        register(registry, SUPREMIUM_INGOT_BLOCK.setRegistryName("supremium_ingot_block"));
        register(registry, SOULIUM_INGOT_BLOCK.setRegistryName("soulium_ingot_block"));
        register(registry, PROSPERITY_GEMSTONE_BLOCK.setRegistryName("prosperity_gemstone_block"));
        register(registry, INFERIUM_GEMSTONE_BLOCK.setRegistryName("inferium_gemstone_block"));
        register(registry, PRUDENTIUM_GEMSTONE_BLOCK.setRegistryName("prudentium_gemstone_block"));
        register(registry, INTERMEDIUM_GEMSTONE_BLOCK.setRegistryName("intermedium_gemstone_block"));
        register(registry, IMPERIUM_GEMSTONE_BLOCK.setRegistryName("imperium_gemstone_block"));
        register(registry, SUPREMIUM_GEMSTONE_BLOCK.setRegistryName("supremium_gemstone_block"));
        register(registry, SOULIUM_GEMSTONE_BLOCK.setRegistryName("soulium_gemstone_block"));
        register(registry, INFERIUM_FARMLAND.setRegistryName("inferium_farmland"));
        register(registry, PRUDENTIUM_FARMLAND.setRegistryName("prudentium_farmland"));
        register(registry, INTERMEDIUM_FARMLAND.setRegistryName("intermedium_farmland"));
        register(registry, IMPERIUM_FARMLAND.setRegistryName("imperium_farmland"));
        register(registry, SUPREMIUM_FARMLAND.setRegistryName("supremium_farmland"));
        register(registry, INFERIUM_GROWTH_ACCELERATOR.setRegistryName("inferium_growth_accelerator"));
        register(registry, PRUDENTIUM_GROWTH_ACCELERATOR.setRegistryName("prudentium_growth_accelerator"));
        register(registry, INTERMEDIUM_GROWTH_ACCELERATOR.setRegistryName("intermedium_growth_accelerator"));
        register(registry, IMPERIUM_GROWTH_ACCELERATOR.setRegistryName("imperium_growth_accelerator"));
        register(registry, SUPREMIUM_GROWTH_ACCELERATOR.setRegistryName("supremium_growth_accelerator"));
        register(registry, INFERIUM_FURNACE.setRegistryName("inferium_furnace"));
        register(registry, PRUDENTIUM_FURNACE.setRegistryName("prudentium_furnace"));
        register(registry, INTERMEDIUM_FURNACE.setRegistryName("intermedium_furnace"));
        register(registry, IMPERIUM_FURNACE.setRegistryName("imperium_furnace"));
        register(registry, SUPREMIUM_FURNACE.setRegistryName("supremium_furnace"));
        register(registry, PROSPERITY_ORE.setRegistryName("prosperity_ore"));
        register(registry, INFERIUM_ORE.setRegistryName("inferium_ore"));
        register(registry, SOULIUM_ORE.setRegistryName("soulium_ore"));
        register(registry, SOULSTONE.setRegistryName("soulstone"));
        register(registry, SOULSTONE_COBBLE.setRegistryName("soulstone_cobble"));
        register(registry, SOULSTONE_BRICKS.setRegistryName("soulstone_bricks"));
        register(registry, SOULSTONE_CRACKED_BRICKS.setRegistryName("soulstone_cracked_bricks"));
        register(registry, SOULSTONE_CHISELED_BRICKS.setRegistryName("soulstone_chiseled_bricks"));
        register(registry, SOULSTONE_SMOOTH.setRegistryName("soulstone_smooth"));
        register(registry, SOUL_GLASS.setRegistryName("soul_glass"));
        register(registry, SOULSTONE_SLAB.setRegistryName("soulstone_slab"));
        register(registry, SOULSTONE_COBBLE_SLAB.setRegistryName("soulstone_cobble_slab"));
        register(registry, SOULSTONE_BRICKS_SLAB.setRegistryName("soulstone_bricks_slab"));
        register(registry, SOULSTONE_SMOOTH_SLAB.setRegistryName("soulstone_smooth_slab"));
        register(registry, SOULSTONE_STAIRS.setRegistryName("soulstone_stairs"));
        register(registry, SOULSTONE_COBBLE_STAIRS.setRegistryName("soulstone_cobble_stairs"));
        register(registry, SOULSTONE_BRICKS_STAIRS.setRegistryName("soulstone_bricks_stairs"));
        register(registry, SOULSTONE_COBBLE_WALL.setRegistryName("soulstone_cobble_wall"));
        register(registry, SOULSTONE_BRICKS_WALL.setRegistryName("soulstone_bricks_wall"));
        register(registry, WITHERPROOF_BLOCK.setRegistryName("witherproof_block"));
        register(registry, WITHERPROOF_BRICKS.setRegistryName("witherproof_bricks"));
        register(registry, WITHERPROOF_GLASS.setRegistryName("witherproof_glass"));
        register(registry, INFUSION_PEDESTAL.setRegistryName("infusion_pedestal"));
        register(registry, INFUSION_ALTAR.setRegistryName("infusion_altar"));
        register(registry, TINKERING_TABLE.setRegistryName("tinkering_table"));

        CropRegistry.getInstance().allowRegistration();
        CropRegistry.getInstance().onRegisterBlocks(registry);
        CropRegistry.getInstance().denyRegistration();
    }

    private static void register(IForgeRegistry<Block> registry, Block block) {
        if (block.getRegistryName() != null) {
            BaseBlockItem item = new BaseBlockItem(block, p -> p.group(ITEM_GROUP));
            item.setRegistryName(block.getRegistryName());
            register(registry, block, item);
        }
    }

    private static void register(IForgeRegistry<Block> registry, Block block, BlockItem item) {
        registry.register(block);
        ModItems.BLOCK_ITEMS.add(item);
    }
}
