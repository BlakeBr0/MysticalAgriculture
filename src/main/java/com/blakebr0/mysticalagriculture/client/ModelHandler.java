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
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.IntStream;

public final class ModelHandler {
    private static final ResourceLocation MISSING_NO = new ResourceLocation("minecraft", "missingno");
    private static final ResourceLocation BLOCK_ATLAS = new ResourceLocation("minecraft", "textures/atlas/blocks.png");

    @SubscribeEvent
    public void onRegisterModels(ModelRegistryEvent event) {
        if (!ModConfigs.ANIMATED_GROWTH_ACCELERATORS.get()) {
            for (var type : new String[] { "block", "item" }) {
                for (var tier : new String[] { "inferium", "prudentium", "tertium", "imperium", "supremium" }) {
                    ForgeModelBakery.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, String.format("%s/%s_growth_accelerator_static", type, tier)));
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            ForgeModelBakery.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_resource_crop_" + i));
            ForgeModelBakery.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_mob_crop_" + i));
        }

        ForgeModelBakery.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_essence"));
        ForgeModelBakery.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_seeds"));
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

        Function<Material, TextureAtlasSprite> getSprite = bakery.getSpriteMap()::getSprite;
        var generator = new ItemModelGenerator();

        var essenceModel = bakery.getModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_essence"));
        var essenceModelWrapper = new RetextureableItemModelWrapper((BlockModel) essenceModel);
        var seedsModel = bakery.getModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_seeds"));
        var seedsModelWrapper = new RetextureableItemModelWrapper((BlockModel) seedsModel);

        for (var crop : CropRegistry.getInstance().getCrops()) {
            var textures = crop.getTextures();
            var crops = crop.getCropBlock();

            if (crops.getRegistryName() != null) {
                for (int i = 0; i < 7; i++) {
                    var location = new ModelResourceLocation(crops.getRegistryName(), "age=" + i);
                    var bakedModel = registry.get(location);

                    if (bakedModel == null || bakedModel.getParticleIcon(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
                        var type = crop.getType().getId();
                        registry.replace(location, cropModels.get(type)[i]);
                    }
                }

                var location = new ModelResourceLocation(crops.getRegistryName(), "age=7");
                var bakedModel = registry.get(location);

                if (bakedModel == null || bakedModel.getParticleIcon(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
                    var texture = crop.getTextures().getFlowerTexture();
                    var cropRetexturedModel = cropModelsGrown.get(crop.getType().getId()).retexture(ImmutableMap.of("flower", texture.toString()));
                    var cropBakedModel = cropRetexturedModel.bake(bakery, getSprite, BlockModelRotation.X0_Y0, location);

                    registry.replace(location, cropBakedModel);
                }
            }

            var essence = crop.getEssenceItem();

            if (essence.getRegistryName() != null) {
                var location = new ModelResourceLocation(essence.getRegistryName(), "inventory");
                var bakedModel = registry.get(location);

                if (bakedModel == null || bakedModel.getParticleIcon(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
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

                if (bakedModel == null || bakedModel.getParticleIcon(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
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
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PROSPERITY_ORE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DEEPSLATE_PROSPERITY_ORE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.INFERIUM_ORE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DEEPSLATE_INFERIUM_ORE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOULIUM_ORE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOUL_GLASS.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WITHERPROOF_GLASS.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ESSENCE_VESSEL.get(), RenderType.translucent());

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
            ItemProperties.register(ModItems.PRUDENTIUM_BOW.get(), new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.PRUDENTIUM_BOW.get(), new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.PRUDENTIUM_CROSSBOW.get(), new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.PRUDENTIUM_CROSSBOW.get(), new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.PRUDENTIUM_CROSSBOW.get(), new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
            ItemProperties.register(ModItems.PRUDENTIUM_CROSSBOW.get(), new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
            ItemProperties.register(ModItems.TERTIUM_BOW.get(), new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.TERTIUM_BOW.get(), new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.TERTIUM_CROSSBOW.get(), new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.TERTIUM_CROSSBOW.get(), new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.TERTIUM_CROSSBOW.get(), new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
            ItemProperties.register(ModItems.TERTIUM_CROSSBOW.get(), new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
            ItemProperties.register(ModItems.IMPERIUM_BOW.get(), new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.IMPERIUM_BOW.get(), new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.IMPERIUM_CROSSBOW.get(), new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.IMPERIUM_CROSSBOW.get(), new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.IMPERIUM_CROSSBOW.get(), new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
            ItemProperties.register(ModItems.IMPERIUM_CROSSBOW.get(), new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
            ItemProperties.register(ModItems.SUPREMIUM_BOW.get(), new ResourceLocation("pull"), EssenceBowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.SUPREMIUM_BOW.get(), new ResourceLocation("pulling"), EssenceBowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.SUPREMIUM_CROSSBOW.get(), new ResourceLocation("pull"), EssenceCrossbowItem.getPullPropertyGetter());
            ItemProperties.register(ModItems.SUPREMIUM_CROSSBOW.get(), new ResourceLocation("pulling"), EssenceCrossbowItem.getPullingPropertyGetter());
            ItemProperties.register(ModItems.SUPREMIUM_CROSSBOW.get(), new ResourceLocation("charged"), EssenceCrossbowItem.getChargedPropertyGetter());
            ItemProperties.register(ModItems.SUPREMIUM_CROSSBOW.get(), new ResourceLocation("firework"), EssenceCrossbowItem.getFireworkPropertyGetter());
        });
    }
}
