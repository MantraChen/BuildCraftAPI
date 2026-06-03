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

// STUB(R.Chen): FluidStackBC → Fabric Transfer API FluidVariant + long droplets in Phase 4E.
// IFluidFilter → Object stub (Forge FluidStackBC dependency removed).
// ActionResult<FluidStackBC> replaced with long (droplet amount) until proper migration.
public interface IFlowFluid {
    /** @deprecated use the version below with a simulate parameter. */
    @Deprecated
    @Nullable
    default long tryExtractFluid(long droplets, Direction from, /* STUB: FluidStackBC filter */ Object filter) {
        return tryExtractFluid(droplets, from, filter, false);
    }

    /** Attempts to extract fluid from the connected tank.
     * STUB(R.Chen): return type will be FluidVariant-based in Phase 4E. */
    @Nullable
    long tryExtractFluid(long droplets, Direction from, /* STUB: FluidStackBC filter */ Object filter, boolean simulate);

    /** @deprecated use the version below with a simulate parameter. */
    @Deprecated
    default long tryExtractFluidAdv(long droplets, Direction from,
        /* STUB(R.Chen): IFluidFilter → FluidVariant filter in Phase 4E */ Object filter) {
        return tryExtractFluidAdv(droplets, from, filter, false);
    }

    /** Advanced extraction.
     * STUB(R.Chen): IFluidFilter/FluidVariant-based return in Phase 4E. */
    long tryExtractFluidAdv(long droplets, Direction from,
        /* STUB(R.Chen): IFluidFilter → FluidVariant filter */ Object filter, boolean simulate);

    /** Attempts to insert a fluid directly into the pipe.
     * STUB(R.Chen): fluid param will be FluidVariant + amount in Phase 4E.
     *
     * @param fluid placeholder for FluidVariant
     * @param from The side that the fluid should NOT go in, or null for any direction.
     * @return The amount of fluid accepted in droplets. */
    long insertFluidsForce(/* STUB: FluidVariant */ Object fluid, long amount, @Nullable Direction from,
        boolean simulate);

    /** Tries to extract fluids directly from the pipe.
     * STUB(R.Chen): return type will be FluidVariant-based in Phase 4E.
     *
     * @param section The section to extract from. Null means the center. */
    @Nullable
    Object extractFluidsForce(long min, long max, @Nullable Direction section, boolean simulate);
}
