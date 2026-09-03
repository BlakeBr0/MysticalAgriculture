package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.inventory.CItemStacksHandler;
import com.blakebr0.cucumber.inventory.CachedRecipe;
import com.blakebr0.cucumber.inventory.OnContentsChangedFunction;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.MultiblockPositions;
import com.blakebr0.mysticalagriculture.api.crafting.IInfusionRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.IActivatable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.item.ItemResource;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InfusionAltarTileEntity extends BaseInventoryTileEntity implements IActivatable {
    private final CItemStacksHandler inventory;
    private final CItemStacksHandler recipeInventory;
    private final MultiblockPositions pedestalLocations = new MultiblockPositions.Builder()
            .pos(3, 0, 0).pos(0, 0, 3).pos(-3, 0, 0).pos(0, 0, -3)
            .pos(2, 0, 2).pos(2, 0, -2).pos(-2, 0, 2).pos(-2, 0, -2).build();
    private final CachedRecipe<CraftingInput, IInfusionRecipe> recipe;
    private int progress;
    private boolean active;
    private @Nullable Identifier recipeId;

    public InfusionAltarTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.INFUSION_ALTAR.get(), pos, state);
        this.inventory = createInventoryHandler((_, _) -> this.setChanged());
        this.recipeInventory = CItemStacksHandler.create(9);
        this.recipe = new CachedRecipe<>(ModRecipeTypes.INFUSION.get());
    }

    @Override
    public CItemStacksHandler getInventory() {
        return this.inventory;
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        this.progress = input.getIntOr("progress", 0);
        this.active = input.getBooleanOr("active", false);
        this.recipeId = input.read("recipe_id", Identifier.CODEC).orElse(null);
    }

    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);

        output.putInt("progress", this.progress);
        output.putBoolean("active", this.active);
        output.storeNullable("recipe_id", Identifier.CODEC, this.recipeId);
    }

    @Override
    public boolean isActive() {
        if (!this.active) {
            this.active = this.level != null && this.level.hasNeighborSignal(this.getBlockPos());
        }

        return this.active;
    }

    @Override
    public void activate() {
        this.active = true;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, InfusionAltarTileEntity tile) {
        var input = tile.inventory.getResource(0);

        if (!input.isEmpty()) {
            var recipe = tile.getActiveRecipe();

            if (tile.isActive()) {
                if (recipe != null) {
                    tile.progress++;

                    var pedestals = tile.getPedestals();

                    if (tile.progress >= 100) {
                        var inventory = tile.toCraftingInput();
                        var remaining = recipe.getRemainingItems(inventory);

                        for (var i = 1; i < remaining.size(); i++) {
                            var pedestal = pedestals.get(i - 1);
                            var remainder = remaining.get(i);

                            pedestal.getInventory().set(0, ItemResource.of(remainder), remainder.count());

                            tile.spawnParticles(ParticleTypes.SMOKE, pedestal.getBlockPos(), 1.2D, 20);
                        }

                        var result = recipe.assemble(inventory);

                        tile.setOutput(result, remaining.getFirst());
                        tile.reset();
                        tile.setChangedFast();
                        tile.spawnParticles(ParticleTypes.HAPPY_VILLAGER, pos, 1.0D, 10);
                    } else {
                        for (var pedestal : pedestals) {
                            var pedestalPos = pedestal.getBlockPos();
                            var stack = pedestal.getInventory().getResource(0);

                            tile.spawnItemParticles(pedestalPos, stack);
                        }
                    }
                } else {
                    tile.reset();
                }
            } else {
                tile.progress = 0;
            }
        } else {
            tile.reset();
        }

        tile.dispatchIfChanged();
    }

    public static CItemStacksHandler createInventoryHandler(OnContentsChangedFunction onContentsChanged) {
        return CItemStacksHandler.create(2, onContentsChanged, builder -> {
            builder.setDefaultSlotLimit(1);
            builder.setCanInsert((_, _) -> builder.getResource(1).isEmpty());
            builder.setOutputSlots(1);
        });
    }

    public List<BlockPos> getPedestalPositions() {
        return this.pedestalLocations.get(this.getBlockPos());
    }

    public IInfusionRecipe getActiveRecipe() {
        if (this.level == null)
            return null;

        this.updateRecipeInventory(this.getPedestals());
        this.recipe.check(this.toCraftingInput(), (ServerLevel) this.level);

        if (this.recipeId != this.recipe.id()) {
            this.recipeId = this.recipe.id();
            this.setChangedFast();
        }

        return this.recipe.get();
    }

    public @Nullable Identifier getActiveRecipeId() {
        return this.recipeId;
    }

    private CraftingInput toCraftingInput() {
        return this.recipeInventory.toCraftingInput(3, 3);
    }

    private void reset() {
        this.progress = 0;
        this.active = false;
        this.recipeId = null;
    }

    private void updateRecipeInventory(List<InfusionPedestalTileEntity> pedestals) {
        this.recipeInventory.set(0, this.inventory.getResource(0), this.inventory.getAmountAsInt(0));

        for (int i = 0; i < pedestals.size(); i++) {
            var inventory = pedestals.get(i).getInventory();
            var resource = inventory.getResource(0);
            var amount = inventory.getAmountAsInt(0);

            this.recipeInventory.set(i + 1, resource, amount);
        }
    }

    private List<InfusionPedestalTileEntity> getPedestals() {
        if (this.level == null) {
            return Collections.emptyList();
        }

        List<InfusionPedestalTileEntity> pedestals = new ArrayList<>();

        for (var pos : this.getPedestalPositions()) {
            if (!this.level.isLoaded(pos)) {
                return Collections.emptyList();
            }

            var tile = this.level.getBlockEntity(pos);
            if (tile instanceof InfusionPedestalTileEntity pedestal)
                pedestals.add(pedestal);
        }

        return pedestals;
    }

    private <T extends ParticleOptions> void spawnParticles(T particle, BlockPos pos, double yOffset, int count) {
        if (this.level == null || this.level.isClientSide())
            return;

        var level = (ServerLevel) this.level;

        double x = pos.getX() + 0.5D;
        double y = pos.getY() + yOffset;
        double z = pos.getZ() + 0.5D;

        level.sendParticles(particle, x, y, z, count, 0, 0, 0, 0.1D);
    }

    private void spawnItemParticles(BlockPos pedestalPos, ItemResource resource) {
        if (this.level == null || this.level.isClientSide() || resource.isEmpty())
            return;

        var level = (ServerLevel) this.level;
        var pos = this.getBlockPos();

        double x = pedestalPos.getX() + (level.getRandom().nextDouble() * 0.2D) + 0.4D;
        double y = pedestalPos.getY() + (level.getRandom().nextDouble() * 0.2D) + 1.2D;
        double z = pedestalPos.getZ() + (level.getRandom().nextDouble() * 0.2D) + 0.4D;

        double velX = pos.getX() - pedestalPos.getX();
        double velY = 0.25D;
        double velZ = pos.getZ() - pedestalPos.getZ();

        level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, resource.getItem()), x, y, z, 0, velX, velY, velZ, 0.18D);
    }

    private void setOutput(ItemStack stack, ItemStack remaining) {
        var stacks = this.inventory.getStacks();

        stacks.set(0, remaining);
        stacks.set(1, stack);
    }
}
