package buildcraft.api.robots;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public interface IRobotOverlayItem {
    boolean isValidRobotOverlay(ItemStack stack);

    @Environment(EnvType.CLIENT)
    void renderRobotOverlay(ItemStack stack, TextureManager textureManager);
}
