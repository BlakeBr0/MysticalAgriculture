package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.api.crop.CropTiers;
import com.blakebr0.mysticalagriculture.item.armor.EssenceBootsItem;
import com.blakebr0.mysticalagriculture.item.armor.EssenceChestplateItem;
import com.blakebr0.mysticalagriculture.item.armor.EssenceHelmetItem;
import com.blakebr0.mysticalagriculture.item.armor.EssenceLeggingsItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceAxeItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceHoeItem;
import com.blakebr0.mysticalagriculture.item.tool.EssencePickaxeItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceShovelItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceSwordItem;
import com.blakebr0.mysticalagriculture.lib.ModArmorMaterial;
import com.blakebr0.mysticalagriculture.lib.ModItemTier;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.ITEM_GROUP;

public class ModItems {
    public static final List<BlockItem> ITEM_BLOCKS = new ArrayList<>();

    public static final RegistryObject<BaseItem> PROSPERITY_SHARD = get("prosperity_shard");
    public static final RegistryObject<EssenceItem> INFERIUM_ESSENCE = get("inferium_essence");
    public static final RegistryObject<EssenceItem> PRUDENTIUM_ESSENCE = get("prudentium_essence");
    public static final RegistryObject<EssenceItem> INTERMEDIUM_ESSENCE = get("intermedium_essence");
    public static final RegistryObject<EssenceItem> IMPERIUM_ESSENCE = get("imperium_essence");
    public static final RegistryObject<EssenceItem> SUPREMIUM_ESSENCE = get("supremium_essence");
    public static final RegistryObject<BaseItem> SOULIUM_DUST = get("soulium_dust");
    public static final RegistryObject<BaseItem> PROSPERITY_INGOT = get("prosperity_ingot");
    public static final RegistryObject<BaseItem> INFERIUM_INGOT = get("inferium_ingot");
    public static final RegistryObject<BaseItem> PRUDENTIUM_INGOT = get("prudentium_ingot");
    public static final RegistryObject<BaseItem> INTERMEDIUM_INGOT = get("intermedium_ingot");
    public static final RegistryObject<BaseItem> IMPERIUM_INGOT = get("imperium_ingot");
    public static final RegistryObject<BaseItem> SUPREMIUM_INGOT = get("supremium_ingot");
    public static final RegistryObject<BaseItem> SOULIUM_INGOT = get("soulium_ingot");
    public static final RegistryObject<BaseItem> PROSPERITY_NUGGET = get("prosperity_nugget");
    public static final RegistryObject<BaseItem> INFERIUM_NUGGET = get("inferium_nugget");
    public static final RegistryObject<BaseItem> PRUDENTIUM_NUGGET = get("prudentium_nugget");
    public static final RegistryObject<BaseItem> INTERMEDIUM_NUGGET = get("intermedium_nugget");
    public static final RegistryObject<BaseItem> IMPERIUM_NUGGET = get("imperium_nugget");
    public static final RegistryObject<BaseItem> SUPREMIUM_NUGGET = get("supremium_nugget");
    public static final RegistryObject<BaseItem> SOULIUM_NUGGET = get("soulium_nugget");
    public static final RegistryObject<BaseItem> PROSPERITY_GEMSTONE = get("prosperity_gemstone");
    public static final RegistryObject<BaseItem> INFERIUM_GEMSTONE = get("inferium_gemstone");
    public static final RegistryObject<BaseItem> PRUDENTIUM_GEMSTONE = get("prudentium_gemstone");
    public static final RegistryObject<BaseItem> INTERMEDIUM_GEMSTONE = get("intermedium_gemstone");
    public static final RegistryObject<BaseItem> IMPERIUM_GEMSTONE = get("imperium_gemstone");
    public static final RegistryObject<BaseItem> SUPREMIUM_GEMSTONE = get("supremium_gemstone");
    public static final RegistryObject<BaseItem> SOULIUM_GEMSTONE = get("soulium_gemstone");
    public static final RegistryObject<BaseItem> PROSPERITY_SEED_BASE = get("prosperity_seed_base");
    public static final RegistryObject<BaseItem> SOULIUM_SEED_BASE = get("soulium_seed_base");
    public static final RegistryObject<BaseItem> SOUL_DUST = get("soul_dust");
    public static final RegistryObject<InfusionCrystalItem> INFUSION_CRYSTAL = get("infusion_crystal");
    public static final RegistryObject<MasterInfusionCrystalItem> MASTER_INFUSION_CRYSTAL = get("master_infusion_crystal");
    public static final RegistryObject<FertilizedEssenceItem> FERTILIZED_ESSENCE = get("fertilized_essence");
    public static final RegistryObject<MysticalFertilizerItem> MYSTICAL_FERTILIZER = get("mystical_fertilizer");

