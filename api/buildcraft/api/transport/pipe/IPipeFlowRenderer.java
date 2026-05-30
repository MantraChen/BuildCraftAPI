/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public interface IPipeFlowRenderer<F extends PipeFlow> {
    /**
     * Render dynamic pipe-flow content (items in transit, power fill, etc.).
     * The MatrixStack is pre-translated to the pipe block's world position.
     *
     * @param flow           the flow instance to render
     * @param matrices       matrix stack at block-entity origin
     * @param vertexConsumer vertex output (use a cutout or translucent consumer as needed)
     * @param light          packed block + sky light (LightmapTextureManager.pack)
     * @param partialTicks   partial tick for interpolation
     */
    void render(F flow, MatrixStack matrices, VertexConsumer vertexConsumer, int light, float partialTicks);
}
