/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public interface IStripesHandlerBlock {

    /** @param world
     * @param pos
     * @param direction
     * @param player
     * @param activator
     * @return True if this broke a block, false otherwise (note that this handler MUST NOT return false if it has
     *         changed the world in any way) */
    boolean handle(World world, BlockPos pos, Direction direction, PlayerEntity player, IStripesActivator activator);
}
