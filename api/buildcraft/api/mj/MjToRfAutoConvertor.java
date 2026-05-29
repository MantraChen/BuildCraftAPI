/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.mj;

// STUB(R.Chen): Forge IEnergyStorage / RF bridge completely removed.
// createReceiver() always returns null until a Team Reborn EnergyStorage lookup replaces it.
// TODO(R.Chen): implement via Transfer API EnergyStorage.SIDED once the RF compat layer is ported.
public final class MjToRfAutoConvertor {

    private MjToRfAutoConvertor() {}

    /** @return null — RF auto-conversion is not yet ported to Fabric. */
    public static IMjReceiver createReceiver(Object rfStorage) {
        return null;
    }
}
