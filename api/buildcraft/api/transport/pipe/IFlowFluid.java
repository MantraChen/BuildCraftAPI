/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import javax.annotation.Nullable;

import net.minecraft.util.math.Direction;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import java.util.function.Predicate;

/** Native fluid identity and exact droplet amounts (81,000 droplets per bucket). */
public interface IFlowFluid {
    /** @deprecated use the version below with a simulate parameter. */
    @Deprecated
    default long tryExtractFluid(long droplets, Direction from, @Nullable FluidVariant filter) {
        return tryExtractFluid(droplets, from, filter, false);
    }

    /** Moves up to the requested droplets from a connected tank; null accepts any fluid. */
    long tryExtractFluid(long droplets, Direction from, @Nullable FluidVariant filter, boolean simulate);

    /** @deprecated use the version below with a simulate parameter. */
    @Deprecated
    default long tryExtractFluidAdv(long droplets, Direction from,
        Predicate<FluidVariant> filter) {
        return tryExtractFluidAdv(droplets, from, filter, false);
    }

    /** Searches the connected storage for a matching variant, including its NBT. */
    long tryExtractFluidAdv(long droplets, Direction from,
        Predicate<FluidVariant> filter, boolean simulate);

    /** Attempts to insert a fluid directly into the pipe.
     * @param from The side that the fluid should NOT go in, or null for any direction.
     * @return The amount of fluid accepted in droplets. */
    long insertFluidsForce(FluidVariant fluid, long amount, @Nullable Direction from,
        boolean simulate);

    /** Tries to extract fluids directly from the pipe.
     * @param section The section to extract from. Null means the center. */
    @Nullable
    FluidVolume extractFluidsForce(long min, long max, @Nullable Direction section, boolean simulate);

    /** The exact variant and amount removed by force extraction. */
    record FluidVolume(FluidVariant resource, long amount) {
        public FluidVolume {
            if (resource == null || resource.isBlank() || amount <= 0) throw new IllegalArgumentException("Empty fluid volume");
        }
    }
}
