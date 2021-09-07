package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.cucumber.client.model.RetextureableBlockModelWrapper;
import com.blakebr0.cucumber.client.model.RetextureableItemModelWrapper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.item.ExperienceCapsuleItem;
import com.blakebr0.mysticalagriculture.item.SoulJarItem;
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
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.IntStream;

public final class ModelHandler {
    private static final Logger LOGGER = LogManager.getLogger(MysticalAgriculture.NAME);

    private static final ResourceLocation MISSING_NO = new ResourceLocation("minecraft", "missingno");
    private static final ResourceLocation BLOCK_ATLAS = new ResourceLocation("minecraft", "textures/atlas/blocks.png");

    @SubscribeEvent
    public void onRegisterModels(ModelRegistryEvent event) {
        if (!ModConfigs.ANIMATED_GROWTH_ACCELERATORS.get()) {
            for (var type : new String[] { "block", "item" }) {
                for (var tier : new String[] { "inferium", "prudentium", "tertium", "imperium", "supremium" }) {
                    ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, String.format("%s/%s_growth_accelerator_static", type, tier)));
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_resource_crop_" + i));
            ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_mob_crop_" + i));
        }

        ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_essence"));
        ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_seeds"));
    }

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event) {
        var stopwatch = Stopwatch.createStarted();

        var registry = event.getModelRegistry();
        var bakery = event.getModelLoader();

        if (!ModConfigs.ANIMATED_GROWTH_ACCELERATORS.get()) {
            for (var tier : new String[] { "inferium", "prudentium", "tertium", "imperium", "supremium" }) {
                var loc = String.format("%s_growth_accelerator", tier);
                var blockModel = registry.get(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/" + loc + "_static"));
                var itemModel = registry.get(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/" + loc + "_static"));

                registry.replace(new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + loc), blockModel);
                registry.replace(new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + loc, "inventory"), itemModel);
            }
        }

        Map<String, BakedModel[]> cropModels = new HashMap<>();
        Map<String, RetextureableBlockModelWrapper> cropModelsGrown = new HashMap<>();

        MysticalAgricultureAPI.CROP_TYPES.forEach(type -> {
            cropModels.put(type.getName(), IntStream.range(0, 7)
                    .mapToObj(i -> registry.get(new ResourceLocation(type.getStemModel() + "_" + i)))
                    .toArray(BakedModel[]::new));

            var model = bakery.getModel(new ResourceLocation(type.getStemModel() + "_7"));
            var modelWrapper = new RetextureableBlockModelWrapper((BlockModel) model);

            cropModelsGrown.put(type.getName(), modelWrapper);
        });

        Function<Material, TextureAtlasSprite> getSprite = bakery.getSpriteMap()::getSprite;
        var generator = new ItemModelGenerator();

        var essenceModel = bakery.getModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_essence"));
        var essenceModelWrapper = new RetextureableItemModelWrapper((BlockModel) essenceModel);
        var seedsModel = bakery.getModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_seeds"));
        var seedsModelWrapper = new RetextureableItemModelWrapper((BlockModel) seedsModel);

        CropRegistry.getInstance().getCrops().forEach(crop -> {
            var textures = crop.getTextures();
            var crops = crop.getCropBlock();

            if (crops.getRegistryName() != null) {
                for (int i = 0; i < 7; i++) {
                    var location = new ModelResourceLocation(crops.getRegistryName(), "age=" + i);
                    var bakedModel = registry.get(location);

                    if (bakedModel == null || bakedModel.getParticleTexture(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
                        String type = crop.getType().getName();
                        registry.replace(location, cropModels.get(type)[i]);
                    }
                }

                var location = new ModelResourceLocation(crops.getRegistryName(), "age=7");
                var bakedModel = registry.get(location);

                if (bakedModel == null || bakedModel.getParticleTexture(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
                    var texture = crop.getTextures().getFlowerTexture();
                    var cropRetexturedModel = cropModelsGrown.get(crop.getType().getName()).retexture(ImmutableMap.of("flower", texture.toString()));
                    var cropBakedModel = cropRetexturedModel.bake(bakery, getSprite, BlockModelRotation.X0_Y0, location);

                    registry.replace(location, cropBakedModel);
                }
            }

            var essence = crop.getEssenceItem();

            if (essence.getRegistryName() != null) {
                var location = new ModelResourceLocation(essence.getRegistryName(), "inventory");
                var bakedModel = registry.get(location);

                if (bakedModel == null || bakedModel.getParticleTexture(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
                    var texture = textures.getEssenceTexture();
                    var retexture = essenceModelWrapper.retexture(ImmutableMap.of("layer0", texture.toString()));
                    var generated = generator.generateBlockModel(getSprite, retexture);
                    var model = generated.bake(bakery, generated, getSprite, BlockModelRotation.X0_Y0, location, false);

                    registry.replace(location, model);
                }
            }

            var seeds = crop.getSeedsItem();

            if (seeds.getRegistryName() != null) {
                var location = new ModelResourceLocation(seeds.getRegistryName(), "inventory");
                var bakedModel = registry.get(location);

                if (bakedModel == null || bakedModel.getParticleTexture(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
                    var texture = textures.getSeedTexture();
                    var retexture = seedsModelWrapper.retexture(ImmutableMap.of("layer0", texture.toString()));
                    var generated = generator.generateBlockModel(getSprite, retexture);
                    var model = generated.bake(bakery, generated, getSprite, BlockModelRotation.X0_Y0, location, false);

                    registry.replace(location, model);
                }
            }
        });

        stopwatch.stop();

        LOGGER.info("Model replacement took {} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getMap().location().equals(BLOCK_ATLAS)) {
            CropRegistry.getInstance().getCrops().forEach(crop -> {
                var textures = crop.getTextures();

                event.addSprite(textures.getFlowerTexture());
                event.addSprite(textures.getEssenceTexture());
                event.addSprite(textures.getSeedTexture());
            });
        }
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PROSPERITY_ORE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.INFERIUM_ORE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOULIUM_ORE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOUL_GLASS.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WITHERPROOF_GLASS.get(), RenderType.translucent());

        CropRegistry.getInstance().getCrops().forEach(crop -> {
            ItemBlockRenderTypes.setRenderLayer(crop.getCropBlock(), RenderType.cutoutMipped());
        });

        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.EXPERIENCE_CAPSULE.get(), new ResourceLocation("fill"), ExperienceCapsuleItem.getFillPropertyGetter());
            ItemProperties.register(ModItems.SOUL_JAR.get(), new ResourceLocation("fill"), SoulJarItem.getFillPropertyGetter());
        });
    }
}
