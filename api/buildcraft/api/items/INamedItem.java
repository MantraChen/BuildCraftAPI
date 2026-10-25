package buildcraft.api.items;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;

public interface INamedItem {
    /** User-assigned label; distinct from Minecraft's Text-valued Item.getName. */
    String getLabel(@Nonnull ItemStack stack);

    boolean setName(@Nonnull ItemStack stack, String name);
}
