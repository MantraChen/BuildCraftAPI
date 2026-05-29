/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.mj;

/** This interface should be defined by any Tile which wants to receive power from BuildCraft lasers.
 *
 * The respective Block MUST implement ILaserTargetBlock! */
public interface ILaserTarget {

    /** Returns The amount of power this target currently needs.
     *
     * @return The amount of power required, or 0 if no power is required. */
    long getRequiredLaserPower();

    /** Transfers power from the laser to the target.
     *
     * @param microJoules The number of micro Minecraft Joules to accept
     * @return The excess power. If the input is less than or equal to {@link #getRequiredLaserPower()} then this will return 0. */
    long receiveLaserPower(long microJoules);

    /** Return true if the Tile Entity object is no longer a valid target. For example, if its been invalidated.
     *
     * @return true if no longer a valid target object */
    boolean isInvalidTarget();
}
