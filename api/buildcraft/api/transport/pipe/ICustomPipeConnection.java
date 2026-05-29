/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public interface ICustomPipeConnection {
    /** @return How long the connecting pipe should extend for, in addition to its normal 4/16f connection. Values less
     *         than or equal to <code>-4 / 16.0f</code> indicate that the pipe will not connect at all, and will render
     *         as it it was not connected. */
    float getExtension(World world, BlockPos pos, Direction face, BlockState state);
}
