/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.mj;

import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/** Various effects for showing power loss visibly, and for large amounts of power, causes some damage to nearby
 * entities. */
public interface IMjEffectManager {
    void createPowerLossEffect(World world, Vec3d center, long microJoulesLost);

    void createPowerLossEffect(World world, Vec3d center, Direction direction, long microJoulesLost);

    void createPowerLossEffect(World world, Vec3d center, Vec3d direction, long microJoulesLost);
}
