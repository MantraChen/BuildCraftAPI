/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.fuels;

import buildcraft.lib.compat.FluidStackBC;

public interface ICoolant {
    boolean matchesFluid(FluidStackBC fluid);

    /** @param fluid
     * @param heat
     * @return 0 if the input fluid provides no cooling, or a value greater than 0 if it does. */
    float getDegreesCoolingPerMB(FluidStackBC fluid, float heat);
}
