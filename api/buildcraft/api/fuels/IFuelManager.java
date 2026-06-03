/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.fuels;

import java.util.Collection;

import net.minecraft.fluid.Fluid;
import buildcraft.lib.compat.FluidStackBC;

public interface IFuelManager {
    <F extends IFuel> F addFuel(F fuel);

    IFuel addFuel(FluidStackBC fluid, long powerPerCycle, int totalBurningTime);

    default IFuel addFuel(Fluid fluid, long powerPerCycle, int totalBurningTime) {
        return addFuel(new FluidStackBC(fluid, 1), powerPerCycle, totalBurningTime);
    }

    /** @param residue The residue fluidstack, per bucket of the original fuel. */
    IDirtyFuel addDirtyFuel(FluidStackBC fuel, long powerPerCycle, int totalBurningTime, FluidStackBC residue);

    /** @param residue The residue fluidstack, per bucket of the original fuel. */
    default IDirtyFuel addDirtyFuel(Fluid fuel, long powerPerCycle, int totalBurningTime, FluidStackBC residue) {
        return addDirtyFuel(new FluidStackBC(fuel, 1), powerPerCycle, totalBurningTime, residue);
    }

    Collection<IFuel> getFuels();

    IFuel getFuel(FluidStackBC fluid);

    interface IDirtyFuel extends IFuel {
        /** @return The residue fluidstack, per bucket of original fuel. */
        FluidStackBC getResidue();
    }
}
