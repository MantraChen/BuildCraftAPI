/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import net.minecraft.util.math.Direction;

@Deprecated
// TODO: Test to see if this is necessary! (Or perhaps make it a capability)
public interface IPipeConnection {

    enum ConnectOverride {

        CONNECT,
        DISCONNECT,
        DEFAULT
    }

    /** Allows you to override pipe connection logic.
     *
     * @param type
     * @param with
     * @return CONNECT to force a connection, DISCONNECT to force no connection, and DEFAULT to let the pipe decide. */
    ConnectOverride overridePipeConnection(Object/*IPipeTile.PipeType*/ type, Direction with);
}
