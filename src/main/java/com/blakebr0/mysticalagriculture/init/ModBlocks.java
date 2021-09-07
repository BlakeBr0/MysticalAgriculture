package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.cucumber.block.BaseBlock;
import com.blakebr0.cucumber.block.BaseGlassBlock;
import com.blakebr0.cucumber.block.BaseOreBlock;
import com.blakebr0.cucumber.block.BaseSlabBlock;
import com.blakebr0.cucumber.block.BaseStairsBlock;
import com.blakebr0.cucumber.block.BaseWallBlock;
import com.blakebr0.cucumber.item.BaseBlockItem;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.block.EssenceFurnaceBlock;
import com.blakebr0.mysticalagriculture.block.GrowthAcceleratorBlock;
import com.blakebr0.mysticalagriculture.block.InferiumCropBlock;
import com.blakebr0.mysticalagriculture.block.InfusedFarmlandBlock;
import com.blakebr0.mysticalagriculture.block.InfusionAltarBlock;
import com.blakebr0.mysticalagriculture.block.InfusionPedestalBlock;
import com.blakebr0.mysticalagriculture.block.ReprocessorBlock;
import com.blakebr0.mysticalagriculture.block.SoulExtractorBlock;
import com.blakebr0.mysticalagriculture.block.TinkeringTableBlock;
import com.blakebr0.mysticalagriculture.block.WitherproofBlock;
import com.blakebr0.mysticalagriculture.block.WitherproofGlassBlock;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.blakebr0.mysticalagriculture.util.FurnaceTier;
import com.blakebr0.mysticalagriculture.util.ReprocessorTier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.ITEM_GROUP;

public final class ModBlocks {
    public static final Map<RegistryObject<Block>, Supplier<Block>> ENTRIES = new LinkedHashMap<>();

