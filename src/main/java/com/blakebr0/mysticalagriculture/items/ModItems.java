package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.CropTiers;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.ITEM_GROUP;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MysticalAgriculture.MOD_ID)
public class ModItems {

    public static final List<ItemBlock> ITEM_BLOCKS = new ArrayList<>();

    public static final ItemBase PROSPERITY_SHARD = new ItemBase("prosperity_shard", p -> p.group(ITEM_GROUP));
    public static final ItemEssence INFERIUM_ESSENCE = new ItemEssence("inferium_essence", CropTiers.ONE, p -> p.group(ITEM_GROUP));
    public static final ItemEssence PRUDENTIUM_ESSENCE = new ItemEssence("prudentium_essence", CropTiers.TWO, p -> p.group(ITEM_GROUP));
    public static final ItemEssence INTERMEDIUM_ESSENCE = new ItemEssence("intermedium_essence", CropTiers.THREE, p -> p.group(ITEM_GROUP));
    public static final ItemEssence IMPERIUM_ESSENCE = new ItemEssence("imperium_essence", CropTiers.FOUR, p -> p.group(ITEM_GROUP));
    public static final ItemEssence SUPREMIUM_ESSENCE = new ItemEssence("supremium_essence", CropTiers.FIVE, p -> p.group(ITEM_GROUP));
    public static final ItemBase SOULIUM_DUST = new ItemBase("soulium_dust", p -> p.group(ITEM_GROUP));
    public static final ItemBase PROSPERITY_INGOT = new ItemBase("prosperity_ingot", p -> p.group(ITEM_GROUP));
    public static final ItemBase INFERIUM_INGOT = new ItemBase("inferium_ingot", p -> p.group(ITEM_GROUP));
    public static final ItemBase PRUDENTIUM_INGOT = new ItemBase("prudentium_ingot", p -> p.group(ITEM_GROUP));
    public static final ItemBase INTERMEDIUM_INGOT = new ItemBase("intermedium_ingot", p -> p.group(ITEM_GROUP));
    public static final ItemBase IMPERIUM_INGOT = new ItemBase("imperium_ingot", p -> p.group(ITEM_GROUP));
    public static final ItemBase SUPREMIUM_INGOT = new ItemBase("supremium_ingot", p -> p.group(ITEM_GROUP));
    public static final ItemBase SOULIUM_INGOT = new ItemBase("soulium_ingot", p -> p.group(ITEM_GROUP));
    public static final ItemBase PROSPERITY_NUGGET = new ItemBase("prosperity_nugget", p -> p.group(ITEM_GROUP));
    public static final ItemBase INFERIUM_NUGGET = new ItemBase("inferium_nugget", p -> p.group(ITEM_GROUP));
    public static final ItemBase PRUDENTIUM_NUGGET = new ItemBase("prudentium_nugget", p -> p.group(ITEM_GROUP));
    public static final ItemBase INTERMEDIUM_NUGGET = new ItemBase("intermedium_nugget", p -> p.group(ITEM_GROUP));
    public static final ItemBase IMPERIUM_NUGGET = new ItemBase("imperium_nugget", p -> p.group(ITEM_GROUP));
    public static final ItemBase SUPREMIUM_NUGGET = new ItemBase("supremium_nugget", p -> p.group(ITEM_GROUP));
    public static final ItemBase SOULIUM_NUGGET = new ItemBase("soulium_nugget", p -> p.group(ITEM_GROUP));
    public static final ItemBase PROSPERITY_GEMSTONE = new ItemBase("prosperity_gemstone", p -> p.group(ITEM_GROUP));
    public static final ItemBase INFERIUM_GEMSTONE = new ItemBase("inferium_gemstone", p -> p.group(ITEM_GROUP));
    public static final ItemBase PRUDENTIUM_GEMSTONE = new ItemBase("prudentium_gemstone", p -> p.group(ITEM_GROUP));
    public static final ItemBase INTERMEDIUM_GEMSTONE = new ItemBase("intermedium_gemstone", p -> p.group(ITEM_GROUP));
    public static final ItemBase IMPERIUM_GEMSTONE = new ItemBase("imperium_gemstone", p -> p.group(ITEM_GROUP));
    public static final ItemBase SUPREMIUM_GEMSTONE = new ItemBase("supremium_gemstone", p -> p.group(ITEM_GROUP));
    public static final ItemBase SOULIUM_GEMSTONE = new ItemBase("soulium_gemstone", p -> p.group(ITEM_GROUP));
    public static final ItemBase PROSPERITY_SEED_BASE = new ItemBase("prosperity_seed_base", p -> p.group(ITEM_GROUP));
    public static final ItemBase SOULIUM_SEED_BASE = new ItemBase("soulium_seed_base", p -> p.group(ITEM_GROUP));
    public static final ItemBase SOUL_DUST = new ItemBase("soul_dust", p -> p.group(ITEM_GROUP));
    public static final ItemInfusionCrystal INFUSION_CRYSTAL = new ItemInfusionCrystal("infusion_crystal", 1000, p -> p.group(ITEM_GROUP));
    public static final ItemMasterInfusionCrystal MASTER_INFUSION_CRYSTAL = new ItemMasterInfusionCrystal("master_infusion_crystal", p -> p.group(ITEM_GROUP));

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        ITEM_BLOCKS.forEach(registry::register);

        registry.register(PROSPERITY_SHARD);
        registry.register(INFERIUM_ESSENCE);
        registry.register(PRUDENTIUM_ESSENCE);
        registry.register(INTERMEDIUM_ESSENCE);
        registry.register(IMPERIUM_ESSENCE);
        registry.register(SUPREMIUM_ESSENCE);
        registry.register(SOULIUM_DUST);
        registry.register(PROSPERITY_INGOT);
        registry.register(INFERIUM_INGOT);
        registry.register(INTERMEDIUM_INGOT);
        registry.register(PRUDENTIUM_INGOT);
        registry.register(IMPERIUM_INGOT);
        registry.register(SUPREMIUM_INGOT);
        registry.register(SOULIUM_INGOT);
        registry.register(PROSPERITY_NUGGET);
        registry.register(INFERIUM_NUGGET);
        registry.register(PRUDENTIUM_NUGGET);
        registry.register(INTERMEDIUM_NUGGET);
        registry.register(IMPERIUM_NUGGET);
        registry.register(SUPREMIUM_NUGGET);
        registry.register(SOULIUM_NUGGET);
        registry.register(PROSPERITY_GEMSTONE);
        registry.register(INFERIUM_GEMSTONE);
        registry.register(PRUDENTIUM_GEMSTONE);
        registry.register(INTERMEDIUM_GEMSTONE);
        registry.register(IMPERIUM_GEMSTONE);
        registry.register(SUPREMIUM_GEMSTONE);
        registry.register(SOULIUM_GEMSTONE);
        registry.register(PROSPERITY_SEED_BASE);
        registry.register(SOULIUM_SEED_BASE);
        registry.register(SOUL_DUST);
        registry.register(INFUSION_CRYSTAL);
        registry.register(MASTER_INFUSION_CRYSTAL);

        CropRegistry.getInstance().onRegisterItems(registry);
    }
}
