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

    public static final BaseItem PROSPERITY_SHARD = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final EssenceItem INFERIUM_ESSENCE = new EssenceItem(CropTiers.ONE, p -> p.group(ITEM_GROUP));
    public static final EssenceItem PRUDENTIUM_ESSENCE = new EssenceItem(CropTiers.TWO, p -> p.group(ITEM_GROUP));
    public static final EssenceItem INTERMEDIUM_ESSENCE = new EssenceItem(CropTiers.THREE, p -> p.group(ITEM_GROUP));
    public static final EssenceItem IMPERIUM_ESSENCE = new EssenceItem(CropTiers.FOUR, p -> p.group(ITEM_GROUP));
    public static final EssenceItem SUPREMIUM_ESSENCE = new EssenceItem(CropTiers.FIVE, p -> p.group(ITEM_GROUP));
    public static final BaseItem SOULIUM_DUST = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem PROSPERITY_INGOT = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem INFERIUM_INGOT = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem PRUDENTIUM_INGOT = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem INTERMEDIUM_INGOT = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem IMPERIUM_INGOT = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem SUPREMIUM_INGOT = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem SOULIUM_INGOT = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem PROSPERITY_NUGGET = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem INFERIUM_NUGGET = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem PRUDENTIUM_NUGGET = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem INTERMEDIUM_NUGGET = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem IMPERIUM_NUGGET = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem SUPREMIUM_NUGGET = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem SOULIUM_NUGGET = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem PROSPERITY_GEMSTONE = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem INFERIUM_GEMSTONE = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem PRUDENTIUM_GEMSTONE = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem INTERMEDIUM_GEMSTONE = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem IMPERIUM_GEMSTONE = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem SUPREMIUM_GEMSTONE = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem SOULIUM_GEMSTONE = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem PROSPERITY_SEED_BASE = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem SOULIUM_SEED_BASE = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final BaseItem SOUL_DUST = new BaseItem(p -> p.group(ITEM_GROUP));
    public static final InfusionCrystalItem INFUSION_CRYSTAL = new InfusionCrystalItem(1000, p -> p.group(ITEM_GROUP));
    public static final MasterInfusionCrystalItem MASTER_INFUSION_CRYSTAL = new MasterInfusionCrystalItem(p -> p.group(ITEM_GROUP));
    public static final FertilizedEssenceItem FERTILIZED_ESSENCE = new FertilizedEssenceItem(p -> p.group(ITEM_GROUP));
    public static final MysticalFertilizerItem MYSTICAL_FERTILIZER = new MysticalFertilizerItem(p -> p.group(ITEM_GROUP));

    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        ITEM_BLOCKS.forEach(registry::register);

        registry.register(PROSPERITY_SHARD.setRegistryName("prosperity_shard"));
        registry.register(INFERIUM_ESSENCE.setRegistryName("inferium_essence"));
        registry.register(PRUDENTIUM_ESSENCE.setRegistryName("prudentium_essence"));
        registry.register(INTERMEDIUM_ESSENCE.setRegistryName("intermedium_essence"));
        registry.register(IMPERIUM_ESSENCE.setRegistryName("imperium_essence"));
        registry.register(SUPREMIUM_ESSENCE.setRegistryName("supremium_essence"));
        registry.register(SOULIUM_DUST.setRegistryName("soulium_dust"));
        registry.register(PROSPERITY_INGOT.setRegistryName("prosperity_ingot"));
        registry.register(INFERIUM_INGOT.setRegistryName("inferium_ingot"));
        registry.register(INTERMEDIUM_INGOT.setRegistryName("intermedium_ingot"));
        registry.register(PRUDENTIUM_INGOT.setRegistryName("prudentium_ingot"));
        registry.register(IMPERIUM_INGOT.setRegistryName("imperium_ingot"));
        registry.register(SUPREMIUM_INGOT.setRegistryName("supremium_ingot"));
        registry.register(SOULIUM_INGOT.setRegistryName("soulium_ingot"));
        registry.register(PROSPERITY_NUGGET.setRegistryName("prosperity_nugget"));
        registry.register(INFERIUM_NUGGET.setRegistryName("inferium_nugget"));
        registry.register(PRUDENTIUM_NUGGET.setRegistryName("prudentium_nugget"));
        registry.register(INTERMEDIUM_NUGGET.setRegistryName("intermedium_nugget"));
        registry.register(IMPERIUM_NUGGET.setRegistryName("imperium_nugget"));
        registry.register(SUPREMIUM_NUGGET.setRegistryName("supremium_nugget"));
        registry.register(SOULIUM_NUGGET.setRegistryName("soulium_nugget"));
        registry.register(PROSPERITY_GEMSTONE.setRegistryName("prosperity_gemstone"));
        registry.register(INFERIUM_GEMSTONE.setRegistryName("inferium_gemstone"));
        registry.register(PRUDENTIUM_GEMSTONE.setRegistryName("prudentium_gemstone"));
        registry.register(INTERMEDIUM_GEMSTONE.setRegistryName("intermedium_gemstone"));
        registry.register(IMPERIUM_GEMSTONE.setRegistryName("imperium_gemstone"));
        registry.register(SUPREMIUM_GEMSTONE.setRegistryName("supremium_gemstone"));
        registry.register(SOULIUM_GEMSTONE.setRegistryName("soulium_gemstone"));
        registry.register(PROSPERITY_SEED_BASE.setRegistryName("prosperity_seed_base"));
        registry.register(SOULIUM_SEED_BASE.setRegistryName("soulium_seed_base"));
        registry.register(SOUL_DUST.setRegistryName("soul_dust"));
        registry.register(INFUSION_CRYSTAL.setRegistryName("infusion_crystal"));
        registry.register(MASTER_INFUSION_CRYSTAL.setRegistryName("master_infusion_crystal"));
        registry.register(FERTILIZED_ESSENCE.setRegistryName("fertilized_essence"));
        registry.register(MYSTICAL_FERTILIZER.setRegistryName("mystical_fertilizer"));

        CropRegistry.getInstance().onRegisterItems(registry);
    }
}