    public static final RegistryObject<EssenceSwordItem> INFERIUM_SWORD = get("inferium_sword");
    public static final RegistryObject<EssencePickaxeItem> INFERIUM_PICKAXE = get("inferium_pickaxe");
    public static final RegistryObject<EssenceShovelItem> INFERIUM_SHOVEL = get("inferium_shovel");
    public static final RegistryObject<EssenceAxeItem> INFERIUM_AXE = get("inferium_axe");
    public static final RegistryObject<EssenceHoeItem> INFERIUM_HOE = get("inferium_hoe");
    public static final RegistryObject<EssenceSwordItem> PRUDENTIUM_SWORD = get("prudentium_sword");
    public static final RegistryObject<EssencePickaxeItem> PRUDENTIUM_PICKAXE = get("prudentium_pickaxe");
    public static final RegistryObject<EssenceShovelItem> PRUDENTIUM_SHOVEL = get("prudentium_shovel");
    public static final RegistryObject<EssenceAxeItem> PRUDENTIUM_AXE = get("prudentium_axe");
    public static final RegistryObject<EssenceHoeItem> PRUDENTIUM_HOE = get("prudentium_hoe");
    public static final RegistryObject<EssenceSwordItem> INTERMEDIUM_SWORD = get("intermedium_sword");
    public static final RegistryObject<EssencePickaxeItem> INTERMEDIUM_PICKAXE = get("intermedium_pickaxe");
    public static final RegistryObject<EssenceShovelItem> INTERMEDIUM_SHOVEL = get("intermedium_shovel");
    public static final RegistryObject<EssenceAxeItem> INTERMEDIUM_AXE = get("intermedium_axe");
    public static final RegistryObject<EssenceHoeItem> INTERMEDIUM_HOE = get("intermedium_hoe");
    public static final RegistryObject<EssenceSwordItem> IMPERIUM_SWORD = get("imperium_sword");
    public static final RegistryObject<EssencePickaxeItem> IMPERIUM_PICKAXE = get("imperium_pickaxe");
    public static final RegistryObject<EssenceShovelItem> IMPERIUM_SHOVEL = get("imperium_shovel");
    public static final RegistryObject<EssenceAxeItem> IMPERIUM_AXE = get("imperium_axe");
    public static final RegistryObject<EssenceHoeItem> IMPERIUM_HOE = get("imperium_hoe");
    public static final RegistryObject<EssenceSwordItem> SUPREMIUM_SWORD = get("supremium_sword");
    public static final RegistryObject<EssencePickaxeItem> SUPREMIUM_PICKAXE = get("supremium_pickaxe");
    public static final RegistryObject<EssenceShovelItem> SUPREMIUM_SHOVEL = get("supremium_shovel");
    public static final RegistryObject<EssenceAxeItem> SUPREMIUM_AXE = get("supremium_axe");
    public static final RegistryObject<EssenceHoeItem> SUPREMIUM_HOE = get("supremium_hoe");
    public static final RegistryObject<EssenceHelmetItem> INFERIUM_HELMET = get("inferium_helmet");
    public static final RegistryObject<EssenceChestplateItem> INFERIUM_CHESTPLATE = get("inferium_chestplate");
    public static final RegistryObject<EssenceLeggingsItem> INFERIUM_LEGGINGS = get("inferium_leggings");
    public static final RegistryObject<EssenceBootsItem> INFERIUM_BOOTS = get("inferium_boots");
    public static final RegistryObject<EssenceHelmetItem> PRUDENTIUM_HELMET = get("prudentium_helmet");
    public static final RegistryObject<EssenceChestplateItem> PRUDENTIUM_CHESTPLATE = get("prudentium_chestplate");
    public static final RegistryObject<EssenceLeggingsItem> PRUDENTIUM_LEGGINGS = get("prudentium_leggings");
    public static final RegistryObject<EssenceBootsItem> PRUDENTIUM_BOOTS = get("prudentium_boots");
    public static final RegistryObject<EssenceHelmetItem> INTERMEDIUM_HELMET = get("intermedium_helmet");
    public static final RegistryObject<EssenceChestplateItem> INTERMEDIUM_CHESTPLATE = get("intermedium_chestplate");
    public static final RegistryObject<EssenceLeggingsItem> INTERMEDIUM_LEGGINGS = get("intermedium_leggings");
    public static final RegistryObject<EssenceBootsItem> INTERMEDIUM_BOOTS = get("intermedium_boots");
    public static final RegistryObject<EssenceHelmetItem> IMPERIUM_HELMET = get("imperium_helmet");
    public static final RegistryObject<EssenceChestplateItem> IMPERIUM_CHESTPLATE = get("imperium_chestplate");
    public static final RegistryObject<EssenceLeggingsItem> IMPERIUM_LEGGINGS = get("imperium_leggings");
    public static final RegistryObject<EssenceBootsItem> IMPERIUM_BOOTS = get("imperium_boots");
    public static final RegistryObject<EssenceHelmetItem> SUPREMIUM_HELMET = get("supremium_helmet");
    public static final RegistryObject<EssenceChestplateItem> SUPREMIUM_CHESTPLATE = get("supremium_chestplate");
    public static final RegistryObject<EssenceLeggingsItem> SUPREMIUM_LEGGINGS = get("supremium_leggings");
    public static final RegistryObject<EssenceBootsItem> SUPREMIUM_BOOTS = get("supremium_boots");

