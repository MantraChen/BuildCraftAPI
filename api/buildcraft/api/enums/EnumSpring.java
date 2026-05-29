/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.enums;

import java.util.Locale;
import java.util.function.Supplier;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.StringIdentifiable;

import buildcraft.api.properties.BuildCraftProperties;

public enum EnumSpring implements StringIdentifiable {
    WATER(5, -1, Blocks.WATER.getDefaultState()),
    OIL(6000, 8, null); // Set in BuildCraftEnergy

    public static final EnumSpring[] VALUES = values();

    public final int tickRate, chance;
    public BlockState liquidBlock;
    public boolean canGen = true;
    public Supplier<BlockEntity> tileConstructor;

    private final String lowerCaseName = name().toLowerCase(Locale.ROOT);

    EnumSpring(int tickRate, int chance, BlockState liquidBlock) {
        this.tickRate = tickRate;
        this.chance = chance;
        this.liquidBlock = liquidBlock;
    }

    public static EnumSpring fromState(BlockState state) {
        return state.get(BuildCraftProperties.SPRING_TYPE);
    }

    @Override
    public String asString() {
        return lowerCaseName;
    }
}
