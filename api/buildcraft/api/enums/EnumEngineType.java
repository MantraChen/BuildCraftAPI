/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.enums;

import net.minecraft.util.StringIdentifiable;

import buildcraft.api.core.IEngineType;

public enum EnumEngineType implements StringIdentifiable, IEngineType {
    WOOD("core", "wood"),
    STONE("energy", "stone"),
    IRON("energy", "iron"),
    CREATIVE("energy", "creative"),
    RF("energy", "rf");

    public final String unlocalizedTag;
    public final String resourceLocation;

    public static final EnumEngineType[] VALUES = values();

    EnumEngineType(String mod, String loc) {
        unlocalizedTag = loc;
        resourceLocation = "buildcraft" + mod + ":blocks/engine/inv/" + loc;
    }

    @Override
    public String getItemModelLocation() {
        return resourceLocation;
    }

    @Override
    public String asString() {
        return unlocalizedTag;
    }

    public static EnumEngineType fromMeta(int meta) {
        if (meta < 0 || meta >= VALUES.length) {
            meta = 0;
        }
        return VALUES[meta];
    }
}
