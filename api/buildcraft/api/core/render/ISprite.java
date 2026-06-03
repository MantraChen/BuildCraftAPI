/*
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.core.render;

// Yarn 1.20.1: net.minecraft.client.renderer.texture.Sprite → net.minecraft.client.texture.Sprite
import net.minecraft.client.texture.Sprite;

/** Holds information on some sort of sprite. These might not be part of the main texture atlas (they might be from a
 * GUI texture), in which case {@link #bindTexture()} should be called before using the results from
 * {@link #getInterpU(double)} or {@link #getInterpV(double)}
 * <p>
 * <b> IMPORTANT: Unlike MinecraftClient's {@link Sprite} this uses co-ordinates between 0 and 1, rather than 0
 * and 16! </b> */
public interface ISprite {
    /** Binds this sprites backing texture so that this sprite will be referenced when you use the results of
     * {@link #getInterpU(double)} and {@link #getInterpV(double)} */
    void bindTexture();

    /** @param u A value between 0 and 1
     * @return */
    double getInterpU(double u);

    /** @param v A value between 0 and 1
     * @return */
    double getInterpV(double v);
}
