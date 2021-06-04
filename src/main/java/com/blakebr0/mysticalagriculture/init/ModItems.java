package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.item.EssenceItem;
import com.blakebr0.mysticalagriculture.item.EssenceWateringCanItem;
import com.blakebr0.mysticalagriculture.item.ExperienceCapsuleItem;
import com.blakebr0.mysticalagriculture.item.ExperienceDropletItem;
import com.blakebr0.mysticalagriculture.item.FertilizedEssenceItem;
import com.blakebr0.mysticalagriculture.item.InfusionCrystalItem;
import com.blakebr0.mysticalagriculture.item.MasterInfusionCrystalItem;
import com.blakebr0.mysticalagriculture.item.MysticalFertilizerItem;
import com.blakebr0.mysticalagriculture.item.SoulJarItem;
import com.blakebr0.mysticalagriculture.item.SouliumDaggerItem;
import com.blakebr0.mysticalagriculture.item.WateringCanItem;
import com.blakebr0.mysticalagriculture.item.armor.EssenceBootsItem;
import com.blakebr0.mysticalagriculture.item.armor.EssenceChestplateItem;
import com.blakebr0.mysticalagriculture.item.armor.EssenceHelmetItem;
import com.blakebr0.mysticalagriculture.item.armor.EssenceLeggingsItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceAxeItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceHoeItem;
import com.blakebr0.mysticalagriculture.item.tool.EssencePickaxeItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceShovelItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceStaffItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceSwordItem;
import com.blakebr0.mysticalagriculture.lib.ModArmorMaterial;
import com.blakebr0.mysticalagriculture.lib.ModItemTier;
import com.blakebr0.mysticalagriculture.registry.AugmentRegistry;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.ITEM_GROUP;

public final class ModItems {
    public static final List<Supplier<Item>> BLOCK_ENTRIES = new ArrayList<>();
    public static final Map<RegistryObject<Item>, Supplier<Item>> ENTRIES = new LinkedHashMap<>();
    public static final Map<RegistryObject<Item>, Supplier<Item>> GEAR_ENTRIES = new LinkedHashMap<>();

