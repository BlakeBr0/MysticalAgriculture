package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.client.tesr.InfusionAltarRenderer;
import com.blakebr0.mysticalagriculture.client.tesr.InfusionPedestalRenderer;
import com.blakebr0.mysticalagriculture.client.tesr.TinkeringTableRenderer;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModTileEntities {
    private static final List<Supplier<TileEntityType<?>>> ENTRIES = new ArrayList<>();

    public static final RegistryObject<TileEntityType<EssenceFurnaceTileEntity.Inferium>> INFERIUM_FURNACE = register("inferium_furnace", EssenceFurnaceTileEntity.Inferium::new, () -> new Block[] { ModBlocks.INFERIUM_FURNACE.get() });
    public static final RegistryObject<TileEntityType<EssenceFurnaceTileEntity.Prudentium>> PRUDENTIUM_FURNACE = register("prudentium_furnace", EssenceFurnaceTileEntity.Prudentium::new, () -> new Block[] { ModBlocks.PRUDENTIUM_FURNACE.get() });
    public static final RegistryObject<TileEntityType<EssenceFurnaceTileEntity.Tertium>> TERTIUM_FURNACE = register("tertium_furnace", EssenceFurnaceTileEntity.Tertium::new, () -> new Block[] { ModBlocks.TERTIUM_FURNACE.get() });
    public static final RegistryObject<TileEntityType<EssenceFurnaceTileEntity.Imperium>> IMPERIUM_FURNACE = register("imperium_furnace", EssenceFurnaceTileEntity.Imperium::new, () -> new Block[] { ModBlocks.IMPERIUM_FURNACE.get() });
    public static final RegistryObject<TileEntityType<EssenceFurnaceTileEntity.Supremium>> SUPREMIUM_FURNACE = register("supremium_furnace", EssenceFurnaceTileEntity.Supremium::new, () -> new Block[] { ModBlocks.SUPREMIUM_FURNACE.get() });
    public static final RegistryObject<TileEntityType<InfusionPedestalTileEntity>> INFUSION_PEDESTAL = register("infusion_pedestal", InfusionPedestalTileEntity::new, () -> new Block[] { ModBlocks.INFUSION_PEDESTAL.get() });
    public static final RegistryObject<TileEntityType<InfusionAltarTileEntity>> INFUSION_ALTAR = register("infusion_altar", InfusionAltarTileEntity::new, () -> new Block[] { ModBlocks.INFUSION_ALTAR.get() });
    public static final RegistryObject<TileEntityType<TinkeringTableTileEntity>> TINKERING_TABLE = register("tinkering_table", TinkeringTableTileEntity::new, () -> new Block[] { ModBlocks.TINKERING_TABLE.get() });
    public static final RegistryObject<TileEntityType<ReprocessorTileEntity.Basic>> BASIC_REPROCESSOR = register("basic_reprocessor", ReprocessorTileEntity.Basic::new, () -> new Block[] { ModBlocks.BASIC_REPROCESSOR.get() });
    public static final RegistryObject<TileEntityType<ReprocessorTileEntity.Inferium>> INFERIUM_REPROCESSOR = register("inferium_reprocessor", ReprocessorTileEntity.Inferium::new, () -> new Block[] { ModBlocks.INFERIUM_REPROCESSOR.get() });
    public static final RegistryObject<TileEntityType<ReprocessorTileEntity.Prudentium>> PRUDENTIUM_REPROCESSOR = register("prudentium_reprocessor", ReprocessorTileEntity.Prudentium::new, () -> new Block[] { ModBlocks.PRUDENTIUM_REPROCESSOR.get() });
    public static final RegistryObject<TileEntityType<ReprocessorTileEntity.Tertium>> TERTIUM_REPROCESSOR = register("tertium_reprocessor", ReprocessorTileEntity.Tertium::new, () -> new Block[] { ModBlocks.TERTIUM_REPROCESSOR.get() });
    public static final RegistryObject<TileEntityType<ReprocessorTileEntity.Imperium>> IMPERIUM_REPROCESSOR = register("imperium_reprocessor", ReprocessorTileEntity.Imperium::new, () -> new Block[] { ModBlocks.IMPERIUM_REPROCESSOR.get() });
    public static final RegistryObject<TileEntityType<ReprocessorTileEntity.Supremium>> SUPREMIUM_REPROCESSOR = register("supremium_reprocessor", ReprocessorTileEntity.Supremium::new, () -> new Block[] { ModBlocks.SUPREMIUM_REPROCESSOR.get() });

    @SubscribeEvent
    public void onRegisterTypes(RegistryEvent.Register<TileEntityType<?>> event) {
        IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();

        ENTRIES.stream().map(Supplier::get).forEach(registry::register);
    }

    @OnlyIn(Dist.CLIENT)
    public static void onClientSetup() {
        ClientRegistry.bindTileEntityRenderer(INFUSION_PEDESTAL.get(), InfusionPedestalRenderer::new);
        ClientRegistry.bindTileEntityRenderer(INFUSION_ALTAR.get(), InfusionAltarRenderer::new);
        ClientRegistry.bindTileEntityRenderer(TINKERING_TABLE.get(), TinkeringTableRenderer::new);
    }

    private static <T extends TileEntityType<?>> RegistryObject<T> register(String name, Supplier<TileEntity> tile, Supplier<Block[]> blocks) {
        ResourceLocation loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        ENTRIES.add(() -> TileEntityType.Builder.create(tile, blocks.get()).build(null).setRegistryName(loc));
        return RegistryObject.of(loc, ForgeRegistries.TILE_ENTITIES);
    }
}
