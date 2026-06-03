package buildcraft.api.recipes;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.Identifier;

// STUB(R.Chen): IForgeRegistryEntry dropped — Phase 10

/**
 * @deprecated TEMPORARY CLASS DO NOT USE!
 */
@Deprecated
public abstract class AssemblyRecipe implements Comparable<AssemblyRecipe> {
    private Identifier name;

    /**
     * The outputs this recipe can generate with any of the given inputs
     * @param inputs Current ingredients in the assembly table
     * @return A Set containing all possible outputs given the given inputs or an empty one if nothing can be assembled from the given inputs
     */
    public abstract Set<ItemStack> getOutputs(DefaultedList<ItemStack> inputs);

    /**
     * Used to determine all outputs from this recipe for recipe previews (guide book and/or JEI)
     */
    public abstract Set<ItemStack> getOutputPreviews();

    /**
     * Used to determine what items to use up for the given output
     * @param output The output we want to know the inputs for, only ever called using stacks obtained from getOutputs or getOutputPreviews
     */
    public abstract Set<IngredientStack> getInputsFor(@Nonnull ItemStack output);

    /**
     * Used to determine how much MJ is required to asemble the given output item
     * @param output The output we want to know the MJ cost for, only ever called using stacks obtained from getOutputs or getOutputPreviews
     */
    public abstract long getRequiredMicroJoulesFor(@Nonnull ItemStack output);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AssemblyRecipe that = (AssemblyRecipe) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(AssemblyRecipe o) {
        return name.toString().compareTo(o.name.toString());
    }

    // @Override -- removed: method does not exist in Fabric 1.20.1
    public AssemblyRecipe setRegistryName(Identifier name) {
        this.name = name;
        return this;
    }

    @Nullable
    // @Override -- removed: method does not exist in Fabric 1.20.1
    public Identifier getRegistryName() {
        return name;
    }

    // @Override -- removed: method does not exist in Fabric 1.20.1
    public Class<AssemblyRecipe> getRegistryType() {
        return AssemblyRecipe.class;
    }
}
