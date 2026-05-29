/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import net.minecraft.client.render.BufferBuilder;

public interface IPipeFlowRenderer<F extends PipeFlow> {
    /** @param flow The flow to render
     * @param x
     * @param y
     * @param z
     * @param bufferBuilder The (optional) vertex buffer that you can render into. Note that you can still do GL stuff. */
    void render(F flow, double x, double y, double z, float partialTicks, BufferBuilder bufferBuilder);
}
