/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nonnull;

import net.fabricmc.api.EnvType;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;

import buildcraft.api.core.EnumPipePart;
import buildcraft.api.transport.pipe.IPipeHolder.IWriter;
import buildcraft.api.transport.pipe.IPipeHolder.PipeMessageReceiver;

// STUB(R.Chen): ICapabilityProvider removed — Forge capability system has no Fabric equivalent.
// Lookup API capability methods stubbed; restore in Phase 4E.
// STUB(R.Chen): Side → EnvType.
public abstract class PipeFlow {
    /** The ID for completely refreshing the state of this flow. */
    public static final int NET_ID_FULL_STATE = 0;
    /** The ID for updating what has changed since the last NET_ID_FULL_STATE or NET_ID_UPDATE has been sent. */
    public static final int NET_ID_UPDATE = 1;

    public final IPipe pipe;

    public PipeFlow(IPipe pipe) {
        this.pipe = pipe;
    }

    public PipeFlow(IPipe pipe, NbtCompound nbt) {
        this.pipe = pipe;
    }

    public NbtCompound writeToNbt() {
        return new NbtCompound();
    }

    /** Writes a payload with the specified id. Standard ID's are NET_ID_FULL_STATE and NET_ID_UPDATE.
     * STUB(R.Chen): Side → EnvType. */
    public void writePayload(int id, PacketByteBuf buffer, EnvType side) {}

    /** Reads a payload with the specified id. Standard ID's are NET_ID_FULL_STATE and NET_ID_UPDATE.
     * STUB(R.Chen): Side → EnvType. */
    public void readPayload(int id, PacketByteBuf buffer, EnvType side) throws IOException {}

    public void sendPayload(int id) {
        // STUB(R.Chen): world.isRemote → world.isClient
        final EnvType side = pipe.getHolder().getPipeWorld().isClient ? EnvType.CLIENT : EnvType.SERVER;
        sendCustomPayload(id, (buf) -> writePayload(id, buf, side));
    }

    public final void sendCustomPayload(int id, IWriter writer) {
        pipe.getHolder().sendMessage(PipeMessageReceiver.FLOW, buffer -> {
            buffer.writeBoolean(true);
            buffer.writeShort(id);
            writer.write(buffer);
        });
    }

    public abstract boolean canConnect(Direction face, PipeFlow other);

    public abstract boolean canConnect(Direction face, BlockEntity oTile);

    /** Used to force a connection to a given tile, even if the {@link PipeBehaviour} wouldn't normally connect to
     * it. */
    public boolean shouldForceConnection(Direction face, BlockEntity oTile) {
        return false;
    }

    public void onTick() {}

    public void postPluggableTick() {}

    public void addDrops(List<ItemStack> toDrop, int fortune) {}

    public boolean onFlowActivate(PlayerEntity player, HitResult trace, float hitX, float hitY, float hitZ,
        EnumPipePart part) {
        return false;
    }

    /** STUB(R.Chen): Forge Capability → Fabric API Lookup in Phase 4E. */
    public final boolean hasCapability(@Nonnull Object capability, Direction facing) {
        return getCapability(capability, facing) != null;
    }

    /** STUB(R.Chen): Forge Capability<T> → Fabric API Lookup in Phase 4E. */
    public <T> T getCapability(@Nonnull Object capability, Direction facing) {
        return null;
    }
}
