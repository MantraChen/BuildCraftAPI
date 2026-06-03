package buildcraft.api.library;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public interface ILibraryTypeHandler {
    boolean isHandler(ItemStack stack, boolean store);

    String getFileExtension();

    int getTextColor();

    String getName(ItemStack stack);

    ItemStack load(ItemStack stack, NbtCompound compound);

    boolean store(ItemStack stack, NbtCompound compound);
}
