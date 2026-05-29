/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.statements.containers;

import net.minecraft.util.math.Direction;

public interface IRedstoneStatementContainer {
    /** Get the redstone input from a given side.
     *
     * @param side The side - use "null" for maximum input.
     * @return The redstone input, from 0 to 15. */
    int getRedstoneInput(Direction side);

    /** Set the redstone input for a given side.
     *
     * @param side The side - use "null" for all sides.
     * @return Whether the set was successful. */
    boolean setRedstoneOutput(Direction side, int value);
}
