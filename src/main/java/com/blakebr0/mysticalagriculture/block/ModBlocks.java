package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseBlock;
import com.blakebr0.cucumber.block.BaseGlassBlock;
import com.blakebr0.cucumber.block.BaseOreBlock;
import com.blakebr0.cucumber.block.BaseSlabBlock;
import com.blakebr0.cucumber.block.BaseStairsBlock;
import com.blakebr0.cucumber.block.BaseWallBlock;
import com.blakebr0.cucumber.item.BaseBlockItem;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.item.ModItems;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.ITEM_GROUP;

public class ModBlocks {
    public static final List<Supplier<? extends Block>> ENTRIES = new ArrayList<>();

    public static final RegistryObject<BaseBlock> PROSPERITY_BLOCK = register("prosperity_block", () -> new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F));
    public static final RegistryObject<BaseBlock> INFERIUM_BLOCK = register("inferium_block", () -> new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F));
    public static final RegistryObject<BaseBlock> PRUDENTIUM_BLOCK = register("prudentium_block", () -> new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F));
    public static final RegistryObject<BaseBlock> TERTIUM_BLOCK = register("tertium_block", () -> new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F));
    public static final RegistryObject<BaseBlock> IMPERIUM_BLOCK = register("imperium_block", () -> new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 5.0F));
    public static final RegistryObject<BaseBlock> SUPREMIUM_BLOCK = register("supremium_block", () -> new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F));
    public static final RegistryObject<BaseBlock> SOULIUM_BLOCK = register("soulium_block", () -> new BaseBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F));
    public static final RegistryObject<BaseBlock> PROSPERITY_INGOT_BLOCK = register("prosperity_ingot_block", () -> new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F));
    public static final RegistryObject<BaseBlock> INFERIUM_INGOT_BLOCK = register("inferium_ingot_block", () -> new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F));
    public static final RegistryObject<BaseBlock> PRUDENTIUM_INGOT_BLOCK = register("prudentium_ingot_block", () -> new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F));
    public static final RegistryObject<BaseBlock> TERTIUM_INGOT_BLOCK = register("tertium_ingot_block", () -> new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F));
    public static final RegistryObject<BaseBlock> IMPERIUM_INGOT_BLOCK = register("imperium_ingot_block", () -> new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F));
    public static final RegistryObject<BaseBlock> SUPREMIUM_INGOT_BLOCK = register("supremium_ingot_block", () -> new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F));
    public static final RegistryObject<BaseBlock> SOULIUM_INGOT_BLOCK = register("soulium_ingot_block", () -> new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F));
    public static final RegistryObject<BaseBlock> PROSPERITY_GEMSTONE_BLOCK = register("prosperity_gemstone_block", () -> new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F));
    public static final RegistryObject<BaseBlock> INFERIUM_GEMSTONE_BLOCK = register("inferium_gemstone_block", () -> new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F));
    public static final RegistryObject<BaseBlock> PRUDENTIUM_GEMSTONE_BLOCK = register("prudentium_gemstone_block", () -> new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F));
    public static final RegistryObject<BaseBlock> TERTIUM_GEMSTONE_BLOCK = register("tertium_gemstone_block", () -> new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F));
    public static final RegistryObject<BaseBlock> IMPERIUM_GEMSTONE_BLOCK = register("imperium_gemstone_block", () -> new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F));
    public static final RegistryObject<BaseBlock> SUPREMIUM_GEMSTONE_BLOCK = register("supremium_gemstone_block", () -> new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F));
    public static final RegistryObject<BaseBlock> SOULIUM_GEMSTONE_BLOCK = register("soulium_gemstone_block", () -> new BaseBlock(Material.IRON, SoundType.METAL, 5.0F, 6.0F));
    public static final RegistryObject<InfusedFarmlandBlock> INFERIUM_FARMLAND = register("inferium_farmland", () -> new InfusedFarmlandBlock(CropTier.ONE));
    public static final RegistryObject<InfusedFarmlandBlock> PRUDENTIUM_FARMLAND = register("prudentium_farmland", () -> new InfusedFarmlandBlock(CropTier.TWO));
    public static final RegistryObject<InfusedFarmlandBlock> TERTIUM_FARMLAND = register("tertium_farmland", () -> new InfusedFarmlandBlock(CropTier.THREE));
    public static final RegistryObject<InfusedFarmlandBlock> IMPERIUM_FARMLAND = register("imperium_farmland", () -> new InfusedFarmlandBlock(CropTier.FOUR));
    public static final RegistryObject<InfusedFarmlandBlock> SUPREMIUM_FARMLAND = register("supremium_farmland", () -> new InfusedFarmlandBlock(CropTier.FIVE));
    public static final RegistryObject<GrowthAcceleratorBlock> INFERIUM_GROWTH_ACCELERATOR = register("inferium_growth_accelerator", () -> new GrowthAcceleratorBlock(12, CropTier.ONE.getTextColor()));
    public static final RegistryObject<GrowthAcceleratorBlock> PRUDENTIUM_GROWTH_ACCELERATOR = register("prudentium_growth_accelerator", () -> new GrowthAcceleratorBlock(24, CropTier.TWO.getTextColor()));
    public static final RegistryObject<GrowthAcceleratorBlock> TERTIUM_GROWTH_ACCELERATOR = register("tertium_growth_accelerator", () -> new GrowthAcceleratorBlock(36, CropTier.THREE.getTextColor()));
    public static final RegistryObject<GrowthAcceleratorBlock> IMPERIUM_GROWTH_ACCELERATOR = register("imperium_growth_accelerator", () -> new GrowthAcceleratorBlock(48, CropTier.FOUR.getTextColor()));
    public static final RegistryObject<GrowthAcceleratorBlock> SUPREMIUM_GROWTH_ACCELERATOR = register("supremium_growth_accelerator", () -> new GrowthAcceleratorBlock(60, CropTier.FIVE.getTextColor()));
    public static final RegistryObject<EssenceFurnaceBlock> INFERIUM_FURNACE = register("inferium_furnace", () -> new EssenceFurnaceBlock(EssenceFurnaceBlock.FurnaceTier.INFERIUM));
    public static final RegistryObject<EssenceFurnaceBlock> PRUDENTIUM_FURNACE = register("prudentium_furnace", () -> new EssenceFurnaceBlock(EssenceFurnaceBlock.FurnaceTier.PRUDENTIUM));
    public static final RegistryObject<EssenceFurnaceBlock> TERTIUM_FURNACE = register("tertium_furnace", () -> new EssenceFurnaceBlock(EssenceFurnaceBlock.FurnaceTier.TERTIUM));
    public static final RegistryObject<EssenceFurnaceBlock> IMPERIUM_FURNACE = register("imperium_furnace", () -> new EssenceFurnaceBlock(EssenceFurnaceBlock.FurnaceTier.IMPERIUM));
    public static final RegistryObject<EssenceFurnaceBlock> SUPREMIUM_FURNACE = register("supremium_furnace", () -> new EssenceFurnaceBlock(EssenceFurnaceBlock.FurnaceTier.SUPREMIUM));
    public static final RegistryObject<BaseOreBlock> PROSPERITY_ORE = register("prosperity_ore", () -> new BaseOreBlock(Material.ROCK, SoundType.STONE, 3.0F, 3.0F, 2, 5));
    public static final RegistryObject<BaseOreBlock> INFERIUM_ORE = register("inferium_ore", () -> new BaseOreBlock(Material.ROCK, SoundType.STONE, 3.0F, 3.0F, 2, 5));
    public static final RegistryObject<BaseOreBlock> SOULIUM_ORE = register("soulium_ore", () -> new BaseOreBlock(Material.ROCK, SoundType.STONE, 3.0F, 3.0F, 3, 7));
    public static final RegistryObject<Block> SOULSTONE = register("soulstone", () -> new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F));
    public static final RegistryObject<BaseBlock> SOULSTONE_COBBLE = register("soulstone_cobble", () -> new BaseBlock(Material.ROCK, SoundType.STONE, 2.0F, 6.0F));
    public static final RegistryObject<BaseBlock> SOULSTONE_BRICKS = register("soulstone_bricks", () -> new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F));
    public static final RegistryObject<BaseBlock> SOULSTONE_CRACKED_BRICKS = register("soulstone_cracked_bricks", () -> new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F));
    public static final RegistryObject<BaseBlock> SOULSTONE_CHISELED_BRICKS = register("soulstone_chiseled_bricks", () -> new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F));
    public static final RegistryObject<BaseBlock> SOULSTONE_SMOOTH = register("soulstone_smooth", () -> new BaseBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F));
    public static final RegistryObject<BaseGlassBlock> SOUL_GLASS = register("soul_glass", () -> new BaseGlassBlock(Material.GLASS, SoundType.GLASS, 0.3F, 0.3F));
    public static final RegistryObject<BaseSlabBlock> SOULSTONE_SLAB = register("soulstone_slab", () -> new BaseSlabBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F));
    public static final RegistryObject<BaseSlabBlock> SOULSTONE_COBBLE_SLAB = register("soulstone_cobble_slab", () -> new BaseSlabBlock(Material.ROCK, SoundType.STONE, 2.0F, 6.0F));
    public static final RegistryObject<BaseSlabBlock> SOULSTONE_BRICKS_SLAB = register("soulstone_bricks_slab", () -> new BaseSlabBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F));
    public static final RegistryObject<BaseSlabBlock> SOULSTONE_SMOOTH_SLAB = register("soulstone_smooth_slab", () -> new BaseSlabBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F));
    public static final RegistryObject<BaseStairsBlock> SOULSTONE_STAIRS = register("soulstone_stairs", () -> new BaseStairsBlock(SOULSTONE.lazyMap(Block::getDefaultState), Material.ROCK, SoundType.STONE, 1.5F, 6.0F));
    public static final RegistryObject<BaseStairsBlock> SOULSTONE_COBBLE_STAIRS = register("soulstone_cobble_stairs", () -> new BaseStairsBlock(SOULSTONE_COBBLE.lazyMap(Block::getDefaultState), Material.ROCK, SoundType.STONE, 2.0F, 6.0F));
    public static final RegistryObject<BaseStairsBlock> SOULSTONE_BRICKS_STAIRS = register("soulstone_bricks_stairs", () -> new BaseStairsBlock(SOULSTONE_BRICKS.lazyMap(Block::getDefaultState), Material.ROCK, SoundType.STONE, 1.5F, 6.0F));
    public static final RegistryObject<BaseWallBlock> SOULSTONE_COBBLE_WALL = register("soulstone_cobble_wall", () -> new BaseWallBlock(Material.ROCK, SoundType.STONE, 2.0F, 6.0F));
    public static final RegistryObject<BaseWallBlock> SOULSTONE_BRICKS_WALL = register("soulstone_bricks_wall", () -> new BaseWallBlock(Material.ROCK, SoundType.STONE, 1.5F, 6.0F));
    public static final RegistryObject<WitherproofBlock> WITHERPROOF_BLOCK = register("witherproof_block", WitherproofBlock::new);
    public static final RegistryObject<WitherproofBlock> WITHERPROOF_BRICKS = register("witherproof_bricks", WitherproofBlock::new);
    public static final RegistryObject<WitherproofGlassBlock> WITHERPROOF_GLASS = register("witherproof_glass", WitherproofGlassBlock::new);
    public static final RegistryObject<InfusionPedestalBlock> INFUSION_PEDESTAL = register("infusion_pedestal", InfusionPedestalBlock::new);
    public static final RegistryObject<InfusionAltarBlock> INFUSION_ALTAR = register("infusion_altar", InfusionAltarBlock::new);
    public static final RegistryObject<TinkeringTableBlock> TINKERING_TABLE = register("tinkering_table", TinkeringTableBlock::new);

    @SubscribeEvent
    public void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        ENTRIES.stream().map(Supplier::get).forEach(registry::register);

        CropRegistry.getInstance().allowRegistration();
        CropRegistry.getInstance().onRegisterBlocks(registry);
        CropRegistry.getInstance().denyRegistration();
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        return register(name, block, b -> () -> new BaseBlockItem(b.get(), p -> p.group(ITEM_GROUP)));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, Function<RegistryObject<T>, Supplier<? extends BlockItem>> item) {
        ResourceLocation loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        ENTRIES.add(() -> block.get().setRegistryName(loc));
        RegistryObject<T> reg = RegistryObject.of(loc, ForgeRegistries.BLOCKS);
        ModItems.ENTRIES.add(() -> item.apply(reg).get().setRegistryName(loc));
        return reg;
    }
}
