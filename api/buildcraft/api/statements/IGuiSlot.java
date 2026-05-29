/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.statements;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import buildcraft.api.core.IConvertable;
import buildcraft.api.core.render.ISprite;

public interface IGuiSlot extends IConvertable {
    String getUniqueTag();

    @Environment(EnvType.CLIENT)
    String getDescription();

    @Environment(EnvType.CLIENT)
    default List<String> getTooltip() {
        String desc = getDescription();
        if (desc == null) {
            return ImmutableList.of();
        }
        return ImmutableList.of(desc);
    }

    @Environment(EnvType.CLIENT)
    @Nullable
    ISprite getSprite();
}
