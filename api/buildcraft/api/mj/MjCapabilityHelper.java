/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.mj;

import javax.annotation.Nonnull;

/** Holds a machine's connector for exposure through {@link MjAPI#CONNECTOR}. */
public class MjCapabilityHelper {

    @Nonnull
    public final IMjConnector connector;

    public MjCapabilityHelper(@Nonnull IMjConnector connector) {
        this.connector = java.util.Objects.requireNonNull(connector, "connector");
    }
}
