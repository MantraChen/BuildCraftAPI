/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.enums;

import java.util.Locale;

import net.minecraft.util.StringIdentifiable;

public enum EnumDecoratedBlock implements StringIdentifiable {
    DESTROY(0),
    BLUEPRINT(10),
    TEMPLATE(10),
    PAPER(10),
    LEATHER(10),
    LASER_BACK(0);

    public static final EnumDecoratedBlock[] VALUES = values();

    public final int lightValue;

    EnumDecoratedBlock(int lightValue) {
        this.lightValue = lightValue;
    }

    @Override
    public String asString() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static EnumDecoratedBlock fromMeta(int meta) {
        if (meta < 0 || meta >= VALUES.length) {
            return EnumDecoratedBlock.DESTROY;
        }
        return VALUES[meta];
    }
}
