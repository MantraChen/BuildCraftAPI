/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import net.minecraft.util.math.Direction;

import buildcraft.api.mj.IMjPassiveProvider;

public interface IFlowPower extends IFlowPowerLike {
    /** Makes this pipe reconfigure itself, possibly due to the addition of new modules. */
    @Override
    void reconfigure();

    /** Attempts to extract power from the {@link IMjPassiveProvider} connected to this pipe on the given side.
     *
     * @param maxPower The Maximum amount of power that can be extracted.
     * @param from The side (of this pipe) to take power from.
     * @return The amount of power extracted. */
    long tryExtractPower(long maxPower, Direction from);
}
