package buildcraft.api.enums;

import java.util.Locale;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringIdentifiable;

public enum EnumRedstoneChipset implements StringIdentifiable {
    RED,
    IRON,
    GOLD,
    QUARTZ,
    DIAMOND;

    private final String name = name().toLowerCase(Locale.ROOT);

    public ItemStack getStack(int stackSize) {
        Item chipset = net.minecraft.registry.Registries.ITEM.getOrEmpty(new net.minecraft.util.Identifier("buildcraftsilicon","redstone_chipset")).orElse(null);
        if (chipset == null) {
            return ItemStack.EMPTY;
        }

        ItemStack stack=new ItemStack(chipset,stackSize);
        stack.getOrCreateNbt().putString("chipset",name);
        return stack;
    }

    public ItemStack getStack() {
        return getStack(1);
    }

    public static EnumRedstoneChipset fromStack(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return RED;
        }
        if(stack.hasNbt() && stack.getNbt().contains("chipset",net.minecraft.nbt.NbtElement.STRING_TYPE)) {
            String type=stack.getNbt().getString("chipset");
            for(var value:values())if(value.name.equals(type))return value;
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
