package com.blakebr0.mysticalagriculture.api.crop;

import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collections;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Represents a crop and all of its information
 */
// TODO: 1.17: remove
@Deprecated
public interface ICrop {
    /**
     * The id of this crop, the modid is taken from the namespace for {@link ICrop#getModId()},
     * and the path is used for {@link ICrop#getName()}
     * @return the id of this crop
     */
    ResourceLocation getId();

    /**
     * The internal name of this crop.
     * This is used for registration, so it MUST be all lowercase with underscores for spaces
     * @return the internal name of this crop
     */
    default String getName() {
        return this.getId().getPath();
    }

    /**
     * Used to get the internal name of this crop with an _suffix
     * @param suffix the suffix to append (without the initial underscore)
     * @return the name with _suffix
     */
    default String getNameWithSuffix(String suffix) {
        return String.format("%s_%s", this.getName(), suffix);
    }

    /**
     * Get the localized name of this crop using the key crop.{@link ICrop#getModId()}.{@link ICrop#getName()},
     * or the display name set via {@link ICrop#setDisplayName(ITextComponent)}
     * @return the localized name of this crop
     */
    default ITextComponent getDisplayName() {
        return new TranslationTextComponent(String.format("crop.%s.%s", this.getModId(), this.getName()));
    }

    /**
     * Sets the display name of this crop
     * @param name the new display name
     * @return this crop
     */
    default ICrop setDisplayName(ITextComponent name) {
        return this;
    }

    /**
     * The tier/group this crop belongs to
     * @return the tier of this crop
     */
    CropTier getTier();

    /**
     * Sets this crops tier
     * @param tier the tier
     * @return this crop
     */
    ICrop setTier(CropTier tier);

    /**
     * The type of crop this crop belongs to
     * @return the type of this crop
     */
    CropType getType();

    /**
     * Sets this crops type
     * @param type the type
     * @return this crop
     */
    ICrop setType(CropType type);

    /**
     * Whether or not this crop's flower should be colored using the color defined by {@link #getFlowerColor()}
     * @return is the crop's flower colored
     */
    default boolean isFlowerColored() {
        return this.getFlowerColor() > -1;
    }

    /**
     * The color that should overlay this crop's flower
     * @return the color of this crop's flower
     */
    int getFlowerColor();

    /**
     * Set the color that should overlay this crop's flower
     * @param color the color
     * @return this crop
     */
    ICrop setFlowerColor(int color);

    /**
     * All the textures related to this crop
     * @return the crop's textures
     */
    CropTextures getTextures();

    /**
     * Whether or not this crop's essence should be colored using the color defined by {@link #getEssenceColor()}
     * @return is the crop's essence colored
     */
    default boolean isEssenceColored() {
        return this.getEssenceColor() > -1;
    }

    /**
     * The color that should overlay this crop's essence
     * @return the color of this crop's essence
     */
    int getEssenceColor();

    /**
     * Set the color that should overlay this crop's essence
     * @param color the color
     * @return this crop
     */
    ICrop setEssenceColor(int color);

    /**
     * Whether or not this crop's seed should be colored using the color defined by {@link #getSeedColor()}
     * @return is the crop's seed colored
     */
    default boolean isSeedColored() {
        return this.getSeedColor() > -1;
    }

    /**
     * The color that should overlay this crop's seed
     * @return the color of this crop's seed
     */
    int getSeedColor();

    /**
     * Set the color that should overlay this crop's seed
     * @param color the color
     * @return this crop
     */
    ICrop setSeedColor(int color);

    /**
     * The modid of the mod that registered this crop
     * @return the modid of this crop
     */
    default String getModId() {
        return this.getId().getNamespace();
    }

    /**
     * The crop block for this crop type
     * @return the crop block
     */
    CropsBlock getCrop();

    /**
     * Used to set the crop block instance for this crop, the supplier should return the same instance every time
     * @param crop the crop block
     * @return this crop
     */
    default ICrop setCrop(Supplier<? extends CropsBlock> crop) {
        return this.setCrop(crop, false);
    }

    /**
     * Used to set the crop block instance for this crop, the supplier should return the same instance every time
     * @param crop the crop block
     * @param register should this block be registered
     * @return this crop
     */
    ICrop setCrop(Supplier<? extends CropsBlock> crop, boolean register);

    /**
     * Should this crop's crop block be registered
     * @return should be registered
     */
    boolean shouldRegisterCropBlock();

