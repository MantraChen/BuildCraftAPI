package buildcraft.api.enums;

import java.util.Locale;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringIdentifiable;

import buildcraft.api.BCItems;

public enum EnumRedstoneChipset implements StringIdentifiable {
    RED,
    IRON,
    GOLD,
    QUARTZ,
    DIAMOND;

    private final String name = name().toLowerCase(Locale.ROOT);

    public ItemStack getStack(int stackSize) {
        Item chipset = BCItems.Silicon.REDSTONE_CHIPSET;
        if (chipset == null) {
            return ItemStack.EMPTY;
        }

        // STUB(R.Chen): ItemStack(Item,count,meta) — item flattening removed the meta arg in 1.13+
        return new ItemStack(chipset, stackSize);
    }

    public ItemStack getStack() {
        return getStack(1);
    }

    public static EnumRedstoneChipset fromStack(ItemStack stack) {
        if (stack == null) {
            return RED;
        }
        return fromOrdinal(stack.getDamage());
    }

    public static EnumRedstoneChipset fromOrdinal(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            return RED;
        }
        return values()[ordinal];
    }

    // @Override -- removed: method does not exist in Fabric 1.20.1
    public String getName() {
        return name;
    }

    @Override
    public String asString() { return getName(); }
}
