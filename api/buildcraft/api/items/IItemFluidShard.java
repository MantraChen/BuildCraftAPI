package buildcraft.api.items;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import buildcraft.lib.compat.FluidStackBC;

public interface IItemFluidShard {
    void addFluidDrops(DefaultedList<ItemStack> toDrop, @Nullable FluidStackBC fluid);
}
