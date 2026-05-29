/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.mj;

import javax.annotation.Nonnull;

// STUB(R.Chen): Forge ICapabilityProvider / getCapability() removed. The MJ connector must be exposed via
// a Transfer API EnergyStorage.SIDED lookup registered against the owning BlockEntityType.
// This class is retained as a compile-compatibility shim until that lookup is wired up.
public class MjCapabilityHelper {

    @Nonnull
    public final IMjConnector connector;

    public MjCapabilityHelper(@Nonnull IMjConnector connector) {
        this.connector = connector;
    }
}
