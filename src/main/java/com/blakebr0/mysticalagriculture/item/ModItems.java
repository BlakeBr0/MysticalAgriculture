package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
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
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.ITEM_GROUP;

public class ModItems {
    public static final List<Supplier<? extends Item>> ENTRIES = new ArrayList<>();
    public static final List<Supplier<? extends Item>> GEAR_ENTRIES = new ArrayList<>();

    public static final RegistryObject<BaseItem> PROSPERITY_SHARD = register("prosperity_shard");
    public static final RegistryObject<EssenceItem> INFERIUM_ESSENCE = register("inferium_essence", () -> new EssenceItem(CropTier.ONE, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceItem> PRUDENTIUM_ESSENCE = register("prudentium_essence", () -> new EssenceItem(CropTier.TWO, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceItem> TERTIUM_ESSENCE = register("tertium_essence", () -> new EssenceItem(CropTier.THREE, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceItem> IMPERIUM_ESSENCE = register("imperium_essence", () -> new EssenceItem(CropTier.FOUR, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceItem> SUPREMIUM_ESSENCE = register("supremium_essence", () -> new EssenceItem(CropTier.FIVE, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<BaseItem> SOULIUM_DUST = register("soulium_dust");
    public static final RegistryObject<BaseItem> PROSPERITY_INGOT = register("prosperity_ingot");
    public static final RegistryObject<BaseItem> INFERIUM_INGOT = register("inferium_ingot");
    public static final RegistryObject<BaseItem> PRUDENTIUM_INGOT = register("prudentium_ingot");
    public static final RegistryObject<BaseItem> TERTIUM_INGOT = register("tertium_ingot");
    public static final RegistryObject<BaseItem> IMPERIUM_INGOT = register("imperium_ingot");
    public static final RegistryObject<BaseItem> SUPREMIUM_INGOT = register("supremium_ingot");
    public static final RegistryObject<BaseItem> SOULIUM_INGOT = register("soulium_ingot");
    public static final RegistryObject<BaseItem> PROSPERITY_NUGGET = register("prosperity_nugget");
    public static final RegistryObject<BaseItem> INFERIUM_NUGGET = register("inferium_nugget");
    public static final RegistryObject<BaseItem> PRUDENTIUM_NUGGET = register("prudentium_nugget");
    public static final RegistryObject<BaseItem> TERTIUM_NUGGET = register("tertium_nugget");
    public static final RegistryObject<BaseItem> IMPERIUM_NUGGET = register("imperium_nugget");
    public static final RegistryObject<BaseItem> SUPREMIUM_NUGGET = register("supremium_nugget");
    public static final RegistryObject<BaseItem> SOULIUM_NUGGET = register("soulium_nugget");
    public static final RegistryObject<BaseItem> PROSPERITY_GEMSTONE = register("prosperity_gemstone");
    public static final RegistryObject<BaseItem> INFERIUM_GEMSTONE = register("inferium_gemstone");
    public static final RegistryObject<BaseItem> PRUDENTIUM_GEMSTONE = register("prudentium_gemstone");
    public static final RegistryObject<BaseItem> TERTIUM_GEMSTONE = register("tertium_gemstone");
    public static final RegistryObject<BaseItem> IMPERIUM_GEMSTONE = register("imperium_gemstone");
    public static final RegistryObject<BaseItem> SUPREMIUM_GEMSTONE = register("supremium_gemstone");
    public static final RegistryObject<BaseItem> SOULIUM_GEMSTONE = register("soulium_gemstone");
    public static final RegistryObject<BaseItem> PROSPERITY_SEED_BASE = register("prosperity_seed_base");
    public static final RegistryObject<BaseItem> SOULIUM_SEED_BASE = register("soulium_seed_base");
    public static final RegistryObject<BaseItem> SOUL_DUST = register("soul_dust");
    public static final RegistryObject<SouliumDaggerItem> SOULIUM_DAGGER = register("soulium_dagger", () -> new SouliumDaggerItem(ModItemTier.SOULIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> INFUSION_CRYSTAL = register("infusion_crystal", () -> new InfusionCrystalItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<MasterInfusionCrystalItem> MASTER_INFUSION_CRYSTAL = register("master_infusion_crystal", () -> new MasterInfusionCrystalItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<FertilizedEssenceItem> FERTILIZED_ESSENCE = register("fertilized_essence", () -> new FertilizedEssenceItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<MysticalFertilizerItem> MYSTICAL_FERTILIZER = register("mystical_fertilizer", () -> new MysticalFertilizerItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<Item> AIR_AGGLOMERATIO = register("air_agglomeratio");
    public static final RegistryObject<Item> EARTH_AGGLOMERATIO = register("earth_agglomeratio");
    public static final RegistryObject<Item> WATER_AGGLOMERATIO = register("water_agglomeratio");
    public static final RegistryObject<Item> FIRE_AGGLOMERATIO = register("fire_agglomeratio");
    public static final RegistryObject<BaseItem> NATURE_AGGLOMERATIO = register("nature_agglomeratio");
    public static final RegistryObject<BaseItem> DYE_AGGLOMERATIO = register("dye_agglomeratio");
    public static final RegistryObject<BaseItem> NETHER_AGGLOMERATIO = register("nether_agglomeratio");
    public static final RegistryObject<BaseItem> PRISMARINE_AGGLOMERATIO = register("prismarine_agglomeratio");
    public static final RegistryObject<BaseItem> END_AGGLOMERATIO = register("end_agglomeratio");
    public static final RegistryObject<ExperienceDropletItem> EXPERIENCE_DROPLET = register("experience_droplet", () -> new ExperienceDropletItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<BaseItem> BLANK_SKULL = register("blank_skull");
    public static final RegistryObject<BaseItem> BLANK_RECORD = register("blank_record");
    public static final RegistryObject<Item> SOUL_JAR = register("soul_jar", () -> new SoulJarItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<ExperienceCapsuleItem> EXPERIENCE_CAPSULE = register("experience_capsule", () -> new ExperienceCapsuleItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<WateringCanItem> WATERING_CAN = register("watering_can", () -> new WateringCanItem(p -> p.group(ITEM_GROUP)));

    public static final RegistryObject<EssenceSwordItem> INFERIUM_SWORD = registerGear("inferium_sword", () -> new EssenceSwordItem(ModItemTier.INFERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssencePickaxeItem> INFERIUM_PICKAXE = registerGear("inferium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.INFERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceShovelItem> INFERIUM_SHOVEL = registerGear("inferium_shovel", () -> new EssenceShovelItem(ModItemTier.INFERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceAxeItem> INFERIUM_AXE = registerGear("inferium_axe", () -> new EssenceAxeItem(ModItemTier.INFERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceHoeItem> INFERIUM_HOE = registerGear("inferium_hoe", () -> new EssenceHoeItem(ModItemTier.INFERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceStaffItem> INFERIUM_STAFF = registerGear("inferium_staff", () -> new EssenceStaffItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceWateringCanItem> INFERIUM_WATERING_CAN = registerGear("inferium_watering_can", () -> new EssenceWateringCanItem(3, 0.25, CropTier.ONE.getTextColor(), p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceSwordItem> PRUDENTIUM_SWORD = registerGear("prudentium_sword", () -> new EssenceSwordItem(ModItemTier.PRUDENTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssencePickaxeItem> PRUDENTIUM_PICKAXE = registerGear("prudentium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.PRUDENTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceShovelItem> PRUDENTIUM_SHOVEL = registerGear("prudentium_shovel", () -> new EssenceShovelItem(ModItemTier.PRUDENTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceAxeItem> PRUDENTIUM_AXE = registerGear("prudentium_axe", () -> new EssenceAxeItem(ModItemTier.PRUDENTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceHoeItem> PRUDENTIUM_HOE = registerGear("prudentium_hoe", () -> new EssenceHoeItem(ModItemTier.PRUDENTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceStaffItem> PRUDENTIUM_STAFF = registerGear("prudentium_staff", () -> new EssenceStaffItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceWateringCanItem> PRUDENTIUM_WATERING_CAN = registerGear("prudentium_watering_can", () -> new EssenceWateringCanItem(5, 0.30, CropTier.TWO.getTextColor(), p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceSwordItem> TERTIUM_SWORD = registerGear("tertium_sword", () -> new EssenceSwordItem(ModItemTier.TERTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssencePickaxeItem> TERTIUM_PICKAXE = registerGear("tertium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.TERTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceShovelItem> TERTIUM_SHOVEL = registerGear("tertium_shovel", () -> new EssenceShovelItem(ModItemTier.TERTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceAxeItem> TERTIUM_AXE = registerGear("tertium_axe", () -> new EssenceAxeItem(ModItemTier.TERTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceHoeItem> TERTIUM_HOE = registerGear("tertium_hoe", () -> new EssenceHoeItem(ModItemTier.TERTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceStaffItem> TERTIUM_STAFF = registerGear("tertium_staff", () -> new EssenceStaffItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceWateringCanItem> TERTIUM_WATERING_CAN = registerGear("tertium_watering_can", () -> new EssenceWateringCanItem(7, 0.35, CropTier.THREE.getTextColor(), p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceSwordItem> IMPERIUM_SWORD = registerGear("imperium_sword", () -> new EssenceSwordItem(ModItemTier.IMPERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssencePickaxeItem> IMPERIUM_PICKAXE = registerGear("imperium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.IMPERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceShovelItem> IMPERIUM_SHOVEL = registerGear("imperium_shovel", () -> new EssenceShovelItem(ModItemTier.IMPERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceAxeItem> IMPERIUM_AXE = registerGear("imperium_axe", () -> new EssenceAxeItem(ModItemTier.IMPERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceHoeItem> IMPERIUM_HOE = registerGear("imperium_hoe", () -> new EssenceHoeItem(ModItemTier.IMPERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceStaffItem> IMPERIUM_STAFF = registerGear("imperium_staff", () -> new EssenceStaffItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceWateringCanItem> IMPERIUM_WATERING_CAN = registerGear("imperium_watering_can", () -> new EssenceWateringCanItem(9, 0.40, CropTier.FOUR.getTextColor(), p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceSwordItem> SUPREMIUM_SWORD = registerGear("supremium_sword", () -> new EssenceSwordItem(ModItemTier.SUPREMIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssencePickaxeItem> SUPREMIUM_PICKAXE = registerGear("supremium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.SUPREMIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceShovelItem> SUPREMIUM_SHOVEL = registerGear("supremium_shovel", () -> new EssenceShovelItem(ModItemTier.SUPREMIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceAxeItem> SUPREMIUM_AXE = registerGear("supremium_axe", () -> new EssenceAxeItem(ModItemTier.SUPREMIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceHoeItem> SUPREMIUM_HOE = registerGear("supremium_hoe", () -> new EssenceHoeItem(ModItemTier.SUPREMIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceStaffItem> SUPREMIUM_STAFF = registerGear("supremium_staff", () -> new EssenceStaffItem(p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceWateringCanItem> SUPREMIUM_WATERING_CAN = registerGear("supremium_watering_can", () -> new EssenceWateringCanItem(11, 0.45, CropTier.FIVE.getTextColor(), p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceHelmetItem> INFERIUM_HELMET = registerGear("inferium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.INFERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceChestplateItem> INFERIUM_CHESTPLATE = registerGear("inferium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.INFERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceLeggingsItem> INFERIUM_LEGGINGS = registerGear("inferium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.INFERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceBootsItem> INFERIUM_BOOTS = registerGear("inferium_boots", () -> new EssenceBootsItem(ModArmorMaterial.INFERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceHelmetItem> PRUDENTIUM_HELMET = registerGear("prudentium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.PRUDENTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceChestplateItem> PRUDENTIUM_CHESTPLATE = registerGear("prudentium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.PRUDENTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceLeggingsItem> PRUDENTIUM_LEGGINGS = registerGear("prudentium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.PRUDENTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceBootsItem> PRUDENTIUM_BOOTS = registerGear("prudentium_boots", () -> new EssenceBootsItem(ModArmorMaterial.PRUDENTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceHelmetItem> TERTIUM_HELMET = registerGear("tertium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.TERTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceChestplateItem> TERTIUM_CHESTPLATE = registerGear("tertium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.TERTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceLeggingsItem> TERTIUM_LEGGINGS = registerGear("tertium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.TERTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceBootsItem> TERTIUM_BOOTS = registerGear("tertium_boots", () -> new EssenceBootsItem(ModArmorMaterial.TERTIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceHelmetItem> IMPERIUM_HELMET = registerGear("imperium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.IMPERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceChestplateItem> IMPERIUM_CHESTPLATE = registerGear("imperium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.IMPERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceLeggingsItem> IMPERIUM_LEGGINGS = registerGear("imperium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.IMPERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceBootsItem> IMPERIUM_BOOTS = registerGear("imperium_boots", () -> new EssenceBootsItem(ModArmorMaterial.IMPERIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceHelmetItem> SUPREMIUM_HELMET = registerGear("supremium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.SUPREMIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceChestplateItem> SUPREMIUM_CHESTPLATE = registerGear("supremium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.SUPREMIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceLeggingsItem> SUPREMIUM_LEGGINGS = registerGear("supremium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.SUPREMIUM, p -> p.group(ITEM_GROUP)));
    public static final RegistryObject<EssenceBootsItem> SUPREMIUM_BOOTS = registerGear("supremium_boots", () -> new EssenceBootsItem(ModArmorMaterial.SUPREMIUM, p -> p.group(ITEM_GROUP)));

    @SubscribeEvent
    public void onRegisterItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ENTRIES.stream().map(Supplier::get).forEach(registry::register);
        CropRegistry.getInstance().onRegisterItems(registry);
        GEAR_ENTRIES.stream().map(Supplier::get).forEach(registry::register);

        INFUSION_CRYSTAL.updateReference(registry);
        AIR_AGGLOMERATIO.updateReference(registry);
        EARTH_AGGLOMERATIO.updateReference(registry);
        WATER_AGGLOMERATIO.updateReference(registry);
        FIRE_AGGLOMERATIO.updateReference(registry);
        SOUL_JAR.updateReference(registry);
    }

    private static <T extends Item> RegistryObject<T> register(String name) {
        return register(name, () -> new BaseItem(p -> p.group(ITEM_GROUP)));
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<? extends Item> item) {
        ResourceLocation loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        ENTRIES.add(() -> item.get().setRegistryName(loc));
        return RegistryObject.of(loc, ForgeRegistries.ITEMS);
    }

    private static <T extends Item> RegistryObject<T> registerGear(String name, Supplier<? extends Item> item) {
        ResourceLocation loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        GEAR_ENTRIES.add(() -> item.get().setRegistryName(loc));
        return RegistryObject.of(loc, ForgeRegistries.ITEMS);
    }
}