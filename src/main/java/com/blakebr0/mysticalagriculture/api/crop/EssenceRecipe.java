package com.blakebr0.mysticalagriculture.api.crop;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

public class EssenceRecipe implements ICropGetter {
    private static final Logger LOGGER = LogManager.getLogger(MysticalAgriculture.NAME);
    private final Map<Character, LazyIngredient> inputMap = new LinkedHashMap<>();
    private final NonNullList<Ingredient> inputs = NonNullList.withSize(9, Ingredient.EMPTY);
    private final ICrop crop;
    private String pattern;
    private ItemStack output;
    private boolean valid = true;

    public EssenceRecipe(String pattern, ICrop crop, ItemStack output, Object... inputs) {
        this.pattern = pattern;
        this.crop = crop;
        this.output = output;

        char toBind = ' ';
        for (Object obj : inputs) {
            if (toBind == ' ' && obj instanceof Character) {
                toBind = (char) obj;
            } else {
                if (toBind != ' ' && obj instanceof LazyIngredient) {
                    this.inputMap.put(toBind, (LazyIngredient) obj);
                    toBind = ' ';
                } else {
                    LOGGER.error("Recipe key in recipe for crop {} is invalid", crop.getId());
                    this.valid = false;
                    break;
                }
            }
        }
    }

    @Override
    public ICrop getCrop() {
        return this.crop;
    }

    /**
     * Gets a new instance of the output ItemStack
     * @return the output
     */
    public ItemStack getOutput() {
        return this.output.copy();
    }

    /**
     * Gets the ingredient list for this recipe
     * @return the inputs
     */
    public NonNullList<Ingredient> getInputs() {
        if (this.inputs.stream().allMatch(i -> i == Ingredient.EMPTY)) {
            for (int i = 0; i < this.pattern.length(); i++) {
                Ingredient ingredient = this.inputMap.getOrDefault(this.pattern.charAt(i), LazyIngredient.EMPTY).getIngredient();
                this.inputs.set(i, ingredient);
            }
        }

        return this.inputs;
    }

    /**
     * Whether or not this recipe is valid
     * @return is valid
     */
    public boolean isValid() {
        return this.valid;
    }
}
