/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.mj;

import java.text.DecimalFormat;

import javax.annotation.Nullable;

import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class MjAPI {

    /** Native, sided MJ lookup. Amounts remain micro-MJ; this is not a Team Reborn energy-unit conversion. */
    public static final BlockApiLookup<IMjConnector, Direction> CONNECTOR = BlockApiLookup.get(
        new Identifier("buildcraft", "mj_connector"), IMjConnector.class, Direction.class);

    @Nullable
    public static IMjConnector findConnector(@Nullable BlockEntity tile, @Nullable Direction side) {
        if (tile == null || tile.getWorld() == null || tile.isRemoved()) return null;
        return CONNECTOR.find(tile.getWorld(), tile.getPos(), tile.getCachedState(), tile, side);
    }

    /** Resolves a receiving connection only when both endpoints allow it. */
    @Nullable
    public static IMjReceiver getReceiver(IMjConnector source, @Nullable IMjConnector target) {
        return target instanceof IMjReceiver receiver && receiver.canReceive()
            && source.canConnect(receiver) && receiver.canConnect(source) ? receiver : null;
    }

    // TODO(R.Chen): wire up EFFECT_MANAGER on mod init (register via event or initializer).
    @Nullable
    public static IMjEffectManager EFFECT_MANAGER = null;

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

}
