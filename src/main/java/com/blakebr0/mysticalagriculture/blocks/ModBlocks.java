package com.blakebr0.mysticalagriculture.blocks;

import com.blakebr0.cucumber.block.BlockBase;
import com.blakebr0.cucumber.item.ItemBlockBase;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.ITEM_GROUP;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MysticalAgriculture.MOD_ID)
public class ModBlocks {

    public static final BlockBase PROSPERITY_BLOCK = new BlockBase("prosperity_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BlockBase PROSPERITY_INGOT_BLOCK = new BlockBase("prosperity_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BlockBase PROSPERITY_GEMSTONE_BLOCK = new BlockBase("prosperity_gemstone_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BlockBase INFERIUM_BLOCK = new BlockBase("inferium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BlockBase INFERIUM_INGOT_BLOCK = new BlockBase("inferium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BlockBase INFERIUM_GEMSTONE_BLOCK = new BlockBase("inferium_gemstone_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BlockInfusedFarmland INFERIUM_FARMLAND = new BlockInfusedFarmland("inferium_farmland", 0x748E00);
    public static final BlockBase PRUDENTIUM_BLOCK = new BlockBase("prudentium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BlockBase PRUDENTIUM_INGOT_BLOCK = new BlockBase("prudentium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BlockBase PRUDENTIUM_GEMSTONE_BLOCK = new BlockBase("prudentium_gemstone_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BlockInfusedFarmland PRUDENTIUM_FARMLAND = new BlockInfusedFarmland("prudentium_farmland", 0x008C23);
    public static final BlockBase INTERMEDIUM_BLOCK = new BlockBase("intermedium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BlockBase INTERMEDIUM_INGOT_BLOCK = new BlockBase("intermedium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BlockBase INTERMEDIUM_GEMSTONE_BLOCK = new BlockBase("intermedium_gemstone_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BlockInfusedFarmland INTERMEDIUM_FARMLAND = new BlockInfusedFarmland("intermedium_farmland", 0xB74900);
    public static final BlockBase IMPERIUM_BLOCK = new BlockBase("imperium_block", Material.ROCK, SoundType.STONE, 4.0F, 5.0F);
    public static final BlockBase IMPERIUM_INGOT_BLOCK = new BlockBase("imperium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BlockBase IMPERIUM_GEMSTONE_BLOCK = new BlockBase("imperium_gemstone_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BlockInfusedFarmland IMPERIUM_FARMLAND = new BlockInfusedFarmland("imperium_farmland", 0x007FDB);
    public static final BlockBase SUPREMIUM_BLOCK = new BlockBase("supremium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BlockBase SUPREMIUM_INGOT_BLOCK = new BlockBase("supremium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BlockBase SUPREMIUM_GEMSTONE_BLOCK = new BlockBase("supremium_gemstone_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BlockInfusedFarmland SUPREMIUM_FARMLAND = new BlockInfusedFarmland("supremium_farmland", 0xC40000);
    public static final BlockBase SOULIUM_BLOCK = new BlockBase("soulium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
    public static final BlockBase SOULIUM_INGOT_BLOCK = new BlockBase("soulium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BlockBase SOULIUM_GEMSTONE_BLOCK = new BlockBase("soulium_gemstone_block", Material.IRON, SoundType.METAL, 5.0F, 6.0F);
    public static final BlockBase SOULSTONE = new BlockBase("soulstone", Material.ROCK, SoundType.STONE, 1.5F, 6.0F);
    public static final BlockBase SOULSTONE_COBBLE = new BlockBase("soulstone_cobble", Material.ROCK, SoundType.STONE, 2.0F, 6.0F);
    public static final BlockBase SOULSTONE_BRICKS = new BlockBase("soulstone_bricks", Material.ROCK, SoundType.STONE, 1.5F, 6.0F);
    public static final BlockBase SOULSTONE_CRACKED_BRICKS = new BlockBase("soulstone_cracked_bricks", Material.ROCK, SoundType.STONE, 1.5F, 6.0F);
    public static final BlockBase SOULSTONE_CHISELED_BRICKS = new BlockBase("soulstone_chiseled_bricks", Material.ROCK, SoundType.STONE, 1.5F, 6.0F);
    public static final BlockWitherproof WITHERPROOF_BLOCK = new BlockWitherproof("witherproof_block", Material.ROCK, SoundType.STONE, 20.0F, 2000.0F);
    public static final BlockWitherproof WITHERPROOF_BRICKS = new BlockWitherproof("witherproof_bricks", Material.ROCK, SoundType.STONE, 20.0F, 2000.0F);

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        register(registry, PROSPERITY_BLOCK);
        register(registry, PROSPERITY_INGOT_BLOCK);
        register(registry, PROSPERITY_GEMSTONE_BLOCK);
        register(registry, INFERIUM_BLOCK);
        register(registry, INFERIUM_INGOT_BLOCK);
        register(registry, INFERIUM_GEMSTONE_BLOCK);
        register(registry, INFERIUM_FARMLAND);
        register(registry, PRUDENTIUM_BLOCK);
        register(registry, PRUDENTIUM_INGOT_BLOCK);
        register(registry, PRUDENTIUM_GEMSTONE_BLOCK);
        register(registry, PRUDENTIUM_FARMLAND);
        register(registry, INTERMEDIUM_BLOCK);
        register(registry, INTERMEDIUM_INGOT_BLOCK);
        register(registry, INTERMEDIUM_GEMSTONE_BLOCK);
        register(registry, INTERMEDIUM_FARMLAND);
        register(registry, IMPERIUM_BLOCK);
        register(registry, IMPERIUM_INGOT_BLOCK);
        register(registry, IMPERIUM_GEMSTONE_BLOCK);
        register(registry, IMPERIUM_FARMLAND);
        register(registry, SUPREMIUM_BLOCK);
        register(registry, SUPREMIUM_INGOT_BLOCK);
        register(registry, SUPREMIUM_GEMSTONE_BLOCK);
        register(registry, SUPREMIUM_FARMLAND);
        register(registry, SOULIUM_BLOCK);
        register(registry, SOULIUM_INGOT_BLOCK);
        register(registry, SOULIUM_GEMSTONE_BLOCK);
        register(registry, SOULSTONE);
        register(registry, SOULSTONE_COBBLE);
        register(registry, SOULSTONE_BRICKS);
        register(registry, SOULSTONE_CRACKED_BRICKS);
        register(registry, SOULSTONE_CHISELED_BRICKS);
        register(registry, WITHERPROOF_BLOCK);
        register(registry, WITHERPROOF_BRICKS);

        CropRegistry cropRegistry = CropRegistry.getInstance();
        cropRegistry.allowRegistration();
        cropRegistry.onRegisterBlocks(registry);
        cropRegistry.denyRegistration();
    }

    private static void register(IForgeRegistry<Block> registry, Block block) {
        ItemBlockBase itemBlock = new ItemBlockBase(block, p -> p.group(ITEM_GROUP));
        register(registry, block, itemBlock);
    }

    private static void register(IForgeRegistry<Block> registry, Block block, ItemBlock itemBlock) {
        registry.register(block);
        ModItems.ITEM_BLOCKS.add(itemBlock);
    }
}
