package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.client.tesr.InfusionAltarRenderer;
import com.blakebr0.mysticalagriculture.client.tesr.InfusionPedestalRenderer;
import com.blakebr0.mysticalagriculture.client.tesr.TinkeringTableRenderer;
import com.blakebr0.mysticalagriculture.tileentity.furnace.ImperiumFurnaceTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.furnace.InferiumFurnaceTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TertiumFurnaceTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.furnace.PrudentiumFurnaceTileEntity;
import com.blakebr0.mysticalagriculture.tileentity.furnace.SupremiumFurnaceTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ModTileEntities {
    private static final List<Supplier<TileEntityType<?>>> ENTRIES = new ArrayList<>();

    public static final RegistryObject<TileEntityType<InferiumFurnaceTileEntity>> INFERIUM_FURNACE = register("inferium_furnace", InferiumFurnaceTileEntity::new, () -> new Block[] { ModBlocks.INFERIUM_FURNACE.get() });
    public static final RegistryObject<TileEntityType<PrudentiumFurnaceTileEntity>> PRUDENTIUM_FURNACE = register("prudentium_furnace", PrudentiumFurnaceTileEntity::new, () -> new Block[] { ModBlocks.PRUDENTIUM_FURNACE.get() });
    public static final RegistryObject<TileEntityType<TertiumFurnaceTileEntity>> TERTIUM_FURNACE = register("tertium_furnace", TertiumFurnaceTileEntity::new, () -> new Block[] { ModBlocks.TERTIUM_FURNACE.get() });
    public static final RegistryObject<TileEntityType<ImperiumFurnaceTileEntity>> IMPERIUM_FURNACE = register("imperium_furnace", ImperiumFurnaceTileEntity::new, () -> new Block[] { ModBlocks.IMPERIUM_FURNACE.get() });
    public static final RegistryObject<TileEntityType<SupremiumFurnaceTileEntity>> SUPREMIUM_FURNACE = register("supremium_furnace", SupremiumFurnaceTileEntity::new, () -> new Block[] { ModBlocks.SUPREMIUM_FURNACE.get() });
    public static final RegistryObject<TileEntityType<InfusionPedestalTileEntity>> INFUSION_PEDESTAL = register("infusion_pedestal", InfusionPedestalTileEntity::new, () -> new Block[] { ModBlocks.INFUSION_PEDESTAL.get() });
    public static final RegistryObject<TileEntityType<InfusionAltarTileEntity>> INFUSION_ALTAR = register("infusion_altar", InfusionAltarTileEntity::new, () -> new Block[] { ModBlocks.INFUSION_ALTAR.get() });
    public static final RegistryObject<TileEntityType<TinkeringTableTileEntity>> TINKERING_TABLE = register("tinkering_table", TinkeringTableTileEntity::new, () -> new Block[] { ModBlocks.TINKERING_TABLE.get() });

    @SubscribeEvent
    public void onRegisterTypes(RegistryEvent.Register<TileEntityType<?>> event) {
        IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();

        ENTRIES.stream().map(Supplier::get).forEach(registry::register);
    }

    public static void onClientSetup() {
        ClientRegistry.bindTileEntitySpecialRenderer(InfusionPedestalTileEntity.class, new InfusionPedestalRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(InfusionAltarTileEntity.class, new InfusionAltarRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TinkeringTableTileEntity.class, new TinkeringTableRenderer());
    }

    private static <T extends TileEntityType<?>> RegistryObject<T> register(String name, Supplier<TileEntity> tile, Supplier<Block[]> blocks) {
        ResourceLocation loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        ENTRIES.add(() -> TileEntityType.Builder.create(tile, blocks.get()).build(null).setRegistryName(loc));
        return RegistryObject.of(loc, ForgeRegistries.TILE_ENTITIES);
    }
}
