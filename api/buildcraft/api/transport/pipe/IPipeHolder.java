/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import buildcraft.api.statements.containers.IRedstoneStatementContainer;
import buildcraft.api.transport.IWireManager;
import buildcraft.api.transport.pluggable.PipePluggable;

/** Designates a tile that can contain a pipe, up to 6 sided pluggables. */
public interface IPipeHolder extends IRedstoneStatementContainer {
    World getPipeWorld();

    BlockPos getPipePos();

    BlockEntity getPipeTile();

    IPipe getPipe();

    /** @return true if the player should be able to interact with the pipe holder in GUI form. */
    boolean canPlayerInteract(PlayerEntity player);

    @Nullable
    PipePluggable getPluggable(Direction side);

    @Nullable
    BlockEntity getNeighbourTile(Direction side);

    @Nullable
    IPipe getNeighbourPipe(Direction side);

    /** Gets the given capability going outwards from the pipe.
     * STUB(R.Chen): Forge Capability<T> → Fabric API Lookup in Phase 4E. */
    @Nullable
    <T> T getCapabilityFromPipe(Direction side, @Nonnull /* STUB(R.Chen): Capability<T> */ Object capability);

    IWireManager getWireManager();

    GameProfile getOwner();

    /** @return True if at least 1 handler received this event, false if not. */
    boolean fireEvent(PipeEvent event);

    void scheduleRenderUpdate();

    /** @param parts The parts that want to send a network update. */
    void scheduleNetworkUpdate(PipeMessageReceiver... parts);

    /** Schedules a GUI network update. */
    void scheduleNetworkGuiUpdate(PipeMessageReceiver... parts);

    /** Sends a custom message from a pluggable or pipe centre to the server/client. */
    void sendMessage(PipeMessageReceiver to, IWriter writer);

    void sendGuiMessage(PipeMessageReceiver to, IWriter writer);

    /** Called on the server whenever a gui container object is opened. */
    void onPlayerOpen(PlayerEntity player);

    /** Called on the server whenever a gui container object is closed. */
    void onPlayerClose(PlayerEntity player);

    enum PipeMessageReceiver {
        BEHAVIOUR(null),
        FLOW(null),
        PLUGGABLE_DOWN(Direction.DOWN),
        PLUGGABLE_UP(Direction.UP),
        PLUGGABLE_NORTH(Direction.NORTH),
        PLUGGABLE_SOUTH(Direction.SOUTH),
        PLUGGABLE_WEST(Direction.WEST),
        PLUGGABLE_EAST(Direction.EAST),
        WIRES(null);

        public static final PipeMessageReceiver[] VALUES = values();
        public static final PipeMessageReceiver[] PLUGGABLES = new PipeMessageReceiver[6];

        static {
            for (PipeMessageReceiver type : VALUES) {
                if (type.face != null) {
                    PLUGGABLES[type.face.ordinal()] = type;
                }
            }
        }

        public final Direction face;

        PipeMessageReceiver(Direction face) {
            this.face = face;
        }
    }

    interface IWriter {
        void write(PacketByteBuf buffer);
    }
}
