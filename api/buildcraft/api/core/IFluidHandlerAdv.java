/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 * <p/>
 * BuildCraft is distributed under the terms of the MinecraftClient Mod Public License 1.0, or MMPL. Please check the contents
 * of the license located in http://www.mod-buildcraft.com/MMPL-1.0.txt
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen). */
package buildcraft.api.core;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;

/** A version of Fabric's {@link Storage}{@code <FluidVariant>} that can drain a fluid which a fluid filter accepts.
 * Replaces Forge's {@code IFluidHandlerAdv extends IFluidHandler}. */
public interface IFluidHandlerAdv extends Storage<FluidVariant> {
    /** Drains fluid out of internal tanks, distribution is left entirely to the storage.
     *
     * @param filter A filter to filter the possible fluids that can be extracted.
     * @param maxDrain The maximum amount of fluid to drain, in droplets (1 bucket = 81000 droplets).
     * @param doDrain If false, drain will only be simulated.
     * @return The amount, in droplets, that was (or would have been, if simulated) drained. */
    long drain(IFluidFilter filter, long maxDrain, boolean doDrain);
}