    /**
     * The essence item for this crop type
     * @return the essence item
     */
    Item getEssence();

    /**
     * Used to set the essence item instance for this crop, the supplier should return the same instance every time
     * @param essence the essence item
     * @return this crop
     */
    default ICrop setEssence(Supplier<? extends Item> essence) {
        return this.setEssence(essence, false);
    }

    /**
     * Used to set the essence item instance for this crop, the supplier should return the same instance every time
     * @param essence the essence item
     * @param register should this item be registered
     * @return this crop
     */
    ICrop setEssence(Supplier<? extends Item> essence, boolean register);

    /**
     * Should this crop's essence item be registered
     * @return should be registered
     */
    boolean shouldRegisterEssenceItem();

    /**
     * The seeds item for this crop type
     * @return the seed item
     */
    BlockNamedItem getSeeds();

    /**
     * Used to set the seeds item instance for this crop, the supplier should return the same instance every time
     * @param seeds the seeds item
     * @return this crop
     */
    default ICrop setSeeds(Supplier<? extends BlockNamedItem> seeds) {
        return this.setSeeds(seeds, false);
    }

    /**
     * Used to set the seeds item instance for this crop, the supplier should return the same instance every time
     * @param seeds the seeds item
     * @param register should this item be registered
     * @return this crop
     */
    ICrop setSeeds(Supplier<? extends BlockNamedItem> seeds, boolean register);

    /**
     * Should this crop's seeds item be registered
     * @return should be registered
     */
    boolean shouldRegisterSeedsItem();

    /**
     * Get the chance of a second seed/essence dropping based on the block provided
     * @param block the block below the crop
     * @return the chance of a second seed/essence dropping
     */
    default double getSecondaryChance(Block block) {
        double chance = 0;
        if (!this.getTier().hasSecondarySeedDrop())
            return chance;
        if (block instanceof IEssenceFarmland)
            chance += 0.1;
        if (this.getTier().isEffectiveFarmland(block))
            chance += 0.1;

        return chance;
    }

    /**
     * Get the crafting ingredient for this crop from the underlying {@link LazyIngredient}
     * @return the crafting material of this crop
     */
    Ingredient getCraftingMaterial();

    /**
     * Used to set the underlying {@link LazyIngredient} for this crop
     * @param ingredient the crafting ingredient for this crop
     * @return this crop
     */
    ICrop setCraftingMaterial(LazyIngredient ingredient);

    /**
     * The underlying {@link LazyIngredient} for this crop, use {@link ICrop#getCraftingMaterial()}
     * to get the actual ingredient
     * @return the underlying lazy ingredient of this crop
     */
    default LazyIngredient getLazyIngredient() {
        return LazyIngredient.EMPTY;
    }

    /**
     * Whether or not this crop has recipes and shows up in the creative menu
     * @return is this crop enabled
     */
    boolean isEnabled();

    /**
     * Set whether or not this crop should be hidden from the game
     * @param enabled the enabled state
     */
    ICrop setEnabled(boolean enabled);

    /**
     * The crux block for this crop type
     * @return the crux block
     */
    Block getCrux();

    /**
     * Used to set the crux block instance for this crop, the supplier should return the same instance every time
     * @param crux the crux block
     * @return this crop
     */
    ICrop setCrux(Supplier<? extends Block> crux);

    /**
     * Whether or not this crops items should have the enchantment glint effect
     * @param stack the stack
     * @return should the crop have the glint effect
     */
    default boolean hasEffect(ItemStack stack) {
        return false;
    }

    /**
     * Set whether or not this crop should have the enchantment glint effect
     * @param hasEffect the effect state
     * @return this crop
     */
    default ICrop setHasEffect(boolean hasEffect) {
        return this;
    }

    /**
     * Get the dynamic recipes config for this crop
     * @return the crop's recipe config
     */
    default CropRecipes getRecipeConfig() {
        return new CropRecipes();
    }

    /**
     * A set of biome ids that this crop can grow in
     * @return this crop's required biomes
     */
    default Set<ResourceLocation> getRequiredBiomes() {
        return Collections.emptySet();
    }

    /**
     * Add a biome id to the list of required biomes for this crop
     * @param id the biome id
     * @return this crop
     */
    default ICrop addRequiredBiome(ResourceLocation id) {
        return this;
    }
}
