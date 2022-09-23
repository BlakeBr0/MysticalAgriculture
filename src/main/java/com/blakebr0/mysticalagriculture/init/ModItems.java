package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.cucumber.item.BaseEnableableItem;
import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.item.tool.BaseScytheItem;
import com.blakebr0.cucumber.item.tool.BaseSickleItem;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.item.EssenceItem;
import com.blakebr0.mysticalagriculture.item.EssenceWateringCanItem;
import com.blakebr0.mysticalagriculture.item.ExperienceCapsuleItem;
import com.blakebr0.mysticalagriculture.item.ExperienceDropletItem;
import com.blakebr0.mysticalagriculture.item.FertilizedEssenceItem;
import com.blakebr0.mysticalagriculture.item.InfusionCrystalItem;
import com.blakebr0.mysticalagriculture.item.MachineUpgradeItem;
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
import com.blakebr0.mysticalagriculture.item.tool.EssenceBowItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceCrossbowItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceFishingRodItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceHoeItem;
import com.blakebr0.mysticalagriculture.item.tool.EssencePickaxeItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceScytheItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceShearsItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceShovelItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceSickleItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceStaffItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceSwordItem;
import com.blakebr0.mysticalagriculture.lib.ModArmorMaterial;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import com.blakebr0.mysticalagriculture.lib.ModItemTier;
import com.blakebr0.mysticalagriculture.registry.AugmentRegistry;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.blakebr0.mysticalagriculture.util.MachineUpgradeTier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.CREATIVE_TAB;

public final class ModItems {
    public static final List<Supplier<BlockItem>> BLOCK_ENTRIES = new ArrayList<>();
    public static final Map<RegistryObject<Item>, Supplier<Item>> ENTRIES = new LinkedHashMap<>();
    public static final Map<RegistryObject<Item>, Supplier<Item>> GEAR_ENTRIES = new LinkedHashMap<>();