    @SubscribeEvent
    public void onRegisterItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        ITEM_BLOCKS.forEach(registry::register);

        registry.registerAll(
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("prosperity_shard"),
                new EssenceItem(CropTiers.ONE, p -> p.group(ITEM_GROUP)).setRegistryName("inferium_essence"),
                new EssenceItem(CropTiers.TWO, p -> p.group(ITEM_GROUP)).setRegistryName("prudentium_essence"),
                new EssenceItem(CropTiers.THREE, p -> p.group(ITEM_GROUP)).setRegistryName("intermedium_essence"),
                new EssenceItem(CropTiers.FOUR, p -> p.group(ITEM_GROUP)).setRegistryName("imperium_essence"),
                new EssenceItem(CropTiers.FIVE, p -> p.group(ITEM_GROUP)).setRegistryName("supremium_essence"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("soulium_dust"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("prosperity_ingot"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("inferium_ingot"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("intermedium_ingot"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("prudentium_ingot"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("imperium_ingot"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("supremium_ingot"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("soulium_ingot"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("prosperity_nugget"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("inferium_nugget"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("prudentium_nugget"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("intermedium_nugget"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("imperium_nugget"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("supremium_nugget"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("soulium_nugget"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("prosperity_gemstone"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("inferium_gemstone"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("prudentium_gemstone"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("intermedium_gemstone"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("imperium_gemstone"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("supremium_gemstone"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("soulium_gemstone"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("prosperity_seed_base"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("soulium_seed_base"),
                new BaseItem(p -> p.group(ITEM_GROUP)).setRegistryName("soul_dust"),
                new InfusionCrystalItem(1000, p -> p.group(ITEM_GROUP)).setRegistryName("infusion_crystal"),
                new MasterInfusionCrystalItem(p -> p.group(ITEM_GROUP)).setRegistryName("master_infusion_crystal"),
                new FertilizedEssenceItem(p -> p.group(ITEM_GROUP)).setRegistryName("fertilized_essence"),
                new MysticalFertilizerItem(p -> p.group(ITEM_GROUP)).setRegistryName("mystical_fertilizer")
        );

        CropRegistry.getInstance().onRegisterItems(registry);

        registry.registerAll(
                new EssenceSwordItem(ModItemTier.INFERIUM, 3, -2.4F, p -> p.group(ITEM_GROUP)).setRegistryName("inferium_sword"),
                new EssencePickaxeItem(ModItemTier.INFERIUM, 1, -2.8F, p -> p.group(ITEM_GROUP)).setRegistryName("inferium_pickaxe"),
                new EssenceShovelItem(ModItemTier.INFERIUM, 1.5F, -3.0F, p -> p.group(ITEM_GROUP)).setRegistryName("inferium_shovel"),
                new EssenceAxeItem(ModItemTier.INFERIUM, 5.0F, -3.0F, p -> p.group(ITEM_GROUP)).setRegistryName("inferium_axe"),
                new EssenceHoeItem(ModItemTier.INFERIUM, 0.0F, p -> p.group(ITEM_GROUP)).setRegistryName("inferium_hoe"),
                new EssenceSwordItem(ModItemTier.PRUDENTIUM, 3, -2.4F, p -> p.group(ITEM_GROUP)).setRegistryName("prudentium_sword"),
                new EssencePickaxeItem(ModItemTier.PRUDENTIUM, 1, -2.8F, p -> p.group(ITEM_GROUP)).setRegistryName("prudentium_pickaxe"),
                new EssenceShovelItem(ModItemTier.PRUDENTIUM, 1.5F, -3.0F, p -> p.group(ITEM_GROUP)).setRegistryName("prudentium_shovel"),
                new EssenceAxeItem(ModItemTier.PRUDENTIUM, 5.0F, -3.0F, p -> p.group(ITEM_GROUP)).setRegistryName("prudentium_axe"),
                new EssenceHoeItem(ModItemTier.PRUDENTIUM, 0.0F, p -> p.group(ITEM_GROUP)).setRegistryName("prudentium_hoe"),
                new EssenceSwordItem(ModItemTier.INTERMEDIUM, 3, -2.4F, p -> p.group(ITEM_GROUP)).setRegistryName("intermedium_sword"),
                new EssencePickaxeItem(ModItemTier.INTERMEDIUM, 1, -2.8F, p -> p.group(ITEM_GROUP)).setRegistryName("intermedium_pickaxe"),
                new EssenceShovelItem(ModItemTier.INTERMEDIUM, 1.5F, -3.0F, p -> p.group(ITEM_GROUP)).setRegistryName("intermedium_shovel"),
                new EssenceAxeItem(ModItemTier.INTERMEDIUM, 5.0F, -3.0F, p -> p.group(ITEM_GROUP)).setRegistryName("intermedium_axe"),
                new EssenceHoeItem(ModItemTier.INTERMEDIUM, 0.0F, p -> p.group(ITEM_GROUP)).setRegistryName("intermedium_hoe"),
                new EssenceSwordItem(ModItemTier.IMPERIUM, 3, -2.4F, p -> p.group(ITEM_GROUP)).setRegistryName("imperium_sword"),
                new EssencePickaxeItem(ModItemTier.IMPERIUM, 1, -2.8F, p -> p.group(ITEM_GROUP)).setRegistryName("imperium_pickaxe"),
                new EssenceShovelItem(ModItemTier.IMPERIUM, 1.5F, -3.0F, p -> p.group(ITEM_GROUP)).setRegistryName("imperium_shovel"),
                new EssenceAxeItem(ModItemTier.IMPERIUM, 5.0F, -3.0F, p -> p.group(ITEM_GROUP)).setRegistryName("imperium_axe"),
                new EssenceHoeItem(ModItemTier.IMPERIUM, 0.0F, p -> p.group(ITEM_GROUP)).setRegistryName("imperium_hoe"),
                new EssenceSwordItem(ModItemTier.SUPREMIUM, 3, -2.4F, p -> p.group(ITEM_GROUP)).setRegistryName("supremium_sword"),
                new EssencePickaxeItem(ModItemTier.SUPREMIUM, 1, -2.8F, p -> p.group(ITEM_GROUP)).setRegistryName("supremium_pickaxe"),
                new EssenceShovelItem(ModItemTier.SUPREMIUM, 1.5F, -3.0F, p -> p.group(ITEM_GROUP)).setRegistryName("supremium_shovel"),
                new EssenceAxeItem(ModItemTier.SUPREMIUM, 5.0F, -3.0F, p -> p.group(ITEM_GROUP)).setRegistryName("supremium_axe"),
                new EssenceHoeItem(ModItemTier.SUPREMIUM, 0.0F, p -> p.group(ITEM_GROUP)).setRegistryName("supremium_hoe"),
                new EssenceHelmetItem(ModArmorMaterial.INFERIUM, p -> p.group(ITEM_GROUP)).setRegistryName("inferium_helmet"),
                new EssenceChestplateItem(ModArmorMaterial.INFERIUM, p -> p.group(ITEM_GROUP)).setRegistryName("inferium_chestplate"),
                new EssenceLeggingsItem(ModArmorMaterial.INFERIUM, p -> p.group(ITEM_GROUP)).setRegistryName("inferium_leggings"),
                new EssenceBootsItem(ModArmorMaterial.INFERIUM, p -> p.group(ITEM_GROUP)).setRegistryName("inferium_boots"),
                new EssenceHelmetItem(ModArmorMaterial.PRUDENTIUM, p -> p.group(ITEM_GROUP)).setRegistryName("prudentium_helmet"),
                new EssenceChestplateItem(ModArmorMaterial.PRUDENTIUM, p -> p.group(ITEM_GROUP)).setRegistryName("prudentium_chestplate"),
                new EssenceLeggingsItem(ModArmorMaterial.PRUDENTIUM, p -> p.group(ITEM_GROUP)).setRegistryName("prudentium_leggings"),
                new EssenceBootsItem(ModArmorMaterial.PRUDENTIUM, p -> p.group(ITEM_GROUP)).setRegistryName("prudentium_boots"),
                new EssenceHelmetItem(ModArmorMaterial.INTERMEDIUM, p -> p.group(ITEM_GROUP)).setRegistryName("intermedium_helmet"),
                new EssenceChestplateItem(ModArmorMaterial.INTERMEDIUM, p -> p.group(ITEM_GROUP)).setRegistryName("intermedium_chestplate"),
                new EssenceLeggingsItem(ModArmorMaterial.INTERMEDIUM, p -> p.group(ITEM_GROUP)).setRegistryName("intermedium_leggings"),
                new EssenceBootsItem(ModArmorMaterial.INTERMEDIUM, p -> p.group(ITEM_GROUP)).setRegistryName("intermedium_boots"),
                new EssenceHelmetItem(ModArmorMaterial.IMPERIUM, p -> p.group(ITEM_GROUP)).setRegistryName("imperium_helmet"),
                new EssenceChestplateItem(ModArmorMaterial.IMPERIUM, p -> p.group(ITEM_GROUP)).setRegistryName("imperium_chestplate"),
                new EssenceLeggingsItem(ModArmorMaterial.IMPERIUM, p -> p.group(ITEM_GROUP)).setRegistryName("imperium_leggings"),
                new EssenceBootsItem(ModArmorMaterial.IMPERIUM, p -> p.group(ITEM_GROUP)).setRegistryName("imperium_boots"),
                new EssenceHelmetItem(ModArmorMaterial.SUPREMIUM, p -> p.group(ITEM_GROUP)).setRegistryName("supremium_helmet"),
                new EssenceChestplateItem(ModArmorMaterial.SUPREMIUM, p -> p.group(ITEM_GROUP)).setRegistryName("supremium_chestplate"),
                new EssenceLeggingsItem(ModArmorMaterial.SUPREMIUM, p -> p.group(ITEM_GROUP)).setRegistryName("supremium_leggings"),
                new EssenceBootsItem(ModArmorMaterial.SUPREMIUM, p -> p.group(ITEM_GROUP)).setRegistryName("supremium_boots")
        );
    }

    private static <T extends Item> RegistryObject<T> get(String name) {
        return RegistryObject.of("mysticalagriculture:" + name, () -> Item.class);
    }
}
