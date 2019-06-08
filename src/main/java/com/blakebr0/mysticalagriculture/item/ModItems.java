package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.api.crop.CropTiers;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.ITEM_GROUP;

public class ModItems {
    public static final List<BlockItem> ITEM_BLOCKS = new ArrayList<>();

    public static final BaseItem PROSPERITY_SHARD = new BaseItem("prosperity_shard", p -> p.group(ITEM_GROUP));
    public static final EssenceItem INFERIUM_ESSENCE = new EssenceItem("inferium_essence", CropTiers.ONE, p -> p.group(ITEM_GROUP));
    public static final EssenceItem PRUDENTIUM_ESSENCE = new EssenceItem("prudentium_essence", CropTiers.TWO, p -> p.group(ITEM_GROUP));
    public static final EssenceItem INTERMEDIUM_ESSENCE = new EssenceItem("intermedium_essence", CropTiers.THREE, p -> p.group(ITEM_GROUP));
    public static final EssenceItem IMPERIUM_ESSENCE = new EssenceItem("imperium_essence", CropTiers.FOUR, p -> p.group(ITEM_GROUP));
    public static final EssenceItem SUPREMIUM_ESSENCE = new EssenceItem("supremium_essence", CropTiers.FIVE, p -> p.group(ITEM_GROUP));
    public static final BaseItem SOULIUM_DUST = new BaseItem("soulium_dust", p -> p.group(ITEM_GROUP));
    public static final BaseItem PROSPERITY_INGOT = new BaseItem("prosperity_ingot", p -> p.group(ITEM_GROUP));
    public static final BaseItem INFERIUM_INGOT = new BaseItem("inferium_ingot", p -> p.group(ITEM_GROUP));
    public static final BaseItem PRUDENTIUM_INGOT = new BaseItem("prudentium_ingot", p -> p.group(ITEM_GROUP));
    public static final BaseItem INTERMEDIUM_INGOT = new BaseItem("intermedium_ingot", p -> p.group(ITEM_GROUP));
    public static final BaseItem IMPERIUM_INGOT = new BaseItem("imperium_ingot", p -> p.group(ITEM_GROUP));
    public static final BaseItem SUPREMIUM_INGOT = new BaseItem("supremium_ingot", p -> p.group(ITEM_GROUP));
    public static final BaseItem SOULIUM_INGOT = new BaseItem("soulium_ingot", p -> p.group(ITEM_GROUP));
    public static final BaseItem PROSPERITY_NUGGET = new BaseItem("prosperity_nugget", p -> p.group(ITEM_GROUP));
    public static final BaseItem INFERIUM_NUGGET = new BaseItem("inferium_nugget", p -> p.group(ITEM_GROUP));
    public static final BaseItem PRUDENTIUM_NUGGET = new BaseItem("prudentium_nugget", p -> p.group(ITEM_GROUP));
    public static final BaseItem INTERMEDIUM_NUGGET = new BaseItem("intermedium_nugget", p -> p.group(ITEM_GROUP));
    public static final BaseItem IMPERIUM_NUGGET = new BaseItem("imperium_nugget", p -> p.group(ITEM_GROUP));
    public static final BaseItem SUPREMIUM_NUGGET = new BaseItem("supremium_nugget", p -> p.group(ITEM_GROUP));
    public static final BaseItem SOULIUM_NUGGET = new BaseItem("soulium_nugget", p -> p.group(ITEM_GROUP));
    public static final BaseItem PROSPERITY_GEMSTONE = new BaseItem("prosperity_gemstone", p -> p.group(ITEM_GROUP));
    public static final BaseItem INFERIUM_GEMSTONE = new BaseItem("inferium_gemstone", p -> p.group(ITEM_GROUP));
    public static final BaseItem PRUDENTIUM_GEMSTONE = new BaseItem("prudentium_gemstone", p -> p.group(ITEM_GROUP));
    public static final BaseItem INTERMEDIUM_GEMSTONE = new BaseItem("intermedium_gemstone", p -> p.group(ITEM_GROUP));
    public static final BaseItem IMPERIUM_GEMSTONE = new BaseItem("imperium_gemstone", p -> p.group(ITEM_GROUP));
    public static final BaseItem SUPREMIUM_GEMSTONE = new BaseItem("supremium_gemstone", p -> p.group(ITEM_GROUP));
    public static final BaseItem SOULIUM_GEMSTONE = new BaseItem("soulium_gemstone", p -> p.group(ITEM_GROUP));
    public static final BaseItem PROSPERITY_SEED_BASE = new BaseItem("prosperity_seed_base", p -> p.group(ITEM_GROUP));
    public static final BaseItem SOULIUM_SEED_BASE = new BaseItem("soulium_seed_base", p -> p.group(ITEM_GROUP));
    public static final BaseItem SOUL_DUST = new BaseItem("soul_dust", p -> p.group(ITEM_GROUP));
    public static final InfusionCrystalItem INFUSION_CRYSTAL = new InfusionCrystalItem("infusion_crystal", 1000, p -> p.group(ITEM_GROUP));
    public static final MasterInfusionCrystalItem MASTER_INFUSION_CRYSTAL = new MasterInfusionCrystalItem("master_infusion_crystal", p -> p.group(ITEM_GROUP));
    public static final FertilizedEssenceItem FERTILIZED_ESSENCE = new FertilizedEssenceItem("fertilized_essence", p -> p.group(ITEM_GROUP));
    public static final MysticalFertilizerItem MYSTICAL_FERTILIZER = new MysticalFertilizerItem("mystical_fertilizer", p -> p.group(ITEM_GROUP));

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
        registry.register(FERTILIZED_ESSENCE);
        registry.register(MYSTICAL_FERTILIZER);

        CropRegistry.getInstance().onRegisterItems(registry);
    }
}
