/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.mj;

import java.text.DecimalFormat;

// STUB(R.Chen): Forge Capability fields (CAP_CONNECTOR, CAP_RECEIVER, etc.) and the RF-conversion API
// (CapabilitiesHelper, IMjToRfStatus) are removed. Transfer API lookups will replace them.
// Only MJ constant, formatMj(), and isRfAutoConversionEnabled() are preserved for compile compatibility.
public class MjAPI {

    /** 1 MJ expressed in micro-joules (the power system base unit). */
    public static final long ONE_MINECRAFT_JOULE = 1_000_000L;
    public static final long MJ = ONE_MINECRAFT_JOULE;

    public static final DecimalFormat MJ_DISPLAY_FORMAT = new DecimalFormat("#,##0.##");

    /** Formats a given MJ value to a player-oriented string. Does not append "MJ". */
    public static String formatMj(long microMj) {
        return MJ_DISPLAY_FORMAT.format(microMj / (double) MJ);
    }

    // TODO(R.Chen): isRfAutoConversionEnabled() — replace with a config-backed flag once the RF bridge is ported.
    public static boolean isRfAutoConversionEnabled() {
        return false;
    }

    // TODO(R.Chen): CAP_CONNECTOR / CAP_RECEIVER / etc. — register via Transfer API EnergyStorage.SIDED lookup.
}
