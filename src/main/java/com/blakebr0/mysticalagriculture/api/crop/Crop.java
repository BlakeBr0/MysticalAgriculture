package com.blakebr0.mysticalagriculture.api.crop;

import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

/**
 * The default implementation of {@link ICrop}
 *
 * Use or extend this class for your crops
 */
public class Crop implements ICrop {
    private final ResourceLocation id;
    private Component displayName;
    private CropTier tier;
    private CropType type;
    private int flowerColor;
    private int essenceColor;
    private int seedColor;
    private CropTextures textures;
    private Supplier<? extends CropBlock> crop;
    private Supplier<? extends Item> essence;
    private Supplier<? extends ItemNameBlockItem> seeds;
    private Supplier<? extends Block> crux;
    private LazyIngredient craftingMaterial;
    private boolean enabled;
    private boolean registerCropBlock;
    private boolean registerEssenceItem;
    private boolean registerSeedsItem;
    private boolean hasEffect;
    private CropRecipes recipeConfig;
    private Set<ResourceLocation> requiredBiomes;

    /**
     * Represents a new crop for registration
     * @param id the id of this crop, the path is used to generate the name
     * @param tier the tier of this crop
     * @param type the type of this crop, like resource or mob
     * @param craftingMaterial the crafting ingredient for this crop
     */
    public Crop(ResourceLocation id, CropTier tier, CropType type, LazyIngredient craftingMaterial) {
        this(id, tier, type, new CropTextures(), craftingMaterial);
    }

    /**
     * Represents a new crop for registration
     * @param id the id of this crop, the path is used to generate the name
     * @param tier the tier of this crop
     * @param type the type of this crop, like resource or mob
     * @param craftingMaterial the crafting ingredient for this crop
     */
    public Crop(ResourceLocation id, CropTier tier, CropType type, int color, LazyIngredient craftingMaterial) {
        this(id, tier, type, new CropTextures(), color, craftingMaterial);
    }

    /**
     * Represents a new crop for registration
     * @param id the id of this crop, the path is used to generate the name
     * @param tier the tier of this crop
     * @param type the type of this crop, like resource or mob
     * @param textures the textures of this crop
     * @param craftingMaterial the crafting ingredient for this crop
     */
    public Crop(ResourceLocation id, CropTier tier, CropType type, CropTextures textures, LazyIngredient craftingMaterial) {
        this(id, tier, type, textures, -1, craftingMaterial);
    }

    /**
     * Represents a new crop for registration
     * @param id the id of this crop, the path is used to generate the name
     * @param tier the tier of this crop
     * @param type the type of this crop, like resource or mob
     * @param textures the textures of this crop
     * @param color the color to color the textures with
     * @param craftingMaterial the crafting ingredient for this crop
     */
    public Crop(ResourceLocation id, CropTier tier, CropType type, CropTextures textures, int color, LazyIngredient craftingMaterial) {
        this.id = id;
        this.tier = tier;
        this.type = type;
        this.textures = textures.init(id);
        this.setColor(color);
        this.craftingMaterial = craftingMaterial;
        this.enabled = true;
        this.registerCropBlock = true;
        this.registerEssenceItem = true;
        this.registerSeedsItem = true;
        this.hasEffect = false;
        this.recipeConfig = new CropRecipes();
        this.requiredBiomes = new HashSet<>();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public Component getDisplayName() {
        return this.displayName != null ? this.displayName : ICrop.super.getDisplayName();
    }

    @Override
    public ICrop setDisplayName(Component name) {
        this.displayName = name;
        return this;
    }

    @Override
    public CropTier getTier() {
        return this.tier;
    }

    @Override
    public ICrop setTier(CropTier tier) {
        this.tier = tier;
        return this;
    }

    @Override
    public CropType getType() {
        return this.type;
    }

    @Override
    public ICrop setType(CropType type) {
        this.type = type;
        return this;
    }

    @Override
    public int getFlowerColor() {
        return this.flowerColor;
    }

    @Override
    public Crop setFlowerColor(int color) {
        this.flowerColor = color;
        return this;
    }

    @Override
    public CropTextures getTextures() {
        return this.textures;
    }

    @Override
    public int getEssenceColor() {
        return this.essenceColor;
    }

    @Override
    public Crop setEssenceColor(int color) {
        this.essenceColor = color;
        return this;
    }

    @Override
    public int getSeedColor() {
        return this.seedColor;
    }

    @Override
    public Crop setSeedColor(int color) {
        this.seedColor = color;
        return this;
    }

    @Override
    public CropBlock getCrop() {
        return this.crop == null ? null : this.crop.get();
    }

    @Override
    public ICrop setCrop(Supplier<? extends CropBlock> crop, boolean register) {
        this.crop = crop;
        this.registerCropBlock = register;
        return this;
    }

    @Override
    public boolean shouldRegisterCropBlock() {
        return this.registerCropBlock;
    }

    @Override
    public Item getEssence() {
        return this.essence == null ? null : this.essence.get();
    }

    @Override
    public ICrop setEssence(Supplier<? extends Item> essence, boolean register) {
        this.essence = essence;
        this.registerEssenceItem = register;
        return this;
    }

    @Override
    public boolean shouldRegisterEssenceItem() {
        return this.registerEssenceItem;
    }

    @Override
    public ItemNameBlockItem getSeeds() {
        return this.seeds == null ? null : this.seeds.get();
    }

    @Override
    public ICrop setSeeds(Supplier<? extends ItemNameBlockItem> seeds, boolean register) {
        this.seeds = seeds;
        this.registerSeedsItem = register;
        return this;
    }

    @Override
    public boolean shouldRegisterSeedsItem() {
        return this.registerSeedsItem;
    }

    @Override
    public Ingredient getCraftingMaterial() {
        return this.craftingMaterial.getIngredient();
    }

    @Override
    public ICrop setCraftingMaterial(LazyIngredient ingredient) {
        this.craftingMaterial = ingredient;
        return this;
    }

    @Override
    public LazyIngredient getLazyIngredient() {
        return this.craftingMaterial;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public ICrop setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @Override
    public Block getCrux() {
        return this.crux == null ? null : this.crux.get();
    }

    @Override
    public ICrop setCrux(Supplier<? extends Block> crux) {
        this.crux = crux;
        return this;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return this.hasEffect;
    }

    @Override
    public ICrop setHasEffect(boolean hasEffect) {
        this.hasEffect = hasEffect;
        return this;
    }

    @Override
    public CropRecipes getRecipeConfig() {
        return this.recipeConfig;
    }

    @Override
    public Set<ResourceLocation> getRequiredBiomes() {
        return this.requiredBiomes;
    }

    @Override
    public ICrop addRequiredBiome(ResourceLocation id) {
        this.requiredBiomes.add(id);
        return this;
    }

    public Crop setColor(int color) {
        this.setFlowerColor(color);
        this.setEssenceColor(color);
        this.setSeedColor(color);

        return this;
    }
}
