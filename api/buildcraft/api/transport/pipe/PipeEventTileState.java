/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import net.minecraft.block.entity.BlockEntity;

/** Fired when the state of a pipe's block entity changes. Listen for subclasses, not this one! */
public abstract class PipeEventTileState extends PipeEvent {
    PipeEventTileState(IPipeHolder holder) {
        super(holder);
    }

    /** Fired in {@link BlockEntity#markRemoved()} */
    public static class Invalidate extends PipeEventTileState {
        public Invalidate(IPipeHolder holder) {
            super(holder);
        }
    }

    /** Fired in {@link BlockEntity#cancelRemoval()} */
    public static class Validate extends PipeEventTileState {
        public Validate(IPipeHolder holder) {
            super(holder);
        }
    }

    /** Fired when the chunk is unloaded */
    public static class ChunkUnload extends PipeEventTileState {
        public ChunkUnload(IPipeHolder holder) {
            super(holder);
        }
    }
}
