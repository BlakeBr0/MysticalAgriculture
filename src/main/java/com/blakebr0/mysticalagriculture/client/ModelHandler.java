package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.cucumber.client.model.RetextureableBlockModelWrapper;
import com.blakebr0.cucumber.client.model.RetextureableItemModelWrapper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.item.ExperienceCapsuleItem;
import com.blakebr0.mysticalagriculture.item.SoulJarItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceBowItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceCrossbowItem;
import com.blakebr0.mysticalagriculture.item.tool.EssenceFishingRodItem;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.IntStream;

public final class ModelHandler {
    private static final ResourceLocation MISSING_NO = new ResourceLocation("minecraft", "missingno");
    private static final ResourceLocation BLOCK_ATLAS = new ResourceLocation("minecraft", "textures/atlas/blocks.png");

    @SubscribeEvent
    public void onRegisterAdditionalModels(ModelEvent.RegisterAdditional event) {
        if (!ModConfigs.ANIMATED_GROWTH_ACCELERATORS.get()) {
            for (var type : new String[] { "block", "item" }) {
                for (var tier : new String[] { "inferium", "prudentium", "tertium", "imperium", "supremium" }) {
                    event.register(new ResourceLocation(MysticalAgriculture.MOD_ID, String.format("%s/%s_growth_accelerator_static", type, tier)));
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            event.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_resource_crop_" + i));
            event.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_mob_crop_" + i));
        }

        event.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_essence"));
        event.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_seeds"));
    }

    @SubscribeEvent
    public void onModelBakingCompleted(ModelEvent.BakingCompleted event) {
        var stopwatch = Stopwatch.createStarted();

        var registry = event.getModels();
        var bakery = event.getModelBakery();

        if (!ModConfigs.ANIMATED_GROWTH_ACCELERATORS.get()) {
            for (var tier : new String[] { "inferium", "prudentium", "tertium", "imperium", "supremium" }) {
                var loc = String.format("%s_growth_accelerator", tier);
                var blockModel = registry.get(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/" + loc + "_static"));
                var itemModel = registry.get(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/" + loc + "_static"));

                registry.replace(new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + loc), blockModel);
                registry.replace(new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + loc, "inventory"), itemModel);
            }
        }

        var cropModels = new HashMap<ResourceLocation, BakedModel[]>();
        var cropModelsGrown = new HashMap<ResourceLocation, RetextureableBlockModelWrapper>();

        for (var cropType : CropRegistry.getInstance().getTypes()) {
            cropModels.put(cropType.getId(), IntStream.range(0, 7)
                    .mapToObj(i -> registry.get(new ResourceLocation(cropType.getStemModel() + "_" + i)))
                    .toArray(BakedModel[]::new));

            var model = bakery.getModel(new ResourceLocation(cropType.getStemModel() + "_7"));
            var modelWrapper = new RetextureableBlockModelWrapper((BlockModel) model);

            cropModelsGrown.put(cropType.getId(), modelWrapper);
        }

        Function<Material, TextureAtlasSprite> getSprite = bakery.getAtlasSet()::getSprite;
        var generator = new ItemModelGenerator();

        var essenceModel = bakery.getModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_essence"));
        var essenceModelWrapper = new RetextureableItemModelWrapper((BlockModel) essenceModel);
        var seedsModel = bakery.getModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_seeds"));
        var seedsModelWrapper = new RetextureableItemModelWrapper((BlockModel) seedsModel);

        for (var crop : CropRegistry.getInstance().getCrops()) {
            var textures = crop.getTextures();
            var crops = crop.getCropBlock();
            var cropId = ForgeRegistries.BLOCKS.getKey(crops);

            if (cropId != null) {
                for (int i = 0; i < 7; i++) {
                    var location = new ModelResourceLocation(cropId, "age=" + i);
                    var bakedModel = registry.get(location);

                    if (bakedModel == null || bakedModel.getParticleIcon(ModelData.EMPTY).getName().equals(MISSING_NO)) {
                        var type = crop.getType().getId();
                        registry.replace(location, cropModels.get(type)[i]);
                    }
                }

                var location = new ModelResourceLocation(cropId, "age=7");
                var bakedModel = registry.get(location);

                if (bakedModel == null || bakedModel.getParticleIcon(ModelData.EMPTY).getName().equals(MISSING_NO)) {
                    var texture = crop.getTextures().getFlowerTexture();
                    var cropRetexturedModel = cropModelsGrown.get(crop.getType().getId()).retexture(ImmutableMap.of("flower", texture.toString()));
                    var cropBakedModel = cropRetexturedModel.bake(bakery, getSprite, BlockModelRotation.X0_Y0, location);

                    registry.replace(location, cropBakedModel);
                }
            }

            var essence = crop.getEssenceItem();
            var essenceId = ForgeRegistries.ITEMS.getKey(essence);

            if (essenceId != null) {
                var location = new ModelResourceLocation(essenceId, "inventory");
                var bakedModel = registry.get(location);

                if (bakedModel == null || bakedModel.getParticleIcon(ModelData.EMPTY).getName().equals(MISSING_NO)) {
                    var texture = textures.getEssenceTexture();
                    var retexture = essenceModelWrapper.retexture(ImmutableMap.of("layer0", texture.toString()));
                    var generated = generator.generateBlockModel(getSprite, retexture);
                    var model = generated.bake(bakery, generated, getSprite, BlockModelRotation.X0_Y0, location, false);

                    registry.replace(location, model);
                }
            }

            var seeds = crop.getSeedsItem();
            var seedsId = ForgeRegistries.ITEMS.getKey(seeds);

            if (seedsId != null) {
                var location = new ModelResourceLocation(seedsId, "inventory");
                var bakedModel = registry.get(location);

                if (bakedModel == null || bakedModel.getParticleIcon(ModelData.EMPTY).getName().equals(MISSING_NO)) {
                    var texture = textures.getSeedTexture();
                    var retexture = seedsModelWrapper.retexture(ImmutableMap.of("layer0", texture.toString()));
                    var generated = generator.generateBlockModel(getSprite, retexture);
                    var model = generated.bake(bakery, generated, getSprite, BlockModelRotation.X0_Y0, location, false);

                    registry.replace(location, model);
                }
            }
        }

        stopwatch.stop();

        MysticalAgriculture.LOGGER.info("Model replacement took {} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getAtlas().location().equals(BLOCK_ATLAS)) {
            for (var crop : CropRegistry.getInstance().getCrops()) {
                var textures = crop.getTextures();

                event.addSprite(textures.getFlowerTexture());
                event.addSprite(textures.getEssenceTexture());
                event.addSprite(textures.getSeedTexture());
            }

            event.addSprite(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/essence_vessel_contents"));
        }
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        for (var crop : CropRegistry.getInstance().getCrops()) {
            ItemBlockRenderTypes.setRenderLayer(crop.getCropBlock(), RenderType.cutoutMipped());
        }

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
