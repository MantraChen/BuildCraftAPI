package buildcraft.api.library;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public abstract class LibraryTypeHandlerNBT extends LibraryTypeHandler {
    public LibraryTypeHandlerNBT(String extension) {
        super(extension);
    }

    public abstract ItemStack load(ItemStack stack, NbtCompound nbt);

    public abstract boolean store(ItemStack stack, NbtCompound nbt);
}
