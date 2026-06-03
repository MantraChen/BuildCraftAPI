/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.fluid.Fluid;
import buildcraft.lib.compat.FluidStackBC;

/** This class is used whenever stacks needs to be stored as keys. */
public final class StackKey {
    public final ItemStack stack;
    public final FluidStackBC fluidStack;

    public StackKey(FluidStackBC fluidStack) {
        this(null, fluidStack);
    }

    public StackKey(ItemStack stack) {
        this(stack, null);
    }

    public StackKey(ItemStack stack, FluidStackBC fluidStack) {
        this.stack = stack;
        this.fluidStack = fluidStack;
    }

    public static StackKey stack(Item item, int amount, int damage) {
        ItemStack s = new ItemStack(item, amount); s.setDamage(damage); return new StackKey(s);
    }

    public static StackKey stack(Block block, int amount, int damage) {
        ItemStack s = new ItemStack(block.asItem(), amount); s.setDamage(damage); return new StackKey(s);
    }

    public static StackKey stack(Item item) {
        return new StackKey(new ItemStack(item, 1));
    }

    public static StackKey stack(Block block) {
        return new StackKey(new ItemStack(block.asItem(), 1));
    }

    public static StackKey stack(ItemStack itemStack) {
        return new StackKey(itemStack);
    }

    public static StackKey fluid(Fluid fluid, int amount) {
        return new StackKey(new FluidStackBC(fluid, amount));
    }

    public static StackKey fluid(Fluid fluid) {
        return new StackKey(new FluidStackBC(fluid, 1000));
    }

    public static StackKey fluid(FluidStackBC fluidStack) {
        return new StackKey(fluidStack);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != StackKey.class) {
            return false;
        }
        StackKey k = (StackKey) o;
        if ((stack == null ^ k.stack == null) || (fluidStack == null ^ k.fluidStack == null)) {
            return false;
        }
        if (stack != null) {
            if (stack.getItem() != k.stack.getItem() || stack.getDamage() != k.stack.getDamage() || !objectsEqual(
                    stack.getNbt(), k.stack.getNbt())) {
                return false;
            }
        }
        if (fluidStack != null) {
            if (!fluidStack.isFluidEqual(k.fluidStack) || fluidStack.amount != k.fluidStack.amount) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 7;
        if (stack != null) {
            result = 31 * result + stack.getItem().hashCode();
            result = 31 * result + stack.getDamage();
            result = 31 * result + objectHashCode(stack.getNbt());
        }
        result = 31 * result + 7;
        if (fluidStack != null) {
            result = 31 * result + fluidStack.getFluid().toString().hashCode();
            result = 31 * result + (int) fluidStack.amount;
            // STUB(R.Chen): FluidStackBC carries NBT via FluidVariant components, not a `tag` field — Phase 10
            result = 31 * result + objectHashCode(null);
        }
        return result;
    }

    private boolean objectsEqual(Object o1, Object o2) {
        if (o1 == null && o2 == null) {
            return true;
        } else if (o1 == null || o2 == null) {
            return false;
        } else {
            return o1.equals(o2);
        }
    }

    private int objectHashCode(Object o) {
        return o != null ? o.hashCode() : 0;
    }

    public StackKey copy() {
        return new StackKey(stack != null ? stack.copy() : null, fluidStack != null ? fluidStack.copy() : null);
    }
}
