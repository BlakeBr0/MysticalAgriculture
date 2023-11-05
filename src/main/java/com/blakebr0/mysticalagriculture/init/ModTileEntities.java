package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.tileentity.AwakeningAltarTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.AwakeningPedestalTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.EnchanterTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.EssenceFurnaceTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.EssenceVesselTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.HarvesterTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.InfusionAltarTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.InfusionPedestalTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.ReprocessorTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.SoulExtractorTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.SouliumSpawnerTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.TinkeringTableTileEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class ModTileEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MysticalAgriculture.MOD_ID);

    public static final RegistryObject<BlockEntityType<InfusionPedestalTileEntity>> INFUSION_PEDESTAL = register("infusion_pedestal", InfusionPedestalTileEntity::new, () -> new Block[] { ModBlocks.INFUSION_PEDESTAL.get() });
    public static final RegistryObject<BlockEntityType<InfusionAltarTileEntity>> INFUSION_ALTAR = register("infusion_altar", InfusionAltarTileEntity::new, () -> new Block[] { ModBlocks.INFUSION_ALTAR.get() });
    public static final RegistryObject<BlockEntityType<AwakeningPedestalTileEntity>> AWAKENING_PEDESTAL = register("awakening_pedestal", AwakeningPedestalTileEntity::new, () -> new Block[] { ModBlocks.AWAKENING_PEDESTAL.get() });
    public static final RegistryObject<BlockEntityType<AwakeningAltarTileEntity>> AWAKENING_ALTAR = register("awakening_altar", AwakeningAltarTileEntity::new, () -> new Block[] { ModBlocks.AWAKENING_ALTAR.get() });
    public static final RegistryObject<BlockEntityType<EssenceVesselTileEntity>> ESSENCE_VESSEL = register("essence_vessel", EssenceVesselTileEntity::new, () -> new Block[] { ModBlocks.ESSENCE_VESSEL.get() });
    public static final RegistryObject<BlockEntityType<TinkeringTableTileEntity>> TINKERING_TABLE = register("tinkering_table", TinkeringTableTileEntity::new, () -> new Block[] { ModBlocks.TINKERING_TABLE.get() });
    public static final RegistryObject<BlockEntityType<EnchanterTileEntity>> ENCHANTER = register("enchanter", EnchanterTileEntity::new, () -> new Block[] { ModBlocks.ENCHANTER.get() });
    public static final RegistryObject<BlockEntityType<EssenceFurnaceTileEntity>> FURNACE = register("furnace", EssenceFurnaceTileEntity::new, () -> new Block[] { ModBlocks.FURNACE.get() });
    public static final RegistryObject<BlockEntityType<ReprocessorTileEntity>> REPROCESSOR = register("seed_reprocessor", ReprocessorTileEntity::new, () -> new Block[] { ModBlocks.REPROCESSOR.get() });
    public static final RegistryObject<BlockEntityType<SoulExtractorTileEntity>> SOUL_EXTRACTOR = register("soul_extractor", SoulExtractorTileEntity::new, () -> new Block[] { ModBlocks.SOUL_EXTRACTOR.get() });
    public static final RegistryObject<BlockEntityType<HarvesterTileEntity>> HARVESTER = register("harvester", HarvesterTileEntity::new, () -> new Block[] { ModBlocks.HARVESTER.get() });
    public static final RegistryObject<BlockEntityType<SouliumSpawnerTileEntity>> SOULIUM_SPAWNER = register("soulium_spawner", SouliumSpawnerTileEntity::new, () -> new Block[] { ModBlocks.SOULIUM_SPAWNER.get() });

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> tile, Supplier<Block[]> blocks) {
        return REGISTRY.register(name, () -> BlockEntityType.Builder.of(tile, blocks.get()).build(null));
    }
}
