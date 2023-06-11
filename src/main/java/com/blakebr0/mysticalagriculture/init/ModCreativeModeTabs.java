package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.cucumber.util.FeatureFlagDisplayItemGenerator;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import com.blakebr0.mysticalagriculture.registry.AugmentRegistry;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.blakebr0.mysticalagriculture.registry.MobSoulTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MysticalAgriculture.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = REGISTRY.register("creative_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.mysticalagriculture"))
            .icon(() -> new ItemStack(ModItems.INFERIUM_ESSENCE.get()))
            .displayItems(FeatureFlagDisplayItemGenerator.create((parameters, output) -> {
                var stack = ItemStack.EMPTY;

                output.accept(ModBlocks.PROSPERITY_BLOCK);
                output.accept(ModBlocks.INFERIUM_BLOCK);
                output.accept(ModBlocks.PRUDENTIUM_BLOCK);
                output.accept(ModBlocks.TERTIUM_BLOCK);
                output.accept(ModBlocks.IMPERIUM_BLOCK);
                output.accept(ModBlocks.SUPREMIUM_BLOCK);
                output.accept(ModBlocks.AWAKENED_SUPREMIUM_BLOCK);
                output.accept(ModBlocks.SOULIUM_BLOCK);
                output.accept(ModBlocks.PROSPERITY_INGOT_BLOCK);
                output.accept(ModBlocks.INFERIUM_INGOT_BLOCK);
                output.accept(ModBlocks.PRUDENTIUM_INGOT_BLOCK);
                output.accept(ModBlocks.TERTIUM_INGOT_BLOCK);
                output.accept(ModBlocks.IMPERIUM_INGOT_BLOCK);
                output.accept(ModBlocks.SUPREMIUM_INGOT_BLOCK);
                output.accept(ModBlocks.AWAKENED_SUPREMIUM_INGOT_BLOCK);
                output.accept(ModBlocks.SOULIUM_INGOT_BLOCK);
                output.accept(ModBlocks.PROSPERITY_GEMSTONE_BLOCK);
                output.accept(ModBlocks.INFERIUM_GEMSTONE_BLOCK);
                output.accept(ModBlocks.PRUDENTIUM_GEMSTONE_BLOCK);
                output.accept(ModBlocks.TERTIUM_GEMSTONE_BLOCK);
                output.accept(ModBlocks.IMPERIUM_GEMSTONE_BLOCK);
                output.accept(ModBlocks.SUPREMIUM_GEMSTONE_BLOCK);
                output.accept(ModBlocks.AWAKENED_SUPREMIUM_GEMSTONE_BLOCK);
                output.accept(ModBlocks.SOULIUM_GEMSTONE_BLOCK);
                output.accept(ModBlocks.INFERIUM_FARMLAND);
                output.accept(ModBlocks.PRUDENTIUM_FARMLAND);
                output.accept(ModBlocks.TERTIUM_FARMLAND);
                output.accept(ModBlocks.IMPERIUM_FARMLAND);
                output.accept(ModBlocks.SUPREMIUM_FARMLAND);
                output.accept(ModBlocks.INFERIUM_GROWTH_ACCELERATOR);
                output.accept(ModBlocks.PRUDENTIUM_GROWTH_ACCELERATOR);
                output.accept(ModBlocks.TERTIUM_GROWTH_ACCELERATOR);
                output.accept(ModBlocks.IMPERIUM_GROWTH_ACCELERATOR);
                output.accept(ModBlocks.SUPREMIUM_GROWTH_ACCELERATOR);
                output.accept(ModBlocks.INFERIUM_FURNACE);
                output.accept(ModBlocks.PRUDENTIUM_FURNACE);
                output.accept(ModBlocks.TERTIUM_FURNACE);
                output.accept(ModBlocks.IMPERIUM_FURNACE);
                output.accept(ModBlocks.SUPREMIUM_FURNACE);
                output.accept(ModBlocks.AWAKENED_SUPREMIUM_FURNACE);
                output.accept(ModBlocks.PROSPERITY_ORE);
                output.accept(ModBlocks.DEEPSLATE_PROSPERITY_ORE);
                output.accept(ModBlocks.INFERIUM_ORE);
                output.accept(ModBlocks.DEEPSLATE_INFERIUM_ORE);
                output.accept(ModBlocks.SOULIUM_ORE);
                output.accept(ModBlocks.SOULSTONE);
                output.accept(ModBlocks.SOULSTONE_COBBLE);
                output.accept(ModBlocks.SOULSTONE_BRICKS);
                output.accept(ModBlocks.SOULSTONE_CRACKED_BRICKS);
                output.accept(ModBlocks.SOULSTONE_CHISELED_BRICKS);
                output.accept(ModBlocks.SOULSTONE_SMOOTH);
                output.accept(ModBlocks.SOUL_GLASS);
                output.accept(ModBlocks.SOULSTONE_SLAB);
                output.accept(ModBlocks.SOULSTONE_COBBLE_SLAB);
                output.accept(ModBlocks.SOULSTONE_BRICKS_SLAB);
                output.accept(ModBlocks.SOULSTONE_SMOOTH_SLAB);
                output.accept(ModBlocks.SOULSTONE_STAIRS);
                output.accept(ModBlocks.SOULSTONE_COBBLE_STAIRS);
                output.accept(ModBlocks.SOULSTONE_BRICKS_STAIRS);
                output.accept(ModBlocks.SOULSTONE_COBBLE_WALL);
                output.accept(ModBlocks.SOULSTONE_BRICKS_WALL);
                output.accept(ModBlocks.WITHERPROOF_BLOCK);
                output.accept(ModBlocks.WITHERPROOF_BRICKS);
                output.accept(ModBlocks.WITHERPROOF_GLASS);
                output.accept(ModBlocks.INFUSION_PEDESTAL);
                output.accept(ModBlocks.INFUSION_ALTAR);
                output.accept(ModBlocks.AWAKENING_PEDESTAL);
                output.accept(ModBlocks.AWAKENING_ALTAR);
                output.accept(ModBlocks.ESSENCE_VESSEL);
                output.accept(ModBlocks.TINKERING_TABLE);
                output.accept(ModBlocks.MACHINE_FRAME);
                output.accept(ModBlocks.BASIC_REPROCESSOR);
                output.accept(ModBlocks.INFERIUM_REPROCESSOR);
                output.accept(ModBlocks.PRUDENTIUM_REPROCESSOR);
                output.accept(ModBlocks.TERTIUM_REPROCESSOR);
                output.accept(ModBlocks.IMPERIUM_REPROCESSOR);
                output.accept(ModBlocks.SUPREMIUM_REPROCESSOR);
                output.accept(ModBlocks.AWAKENED_SUPREMIUM_REPROCESSOR);
                output.accept(ModBlocks.SOUL_EXTRACTOR);
                output.accept(ModBlocks.HARVESTER);

                output.accept(ModItems.PROSPERITY_SHARD);
                output.accept(ModItems.INFERIUM_ESSENCE);
                output.accept(ModItems.PRUDENTIUM_ESSENCE);
                output.accept(ModItems.TERTIUM_ESSENCE);
                output.accept(ModItems.IMPERIUM_ESSENCE);
                output.accept(ModItems.SUPREMIUM_ESSENCE);
                output.accept(ModItems.AWAKENED_SUPREMIUM_ESSENCE);
                output.accept(ModItems.PROSPERITY_INGOT);
                output.accept(ModItems.INFERIUM_INGOT);
                output.accept(ModItems.PRUDENTIUM_INGOT);
                output.accept(ModItems.TERTIUM_INGOT);
                output.accept(ModItems.IMPERIUM_INGOT);
                output.accept(ModItems.SUPREMIUM_INGOT);
                output.accept(ModItems.AWAKENED_SUPREMIUM_INGOT);
                output.accept(ModItems.SOULIUM_INGOT);
                output.accept(ModItems.PROSPERITY_NUGGET);
                output.accept(ModItems.INFERIUM_NUGGET);
                output.accept(ModItems.PRUDENTIUM_NUGGET);
                output.accept(ModItems.TERTIUM_NUGGET);
                output.accept(ModItems.IMPERIUM_NUGGET);
                output.accept(ModItems.SUPREMIUM_NUGGET);
                output.accept(ModItems.AWAKENED_SUPREMIUM_NUGGET);
                output.accept(ModItems.SOULIUM_NUGGET);
                output.accept(ModItems.PROSPERITY_GEMSTONE);
                output.accept(ModItems.INFERIUM_GEMSTONE);
                output.accept(ModItems.PRUDENTIUM_GEMSTONE);
                output.accept(ModItems.TERTIUM_GEMSTONE);
                output.accept(ModItems.IMPERIUM_GEMSTONE);
                output.accept(ModItems.SUPREMIUM_GEMSTONE);
                output.accept(ModItems.AWAKENED_SUPREMIUM_GEMSTONE);
                output.accept(ModItems.SOULIUM_GEMSTONE);
                output.accept(ModItems.PROSPERITY_SEED_BASE);
                output.accept(ModItems.SOULIUM_SEED_BASE);
                output.accept(ModItems.SOUL_DUST);
                output.accept(ModItems.SOULIUM_DUST);
                output.accept(ModItems.COGNIZANT_DUST);
                output.accept(ModItems.SOULIUM_DAGGER);
                output.accept(ModItems.PASSIVE_SOULIUM_DAGGER);
                output.accept(ModItems.HOSTILE_SOULIUM_DAGGER);
                output.accept(ModItems.CREATIVE_SOULIUM_DAGGER);
                output.accept(ModItems.INFUSION_CRYSTAL);
                output.accept(ModItems.MASTER_INFUSION_CRYSTAL);
                output.accept(ModItems.FERTILIZED_ESSENCE);
                output.accept(ModItems.MYSTICAL_FERTILIZER);
                output.accept(ModItems.AIR_AGGLOMERATIO);
                output.accept(ModItems.EARTH_AGGLOMERATIO);
                output.accept(ModItems.WATER_AGGLOMERATIO);
                output.accept(ModItems.NATURE_AGGLOMERATIO);
                output.accept(ModItems.DYE_AGGLOMERATIO);
                output.accept(ModItems.NETHER_AGGLOMERATIO);
                output.accept(ModItems.CORAL_AGGLOMERATIO);
                output.accept(ModItems.HONEY_AGGLOMERATIO);
                output.accept(ModItems.PRISMARINE_AGGLOMERATIO);
                output.accept(ModItems.END_AGGLOMERATIO);

                if (ModCrops.MYSTICAL_FLOWER.isEnabled()) {
                    output.accept(ModItems.MYSTICAL_FLOWER_AGGLOMERATIO);
                }

                output.accept(ModItems.EXPERIENCE_DROPLET);
                output.accept(ModItems.WAND);
                output.accept(ModItems.BLANK_SKULL);
                output.accept(ModItems.BLANK_RECORD);
                output.accept(ModItems.UNATTUNED_AUGMENT);
                output.accept(ModItems.SOUL_JAR);

                for (var type : MobSoulTypeRegistry.getInstance().getMobSoulTypes()) {
                    output.accept(MobSoulUtils.getFilledSoulJar(type, ModItems.SOUL_JAR.get()));
                }

                output.accept(ModItems.EXPERIENCE_CAPSULE);

                stack = new ItemStack(ModItems.WATERING_CAN.get());
                NBTHelper.setBoolean(stack, "Water", false);
                output.accept(stack);

                output.accept(ModItems.DIAMOND_SICKLE);
                output.accept(ModItems.DIAMOND_SCYTHE);
                output.accept(ModItems.UPGRADE_BASE);
                output.accept(ModItems.INFERIUM_UPGRADE);
                output.accept(ModItems.PRUDENTIUM_UPGRADE);
                output.accept(ModItems.TERTIUM_UPGRADE);
                output.accept(ModItems.IMPERIUM_UPGRADE);
                output.accept(ModItems.SUPREMIUM_UPGRADE);
                output.accept(ModItems.AWAKENED_SUPREMIUM_UPGRADE);

                for (var crop : CropRegistry.getInstance().getCrops()) {
                    if (crop.isEnabled()) {
                        output.accept(crop.getEssenceItem());
                    }
                }

                for (var crop : CropRegistry.getInstance().getCrops()) {
                    if (crop.isEnabled()) {
                        output.accept(crop.getSeedsItem());
                    }
                }

                output.accept(ModItems.INFERIUM_SWORD);
                output.accept(ModItems.INFERIUM_PICKAXE);
                output.accept(ModItems.INFERIUM_SHOVEL);
                output.accept(ModItems.INFERIUM_AXE);
                output.accept(ModItems.INFERIUM_HOE);
//                output.accept(ModItems.IMPERIUM_STAFF);

                stack = new ItemStack(ModItems.INFERIUM_WATERING_CAN.get());
                NBTHelper.setBoolean(stack, "Water", false);
                NBTHelper.setBoolean(stack, "Active", false);
                output.accept(stack);
                output.accept(ModItems.INFERIUM_BOW);
                output.accept(ModItems.INFERIUM_CROSSBOW);
                output.accept(ModItems.INFERIUM_SHEARS);
                output.accept(ModItems.INFERIUM_FISHING_ROD);
                output.accept(ModItems.INFERIUM_SICKLE);
                output.accept(ModItems.INFERIUM_SCYTHE);
                output.accept(ModItems.PRUDENTIUM_SWORD);
                output.accept(ModItems.PRUDENTIUM_PICKAXE);
                output.accept(ModItems.PRUDENTIUM_SHOVEL);
                output.accept(ModItems.PRUDENTIUM_AXE);
                output.accept(ModItems.PRUDENTIUM_HOE);
//                output.accept(ModItems.PRUDENTIUM_STAFF);

                stack = new ItemStack(ModItems.PRUDENTIUM_WATERING_CAN.get());
                NBTHelper.setBoolean(stack, "Water", false);
                NBTHelper.setBoolean(stack, "Active", false);
                output.accept(stack);

                output.accept(ModItems.PRUDENTIUM_BOW);
                output.accept(ModItems.PRUDENTIUM_CROSSBOW);
                output.accept(ModItems.PRUDENTIUM_SHEARS);
                output.accept(ModItems.PRUDENTIUM_FISHING_ROD);
                output.accept(ModItems.PRUDENTIUM_SICKLE);
                output.accept(ModItems.PRUDENTIUM_SCYTHE);
                output.accept(ModItems.TERTIUM_SWORD);
                output.accept(ModItems.TERTIUM_PICKAXE);
                output.accept(ModItems.TERTIUM_SHOVEL);
                output.accept(ModItems.TERTIUM_AXE);
                output.accept(ModItems.TERTIUM_HOE);
//                output.accept(ModItems.TERTIUM_STAFF);

                stack = new ItemStack(ModItems.TERTIUM_WATERING_CAN.get());
                NBTHelper.setBoolean(stack, "Water", false);
                NBTHelper.setBoolean(stack, "Active", false);
                output.accept(stack);

                output.accept(ModItems.TERTIUM_BOW);
                output.accept(ModItems.TERTIUM_CROSSBOW);
                output.accept(ModItems.TERTIUM_SHEARS);
                output.accept(ModItems.TERTIUM_FISHING_ROD);
                output.accept(ModItems.TERTIUM_SICKLE);
                output.accept(ModItems.TERTIUM_SCYTHE);
                output.accept(ModItems.IMPERIUM_SWORD);
                output.accept(ModItems.IMPERIUM_PICKAXE);
                output.accept(ModItems.IMPERIUM_SHOVEL);
                output.accept(ModItems.IMPERIUM_AXE);
                output.accept(ModItems.IMPERIUM_HOE);
//                output.accept(ModItems.IMPERIUM_STAFF);

                stack = new ItemStack(ModItems.IMPERIUM_WATERING_CAN.get());
                NBTHelper.setBoolean(stack, "Water", false);
                NBTHelper.setBoolean(stack, "Active", false);
                output.accept(stack);

                output.accept(ModItems.IMPERIUM_BOW);
                output.accept(ModItems.IMPERIUM_CROSSBOW);
                output.accept(ModItems.IMPERIUM_SHEARS);
                output.accept(ModItems.IMPERIUM_FISHING_ROD);
                output.accept(ModItems.IMPERIUM_SICKLE);
                output.accept(ModItems.IMPERIUM_SCYTHE);
                output.accept(ModItems.SUPREMIUM_SWORD);
                output.accept(ModItems.SUPREMIUM_PICKAXE);
                output.accept(ModItems.SUPREMIUM_SHOVEL);
                output.accept(ModItems.SUPREMIUM_AXE);
                output.accept(ModItems.SUPREMIUM_HOE);
//                output.accept(ModItems.SUPREMIUM_STAFF);

                stack = new ItemStack(ModItems.SUPREMIUM_WATERING_CAN.get());
                NBTHelper.setBoolean(stack, "Water", false);
                NBTHelper.setBoolean(stack, "Active", false);
                output.accept(stack);

                output.accept(ModItems.SUPREMIUM_BOW);
                output.accept(ModItems.SUPREMIUM_CROSSBOW);
                output.accept(ModItems.SUPREMIUM_SHEARS);
                output.accept(ModItems.SUPREMIUM_FISHING_ROD);
                output.accept(ModItems.SUPREMIUM_SICKLE);
                output.accept(ModItems.SUPREMIUM_SCYTHE);
                output.accept(ModItems.AWAKENED_SUPREMIUM_SWORD);
                output.accept(ModItems.AWAKENED_SUPREMIUM_PICKAXE);
                output.accept(ModItems.AWAKENED_SUPREMIUM_SHOVEL);
                output.accept(ModItems.AWAKENED_SUPREMIUM_AXE);
                output.accept(ModItems.AWAKENED_SUPREMIUM_HOE);
//                output.accept(ModItems.AWAKENED_SUPREMIUM_STAFF);

                stack = new ItemStack(ModItems.AWAKENED_SUPREMIUM_WATERING_CAN.get());
                NBTHelper.setBoolean(stack, "Water", false);
                NBTHelper.setBoolean(stack, "Active", false);
                output.accept(stack);

                output.accept(ModItems.AWAKENED_SUPREMIUM_BOW);
                output.accept(ModItems.AWAKENED_SUPREMIUM_CROSSBOW);
                output.accept(ModItems.AWAKENED_SUPREMIUM_SHEARS);
                output.accept(ModItems.AWAKENED_SUPREMIUM_FISHING_ROD);
                output.accept(ModItems.AWAKENED_SUPREMIUM_SICKLE);
                output.accept(ModItems.AWAKENED_SUPREMIUM_SCYTHE);

                output.accept(ModItems.INFERIUM_HELMET);
                output.accept(ModItems.INFERIUM_CHESTPLATE);
                output.accept(ModItems.INFERIUM_LEGGINGS);
                output.accept(ModItems.INFERIUM_BOOTS);
                output.accept(ModItems.PRUDENTIUM_HELMET);
                output.accept(ModItems.PRUDENTIUM_CHESTPLATE);
                output.accept(ModItems.PRUDENTIUM_LEGGINGS);
                output.accept(ModItems.PRUDENTIUM_BOOTS);
                output.accept(ModItems.TERTIUM_HELMET);
                output.accept(ModItems.TERTIUM_CHESTPLATE);
                output.accept(ModItems.TERTIUM_LEGGINGS);
                output.accept(ModItems.TERTIUM_BOOTS);
                output.accept(ModItems.IMPERIUM_HELMET);
                output.accept(ModItems.IMPERIUM_CHESTPLATE);
                output.accept(ModItems.IMPERIUM_LEGGINGS);
                output.accept(ModItems.IMPERIUM_BOOTS);
                output.accept(ModItems.SUPREMIUM_HELMET);
                output.accept(ModItems.SUPREMIUM_CHESTPLATE);
                output.accept(ModItems.SUPREMIUM_LEGGINGS);
                output.accept(ModItems.SUPREMIUM_BOOTS);
                output.accept(ModItems.AWAKENED_SUPREMIUM_HELMET);
                output.accept(ModItems.AWAKENED_SUPREMIUM_CHESTPLATE);
                output.accept(ModItems.AWAKENED_SUPREMIUM_LEGGINGS);
                output.accept(ModItems.AWAKENED_SUPREMIUM_BOOTS);

                for (var augment : AugmentRegistry.getInstance().getAugments()) {
                    if (augment.isEnabled()) {
                        output.accept(augment.getItem());
                    }
                }
            }))
            .build());
}
