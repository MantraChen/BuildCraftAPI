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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;

import buildcraft.api.core.EnumPipePart;

// STUB(R.Chen): ICapabilityProvider removed — Forge capability system has no Fabric equivalent.
// Lookup API capability methods stubbed; restore in Phase 4E.
// STUB(R.Chen): Side → EnvType; MessageContext → Object.
public abstract class PipeBehaviour {
    public final IPipe pipe;

    public PipeBehaviour(IPipe pipe) {
        this.pipe = pipe;
    }

    public PipeBehaviour(IPipe pipe, NbtCompound nbt) {
        this.pipe = pipe;
    }

    public NbtCompound writeToNbt() {
        NbtCompound nbt = new NbtCompound();
        return nbt;
    }

    /** STUB(R.Chen): Side → EnvType; MessageContext → Object. Full signature restoration in Phase 4E. */
    public void writePayload(PacketByteBuf buffer, EnvType side) {}

    /** STUB(R.Chen): Side → EnvType; MessageContext → Object. Full signature restoration in Phase 4E. */
    public void readPayload(PacketByteBuf buffer, EnvType side,
        /* STUB(R.Chen): MessageContext */ Object ctx) throws IOException {}

    /** @deprecated Replaced by {@link #getTextureData(Direction)}. */
    @Deprecated
    public int getTextureIndex(Direction face) {
        return 0;
    }

    /** Gets the texture data to use for the specified face. This may return null for the center, which indicates that
     * the center of the pipe will use the face texture instead.
     *
     * @param face Null indicates the center of the pipe.
     * @return The texture data for the given face. This may be null, but only for the center! */
    public PipeFaceTex getTextureData(Direction face) {
        return PipeFaceTex.get(getTextureIndex(face));
    }

    // Event handling

    public boolean canConnect(Direction face, PipeBehaviour other) {
        return true;
    }

    public boolean canConnect(Direction face, BlockEntity oTile) {
        return true;
    }

    /** Used to force a connection to a given tile, even if the {@link PipeFlow} wouldn't normally connect to it. */
    public boolean shouldForceConnection(Direction face, BlockEntity oTile) {
        return false;
    }

    public boolean onPipeActivate(PlayerEntity player, HitResult trace, float hitX, float hitY, float hitZ,
        EnumPipePart part) {
        return false;
    }

    public void onEntityCollide(Entity entity) {}

    public void onTick() {}

    /** STUB(R.Chen): Forge Capability → Fabric API Lookup in Phase 4E. */
    public boolean hasCapability(@Nonnull Object capability, Direction facing) {
        return getCapability(capability, facing) != null;
    }

    /** STUB(R.Chen): Forge Capability<T> → Fabric API Lookup in Phase 4E. */
    public <T> T getCapability(@Nonnull Object capability, Direction facing) {
        return null;
    }

    public void addDrops(List<ItemStack> toDrop, int fortune) {}
}