    public static final RegistryObject<Item> PROSPERITY_SHARD = register("prosperity_shard");
    public static final RegistryObject<Item> INFERIUM_ESSENCE = register("inferium_essence", () -> new EssenceItem(CropTier.ONE, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_ESSENCE = register("prudentium_essence", () -> new EssenceItem(CropTier.TWO, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_ESSENCE = register("tertium_essence", () -> new EssenceItem(CropTier.THREE, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_ESSENCE = register("imperium_essence", () -> new EssenceItem(CropTier.FOUR, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_ESSENCE = register("supremium_essence", () -> new EssenceItem(CropTier.FIVE, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_ESSENCE = register("awakened_supremium_essence");
    public static final RegistryObject<Item> PROSPERITY_INGOT = register("prosperity_ingot");
    public static final RegistryObject<Item> INFERIUM_INGOT = register("inferium_ingot");
    public static final RegistryObject<Item> PRUDENTIUM_INGOT = register("prudentium_ingot");
    public static final RegistryObject<Item> TERTIUM_INGOT = register("tertium_ingot");
    public static final RegistryObject<Item> IMPERIUM_INGOT = register("imperium_ingot");
    public static final RegistryObject<Item> SUPREMIUM_INGOT = register("supremium_ingot");
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_INGOT = register("awakened_supremium_ingot");
    public static final RegistryObject<Item> SOULIUM_INGOT = register("soulium_ingot");
    public static final RegistryObject<Item> PROSPERITY_NUGGET = register("prosperity_nugget");
    public static final RegistryObject<Item> INFERIUM_NUGGET = register("inferium_nugget");
    public static final RegistryObject<Item> PRUDENTIUM_NUGGET = register("prudentium_nugget");
    public static final RegistryObject<Item> TERTIUM_NUGGET = register("tertium_nugget");
    public static final RegistryObject<Item> IMPERIUM_NUGGET = register("imperium_nugget");
    public static final RegistryObject<Item> SUPREMIUM_NUGGET = register("supremium_nugget");
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_NUGGET = register("awakened_supremium_nugget");
    public static final RegistryObject<Item> SOULIUM_NUGGET = register("soulium_nugget");
    public static final RegistryObject<Item> PROSPERITY_GEMSTONE = register("prosperity_gemstone");
    public static final RegistryObject<Item> INFERIUM_GEMSTONE = register("inferium_gemstone");
    public static final RegistryObject<Item> PRUDENTIUM_GEMSTONE = register("prudentium_gemstone");
    public static final RegistryObject<Item> TERTIUM_GEMSTONE = register("tertium_gemstone");
    public static final RegistryObject<Item> IMPERIUM_GEMSTONE = register("imperium_gemstone");
    public static final RegistryObject<Item> SUPREMIUM_GEMSTONE = register("supremium_gemstone");
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_GEMSTONE = register("awakened_supremium_gemstone");
    public static final RegistryObject<Item> SOULIUM_GEMSTONE = register("soulium_gemstone");
    public static final RegistryObject<Item> PROSPERITY_SEED_BASE = register("prosperity_seed_base");
    public static final RegistryObject<Item> SOULIUM_SEED_BASE = register("soulium_seed_base");
    public static final RegistryObject<Item> SOUL_DUST = register("soul_dust");
    public static final RegistryObject<Item> SOULIUM_DUST = register("soulium_dust");
    public static final RegistryObject<Item> COGNIZANT_DUST = register("cognizant_dust");
    public static final RegistryObject<Item> SOULIUM_DAGGER = register("soulium_dagger", () -> new SouliumDaggerItem(ModItemTier.SOULIUM, SouliumDaggerItem.DaggerType.BASIC, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PASSIVE_SOULIUM_DAGGER = register("passive_soulium_dagger", () -> new SouliumDaggerItem(ModItemTier.SOULIUM, SouliumDaggerItem.DaggerType.PASSIVE, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> HOSTILE_SOULIUM_DAGGER = register("hostile_soulium_dagger", () -> new SouliumDaggerItem(ModItemTier.SOULIUM, SouliumDaggerItem.DaggerType.HOSTILE, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> CREATIVE_SOULIUM_DAGGER = register("creative_soulium_dagger", () -> new SouliumDaggerItem(ModItemTier.SOULIUM, SouliumDaggerItem.DaggerType.CREATIVE, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFUSION_CRYSTAL = register("infusion_crystal", () -> new InfusionCrystalItem(p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> MASTER_INFUSION_CRYSTAL = register("master_infusion_crystal", () -> new MasterInfusionCrystalItem(p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> FERTILIZED_ESSENCE = register("fertilized_essence", () -> new FertilizedEssenceItem(p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> MYSTICAL_FERTILIZER = register("mystical_fertilizer", () -> new MysticalFertilizerItem(p -> p.tab(CREATIVE_TAB)));
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
    public static final RegistryObject<Item> MYSTICAL_FLOWER_AGGLOMERATIO = register("mystical_flower_agglomeratio", () -> new BaseEnableableItem(ModCrops.MYSTICAL_FLOWER::isEnabled, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> EXPERIENCE_DROPLET = register("experience_droplet", () -> new ExperienceDropletItem(p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> BLANK_SKULL = register("blank_skull");
    public static final RegistryObject<Item> BLANK_RECORD = register("blank_record");
    public static final RegistryObject<Item> UNATTUNED_AUGMENT = register("unattuned_augment");
    public static final RegistryObject<Item> SOUL_JAR = register("soul_jar", () -> new SoulJarItem(p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> EXPERIENCE_CAPSULE = register("experience_capsule", () -> new ExperienceCapsuleItem(p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> WATERING_CAN = register("watering_can", () -> new WateringCanItem(p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> DIAMOND_SICKLE = register("diamond_sickle", () -> new BaseSickleItem(Tiers.DIAMOND, 4.0F, -3.0F, 3, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> DIAMOND_SCYTHE = register("diamond_scythe", () -> new BaseScytheItem(Tiers.DIAMOND, 4, -2.8F, 3, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> UPGRADE_BASE = register("upgrade_base");
    public static final RegistryObject<Item> INFERIUM_UPGRADE = register("inferium_upgrade", () -> new MachineUpgradeItem(MachineUpgradeTier.INFERIUM, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_UPGRADE = register("prudentium_upgrade", () -> new MachineUpgradeItem(MachineUpgradeTier.PRUDENTIUM, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_UPGRADE = register("tertium_upgrade", () -> new MachineUpgradeItem(MachineUpgradeTier.TERTIUM, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_UPGRADE = register("imperium_upgrade", () -> new MachineUpgradeItem(MachineUpgradeTier.IMPERIUM, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_UPGRADE = register("supremium_upgrade", () -> new MachineUpgradeItem(MachineUpgradeTier.SUPREMIUM, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_UPGRADE = register("awakened_supremium_upgrade", () -> new MachineUpgradeItem(MachineUpgradeTier.AWAKENED_SUPREMIUM, p -> p.tab(CREATIVE_TAB)));

    public static final RegistryObject<Item> INFERIUM_SWORD = registerGear("inferium_sword", () -> new EssenceSwordItem(ModItemTier.INFERIUM, 1, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_PICKAXE = registerGear("inferium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.INFERIUM, 1, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_SHOVEL = registerGear("inferium_shovel", () -> new EssenceShovelItem(ModItemTier.INFERIUM, 1, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_AXE = registerGear("inferium_axe", () -> new EssenceAxeItem(ModItemTier.INFERIUM, 1, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_HOE = registerGear("inferium_hoe", () -> new EssenceHoeItem(ModItemTier.INFERIUM, 1, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_STAFF = registerGear("inferium_staff", () -> new EssenceStaffItem(1, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_WATERING_CAN = registerGear("inferium_watering_can", () -> new EssenceWateringCanItem(3, 0.25, CropTier.ONE.getTextColor(), p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_BOW = registerGear("inferium_bow", () -> new EssenceBowItem(ModItemTier.INFERIUM, 1, 1, 1.1F, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_CROSSBOW = registerGear("inferium_crossbow", () -> new EssenceCrossbowItem(ModItemTier.INFERIUM, 1, 1, 1.1F, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_SHEARS = registerGear("inferium_shears", () -> new EssenceShearsItem(ModItemTier.INFERIUM, 1, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_FISHING_ROD = registerGear("inferium_fishing_rod", () -> new EssenceFishingRodItem(ModItemTier.INFERIUM, 1, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_SICKLE = registerGear("inferium_sickle", () -> new EssenceSickleItem(ModItemTier.INFERIUM, 3, CropTier.ONE.getTextColor(), 1, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_SCYTHE = registerGear("inferium_scythe", () -> new EssenceScytheItem(ModItemTier.INFERIUM, 3, CropTier.ONE.getTextColor(), 1, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_SWORD = registerGear("prudentium_sword", () -> new EssenceSwordItem(ModItemTier.PRUDENTIUM, 2, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_PICKAXE = registerGear("prudentium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.PRUDENTIUM, 2, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_SHOVEL = registerGear("prudentium_shovel", () -> new EssenceShovelItem(ModItemTier.PRUDENTIUM, 2, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_AXE = registerGear("prudentium_axe", () -> new EssenceAxeItem(ModItemTier.PRUDENTIUM, 2, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_HOE = registerGear("prudentium_hoe", () -> new EssenceHoeItem(ModItemTier.PRUDENTIUM, 2, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_STAFF = registerGear("prudentium_staff", () -> new EssenceStaffItem(2, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_WATERING_CAN = registerGear("prudentium_watering_can", () -> new EssenceWateringCanItem(5, 0.30, CropTier.TWO.getTextColor(), p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_BOW = registerGear("prudentium_bow", () ->  new EssenceBowItem(ModItemTier.PRUDENTIUM, 2, 1, 1.2F, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_CROSSBOW = registerGear("prudentium_crossbow", () -> new EssenceCrossbowItem(ModItemTier.PRUDENTIUM, 2, 1, 1.2F, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_SHEARS = registerGear("prudentium_shears", () -> new EssenceShearsItem(ModItemTier.PRUDENTIUM, 2, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_FISHING_ROD = registerGear("prudentium_fishing_rod", () -> new EssenceFishingRodItem(ModItemTier.PRUDENTIUM, 2, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_SICKLE = registerGear("prudentium_sickle", () -> new EssenceSickleItem(ModItemTier.PRUDENTIUM, 4, CropTier.TWO.getTextColor(), 2, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_SCYTHE = registerGear("prudentium_scythe", () -> new EssenceScytheItem(ModItemTier.PRUDENTIUM, 4, CropTier.TWO.getTextColor(), 2, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_SWORD = registerGear("tertium_sword", () -> new EssenceSwordItem(ModItemTier.TERTIUM, 3, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_PICKAXE = registerGear("tertium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.TERTIUM, 3, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_SHOVEL = registerGear("tertium_shovel", () -> new EssenceShovelItem(ModItemTier.TERTIUM, 3, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_AXE = registerGear("tertium_axe", () -> new EssenceAxeItem(ModItemTier.TERTIUM, 3, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_HOE = registerGear("tertium_hoe", () -> new EssenceHoeItem(ModItemTier.TERTIUM, 3, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_STAFF = registerGear("tertium_staff", () -> new EssenceStaffItem(3, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_WATERING_CAN = registerGear("tertium_watering_can", () -> new EssenceWateringCanItem(7, 0.35, CropTier.THREE.getTextColor(), p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_BOW = registerGear("tertium_bow", () -> new EssenceBowItem(ModItemTier.TERTIUM, 3, 1, 1.35F, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_CROSSBOW = registerGear("tertium_crossbow", () -> new EssenceCrossbowItem(ModItemTier.TERTIUM, 3, 1, 1.35F, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_SHEARS = registerGear("tertium_shears", () -> new EssenceShearsItem(ModItemTier.TERTIUM, 3, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_FISHING_ROD = registerGear("tertium_fishing_rod", () -> new EssenceFishingRodItem(ModItemTier.TERTIUM, 3, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_SICKLE = registerGear("tertium_sickle", () -> new EssenceSickleItem(ModItemTier.TERTIUM, 5, CropTier.THREE.getTextColor(), 3, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_SCYTHE = registerGear("tertium_scythe", () -> new EssenceScytheItem(ModItemTier.TERTIUM, 5, CropTier.THREE.getTextColor(), 3, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_SWORD = registerGear("imperium_sword", () -> new EssenceSwordItem(ModItemTier.IMPERIUM, 4, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_PICKAXE = registerGear("imperium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.IMPERIUM, 4, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_SHOVEL = registerGear("imperium_shovel", () -> new EssenceShovelItem(ModItemTier.IMPERIUM, 4, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_AXE = registerGear("imperium_axe", () -> new EssenceAxeItem(ModItemTier.IMPERIUM, 4, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_HOE = registerGear("imperium_hoe", () -> new EssenceHoeItem(ModItemTier.IMPERIUM, 4, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_STAFF = registerGear("imperium_staff", () -> new EssenceStaffItem(4, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_WATERING_CAN = registerGear("imperium_watering_can", () -> new EssenceWateringCanItem(9, 0.40, CropTier.FOUR.getTextColor(), p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_BOW = registerGear("imperium_bow", () -> new EssenceBowItem(ModItemTier.IMPERIUM, 4, 1, 1.55F, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_CROSSBOW = registerGear("imperium_crossbow", () -> new EssenceCrossbowItem(ModItemTier.IMPERIUM, 4, 1, 1.55F, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_SHEARS = registerGear("imperium_shears", () -> new EssenceShearsItem(ModItemTier.IMPERIUM, 4, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_FISHING_ROD = registerGear("imperium_fishing_rod", () -> new EssenceFishingRodItem(ModItemTier.IMPERIUM, 4, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_SICKLE = registerGear("imperium_sickle", () -> new EssenceSickleItem(ModItemTier.IMPERIUM, 6, CropTier.FOUR.getTextColor(), 4, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_SCYTHE = registerGear("imperium_scythe", () -> new EssenceScytheItem(ModItemTier.IMPERIUM, 6, CropTier.FOUR.getTextColor(), 4, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_SWORD = registerGear("supremium_sword", () -> new EssenceSwordItem(ModItemTier.SUPREMIUM, 5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_PICKAXE = registerGear("supremium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.SUPREMIUM, 5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_SHOVEL = registerGear("supremium_shovel", () -> new EssenceShovelItem(ModItemTier.SUPREMIUM, 5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_AXE = registerGear("supremium_axe", () -> new EssenceAxeItem(ModItemTier.SUPREMIUM, 5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_HOE = registerGear("supremium_hoe", () -> new EssenceHoeItem(ModItemTier.SUPREMIUM, 5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_STAFF = registerGear("supremium_staff", () -> new EssenceStaffItem(5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_WATERING_CAN = registerGear("supremium_watering_can", () -> new EssenceWateringCanItem(11, 0.45, CropTier.FIVE.getTextColor(), p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_BOW = registerGear("supremium_bow", () -> new EssenceBowItem(ModItemTier.SUPREMIUM, 5, 1, 1.80F, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_CROSSBOW = registerGear("supremium_crossbow", () -> new EssenceCrossbowItem(ModItemTier.SUPREMIUM, 5, 1, 1.80F, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_SHEARS = registerGear("supremium_shears", () -> new EssenceShearsItem(ModItemTier.SUPREMIUM, 5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_FISHING_ROD = registerGear("supremium_fishing_rod", () -> new EssenceFishingRodItem(ModItemTier.SUPREMIUM, 5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_SICKLE = registerGear("supremium_sickle", () -> new EssenceSickleItem(ModItemTier.SUPREMIUM, 7, CropTier.FIVE.getTextColor(), 5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_SCYTHE = registerGear("supremium_scythe", () -> new EssenceScytheItem(ModItemTier.SUPREMIUM, 7, CropTier.FIVE.getTextColor(), 5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_SWORD = registerGear("awakened_supremium_sword", () -> new EssenceSwordItem(ModItemTier.AWAKENED_SUPREMIUM, 5, 2, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_PICKAXE = registerGear("awakened_supremium_pickaxe", () -> new EssencePickaxeItem(ModItemTier.AWAKENED_SUPREMIUM, 5, 2, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_SHOVEL = registerGear("awakened_supremium_shovel", () -> new EssenceShovelItem(ModItemTier.AWAKENED_SUPREMIUM, 5, 2, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_AXE = registerGear("awakened_supremium_axe", () -> new EssenceAxeItem(ModItemTier.AWAKENED_SUPREMIUM, 5, 2, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_HOE = registerGear("awakened_supremium_hoe", () -> new EssenceHoeItem(ModItemTier.AWAKENED_SUPREMIUM, 5, 2, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_STAFF = registerGear("awakened_supremium_staff", () -> new EssenceStaffItem(5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_WATERING_CAN = registerGear("awakened_supremium_watering_can", () -> new EssenceWateringCanItem(13, 0.50, CropTier.FIVE.getTextColor(), p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_BOW = registerGear("awakened_supremium_bow", () -> new EssenceBowItem(ModItemTier.AWAKENED_SUPREMIUM, 5, 2, 2.10F, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_CROSSBOW = registerGear("awakened_supremium_crossbow", () -> new EssenceCrossbowItem(ModItemTier.AWAKENED_SUPREMIUM, 5, 2, 2.10F, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_SHEARS = registerGear("awakened_supremium_shears", () -> new EssenceShearsItem(ModItemTier.AWAKENED_SUPREMIUM, 5, 2, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_FISHING_ROD = registerGear("awakened_supremium_fishing_rod", () -> new EssenceFishingRodItem(ModItemTier.AWAKENED_SUPREMIUM, 5, 2, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_SICKLE = registerGear("awakened_supremium_sickle", () -> new EssenceSickleItem(ModItemTier.AWAKENED_SUPREMIUM, 8, CropTier.FIVE.getTextColor(), 5, 2, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_SCYTHE = registerGear("awakened_supremium_scythe", () -> new EssenceScytheItem(ModItemTier.AWAKENED_SUPREMIUM, 8, CropTier.FIVE.getTextColor(), 5, 2, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_HELMET = registerGear("inferium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.INFERIUM, 1, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_CHESTPLATE = registerGear("inferium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.INFERIUM, 1, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_LEGGINGS = registerGear("inferium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.INFERIUM, 1, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> INFERIUM_BOOTS = registerGear("inferium_boots", () -> new EssenceBootsItem(ModArmorMaterial.INFERIUM, 1, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_HELMET = registerGear("prudentium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.PRUDENTIUM, 2, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_CHESTPLATE = registerGear("prudentium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.PRUDENTIUM, 2, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_LEGGINGS = registerGear("prudentium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.PRUDENTIUM, 2, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> PRUDENTIUM_BOOTS = registerGear("prudentium_boots", () -> new EssenceBootsItem(ModArmorMaterial.PRUDENTIUM, 2, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_HELMET = registerGear("tertium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.TERTIUM, 3, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_CHESTPLATE = registerGear("tertium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.TERTIUM, 3, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_LEGGINGS = registerGear("tertium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.TERTIUM, 3, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> TERTIUM_BOOTS = registerGear("tertium_boots", () -> new EssenceBootsItem(ModArmorMaterial.TERTIUM, 3, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_HELMET = registerGear("imperium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.IMPERIUM, 4, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_CHESTPLATE = registerGear("imperium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.IMPERIUM, 4, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_LEGGINGS = registerGear("imperium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.IMPERIUM, 4, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> IMPERIUM_BOOTS = registerGear("imperium_boots", () -> new EssenceBootsItem(ModArmorMaterial.IMPERIUM, 4, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_HELMET = registerGear("supremium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.SUPREMIUM, 5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_CHESTPLATE = registerGear("supremium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.SUPREMIUM, 5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_LEGGINGS = registerGear("supremium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.SUPREMIUM, 5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> SUPREMIUM_BOOTS = registerGear("supremium_boots", () -> new EssenceBootsItem(ModArmorMaterial.SUPREMIUM, 5, 1, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_HELMET = registerGear("awakened_supremium_helmet", () -> new EssenceHelmetItem(ModArmorMaterial.AWAKENED_SUPREMIUM, 5, 2, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_CHESTPLATE = registerGear("awakened_supremium_chestplate", () -> new EssenceChestplateItem(ModArmorMaterial.AWAKENED_SUPREMIUM, 5, 2, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_LEGGINGS = registerGear("awakened_supremium_leggings", () -> new EssenceLeggingsItem(ModArmorMaterial.AWAKENED_SUPREMIUM, 5, 2, p -> p.tab(CREATIVE_TAB)));
    public static final RegistryObject<Item> AWAKENED_SUPREMIUM_BOOTS = registerGear("awakened_supremium_boots", () -> new EssenceBootsItem(ModArmorMaterial.AWAKENED_SUPREMIUM, 5, 2, p -> p.tab(CREATIVE_TAB)));

    @SubscribeEvent
    public void onRegisterItems(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.ITEMS, registry -> {
            BLOCK_ENTRIES.stream().map(Supplier::get).forEach(item -> {
                var id = ForgeRegistries.BLOCKS.getKey(item.getBlock());
                registry.register(id, item);
            });

            ENTRIES.forEach((reg, item) -> {
                registry.register(reg.getId(), item.get());
            });

            CropRegistry.getInstance().onRegisterItems(event.getForgeRegistry());

            GEAR_ENTRIES.forEach((reg, item) -> {
                registry.register(reg.getId(), item.get());
            });

            AugmentRegistry.getInstance().onRegisterItems(event.getForgeRegistry());
        });
    }

    private static RegistryObject<Item> register(String name) {
        return register(name, () -> new BaseItem(p -> p.tab(CREATIVE_TAB)));
    }

    private static RegistryObject<Item> register(String name, Supplier<Item> item) {
        var loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        var reg = RegistryObject.create(loc, ForgeRegistries.ITEMS);
        ENTRIES.put(reg, item);
        return reg;
    }

    private static RegistryObject<Item> registerGear(String name, Supplier<? extends Item> item) {
        var loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        var reg = RegistryObject.create(loc, ForgeRegistries.ITEMS);
        GEAR_ENTRIES.put(reg, item::get);
        return reg;
    }
}