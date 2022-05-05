package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.tileentity.AwakeningAltarTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.AwakeningPedestalTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.EssenceFurnaceTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.EssenceVesselTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.InfusionAltarTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.InfusionPedestalTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.ReprocessorTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.SoulExtractorTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.TinkeringTableTileEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class ModTileEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MysticalAgriculture.MOD_ID);

    public static final RegistryObject<BlockEntityType<EssenceFurnaceTileEntity.Inferium>> INFERIUM_FURNACE = register("inferium_furnace", EssenceFurnaceTileEntity.Inferium::new, () -> new Block[] { ModBlocks.INFERIUM_FURNACE.get() });
    public static final RegistryObject<BlockEntityType<EssenceFurnaceTileEntity.Prudentium>> PRUDENTIUM_FURNACE = register("prudentium_furnace", EssenceFurnaceTileEntity.Prudentium::new, () -> new Block[] { ModBlocks.PRUDENTIUM_FURNACE.get() });
    public static final RegistryObject<BlockEntityType<EssenceFurnaceTileEntity.Tertium>> TERTIUM_FURNACE = register("tertium_furnace", EssenceFurnaceTileEntity.Tertium::new, () -> new Block[] { ModBlocks.TERTIUM_FURNACE.get() });
    public static final RegistryObject<BlockEntityType<EssenceFurnaceTileEntity.Imperium>> IMPERIUM_FURNACE = register("imperium_furnace", EssenceFurnaceTileEntity.Imperium::new, () -> new Block[] { ModBlocks.IMPERIUM_FURNACE.get() });
    public static final RegistryObject<BlockEntityType<EssenceFurnaceTileEntity.Supremium>> SUPREMIUM_FURNACE = register("supremium_furnace", EssenceFurnaceTileEntity.Supremium::new, () -> new Block[] { ModBlocks.SUPREMIUM_FURNACE.get() });
    public static final RegistryObject<BlockEntityType<EssenceFurnaceTileEntity.AwakenedSupremium>> AWAKENED_SUPREMIUM_FURNACE = register("awakened_supremium_furnace", EssenceFurnaceTileEntity.AwakenedSupremium::new, () -> new Block[] { ModBlocks.AWAKENED_SUPREMIUM_FURNACE.get() });
    public static final RegistryObject<BlockEntityType<InfusionPedestalTileEntity>> INFUSION_PEDESTAL = register("infusion_pedestal", InfusionPedestalTileEntity::new, () -> new Block[] { ModBlocks.INFUSION_PEDESTAL.get() });
    public static final RegistryObject<BlockEntityType<InfusionAltarTileEntity>> INFUSION_ALTAR = register("infusion_altar", InfusionAltarTileEntity::new, () -> new Block[] { ModBlocks.INFUSION_ALTAR.get() });
    public static final RegistryObject<BlockEntityType<AwakeningPedestalTileEntity>> AWAKENING_PEDESTAL = register("awakening_pedestal", AwakeningPedestalTileEntity::new, () -> new Block[] { ModBlocks.AWAKENING_PEDESTAL.get() });
    public static final RegistryObject<BlockEntityType<AwakeningAltarTileEntity>> AWAKENING_ALTAR = register("awakening_altar", AwakeningAltarTileEntity::new, () -> new Block[] { ModBlocks.AWAKENING_ALTAR.get() });
    public static final RegistryObject<BlockEntityType<EssenceVesselTileEntity>> ESSENCE_VESSEL = register("essence_vessel", EssenceVesselTileEntity::new, () -> new Block[] { ModBlocks.ESSENCE_VESSEL.get() });
    public static final RegistryObject<BlockEntityType<TinkeringTableTileEntity>> TINKERING_TABLE = register("tinkering_table", TinkeringTableTileEntity::new, () -> new Block[] { ModBlocks.TINKERING_TABLE.get() });
    public static final RegistryObject<BlockEntityType<ReprocessorTileEntity.Basic>> BASIC_REPROCESSOR = register("basic_reprocessor", ReprocessorTileEntity.Basic::new, () -> new Block[] { ModBlocks.BASIC_REPROCESSOR.get() });
    public static final RegistryObject<BlockEntityType<ReprocessorTileEntity.Inferium>> INFERIUM_REPROCESSOR = register("inferium_reprocessor", ReprocessorTileEntity.Inferium::new, () -> new Block[] { ModBlocks.INFERIUM_REPROCESSOR.get() });
    public static final RegistryObject<BlockEntityType<ReprocessorTileEntity.Prudentium>> PRUDENTIUM_REPROCESSOR = register("prudentium_reprocessor", ReprocessorTileEntity.Prudentium::new, () -> new Block[] { ModBlocks.PRUDENTIUM_REPROCESSOR.get() });
    public static final RegistryObject<BlockEntityType<ReprocessorTileEntity.Tertium>> TERTIUM_REPROCESSOR = register("tertium_reprocessor", ReprocessorTileEntity.Tertium::new, () -> new Block[] { ModBlocks.TERTIUM_REPROCESSOR.get() });
    public static final RegistryObject<BlockEntityType<ReprocessorTileEntity.Imperium>> IMPERIUM_REPROCESSOR = register("imperium_reprocessor", ReprocessorTileEntity.Imperium::new, () -> new Block[] { ModBlocks.IMPERIUM_REPROCESSOR.get() });
    public static final RegistryObject<BlockEntityType<ReprocessorTileEntity.Supremium>> SUPREMIUM_REPROCESSOR = register("supremium_reprocessor", ReprocessorTileEntity.Supremium::new, () -> new Block[] { ModBlocks.SUPREMIUM_REPROCESSOR.get() });
    public static final RegistryObject<BlockEntityType<ReprocessorTileEntity.AwakenedSupremium>> AWAKENED_SUPREMIUM_REPROCESSOR = register("awakened_supremium_processor", ReprocessorTileEntity.AwakenedSupremium::new, () -> new Block[] { ModBlocks.AWAKENED_SUPREMIUM_REPROCESSOR.get() });
    public static final RegistryObject<BlockEntityType<SoulExtractorTileEntity>> SOUL_EXTRACTOR = register("soul_extractor", SoulExtractorTileEntity::new, () -> new Block[] { ModBlocks.SOUL_EXTRACTOR.get() });

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> tile, Supplier<Block[]> blocks) {
        return REGISTRY.register(name, () -> BlockEntityType.Builder.of(tile, blocks.get()).build(null));
    }
}
