/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport;

import net.minecraft.util.DyeColor;

import buildcraft.api.transport.pipe.IPipeHolder;

public interface IWireManager {

    IPipeHolder getHolder();

    void updateBetweens(boolean recursive);

    DyeColor getColorOfPart(EnumWirePart part);

    DyeColor removePart(EnumWirePart part);

    boolean addPart(EnumWirePart part, DyeColor colour);

    boolean hasPartOfColor(DyeColor color);

    boolean isPowered(EnumWirePart part);

    boolean isAnyPowered(DyeColor color);
}