    public static final RegistryObject<Item> PROSPERITY_SHARD = register("prosperity_shard");
    public static final RegistryObject<Item> INFERIUM_ESSENCE = register("inferium_essence", () -> new EssenceItem(CropTier.ONE, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> PRUDENTIUM_ESSENCE = register("prudentium_essence", () -> new EssenceItem(CropTier.TWO, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> TERTIUM_ESSENCE = register("tertium_essence", () -> new EssenceItem(CropTier.THREE, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> IMPERIUM_ESSENCE = register("imperium_essence", () -> new EssenceItem(CropTier.FOUR, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> SUPREMIUM_ESSENCE = register("supremium_essence", () -> new EssenceItem(CropTier.FIVE, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> SOULIUM_DUST = register("soulium_dust");
    public static final RegistryObject<Item> PROSPERITY_INGOT = register("prosperity_ingot");
    public static final RegistryObject<Item> INFERIUM_INGOT = register("inferium_ingot");
    public static final RegistryObject<Item> PRUDENTIUM_INGOT = register("prudentium_ingot");
    public static final RegistryObject<Item> TERTIUM_INGOT = register("tertium_ingot");
    public static final RegistryObject<Item> IMPERIUM_INGOT = register("imperium_ingot");
    public static final RegistryObject<Item> SUPREMIUM_INGOT = register("supremium_ingot");
    public static final RegistryObject<Item> SOULIUM_INGOT = register("soulium_ingot");
    public static final RegistryObject<Item> PROSPERITY_NUGGET = register("prosperity_nugget");
    public static final RegistryObject<Item> INFERIUM_NUGGET = register("inferium_nugget");
    public static final RegistryObject<Item> PRUDENTIUM_NUGGET = register("prudentium_nugget");
    public static final RegistryObject<Item> TERTIUM_NUGGET = register("tertium_nugget");
    public static final RegistryObject<Item> IMPERIUM_NUGGET = register("imperium_nugget");
    public static final RegistryObject<Item> SUPREMIUM_NUGGET = register("supremium_nugget");
    public static final RegistryObject<Item> SOULIUM_NUGGET = register("soulium_nugget");
    public static final RegistryObject<Item> PROSPERITY_GEMSTONE = register("prosperity_gemstone");
    public static final RegistryObject<Item> INFERIUM_GEMSTONE = register("inferium_gemstone");
    public static final RegistryObject<Item> PRUDENTIUM_GEMSTONE = register("prudentium_gemstone");
    public static final RegistryObject<Item> TERTIUM_GEMSTONE = register("tertium_gemstone");
    public static final RegistryObject<Item> IMPERIUM_GEMSTONE = register("imperium_gemstone");
    public static final RegistryObject<Item> SUPREMIUM_GEMSTONE = register("supremium_gemstone");
    public static final RegistryObject<Item> SOULIUM_GEMSTONE = register("soulium_gemstone");
    public static final RegistryObject<Item> PROSPERITY_SEED_BASE = register("prosperity_seed_base");
    public static final RegistryObject<Item> SOULIUM_SEED_BASE = register("soulium_seed_base");
    public static final RegistryObject<Item> SOUL_DUST = register("soul_dust");
    public static final RegistryObject<Item> SOULIUM_DAGGER = register("soulium_dagger", () -> new SouliumDaggerItem(ModItemTier.SOULIUM, SouliumDaggerItem.DaggerType.BASIC, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> PASSIVE_SOULIUM_DAGGER = register("passive_soulium_dagger", () -> new SouliumDaggerItem(ModItemTier.SOULIUM, SouliumDaggerItem.DaggerType.PASSIVE, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> HOSTILE_SOULIUM_DAGGER = register("hostile_soulium_dagger", () -> new SouliumDaggerItem(ModItemTier.SOULIUM, SouliumDaggerItem.DaggerType.HOSTILE, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> CREATIVE_SOULIUM_DAGGER = register("creative_soulium_dagger", () -> new SouliumDaggerItem(ModItemTier.SOULIUM, SouliumDaggerItem.DaggerType.CREATIVE, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> INFUSION_CRYSTAL = register("infusion_crystal", () -> new InfusionCrystalItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> MASTER_INFUSION_CRYSTAL = register("master_infusion_crystal", () -> new MasterInfusionCrystalItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> FERTILIZED_ESSENCE = register("fertilized_essence", () -> new FertilizedEssenceItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> MYSTICAL_FERTILIZER = register("mystical_fertilizer", () -> new MysticalFertilizerItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> AIR_AGGLOMERATIO = register("air_agglomeratio");
    public static final RegistryObject<Item> EARTH_AGGLOMERATIO = register("earth_agglomeratio");
    public static final RegistryObject<Item> WATER_AGGLOMERATIO = register("water_agglomeratio");
    public static final RegistryObject<Item> FIRE_AGGLOMERATIO = register("fire_agglomeratio");
    public static final RegistryObject<Item> NATURE_AGGLOMERATIO = register("nature_agglomeratio");
    public static final RegistryObject<Item> DYE_AGGLOMERATIO = register("dye_agglomeratio");
    public static final RegistryObject<Item> NETHER_AGGLOMERATIO = register("nether_agglomeratio");
    public static final RegistryObject<Item> CORAL_AGGLOMERATIO = register("coral_agglomeratio");
    public static final RegistryObject<Item> HONEY_AGGLOMERATIO = register("honey_agglomeratio");
    public static final RegistryObject<Item> PRISMARINE_AGGLOMERATIO = register("prismarine_agglomeratio");
    public static final RegistryObject<Item> END_AGGLOMERATIO = register("end_agglomeratio");
    public static final RegistryObject<Item> MYSTICAL_FLOWER_AGGLOMERATIO = register("mystical_flower_agglomeratio");
    public static final RegistryObject<Item> EXPERIENCE_DROPLET = register("experience_droplet", () -> new ExperienceDropletItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> BLANK_SKULL = register("blank_skull");
    public static final RegistryObject<Item> BLANK_RECORD = register("blank_record");
    public static final RegistryObject<Item> UNATTUNED_AUGMENT = register("unattuned_augment");
    public static final RegistryObject<Item> SOUL_JAR = register("soul_jar", () -> new SoulJarItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> EXPERIENCE_CAPSULE = register("experience_capsule", () -> new ExperienceCapsuleItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> WATERING_CAN = register("watering_can", () -> new WateringCanItem(p -> p.group(ITEM_GROUP)));

    public static final RegistryObject<Item> INFERIUM_SWORD = registerGear("inferium_sword", () -> new EssenceSwordItem(ModItemTier.INFERIUM, 1, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> INFERIUM_PICKAXE = registerGear("inferium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.INFERIUM, 1, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> INFERIUM_SHOVEL = registerGear("inferium_shovel", () -> new EssenceShovelItem(ModItemTier.INFERIUM, 1, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> INFERIUM_AXE = registerGear("inferium_axe", () -> new EssenceAxeItem(ModItemTier.INFERIUM, 1, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> INFERIUM_HOE = registerGear("inferium_hoe", () -> new EssenceHoeItem(ModItemTier.INFERIUM, 1, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> INFERIUM_STAFF = registerGear("inferium_staff", () -> new EssenceStaffItem(1, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> INFERIUM_WATERING_CAN = registerGear("inferium_watering_can", () -> new EssenceWateringCanItem(3, 0.25, CropTier.ONE.getTextColor(), p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> PRUDENTIUM_SWORD = registerGear("prudentium_sword", () -> new EssenceSwordItem(ModItemTier.PRUDENTIUM, 1, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> PRUDENTIUM_PICKAXE = registerGear("prudentium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.PRUDENTIUM, 2, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> PRUDENTIUM_SHOVEL = registerGear("prudentium_shovel", () -> new EssenceShovelItem(ModItemTier.PRUDENTIUM, 2, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> PRUDENTIUM_AXE = registerGear("prudentium_axe", () -> new EssenceAxeItem(ModItemTier.PRUDENTIUM, 2, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> PRUDENTIUM_HOE = registerGear("prudentium_hoe", () -> new EssenceHoeItem(ModItemTier.PRUDENTIUM, 2, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> PRUDENTIUM_STAFF = registerGear("prudentium_staff", () -> new EssenceStaffItem(2, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> PRUDENTIUM_WATERING_CAN = registerGear("prudentium_watering_can", () -> new EssenceWateringCanItem(5, 0.30, CropTier.TWO.getTextColor(), p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> TERTIUM_SWORD = registerGear("tertium_sword", () -> new EssenceSwordItem(ModItemTier.TERTIUM, 3, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> TERTIUM_PICKAXE = registerGear("tertium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.TERTIUM, 3, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> TERTIUM_SHOVEL = registerGear("tertium_shovel", () -> new EssenceShovelItem(ModItemTier.TERTIUM, 3, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> TERTIUM_AXE = registerGear("tertium_axe", () -> new EssenceAxeItem(ModItemTier.TERTIUM, 3, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> TERTIUM_HOE = registerGear("tertium_hoe", () -> new EssenceHoeItem(ModItemTier.TERTIUM, 3, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> TERTIUM_STAFF = registerGear("tertium_staff", () -> new EssenceStaffItem(3, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> TERTIUM_WATERING_CAN = registerGear("tertium_watering_can", () -> new EssenceWateringCanItem(7, 0.35, CropTier.THREE.getTextColor(), p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> IMPERIUM_SWORD = registerGear("imperium_sword", () -> new EssenceSwordItem(ModItemTier.IMPERIUM, 4, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> IMPERIUM_PICKAXE = registerGear("imperium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.IMPERIUM, 4, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> IMPERIUM_SHOVEL = registerGear("imperium_shovel", () -> new EssenceShovelItem(ModItemTier.IMPERIUM, 4, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> IMPERIUM_AXE = registerGear("imperium_axe", () -> new EssenceAxeItem(ModItemTier.IMPERIUM, 4, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> IMPERIUM_HOE = registerGear("imperium_hoe", () -> new EssenceHoeItem(ModItemTier.IMPERIUM, 4, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> IMPERIUM_STAFF = registerGear("imperium_staff", () -> new EssenceStaffItem(4, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> IMPERIUM_WATERING_CAN = registerGear("imperium_watering_can", () -> new EssenceWateringCanItem(9, 0.40, CropTier.FOUR.getTextColor(), p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> SUPREMIUM_SWORD = registerGear("supremium_sword", () -> new EssenceSwordItem(ModItemTier.SUPREMIUM, 5, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> SUPREMIUM_PICKAXE = registerGear("supremium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.SUPREMIUM, 5, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> SUPREMIUM_SHOVEL = registerGear("supremium_shovel", () -> new EssenceShovelItem(ModItemTier.SUPREMIUM, 5, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> SUPREMIUM_AXE = registerGear("supremium_axe", () -> new EssenceAxeItem(ModItemTier.SUPREMIUM, 5, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> SUPREMIUM_HOE = registerGear("supremium_hoe", () -> new EssenceHoeItem(ModItemTier.SUPREMIUM, 5, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> SUPREMIUM_STAFF = registerGear("supremium_staff", () -> new EssenceStaffItem(5, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> SUPREMIUM_WATERING_CAN = registerGear("supremium_watering_can", () -> new EssenceWateringCanItem(11, 0.45, CropTier.FIVE.getTextColor(), p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> INFERIUM_HELMET = registerGear("inferium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.INFERIUM, 1, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> INFERIUM_CHESTPLATE = registerGear("inferium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.INFERIUM, 1, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> INFERIUM_LEGGINGS = registerGear("inferium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.INFERIUM, 1, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> INFERIUM_BOOTS = registerGear("inferium_boots", () -> new EssenceBootsItem(ModArmorMaterial.INFERIUM, 1, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> PRUDENTIUM_HELMET = registerGear("prudentium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.PRUDENTIUM, 2, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> PRUDENTIUM_CHESTPLATE = registerGear("prudentium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.PRUDENTIUM, 2, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> PRUDENTIUM_LEGGINGS = registerGear("prudentium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.PRUDENTIUM, 2, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> PRUDENTIUM_BOOTS = registerGear("prudentium_boots", () -> new EssenceBootsItem(ModArmorMaterial.PRUDENTIUM, 2, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> TERTIUM_HELMET = registerGear("tertium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.TERTIUM, 3, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> TERTIUM_CHESTPLATE = registerGear("tertium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.TERTIUM, 3, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> TERTIUM_LEGGINGS = registerGear("tertium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.TERTIUM, 3, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> TERTIUM_BOOTS = registerGear("tertium_boots", () -> new EssenceBootsItem(ModArmorMaterial.TERTIUM, 3, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> IMPERIUM_HELMET = registerGear("imperium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.IMPERIUM, 4, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> IMPERIUM_CHESTPLATE = registerGear("imperium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.IMPERIUM, 4, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> IMPERIUM_LEGGINGS = registerGear("imperium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.IMPERIUM, 4, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> IMPERIUM_BOOTS = registerGear("imperium_boots", () -> new EssenceBootsItem(ModArmorMaterial.IMPERIUM, 4, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> SUPREMIUM_HELMET = registerGear("supremium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.SUPREMIUM, 5, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> SUPREMIUM_CHESTPLATE = registerGear("supremium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.SUPREMIUM, 5, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> SUPREMIUM_LEGGINGS = registerGear("supremium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.SUPREMIUM, 5, 1, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> SUPREMIUM_BOOTS = registerGear("supremium_boots", () -> new EssenceBootsItem(ModArmorMaterial.SUPREMIUM, 5, 1, p -> p.group(ITEM_GROUP)));

    @SubscribeEvent
    public void onRegisterItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        BLOCK_ENTRIES.stream().map(Supplier::get).forEach(registry::register);
        ENTRIES.forEach((reg, item) -> {
            registry.register(item.get());
            reg.updateReference(registry);
        });

        CropRegistry.getInstance().onRegisterItems(registry);

        GEAR_ENTRIES.forEach((reg, item) -> {
            registry.register(item.get());
            reg.updateReference(registry);
        });

        AugmentRegistry.getInstance().onRegisterItems(registry);
    }

    private static RegistryObject<Item> register(String name) {
        return register(name, () -> new BaseItem(p -> p.group(ITEM_GROUP)));
    }

    private static RegistryObject<Item> register(String name, Supplier<Item> item) {
        ResourceLocation loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        RegistryObject<Item> reg = RegistryObject.of(loc, ForgeRegistries.ITEMS);
        ENTRIES.put(reg, () -> item.get().setRegistryName(loc));
        return reg;
    }

    private static RegistryObject<Item> registerGear(String name, Supplier<? extends Item> item) {
        ResourceLocation loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        RegistryObject<Item> reg = RegistryObject.of(loc, ForgeRegistries.ITEMS);
        GEAR_ENTRIES.put(reg, () -> item.get().setRegistryName(loc));
        return reg;
    }
}