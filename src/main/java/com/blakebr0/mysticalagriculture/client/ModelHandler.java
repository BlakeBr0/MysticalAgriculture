package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.cucumber.client.model.RetextureableBlockModelWrapper;
import com.blakebr0.cucumber.client.model.RetextureableItemModelWrapper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.item.ExperienceCapsuleItem;
import com.blakebr0.mysticalagriculture.item.SoulJarItem;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.client.renderer.item.ItemProperties;
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
            for (String type : new String[] { "block", "item" }) {
                for (String tier : new String[] { "inferium", "prudentium", "tertium", "imperium", "supremium" }) {
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
        Stopwatch stopwatch = Stopwatch.createStarted();

        Map<ResourceLocation, BakedModel> registry = event.getModelRegistry();
        ModelBakery bakery = event.getModelLoader();

        if (!ModConfigs.ANIMATED_GROWTH_ACCELERATORS.get()) {
            for (String tier : new String[] { "inferium", "prudentium", "tertium", "imperium", "supremium" }) {
                String loc = String.format("%s_growth_accelerator", tier);
                BakedModel blockModel = registry.get(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/" + loc + "_static"));
                registry.replace(new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + loc), blockModel);
                BakedModel itemModel = registry.get(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/" + loc + "_static"));
                registry.replace(new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + loc, "inventory"), itemModel);
            }
        }

        Map<String, BakedModel[]> cropModels = new HashMap<>();
        Map<String, RetextureableBlockModelWrapper> cropModelsGrown = new HashMap<>();
        MysticalAgricultureAPI.CROP_TYPES.forEach(type -> {
            cropModels.put(type.getName(), IntStream.range(0, 7)
                    .mapToObj(i -> registry.get(new ResourceLocation(type.getStemModel() + "_" + i)))
                    .toArray(BakedModel[]::new));

            UnbakedModel model = bakery.getModel(new ResourceLocation(type.getStemModel() + "_7"));
            RetextureableBlockModelWrapper modelWrapper = new RetextureableBlockModelWrapper((BlockModel) model);
            cropModelsGrown.put(type.getName(), modelWrapper);
        });

        Function<Material, TextureAtlasSprite> getSprite = bakery.getSpriteMap()::getSprite;
        ItemModelGenerator generator = new ItemModelGenerator();

        UnbakedModel essenceModel = bakery.getModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_essence"));
        RetextureableItemModelWrapper essenceModelWrapper = new RetextureableItemModelWrapper((BlockModel) essenceModel);
        UnbakedModel seedsModel = bakery.getModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_seeds"));
        RetextureableItemModelWrapper seedsModelWrapper = new RetextureableItemModelWrapper((BlockModel) seedsModel);

        CropRegistry.getInstance().getCrops().forEach(crop -> {
            CropTextures textures = crop.getTextures();

            CropBlock crops = crop.getCrop();
            if (crops.getRegistryName() != null) {
                for (int i = 0; i < 7; i++) {
                    ModelResourceLocation location = new ModelResourceLocation(crops.getRegistryName(), "age=" + i);
                    BakedModel bakedModel = registry.get(location);
                    if (bakedModel == null || bakedModel.getParticleTexture(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
                        String type = crop.getType().getName();
                        registry.replace(location, cropModels.get(type)[i]);
                    }
                }

                ModelResourceLocation location = new ModelResourceLocation(crops.getRegistryName(), "age=7");
                BakedModel bakedModel = registry.get(location);
                if (bakedModel == null || bakedModel.getParticleTexture(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
                    ResourceLocation texture = crop.getTextures().getFlowerTexture();
                    UnbakedModel cropRetexturedModel = cropModelsGrown.get(crop.getType().getName()).retexture(ImmutableMap.of("flower", texture.toString()));
                    BakedModel cropBakedModel = cropRetexturedModel.bake(bakery, getSprite, BlockModelRotation.X0_Y0, location);
                    registry.replace(location, cropBakedModel);
                }
            }

            Item essence = crop.getEssence();
            if (essence.getRegistryName() != null) {
                ModelResourceLocation location = new ModelResourceLocation(essence.getRegistryName(), "inventory");
                BakedModel bakedModel = registry.get(location);
                if (bakedModel == null || bakedModel.getParticleTexture(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
                    ResourceLocation texture = textures.getEssenceTexture();
                    RetextureableItemModelWrapper retexture = essenceModelWrapper.retexture(ImmutableMap.of("layer0", texture.toString()));
                    BlockModel generated = generator.generateBlockModel(getSprite, retexture);
                    BakedModel model = generated.bake(bakery, generated, getSprite, BlockModelRotation.X0_Y0, location, false);
                    registry.replace(location, model);
                }
            }

            ItemNameBlockItem seeds = crop.getSeeds();
            if (seeds.getRegistryName() != null) {
                ModelResourceLocation location = new ModelResourceLocation(seeds.getRegistryName(), "inventory");
                BakedModel bakedModel = registry.get(location);
                if (bakedModel == null || bakedModel.getParticleTexture(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
                    ResourceLocation texture = textures.getSeedTexture();
                    RetextureableItemModelWrapper retexture = seedsModelWrapper.retexture(ImmutableMap.of("layer0", texture.toString()));
                    BlockModel generated = generator.generateBlockModel(getSprite, retexture);
                    BakedModel model = generated.bake(bakery, generated, getSprite, BlockModelRotation.X0_Y0, location, false);
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
                CropTextures textures = crop.getTextures();

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
            ItemBlockRenderTypes.setRenderLayer(crop.getCrop(), RenderType.cutoutMipped());
        });

        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.EXPERIENCE_CAPSULE.get(), new ResourceLocation("fill"), ExperienceCapsuleItem.getFillPropertyGetter());
            ItemProperties.register(ModItems.SOUL_JAR.get(), new ResourceLocation("fill"), SoulJarItem.getFillPropertyGetter());
        });
    }
}
