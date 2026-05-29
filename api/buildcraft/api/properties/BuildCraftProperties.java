/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.properties;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Direction;

import buildcraft.api.enums.EnumDecoratedBlock;
import buildcraft.api.enums.EnumEngineType;
import buildcraft.api.enums.EnumLaserTableType;
import buildcraft.api.enums.EnumMachineState;
import buildcraft.api.enums.EnumOptionalSnapshotType;
import buildcraft.api.enums.EnumPowerStage;
import buildcraft.api.enums.EnumSpring;

public final class BuildCraftProperties {
    public static final Property<Direction> BLOCK_FACING = EnumProperty.of("facing", Direction.class, Direction.Type.HORIZONTAL);
    public static final Property<Direction> BLOCK_FACING_6 = EnumProperty.of("facing", Direction.class);

    public static final Property<DyeColor> BLOCK_COLOR = EnumProperty.of("color", DyeColor.class);
    public static final Property<EnumSpring> SPRING_TYPE = EnumProperty.of("type", EnumSpring.class);
    public static final Property<EnumEngineType> ENGINE_TYPE = EnumProperty.of("type", EnumEngineType.class);
    public static final Property<EnumLaserTableType> LASER_TABLE_TYPE = EnumProperty.of("type", EnumLaserTableType.class);
    public static final Property<EnumMachineState> MACHINE_STATE = EnumProperty.of("state", EnumMachineState.class);
    public static final Property<EnumPowerStage> ENERGY_STAGE = EnumProperty.of("stage", EnumPowerStage.class);
    public static final Property<EnumOptionalSnapshotType> SNAPSHOT_TYPE = EnumProperty.of("snapshot_type", EnumOptionalSnapshotType.class);
    public static final Property<EnumDecoratedBlock> DECORATED_BLOCK = EnumProperty.of("decoration_type", EnumDecoratedBlock.class);

    public static final Property<Integer> GENERIC_PIPE_DATA = IntProperty.of("pipe_data", 0, 15);
    public static final Property<Integer> LED_POWER = IntProperty.of("led_power", 0, 3);

    public static final Property<Boolean> JOINED_BELOW = BooleanProperty.of("joined_below");
    public static final Property<Boolean> MOVING = BooleanProperty.of("moving");
    public static final Property<Boolean> LED_DONE = BooleanProperty.of("led_done");
    public static final Property<Boolean> ACTIVE = BooleanProperty.of("active");
    public static final Property<Boolean> VALID = BooleanProperty.of("valid");

    public static final Property<Boolean> CONNECTED_UP = BooleanProperty.of("connected_up");
    public static final Property<Boolean> CONNECTED_DOWN = BooleanProperty.of("connected_down");
    public static final Property<Boolean> CONNECTED_EAST = BooleanProperty.of("connected_east");
    public static final Property<Boolean> CONNECTED_WEST = BooleanProperty.of("connected_west");
    public static final Property<Boolean> CONNECTED_NORTH = BooleanProperty.of("connected_north");
    public static final Property<Boolean> CONNECTED_SOUTH = BooleanProperty.of("connected_south");

    public static final Map<Direction, Property<Boolean>> CONNECTED_MAP;

    // Block state setting flags — used by World.setBlockState.
    public static final int UPDATE_NONE = 0;
    public static final int UPDATE_NEIGHBOURS = 1;
    public static final int MARK_BLOCK_FOR_UPDATE = 2;
    public static final int UPDATE_EVEN_CLIENT = 4 + MARK_BLOCK_FOR_UPDATE; // 6
    public static final int MARK_THIS_AND_NEIGHBOURS = UPDATE_NEIGHBOURS + MARK_BLOCK_FOR_UPDATE;
    public static final int UPDATE_ALL = UPDATE_NEIGHBOURS + MARK_BLOCK_FOR_UPDATE + UPDATE_EVEN_CLIENT;

    static {
        Map<Direction, Property<Boolean>> map = Maps.newEnumMap(Direction.class);
        map.put(Direction.DOWN, CONNECTED_DOWN);
        map.put(Direction.UP, CONNECTED_UP);
        map.put(Direction.EAST, CONNECTED_EAST);
        map.put(Direction.WEST, CONNECTED_WEST);
        map.put(Direction.NORTH, CONNECTED_NORTH);
        map.put(Direction.SOUTH, CONNECTED_SOUTH);
        CONNECTED_MAP = Maps.immutableEnumMap(map);
    }

    /** Deactivate constructor */
    private BuildCraftProperties() {}
}