    public static final RegistryObject<Block> PROSPERITY_BLOCK = register("prosperity_block", () -> new BaseBlock(Material.STONE, SoundType.STONE, 4.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> INFERIUM_BLOCK = register("inferium_block", () -> new BaseBlock(Material.STONE, SoundType.STONE, 4.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> PRUDENTIUM_BLOCK = register("prudentium_block", () -> new BaseBlock(Material.STONE, SoundType.STONE, 4.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> TERTIUM_BLOCK = register("tertium_block", () -> new BaseBlock(Material.STONE, SoundType.STONE, 4.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> IMPERIUM_BLOCK = register("imperium_block", () -> new BaseBlock(Material.STONE, SoundType.STONE, 4.0F, 5.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SUPREMIUM_BLOCK = register("supremium_block", () -> new BaseBlock(Material.STONE, SoundType.STONE, 4.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULIUM_BLOCK = register("soulium_block", () -> new BaseBlock(Material.STONE, SoundType.STONE, 4.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> PROSPERITY_INGOT_BLOCK = register("prosperity_ingot_block", () -> new BaseBlock(Material.METAL, SoundType.METAL, 5.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> INFERIUM_INGOT_BLOCK = register("inferium_ingot_block", () -> new BaseBlock(Material.METAL, SoundType.METAL, 5.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> PRUDENTIUM_INGOT_BLOCK = register("prudentium_ingot_block", () -> new BaseBlock(Material.METAL, SoundType.METAL, 5.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> TERTIUM_INGOT_BLOCK = register("tertium_ingot_block", () -> new BaseBlock(Material.METAL, SoundType.METAL, 5.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> IMPERIUM_INGOT_BLOCK = register("imperium_ingot_block", () -> new BaseBlock(Material.METAL, SoundType.METAL, 5.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SUPREMIUM_INGOT_BLOCK = register("supremium_ingot_block", () -> new BaseBlock(Material.METAL, SoundType.METAL, 5.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULIUM_INGOT_BLOCK = register("soulium_ingot_block", () -> new BaseBlock(Material.METAL, SoundType.METAL, 5.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> PROSPERITY_GEMSTONE_BLOCK = register("prosperity_gemstone_block", () -> new BaseBlock(Material.METAL, SoundType.METAL, 5.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> INFERIUM_GEMSTONE_BLOCK = register("inferium_gemstone_block", () -> new BaseBlock(Material.METAL, SoundType.METAL, 5.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> PRUDENTIUM_GEMSTONE_BLOCK = register("prudentium_gemstone_block", () -> new BaseBlock(Material.METAL, SoundType.METAL, 5.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> TERTIUM_GEMSTONE_BLOCK = register("tertium_gemstone_block", () -> new BaseBlock(Material.METAL, SoundType.METAL, 5.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> IMPERIUM_GEMSTONE_BLOCK = register("imperium_gemstone_block", () -> new BaseBlock(Material.METAL, SoundType.METAL, 5.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SUPREMIUM_GEMSTONE_BLOCK = register("supremium_gemstone_block", () -> new BaseBlock(Material.METAL, SoundType.METAL, 5.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULIUM_GEMSTONE_BLOCK = register("soulium_gemstone_block", () -> new BaseBlock(Material.METAL, SoundType.METAL, 5.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> INFERIUM_FARMLAND = register("inferium_farmland", () -> new InfusedFarmlandBlock(CropTier.ONE));
    public static final RegistryObject<Block> PRUDENTIUM_FARMLAND = register("prudentium_farmland", () -> new InfusedFarmlandBlock(CropTier.TWO));
    public static final RegistryObject<Block> TERTIUM_FARMLAND = register("tertium_farmland", () -> new InfusedFarmlandBlock(CropTier.THREE));
    public static final RegistryObject<Block> IMPERIUM_FARMLAND = register("imperium_farmland", () -> new InfusedFarmlandBlock(CropTier.FOUR));
    public static final RegistryObject<Block> SUPREMIUM_FARMLAND = register("supremium_farmland", () -> new InfusedFarmlandBlock(CropTier.FIVE));
    public static final RegistryObject<Block> INFERIUM_GROWTH_ACCELERATOR = register("inferium_growth_accelerator", () -> new GrowthAcceleratorBlock(12, CropTier.ONE.getTextColor()));
    public static final RegistryObject<Block> PRUDENTIUM_GROWTH_ACCELERATOR = register("prudentium_growth_accelerator", () -> new GrowthAcceleratorBlock(24, CropTier.TWO.getTextColor()));
    public static final RegistryObject<Block> TERTIUM_GROWTH_ACCELERATOR = register("tertium_growth_accelerator", () -> new GrowthAcceleratorBlock(36, CropTier.THREE.getTextColor()));
    public static final RegistryObject<Block> IMPERIUM_GROWTH_ACCELERATOR = register("imperium_growth_accelerator", () -> new GrowthAcceleratorBlock(48, CropTier.FOUR.getTextColor()));
    public static final RegistryObject<Block> SUPREMIUM_GROWTH_ACCELERATOR = register("supremium_growth_accelerator", () -> new GrowthAcceleratorBlock(60, CropTier.FIVE.getTextColor()));
    public static final RegistryObject<Block> INFERIUM_FURNACE = register("inferium_furnace", () -> new EssenceFurnaceBlock(FurnaceTier.INFERIUM));
    public static final RegistryObject<Block> PRUDENTIUM_FURNACE = register("prudentium_furnace", () -> new EssenceFurnaceBlock(FurnaceTier.PRUDENTIUM));
    public static final RegistryObject<Block> TERTIUM_FURNACE = register("tertium_furnace", () -> new EssenceFurnaceBlock(FurnaceTier.TERTIUM));
    public static final RegistryObject<Block> IMPERIUM_FURNACE = register("imperium_furnace", () -> new EssenceFurnaceBlock(FurnaceTier.IMPERIUM));
    public static final RegistryObject<Block> SUPREMIUM_FURNACE = register("supremium_furnace", () -> new EssenceFurnaceBlock(FurnaceTier.SUPREMIUM));
    public static final RegistryObject<Block> PROSPERITY_ORE = register("prosperity_ore", () -> new BaseOreBlock(Material.STONE, SoundType.STONE, 3.0F, 3.0F, 2, 5));
    public static final RegistryObject<Block> INFERIUM_ORE = register("inferium_ore", () -> new BaseOreBlock(Material.STONE, SoundType.STONE, 3.0F, 3.0F, 2, 5));
    public static final RegistryObject<Block> SOULIUM_ORE = register("soulium_ore", () -> new BaseOreBlock(Material.STONE, SoundType.STONE, 3.0F, 3.0F, 3, 7));
    public static final RegistryObject<Block> SOULSTONE = register("soulstone", () -> new BaseBlock(Material.STONE, SoundType.STONE, 1.5F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULSTONE_COBBLE = register("soulstone_cobble", () -> new BaseBlock(Material.STONE, SoundType.STONE, 2.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULSTONE_BRICKS = register("soulstone_bricks", () -> new BaseBlock(Material.STONE, SoundType.STONE, 1.5F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULSTONE_CRACKED_BRICKS = register("soulstone_cracked_bricks", () -> new BaseBlock(Material.STONE, SoundType.STONE, 1.5F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULSTONE_CHISELED_BRICKS = register("soulstone_chiseled_bricks", () -> new BaseBlock(Material.STONE, SoundType.STONE, 1.5F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULSTONE_SMOOTH = register("soulstone_smooth", () -> new BaseBlock(Material.STONE, SoundType.STONE, 1.5F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOUL_GLASS = register("soul_glass", () -> new BaseGlassBlock(Material.GLASS, SoundType.GLASS, 0.3F, 0.3F));
    public static final RegistryObject<Block> SOULSTONE_SLAB = register("soulstone_slab", () -> new BaseSlabBlock(Material.STONE, SoundType.STONE, 1.5F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULSTONE_COBBLE_SLAB = register("soulstone_cobble_slab", () -> new BaseSlabBlock(Material.STONE, SoundType.STONE, 2.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULSTONE_BRICKS_SLAB = register("soulstone_bricks_slab", () -> new BaseSlabBlock(Material.STONE, SoundType.STONE, 1.5F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULSTONE_SMOOTH_SLAB = register("soulstone_smooth_slab", () -> new BaseSlabBlock(Material.STONE, SoundType.STONE, 1.5F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULSTONE_STAIRS = register("soulstone_stairs", () -> new BaseStairsBlock(SOULSTONE.lazyMap(Block::defaultBlockState), Material.STONE, SoundType.STONE, 1.5F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULSTONE_COBBLE_STAIRS = register("soulstone_cobble_stairs", () -> new BaseStairsBlock(SOULSTONE_COBBLE.lazyMap(Block::defaultBlockState), Material.STONE, SoundType.STONE, 2.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULSTONE_BRICKS_STAIRS = register("soulstone_bricks_stairs", () -> new BaseStairsBlock(SOULSTONE_BRICKS.lazyMap(Block::defaultBlockState), Material.STONE, SoundType.STONE, 1.5F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULSTONE_COBBLE_WALL = register("soulstone_cobble_wall", () -> new BaseWallBlock(Material.STONE, SoundType.STONE, 2.0F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> SOULSTONE_BRICKS_WALL = register("soulstone_bricks_wall", () -> new BaseWallBlock(Material.STONE, SoundType.STONE, 1.5F, 6.0F, ToolType.PICKAXE));
    public static final RegistryObject<Block> WITHERPROOF_BLOCK = register("witherproof_block", WitherproofBlock::new);
    public static final RegistryObject<Block> WITHERPROOF_BRICKS = register("witherproof_bricks", WitherproofBlock::new);
    public static final RegistryObject<Block> WITHERPROOF_GLASS = register("witherproof_glass", WitherproofGlassBlock::new);
    public static final RegistryObject<Block> INFUSION_PEDESTAL = register("infusion_pedestal", InfusionPedestalBlock::new);
    public static final RegistryObject<Block> INFUSION_ALTAR = register("infusion_altar", InfusionAltarBlock::new);
    public static final RegistryObject<Block> TINKERING_TABLE = register("tinkering_table", TinkeringTableBlock::new);
    public static final RegistryObject<Block> MACHINE_FRAME = register("machine_frame", () -> new BaseBlock(Material.STONE, SoundType.STONE, 1.5F, 6.0F));
    public static final RegistryObject<Block> BASIC_REPROCESSOR = register("basic_reprocessor", () -> new ReprocessorBlock(ReprocessorTier.BASIC));
    public static final RegistryObject<Block> INFERIUM_REPROCESSOR = register("inferium_reprocessor", () -> new ReprocessorBlock(ReprocessorTier.INFERIUM));
    public static final RegistryObject<Block> PRUDENTIUM_REPROCESSOR = register("prudentium_reprocessor", () -> new ReprocessorBlock(ReprocessorTier.PRUDENTIUM));
    public static final RegistryObject<Block> TERTIUM_REPROCESSOR = register("tertium_reprocessor", () -> new ReprocessorBlock(ReprocessorTier.TERTIUM));
    public static final RegistryObject<Block> IMPERIUM_REPROCESSOR = register("imperium_reprocessor", () -> new ReprocessorBlock(ReprocessorTier.IMPERIUM));
    public static final RegistryObject<Block> SUPREMIUM_REPROCESSOR = register("supremium_reprocessor", () -> new ReprocessorBlock(ReprocessorTier.SUPREMIUM));
    public static final RegistryObject<Block> SOUL_EXTRACTOR = register("soul_extractor", SoulExtractorBlock::new);
    public static final RegistryObject<Block> INFERIUM_CROP = registerNoItem("inferium_crop", () -> new InferiumCropBlock(ModCrops.INFERIUM));

    @SubscribeEvent
    public void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        ENTRIES.forEach((reg, block) -> {
            registry.register(block.get());
            reg.updateReference(registry);
        });

        CropRegistry.getInstance().setAllowRegistration(true);
        CropRegistry.getInstance().onRegisterBlocks(registry);
        CropRegistry.getInstance().setAllowRegistration(false);
    }

    private static RegistryObject<Block> register(String name, Supplier<Block> block) {
        return register(name, block, b -> () -> new BaseBlockItem(b.get(), p -> p.tab(ITEM_GROUP)));
    }

    private static RegistryObject<Block> register(String name, Supplier<Block> block, Function<RegistryObject<Block>, Supplier<? extends BlockItem>> item) {
        ResourceLocation loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        RegistryObject<Block> reg = RegistryObject.of(loc, ForgeRegistries.BLOCKS);
        ENTRIES.put(reg, () -> block.get().setRegistryName(loc));
        ModItems.BLOCK_ENTRIES.add(() -> item.apply(reg).get().setRegistryName(loc));
        return reg;
    }

    public static RegistryObject<Block> registerNoItem(String name, Supplier<Block> block) {
        ResourceLocation loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        RegistryObject<Block> reg = RegistryObject.of(loc, ForgeRegistries.BLOCKS);
        ENTRIES.put(reg, () -> block.get().setRegistryName(loc));
        return reg;
    }
}
