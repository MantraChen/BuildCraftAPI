package buildcraft.api.items;

import net.minecraft.item.ItemStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public interface IItemCustomPipeRender {
    float getPipeRenderScale(ItemStack stack);

    /** @return False to use the default renderer, true otherwise. */
    @Environment(EnvType.CLIENT)
    boolean renderItemInPipe(ItemStack stack, double x, double y, double z);
}
