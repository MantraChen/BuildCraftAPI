/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.mj;

import io.netty.buffer.ByteBuf;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

// STUB(R.Chen): INBTSerializable<NBTTagCompound> removed (Forge-only interface).
// readFromNbt/writeToNbt are retained as plain methods for callers.
/** Provides a basic implementation of a simple battery. Note that you should call {@link #tick(World, BlockPos)} or
 * {@link #tick(World, Vec3d)} every tick to allow for losing excess power. */
public class MjBattery {
    private final long capacity;
    private long microJoules = 0;

    public MjBattery(long capacity) {
        this.capacity = capacity;
    }

    public NbtCompound writeToNbt() {
        NbtCompound nbt = new NbtCompound();
        nbt.putLong("stored", microJoules);
        return nbt;
    }

    public void readFromNbt(NbtCompound nbt) {
        microJoules = nbt.getLong("stored");
    }

    public void writeToBuffer(ByteBuf buffer) {
        buffer.writeLong(microJoules);
    }

    public void readFromBuffer(ByteBuf buffer) {
        microJoules = buffer.readLong();
    }

    public long addPower(long microJoulesToAdd, boolean simulate) {
        if (!simulate) {
            this.microJoules += microJoulesToAdd;
        }
        return 0;
    }

    /** Attempts to add power, but only if this is not already full.
     *
     * @param microJoulesToAdd The power to add.
     * @return The excess power. */
    public long addPowerChecking(long microJoulesToAdd, boolean simulate) {
        if (isFull()) {
            return microJoulesToAdd;
        } else {
            return addPower(microJoulesToAdd, simulate);
        }
    }

    public long extractAll() {
        return extractPower(0, microJoules);
    }

    /** Attempts to extract exactly the given amount of power.
     *
     * @param power The amount of power to extract.
     * @return True if the power was removed, false if not. */
    public boolean extractPower(long power) {
        return extractPower(power, power) > 0;
    }

    public long extractPower(long min, long max) {
        if (microJoules < min) return 0;
        long extracting = Math.min(microJoules, max);
        microJoules -= extracting;
        return extracting;
    }

    public boolean isFull() {
        return microJoules >= capacity;
    }

    public long getStored() {
        return microJoules;
    }

    public long getCapacity() {
        return capacity;
    }

    public void tick(World world, BlockPos position) {
        tick(world, new Vec3d(position.getX() + 0.5, position.getY() + 0.5, position.getZ() + 0.5));
    }

    public void tick(World world, Vec3d position) {
        if (microJoules > capacity * 2) {
            losePower(world, position);
        }
    }

    protected void losePower(World world, Vec3d position) {
        long diff = microJoules - capacity * 2;
        long lost = ceilDivide(diff, 32);
        microJoules -= lost;
        if (MjAPI.EFFECT_MANAGER != null) {
            MjAPI.EFFECT_MANAGER.createPowerLossEffect(world, position, lost);
        }
    }

    private static long ceilDivide(long val, long by) {
        return (val / by) + (val % by == 0 ? 0 : 1);
    }

    public String getDebugString() {
        return MjAPI.formatMj(microJoules) + " / " + MjAPI.formatMj(capacity) + " MJ";
    }
}
