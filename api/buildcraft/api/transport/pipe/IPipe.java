/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Direction;

// STUB(R.Chen): ICapabilityProvider removed — Forge capability system has no Fabric equivalent.
// Lookup API integration deferred to Phase 4E.
public interface IPipe {
    IPipeHolder getHolder();

    PipeDefinition getDefinition();

    PipeBehaviour getBehaviour();

    PipeFlow getFlow();

    DyeColor getColour();

    void setColour(DyeColor colour);

    void markForUpdate();

    BlockEntity getConnectedTile(Direction side);

    IPipe getConnectedPipe(Direction side);

    boolean isConnected(Direction side);

    ConnectedType getConnectedType(Direction side);

    enum ConnectedType {
        TILE,
        PIPE
    }
}
