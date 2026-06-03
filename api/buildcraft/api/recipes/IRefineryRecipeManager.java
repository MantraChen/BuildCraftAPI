package buildcraft.api.recipes;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import buildcraft.lib.compat.FluidStackBC;

public interface IRefineryRecipeManager {
    IHeatableRecipe createHeatingRecipe(FluidStackBC in, FluidStackBC out, int heatFrom, int heatTo);

    default IHeatableRecipe addHeatableRecipe(FluidStackBC in, FluidStackBC out, int heatFrom, int heatTo) {
        return getHeatableRegistry().addRecipe(createHeatingRecipe(in, out, heatFrom, heatTo));
    }

    ICoolableRecipe createCoolableRecipe(FluidStackBC in, FluidStackBC out, int heatFrom, int heatTo);

    default ICoolableRecipe addCoolableRecipe(FluidStackBC in, FluidStackBC out, int heatFrom, int heatTo) {
        return getCoolableRegistry().addRecipe(createCoolableRecipe(in, out, heatFrom, heatTo));
    }

    IDistillationRecipe createDistillationRecipe(FluidStackBC in, FluidStackBC outGas, FluidStackBC outLiquid, long powerRequired);

    default IDistillationRecipe addDistillationRecipe(FluidStackBC in, FluidStackBC outGas, FluidStackBC outLiquid, long powerRequired) {
        return getDistillationRegistry().addRecipe(createDistillationRecipe(in, outGas, outLiquid, powerRequired));
    }

    IRefineryRegistry<IHeatableRecipe> getHeatableRegistry();

    IRefineryRegistry<ICoolableRecipe> getCoolableRegistry();

    IRefineryRegistry<IDistillationRecipe> getDistillationRegistry();

    interface IRefineryRegistry<R extends IRefineryRecipe> {
        /** @return an unmodifiable collection containing all of the distillation recipes that satisfy the given
         *         predicate. All of the recipe objects are guaranteed to never be null. */
        Stream<R> getRecipes(Predicate<R> toReturn);

        /** @return an unmodifiable set containing all of the distillation recipes. */
        Collection<R> getAllRecipes();

        @Nullable
        R getRecipeForInput(@Nullable FluidStackBC fluid);

        Collection<R> removeRecipes(Predicate<R> toRemove);

        /** Adds the given recipe to the registry. Note that this will remove any existing recipes for the passed
         * recipe's {@link IRefineryRecipe#in()}
         * 
         * @param recipe The recipe to add.
         * @return The input recipe. */
        R addRecipe(R recipe);
    }

    interface IRefineryRecipe {
        FluidStackBC in();
    }

    interface IHeatExchangerRecipe extends IRefineryRecipe {
        @Nullable
        FluidStackBC out();

        int heatFrom();

        int heatTo();
    }

    interface IHeatableRecipe extends IHeatExchangerRecipe {}

    interface ICoolableRecipe extends IHeatExchangerRecipe {}

    interface IDistillationRecipe extends IRefineryRecipe {
        long powerRequired();

        FluidStackBC outGas();

        FluidStackBC outLiquid();
    }
}
