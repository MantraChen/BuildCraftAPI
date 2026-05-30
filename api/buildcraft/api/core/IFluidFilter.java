/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 * <p/>
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL. Please check the contents
 * of the license located in http://www.mod-buildcraft.com/MMPL-1.0.txt
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen). */
package buildcraft.api.core;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

public interface IFluidFilter {

    /** @param fluid The fluid variant to test (never blank).
     * @param amount The amount available, in droplets (1 bucket = 81000 droplets).
     * @return True if this filter accepts the given fluid. */
    boolean matches(FluidVariant fluid, long amount);
}
