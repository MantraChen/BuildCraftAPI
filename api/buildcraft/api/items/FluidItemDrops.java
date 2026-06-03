package buildcraft.api.items;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import buildcraft.lib.compat.FluidStackBC;
import net.minecraftforge.fluids.IFluidTank;

public class FluidItemDrops {

    public static IItemFluidShard item;

    public static void addFluidDrops(DefaultedList<ItemStack> toDrop, FluidStackBC... fluids) {
        if (item != null) {
            for (FluidStackBC fluid : fluids) {
                item.addFluidDrops(toDrop, fluid);
            }
        }
    }

    public static void addFluidDrops(DefaultedList<ItemStack> toDrop, IFluidTank... tanks) {
        if (item != null) {
            for (IFluidTank tank : tanks) {
                item.addFluidDrops(toDrop, tank.getFluid());
            }
        }
    }
}
