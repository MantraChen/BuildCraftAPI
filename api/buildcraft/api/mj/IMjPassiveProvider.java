/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.mj;

/** Designates a machine that provides power passively- it does not attempt to manually output its power (like an
 * engine). Power can be extracted from this by powered wooden kinesis pipes, for example. */
public interface IMjPassiveProvider extends IMjConnector {
    /** Attempts to extract power from this provider
     * 
     * @param simulate
     * @return Either 0, min, max, or a value between min and max. */
    long extractPower(long min, long max, boolean simulate);
}
