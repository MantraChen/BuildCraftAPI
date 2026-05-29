/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import net.minecraft.util.math.Direction;

/** Fired whenever a connection change is picked up by an {@link IPipe}. This even doesn't include the new value
 * (boolean isConnected) as it can be accessed via {@link IPipe#isConnected(Direction)}. */
public class PipeEventConnectionChange extends PipeEvent {

    public final Direction direction;

    public PipeEventConnectionChange(IPipeHolder holder, Direction direction) {
        super(holder);
        this.direction = direction;
    }
}
