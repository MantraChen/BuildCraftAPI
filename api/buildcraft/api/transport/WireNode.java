/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport;

import java.util.EnumMap;
import java.util.Map;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.AxisDirection;

public class WireNode {
    public final BlockPos pos;
    public final EnumWirePart part;
    private final int hash;

    public WireNode(BlockPos pos, EnumWirePart part) {
        this.pos = pos;
        this.part = part;
        hash = pos.hashCode() * 31 + part.hashCode();
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        WireNode other = (WireNode) obj;
        return part == other.part //
            && pos.equals(other.pos);
    }

    @Override
    public String toString() {
        return "(" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ", " + part + ")";
    }

    public WireNode offset(Direction face) {
        int nx = (part.x == AxisDirection.POSITIVE ? 1 : 0) + face.getOffsetX();
        int ny = (part.y == AxisDirection.POSITIVE ? 1 : 0) + face.getOffsetY();
        int nz = (part.z == AxisDirection.POSITIVE ? 1 : 0) + face.getOffsetZ();
        EnumWirePart nPart = EnumWirePart.get(nx, ny, nz);
        if (nx < 0 || ny < 0 || nz < 0 || nx > 1 || ny > 1 || nz > 1) {
            return new WireNode(pos.offset(face), nPart);
        } else {
            return new WireNode(pos, nPart);
        }
    }

    public Map<Direction, WireNode> getAllPossibleConnections() {
        Map<Direction, WireNode> map = new EnumMap<>(Direction.class);

        for (Direction face : Direction.values()) {
            map.put(face, offset(face));
        }
        return map;
    }
}
