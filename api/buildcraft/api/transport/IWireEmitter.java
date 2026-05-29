/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport;

import net.minecraft.util.DyeColor;

public interface IWireEmitter {
    /** Checks to see if this wire emitter is currently emitting the given colour. Only used to check if a given emitter
     * is still active. */
    boolean isEmitting(DyeColor colour);

    /** Emits the given wire colour this tick. */
    void emitWire(DyeColor colour);
}
