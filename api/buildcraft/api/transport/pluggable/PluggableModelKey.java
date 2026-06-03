/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pluggable;

import java.util.Objects;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.Direction;

// STUB(R.Chen): RenderLayer → RenderLayer; CUTOUT/TRANSLUCENT accessed via RenderLayer.getCutout()/getTranslucent().
@Environment(EnvType.CLIENT)
public abstract class PluggableModelKey {
    public final RenderLayer layer;
    public final Direction side;
    private final int hash;

    public PluggableModelKey(RenderLayer layer, Direction side) {
        if (!layer.equals(RenderLayer.getCutout()) && !layer.equals(RenderLayer.getTranslucent())) {
            throw new IllegalArgumentException(
                "Can only use CUTOUT or TRANSLUCENT at the moment (was " + layer + ")");
        }
        if (side == null) throw new NullPointerException("side");
        this.layer = layer;
        this.side = side;
        this.hash = Objects.hash(layer, side);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        PluggableModelKey other = (PluggableModelKey) obj;
        if (!layer.equals(other.layer)) return false;
        if (side != other.side) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return hash;
    }
}
