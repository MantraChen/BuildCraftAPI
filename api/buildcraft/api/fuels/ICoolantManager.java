/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.fuels;

import java.util.Collection;

import net.minecraft.item.ItemStack;

import net.minecraft.fluid.Fluid;
import buildcraft.lib.compat.FluidStackBC;

public interface ICoolantManager {
    ICoolant addCoolant(ICoolant coolant);

    ICoolant addCoolant(FluidStackBC fluid, float degreesCoolingPerMb);

    default ICoolant addCoolant(Fluid fluid, float degreesCoolingPerMb) {
        return addCoolant(new FluidStackBC(fluid, 1), degreesCoolingPerMb);
    }

    ISolidCoolant addSolidCoolant(ISolidCoolant solidCoolant);

    ISolidCoolant addSolidCoolant(ItemStack solid, FluidStackBC fluid, float multiplier);

    Collection<ICoolant> getCoolants();

    Collection<ISolidCoolant> getSolidCoolants();

    ICoolant getCoolant(FluidStackBC fluid);

    float getDegreesPerMb(FluidStackBC fluid, float heat);

    ISolidCoolant getSolidCoolant(ItemStack solid);
}
