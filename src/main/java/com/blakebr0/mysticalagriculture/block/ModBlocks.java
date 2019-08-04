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
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.IForgeRegistry;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.ITEM_GROUP;

public class ModBlocks {
    public static final RegistryObject<BaseBlock> PROSPERITY_BLOCK = get("prosperity_block");
    public static final RegistryObject<BaseBlock> INFERIUM_BLOCK = get("inferium_block");
    public static final RegistryObject<BaseBlock> PRUDENTIUM_BLOCK = get("prudentium_block");
    public static final RegistryObject<BaseBlock> INTERMEDIUM_BLOCK = get("intermedium_block");
    public static final RegistryObject<BaseBlock> IMPERIUM_BLOCK = get("imperium_block");
    public static final RegistryObject<BaseBlock> SUPREMIUM_BLOCK = get("supremium_block");
    public static final RegistryObject<BaseBlock> SOULIUM_BLOCK = get("soulium_block");
    public static final RegistryObject<BaseBlock> PROSPERITY_INGOT_BLOCK = get("prosperity_ingot_block");
    public static final RegistryObject<BaseBlock> INFERIUM_INGOT_BLOCK = get("inferium_ingot_block");
    public static final RegistryObject<BaseBlock> PRUDENTIUM_INGOT_BLOCK = get("prudentium_ingot_block");
    public static final RegistryObject<BaseBlock> INTERMEDIUM_INGOT_BLOCK = get("intermedium_ingot_block");
    public static final RegistryObject<BaseBlock> IMPERIUM_INGOT_BLOCK = get("imperium_ingot_block");
    public static final RegistryObject<BaseBlock> SUPREMIUM_INGOT_BLOCK = get("supremium_ingot_block");
    public static final RegistryObject<BaseBlock> SOULIUM_INGOT_BLOCK = get("soulium_ingot_block");
    public static final RegistryObject<BaseBlock> PROSPERITY_GEMSTONE_BLOCK = get("prosperity_gemstone_block");
    public static final RegistryObject<BaseBlock> INFERIUM_GEMSTONE_BLOCK = get("inferium_gemstone_block");
    public static final RegistryObject<BaseBlock> PRUDENTIUM_GEMSTONE_BLOCK = get("prudentium_gemstone_block");
    public static final RegistryObject<BaseBlock> INTERMEDIUM_GEMSTONE_BLOCK = get("intermedium_gemstone_block");
    public static final RegistryObject<BaseBlock> IMPERIUM_GEMSTONE_BLOCK = get("imperium_gemstone_block");
    public static final RegistryObject<BaseBlock> SUPREMIUM_GEMSTONE_BLOCK = get("supremium_gemstone_block");
    public static final RegistryObject<BaseBlock> SOULIUM_GEMSTONE_BLOCK = get("soulium_gemstone_block");
    public static final RegistryObject<InfusedFarmlandBlock> INFERIUM_FARMLAND = get("inferium_farmland");
    public static final RegistryObject<InfusedFarmlandBlock> PRUDENTIUM_FARMLAND = get("prudentium_farmland");
    public static final RegistryObject<InfusedFarmlandBlock> INTERMEDIUM_FARMLAND = get("intermedium_farmland");
    public static final RegistryObject<InfusedFarmlandBlock> IMPERIUM_FARMLAND = get("imperium_farmland");
    public static final RegistryObject<InfusedFarmlandBlock> SUPREMIUM_FARMLAND = get("supremium_farmland");
    public static final RegistryObject<GrowthAcceleratorBlock> INFERIUM_GROWTH_ACCELERATOR = get("inferium_growth_accelerator");
    public static final RegistryObject<GrowthAcceleratorBlock> PRUDENTIUM_GROWTH_ACCELERATOR = get("prudentium_growth_accelerator");
    public static final RegistryObject<GrowthAcceleratorBlock> INTERMEDIUM_GROWTH_ACCELERATOR = get("intermedium_growth_accelerator");
    public static final RegistryObject<GrowthAcceleratorBlock> IMPERIUM_GROWTH_ACCELERATOR = get("imperium_growth_accelerator");
    public static final RegistryObject<GrowthAcceleratorBlock> SUPREMIUM_GROWTH_ACCELERATOR = get("supremium_growth_accelerator");
    public static final RegistryObject<BaseBlock> SOULSTONE = get("soulstone");
    public static final RegistryObject<BaseBlock> SOULSTONE_COBBLE = get("soulstone_cobble");
    public static final RegistryObject<BaseBlock> SOULSTONE_BRICKS = get("soulstone_bricks");
    public static final RegistryObject<BaseBlock> SOULSTONE_CRACKED_BRICKS = get("soulstone_cracked_bricks");
    public static final RegistryObject<BaseBlock> SOULSTONE_CHISELED_BRICKS = get("soulstone_chiseled_bricks");
    public static final RegistryObject<BaseBlock> SOULSTONE_SMOOTH = get("soulstone_smooth");
    public static final RegistryObject<BaseGlassBlock> SOUL_GLASS = get("soul_glass");
    public static final RegistryObject<BaseSlabBlock> SOULSTONE_SLAB = get("soulstone_slab");
    public static final RegistryObject<BaseSlabBlock> SOULSTONE_COBBLE_SLAB = get("soulstone_cobble_slab");
    public static final RegistryObject<BaseSlabBlock> SOULSTONE_BRICKS_SLAB = get("soulstone_bricks_slab");
    public static final RegistryObject<BaseSlabBlock> SOULSTONE_SMOOTH_SLAB = get("soulstone_smooth_slab");
    public static final RegistryObject<BaseStairsBlock> SOULSTONE_STAIRS = get("soulstone_stairs");
    public static final RegistryObject<BaseStairsBlock> SOULSTONE_COBBLE_STAIRS = get("soulstone_cobble_stairs");
    public static final RegistryObject<BaseStairsBlock> SOULSTONE_BRICKS_STAIRS = get("soulstone_bricks_stairs");
    public static final RegistryObject<BaseWallBlock> SOULSTONE_COBBLE_WALL = get("soulstone_cobble_wall");
    public static final RegistryObject<BaseWallBlock> SOULSTONE_BRICKS_WALL = get("soulstone_bricks_wall");
    public static final RegistryObject<WitherproofBlock> WITHERPROOF_BLOCK = get("witherproof_block");
    public static final RegistryObject<WitherproofBlock> WITHERPROOF_BRICKS = get("witherproof_bricks");
    public static final RegistryObject<WitherproofGlassBlock> WITHERPROOF_GLASS = get("witherproof_glass");
    public static final RegistryObject<InfusionPedestalBlock> INFUSION_PEDESTAL = get("infusion_pedestal");
    public static final RegistryObject<InfusionAltarBlock> INFUSION_ALTAR = get("infusion_altar");
    public static final RegistryObject<TinkeringTableBlock> TINKERING_TABLE = get("tinkering_table");

    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        registerAll(registry,
                new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F).setRegistryName("prosperity_block"),
                new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F).setRegistryName("inferium_block"),
                new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F).setRegistryName("prudentium_block"),
                new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F).setRegistryName("intermedium_block"),
                new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 5.0F).setRegistryName("imperium_block"),
                new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F).setRegistryName("supremium_block"),
                new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F).setRegistryName("soulium_block"),
                new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F).setRegistryName("prosperity_ingot_block"),
                new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F).setRegistryName("inferium_ingot_block"),
                new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F).setRegistryName("prudentium_ingot_block"),
                new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F).setRegistryName("intermedium_ingot_block"),
                new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F).setRegistryName("imperium_ingot_block"),
                new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F).setRegistryName("supremium_ingot_block"),
                new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F).setRegistryName("soulium_ingot_block"),
                new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F).setRegistryName("prosperity_gemstone_block"),
                new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F).setRegistryName("inferium_gemstone_block"),
                new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F).setRegistryName("prudentium_gemstone_block"),
                new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F).setRegistryName("intermedium_gemstone_block"),
                new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F).setRegistryName("imperium_gemstone_block"),
                new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F).setRegistryName("supremium_gemstone_block"),
                new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F).setRegistryName("soulium_gemstone_block"),
                new InfusedFarmlandBlock(CropTiers.ONE).setRegistryName("inferium_farmland"),
                new InfusedFarmlandBlock(CropTiers.TWO).setRegistryName("prudentium_farmland"),
                new InfusedFarmlandBlock(CropTiers.THREE).setRegistryName("intermedium_farmland"),
                new InfusedFarmlandBlock(CropTiers.FOUR).setRegistryName("imperium_farmland"),
                new InfusedFarmlandBlock(CropTiers.FIVE).setRegistryName("supremium_farmland"),
                new GrowthAcceleratorBlock().setRegistryName("inferium_growth_accelerator"),
                new GrowthAcceleratorBlock().setRegistryName("prudentium_growth_accelerator"),
                new GrowthAcceleratorBlock().setRegistryName("intermedium_growth_accelerator"),
                new GrowthAcceleratorBlock().setRegistryName("imperium_growth_accelerator"),
                new GrowthAcceleratorBlock().setRegistryName("supremium_growth_accelerator"),
                new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F).setRegistryName("soulstone"),
                new BaseBlock(Material.ROCK, SoundType.STONE, 2.0F, 6.0F).setRegistryName("soulstone_cobble"),
                new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F).setRegistryName("soulstone_bricks"),
                new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F).setRegistryName("soulstone_cracked_bricks"),
                new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F).setRegistryName("soulstone_chiseled_bricks"),
                new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F).setRegistryName("soulstone_smooth"),
                new BaseGlassBlock(Material.GLASS, SoundType.GLASS, 0.3F, 0.3F).setRegistryName("soul_glass"),
                new BaseSlabBlock(SOULSTONE.orElse(null)).setRegistryName("soulstone_slab"),
                new BaseSlabBlock(SOULSTONE_COBBLE.orElse(null)).setRegistryName("soulstone_cobble_slab"),
                new BaseSlabBlock(SOULSTONE_BRICKS.orElse(null)).setRegistryName("soulstone_bricks_slab"),
                new BaseSlabBlock(SOULSTONE_SMOOTH.orElse(null)).setRegistryName("soulstone_smooth_slab"),
                new BaseStairsBlock(SOULSTONE.orElse(null)).setRegistryName("soulstone_stairs"),
                new BaseStairsBlock(SOULSTONE_COBBLE.orElse(null)).setRegistryName("soulstone_cobble_stairs"),
                new BaseStairsBlock(SOULSTONE_BRICKS.orElse(null)).setRegistryName("soulstone_bricks_stairs"),
                new BaseWallBlock(SOULSTONE_COBBLE.orElse(null)).setRegistryName("soulstone_cobble_wall"),
                new BaseWallBlock(SOULSTONE_BRICKS.orElse(null)).setRegistryName("soulstone_bricks_wall"),
                new WitherproofBlock().setRegistryName("witherproof_block"),
                new WitherproofBlock().setRegistryName("witherproof_bricks"),
                new WitherproofGlassBlock().setRegistryName("witherproof_glass"),
                new InfusionPedestalBlock().setRegistryName("infusion_pedestal"),
                new InfusionAltarBlock().setRegistryName("infusion_altar"),
                new TinkeringTableBlock().setRegistryName("tinkering_table")
        );

        CropRegistry.getInstance().allowRegistration();
        CropRegistry.getInstance().onRegisterBlocks(registry);
        CropRegistry.getInstance().denyRegistration();
    }

    private static void registerAll(IForgeRegistry<Block> registry, Block... blocks) {
        for (Block block : blocks)
            register(registry, block);
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
        ModItems.ITEM_BLOCKS.add(item);
    }

    private static <T extends Block> RegistryObject<T> get(String name) {
        return RegistryObject.of("mysticalagriculture:" + name, () -> Block.class);
    }
}
