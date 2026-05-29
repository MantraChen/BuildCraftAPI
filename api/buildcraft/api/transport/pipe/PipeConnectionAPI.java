/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;

/** Use this class to register blocks with custom block sizes so that pipes can connect to them properly. Note that you
 * do not need to register a custom pipe connection if your block implements ICustomPipeConnection. The registered
 * version does not override your own implementation. */
public final class PipeConnectionAPI {
    private static final Map<Block, ICustomPipeConnection> connections = Maps.newHashMap();
    private static final ICustomPipeConnection NOTHING = (world, pos, face, state) -> 0;

    /** Register a block with a custom connection. Useful if you don't own the block class or are adding it for
     * some-one else.
     *
     * @param block The block instance
     * @param connection The connection instance */
    public static void registerConnection(Block block, ICustomPipeConnection connection) {
        connections.put(block, connection);
    }

    /** Ensures that a particular block will always have the default connection, no matter what the bounding box the
     * block is. */
    public static void registerConnectionAsNothing(Block block) {
        connections.put(block, NOTHING);
    }

    /** Gets the current custom connection that the block uses. Will be null if nothing has been set. */
    public static ICustomPipeConnection getCustomConnection(Block block) {
        if (block instanceof ICustomPipeConnection) {
            return (ICustomPipeConnection) block;
        }
        ICustomPipeConnection connection = connections.get(block);
        if (connection != null) {
            return connection;
        }
        return null;
    }
}
