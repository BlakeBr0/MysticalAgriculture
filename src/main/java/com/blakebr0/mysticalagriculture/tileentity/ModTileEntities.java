package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.client.tesr.InfusionAltarRenderer;
import com.blakebr0.mysticalagriculture.client.tesr.InfusionPedestalRenderer;
import com.blakebr0.mysticalagriculture.client.tesr.TinkeringTableRenderer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModTileEntities {
    public static final TileEntityType<InfusionPedestalTileEntity> INFUSION_PEDESTAL = TileEntityType.Builder.create(InfusionPedestalTileEntity::new, ModBlocks.INFUSION_PEDESTAL).build(null);
    public static final TileEntityType<InfusionAltarTileEntity> INFUSION_ALTAR = TileEntityType.Builder.create(InfusionAltarTileEntity::new, ModBlocks.INFUSION_ALTAR).build(null);
    public static final TileEntityType<TinkeringTableTileEntity> TINKERING_TABLE = TileEntityType.Builder.create(TinkeringTableTileEntity::new, ModBlocks.TINKERING_TABLE).build(null);

    @SubscribeEvent
    public void onRegisterTypes(RegistryEvent.Register<TileEntityType<?>> event) {
        IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();

        registry.register(INFUSION_PEDESTAL.setRegistryName("infusion_pedestal"));
        registry.register(INFUSION_ALTAR.setRegistryName("infusion_altar"));
        registry.register(TINKERING_TABLE.setRegistryName("tinkering_table"));
    }

    public static void onClientSetup() {
        ClientRegistry.bindTileEntitySpecialRenderer(InfusionPedestalTileEntity.class, new InfusionPedestalRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(InfusionAltarTileEntity.class, new InfusionAltarRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TinkeringTableTileEntity.class, new TinkeringTableRenderer());
    }
}
