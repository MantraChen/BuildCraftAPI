/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.mj;

import javax.annotation.Nonnull;

/** Signifies that this should visibly connect to other Mj handling entities/tiles. This should NEVER be the tile
 * entity, but an encapsulated class that refers back to it. Use {@link buildcraft.api.mj.MjAPI#CAP_CONNECTOR} to access
 * this. */
public interface IMjConnector {
    /** Checks to see if this connector can connect to the other connector. By default this should check that the other
     * connector is the same power system.
     * 
     * @param other
     * @return */
    boolean canConnect(@Nonnull IMjConnector other);
}
