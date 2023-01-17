package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.client.model.EssenceArmorModel;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.item.ExperienceCapsuleItem;
import com.blakebr0.mysticalagriculture.item.SoulJarItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceBowItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceCrossbowItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceFishingRodItem;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.google.common.base.Stopwatch;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public final class ModelHandler {
    private static final ResourceLocation MISSING_NO = new ResourceLocation("minecraft", "missingno");
    public static final ModelLayerLocation ESSENCE_ARMOR_INNER_LAYER = new ModelLayerLocation(new ResourceLocation("minecraft:player"), "mysticalagriculture:essence_armor_inner");
    public static final ModelLayerLocation ESSENCE_ARMOR_OUTER_LAYER = new ModelLayerLocation(new ResourceLocation("minecraft:player"), "mysticalagriculture:essence_armor_outer");

    @SubscribeEvent
    public void onRegisterAdditionalModels(ModelEvent.RegisterAdditional event) {
        if (!ModConfigs.ANIMATED_GROWTH_ACCELERATORS.get()) {
            for (var type : List.of("block", "item")) {
                for (var tier : List.of("inferium", "prudentium", "tertium", "imperium", "supremium")) {
                    event.register(new ResourceLocation(MysticalAgriculture.MOD_ID, String.format("%s/%s_growth_accelerator_static", type, tier)));
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            event.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_resource_crop_" + i));
            event.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_mob_crop_" + i));
        }

        for (var type : CropRegistry.getInstance().getTypes()) {
            event.register(new ResourceLocation(CropTextures.FLOWER_INGOT_BLANK + "_" + type.getName()));
            event.register(new ResourceLocation(CropTextures.FLOWER_ROCK_BLANK + "_" + type.getName()));
            event.register(new ResourceLocation(CropTextures.FLOWER_DUST_BLANK + "_" + type.getName()));
            event.register(new ResourceLocation(CropTextures.FLOWER_FACE_BLANK + "_" + type.getName()));
        }

        event.register(CropTextures.ESSENCE_INGOT_BLANK);
        event.register(CropTextures.ESSENCE_ROCK_BLANK);
        event.register(CropTextures.ESSENCE_DUST_BLANK);
        event.register(CropTextures.ESSENCE_GEM_BLANK);
        event.register(CropTextures.ESSENCE_TALL_GEM_BLANK);
        event.register(CropTextures.ESSENCE_DIAMOND_BLANK);
        event.register(CropTextures.ESSENCE_QUARTZ_BLANK);
        event.register(CropTextures.ESSENCE_FLAME_BLANK);
        event.register(CropTextures.ESSENCE_ROD_BLANK);

        event.register(CropTextures.SEED_BLANK);
    }

    @SubscribeEvent
    public void onModifyBakingResults(ModelEvent.ModifyBakingResult event) {
        var stopwatch = Stopwatch.createStarted();

        var registry = event.getModels();

        if (!ModConfigs.ANIMATED_GROWTH_ACCELERATORS.get()) {
            for (var tier : List.of("inferium", "prudentium", "tertium", "imperium", "supremium")) {
                var loc = String.format("%s_growth_accelerator", tier);
                var blockModel = registry.get(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/" + loc + "_static"));
                var itemModel = registry.get(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/" + loc + "_static"));

                registry.replace(new ModelResourceLocation(MysticalAgriculture.MOD_ID, loc, ""), blockModel);
                registry.replace(new ModelResourceLocation(MysticalAgriculture.MOD_ID, loc, "inventory"), itemModel);
            }
        }

        var cropModels = new HashMap<ResourceLocation, BakedModel[]>();

        for (var cropType : CropRegistry.getInstance().getTypes()) {
            cropModels.put(cropType.getId(), IntStream.range(0, 7)
                    .mapToObj(i -> registry.get(new ResourceLocation(cropType.getStemModel() + "_" + i)))
                    .toArray(BakedModel[]::new));
        }

        for (var crop : CropRegistry.getInstance().getCrops()) {
            var textures = crop.getTextures();
            var crops = crop.getCropBlock();
            var cropId = ForgeRegistries.BLOCKS.getKey(crops);

            if (cropId != null) {
                for (int i = 0; i < 7; i++) {
                    var location = new ModelResourceLocation(cropId, "age=" + i);
                    var bakedModel = registry.get(location);

                    if (bakedModel == null || bakedModel.getParticleIcon(ModelData.EMPTY).contents().name().equals(MISSING_NO)) {
                        var type = crop.getType().getId();
                        registry.replace(location, cropModels.get(type)[i]);
                    }
                }

                var location = new ModelResourceLocation(cropId, "age=7");
                var bakedModel = registry.get(location);

                if (bakedModel == null || bakedModel.getParticleIcon(ModelData.EMPTY).contents().name().equals(MISSING_NO)) {
                    var flower = textures.getFlowerTexture();
                    var type = crop.getType().getId();
                    var path = new ResourceLocation(type.getNamespace(), flower.getPath() + "_" + type.getPath());
                    var model = registry.get(path);

                    registry.replace(location, model);
                }
            }

            var essence = crop.getEssenceItem();
            var essenceId = ForgeRegistries.ITEMS.getKey(essence);

            if (essenceId != null) {
                var location = new ModelResourceLocation(essenceId, "inventory");
                var bakedModel = registry.get(location);

                if (bakedModel == null || bakedModel.getParticleIcon(ModelData.EMPTY).contents().name().equals(MISSING_NO)) {
                    var texture = textures.getEssenceTexture();
                    var model = registry.get(texture);

                    registry.replace(location, model);
                }
            }

            var seeds = crop.getSeedsItem();
            var seedsId = ForgeRegistries.ITEMS.getKey(seeds);

            if (seedsId != null) {
                var location = new ModelResourceLocation(seedsId, "inventory");
                var bakedModel = registry.get(location);

                if (bakedModel == null || bakedModel.getParticleIcon(ModelData.EMPTY).contents().name().equals(MISSING_NO)) {
                    var texture = textures.getSeedTexture();
                    var model = registry.get(texture);

                    registry.replace(location, model);
                }
            }
        }

        stopwatch.stop();

        MysticalAgriculture.LOGGER.info("Model replacement took {} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @SubscribeEvent
    public void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ESSENCE_ARMOR_INNER_LAYER, EssenceArmorModel::createInnerLayer);
        event.registerLayerDefinition(ESSENCE_ARMOR_OUTER_LAYER, EssenceArmorModel::createOuterLayer);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.EXPERIENCE_CAPSULE.get(), new ResourceLocation("fill"), ExperienceCapsuleItem.getFillPropertyGetter());
            ItemProperties.register(ModItems.SOUL_JAR.get(), new ResourceLocation("fill"), SoulJarItem.getFillPropertyGetter());
            ItemProperties.register(ModItems.INFERIUM_BOW.get(), new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.INFERIUM_BOW.get(), new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.INFERIUM_CROSSBOW.get(), new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.INFERIUM_CROSSBOW.get(), new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.INFERIUM_CROSSBOW.get(), new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
            ItemProperties.register(ModItems.INFERIUM_CROSSBOW.get(), new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
            ItemProperties.register(ModItems.INFERIUM_FISHING_ROD.get(), new ResourceLocation("cast"), EssenceFishingRodItem.getCastPropertyGetter());
            ItemProperties.register(ModItems.PRUDENTIUM_BOW.get(), new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.PRUDENTIUM_BOW.get(), new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.PRUDENTIUM_CROSSBOW.get(), new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.PRUDENTIUM_CROSSBOW.get(), new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.PRUDENTIUM_CROSSBOW.get(), new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
            ItemProperties.register(ModItems.PRUDENTIUM_CROSSBOW.get(), new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
            ItemProperties.register(ModItems.PRUDENTIUM_FISHING_ROD.get(), new ResourceLocation("cast"), EssenceFishingRodItem.getCastPropertyGetter());
            ItemProperties.register(ModItems.TERTIUM_BOW.get(), new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.TERTIUM_BOW.get(), new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.TERTIUM_CROSSBOW.get(), new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.TERTIUM_CROSSBOW.get(), new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.TERTIUM_CROSSBOW.get(), new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
            ItemProperties.register(ModItems.TERTIUM_CROSSBOW.get(), new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
            ItemProperties.register(ModItems.TERTIUM_FISHING_ROD.get(), new ResourceLocation("cast"), EssenceFishingRodItem.getCastPropertyGetter());
            ItemProperties.register(ModItems.IMPERIUM_BOW.get(), new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.IMPERIUM_BOW.get(), new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.IMPERIUM_CROSSBOW.get(), new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.IMPERIUM_CROSSBOW.get(), new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.IMPERIUM_CROSSBOW.get(), new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
            ItemProperties.register(ModItems.IMPERIUM_CROSSBOW.get(), new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
            ItemProperties.register(ModItems.IMPERIUM_FISHING_ROD.get(), new ResourceLocation("cast"), EssenceFishingRodItem.getCastPropertyGetter());
            ItemProperties.register(ModItems.SUPREMIUM_BOW.get(), new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.SUPREMIUM_BOW.get(), new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.SUPREMIUM_CROSSBOW.get(), new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.SUPREMIUM_CROSSBOW.get(), new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.SUPREMIUM_CROSSBOW.get(), new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
            ItemProperties.register(ModItems.SUPREMIUM_CROSSBOW.get(), new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
            ItemProperties.register(ModItems.SUPREMIUM_FISHING_ROD.get(), new ResourceLocation("cast"), EssenceFishingRodItem.getCastPropertyGetter());
            ItemProperties.register(ModItems.AWAKENED_SUPREMIUM_BOW.get(), new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.AWAKENED_SUPREMIUM_BOW.get(), new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.AWAKENED_SUPREMIUM_CROSSBOW.get(), new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.AWAKENED_SUPREMIUM_CROSSBOW.get(), new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.AWAKENED_SUPREMIUM_CROSSBOW.get(), new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
            ItemProperties.register(ModItems.AWAKENED_SUPREMIUM_CROSSBOW.get(), new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
            ItemProperties.register(ModItems.AWAKENED_SUPREMIUM_FISHING_ROD.get(), new ResourceLocation("cast"), EssenceFishingRodItem.getCastPropertyGetter());
        });
    }
}
