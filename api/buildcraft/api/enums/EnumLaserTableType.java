/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.enums;

import net.minecraft.util.StringIdentifiable;

public enum EnumLaserTableType implements StringIdentifiable {
    ASSEMBLY_TABLE,
    ADVANCED_CRAFTING_TABLE,
    INTEGRATION_TABLE,
    CHARGING_TABLE,
    PROGRAMMING_TABLE;

    @Override
    public String asString() {
        return name();
    }
}
