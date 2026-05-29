/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import buildcraft.api.transport.IStripesActivator;

public interface IPipeExtensionManager {

    /**
     * Requests an extension by one block from a IStripesActivator (usually a stripes transport pipe) with the pipe
     * supplied by the stack by moving the stripes pipe to the front and placing the new transport pipe behind.
     * If the pipe is a registered retraction pipe (per default only the void transport pipe is) it retracts the
     * pipeline instead by moving the stripes pipe one block in the opposite direction.
     *
     * @param world   the world
     * @param pos     the position or origin of the request (usually the stripes pipe position)
     * @param dir     the direction of the proposed extension
     * @param stripes the stripes pipe
     * @param stack   the pipe stack to use (Note: only uses one item and sends the rest back)
     * @return true on success, false otherwise
     */
    boolean requestPipeExtension(World world, BlockPos pos, Direction dir, IStripesActivator stripes, ItemStack stack);

    /**
     * Registers a pipe as a retraction trigger for pipe extension requests.
     *
     * @param pipeDefinition the pipe definition
     */
    void registerRetractionPipe(PipeDefinition pipeDefinition);
}
