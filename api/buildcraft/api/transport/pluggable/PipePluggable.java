/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pluggable;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import buildcraft.api.transport.pipe.IPipeHolder;
import buildcraft.api.transport.pipe.IPipeHolder.PipeMessageReceiver;

// STUB(R.Chen): ICapabilityProvider removed — Forge capability system has no Fabric equivalent.
// Lookup API capability methods stubbed; restore in Phase 4E.
// STUB(R.Chen): BlockFaceShape removed in 1.16+; getBlockFaceShape() stubbed as no-op.
// STUB(R.Chen): Side → EnvType; MessageContext → Object.
public abstract class PipePluggable {
    public final PluggableDefinition definition;
    public final IPipeHolder holder;
    public final Direction side;

    public PipePluggable(PluggableDefinition definition, IPipeHolder holder, Direction side) {
        this.definition = definition;
        this.holder = holder;
        this.side = side;
    }

    public NbtCompound writeToNbt() {
        NbtCompound nbt = new NbtCompound();
        return nbt;
    }

    /** Writes the payload that will be passed into
     * {@link PluggableDefinition#loadFromBuffer(IPipeHolder, Direction, PacketByteBuf)} on the client. */
    public void writeCreationPayload(PacketByteBuf buffer) {}

    /** STUB(R.Chen): Side → EnvType. */
    public void writePayload(PacketByteBuf buffer, EnvType side) {}

    /** STUB(R.Chen): Side → EnvType; MessageContext → Object. */
    public void readPayload(PacketByteBuf buffer, EnvType side,
        /* STUB(R.Chen): MessageContext */ Object ctx) throws IOException {}

    public final void scheduleNetworkUpdate() {
        holder.scheduleNetworkUpdate(PipeMessageReceiver.PLUGGABLES[side.ordinal()]);
    }

    public void onTick() {}

    /** @return A bounding box that will be used for collisions and raytracing. */
    public abstract Box getBoundingBox();

    /** @return True if the pipe cannot connect outwards (it is blocked), or False if this does not block the pipe. */
    public boolean isBlocking() {
        return false;
    }

    /** STUB(R.Chen): Forge Capability<T> → Fabric API Lookup in Phase 4E. */
    public <T> T getCapability(@Nonnull Object cap) {
        return null;
    }

    /** STUB(R.Chen): Forge Capability<T> → Fabric API Lookup in Phase 4E. */
    public <T> T getInternalCapability(@Nonnull Object cap) {
        return null;
    }

    /** Called whenever this pluggable is removed from the pipe. */
    public void onRemove() {}

    /** @param toDrop A list containing all the items to drop (so you should add your items to this list) */
    public void addDrops(List<ItemStack> toDrop, int fortune) {
        ItemStack stack = getPickStack();
        if (!stack.isEmpty()) {
            toDrop.add(stack);
        }
    }

    /** Called whenever this pluggable is picked by the player (similar to Block.getPickBlock)
     *
     * @return The stack that should be picked, or ItemStack.EMPTY if no stack can be picked from this pluggable. */
    public ItemStack getPickStack() {
        return ItemStack.EMPTY;
    }

    public boolean onPluggableActivate(PlayerEntity player, HitResult trace, float hitX, float hitY, float hitZ) {
        return false;
    }

    @Nullable
    public PluggableModelKey getModelRenderKey(net.minecraft.client.render.RenderLayer layer) {
        return null;
    }

    /** Called if the {@link IPluggableStaticBaker} returns quads with tint indexes set to
     * <code>data * 6 + key.side.ordinal()</code>. <code>"data"</code> is passed in here as <code>"tintIndex"</code>.
     *
     * @return The tint index to render the quad with, or -1 for default. */
    @Environment(EnvType.CLIENT)
    public int getBlockColor(int tintIndex) {
        return -1;
    }

    public boolean canBeConnected() {
        return false;
    }

    public boolean isSideSolid() {
        return false;
    }

    /** @see Block#getBlastResistance() */
    public float getExplosionResistance(@Nullable Entity exploder, Explosion explosion) {
        return 0;
    }

    public boolean canConnectToRedstone(@Nullable Direction to) {
        return false;
    }

    /** STUB(R.Chen): BlockFaceShape was removed in 1.16+. Returns void; replace with isSideSolid/VoxelShape in Phase 4E. */
    public void getBlockFaceShape() {
        // no-op stub
    }

    public void onPlacedBy(PlayerEntity player) {}
}
