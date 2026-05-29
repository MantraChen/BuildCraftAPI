/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pluggable;

import javax.annotation.Nullable;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import buildcraft.api.core.InvalidInputDataException;
import buildcraft.api.transport.pipe.IPipeHolder;

public final class PluggableDefinition {
    public final Identifier identifier;

    public final IPluggableNetLoader loader;
    public final IPluggableNbtReader reader;

    @Nullable
    public final IPluggableCreator creator;

    public PluggableDefinition(Identifier identifier, IPluggableNbtReader reader, IPluggableNetLoader loader) {
        this.identifier = identifier;
        this.reader = reader;
        this.loader = loader;
        this.creator = null;
    }

    public PluggableDefinition(Identifier identifier, @Nullable IPluggableCreator creator) {
        this.identifier = identifier;
        this.reader = creator;
        this.loader = creator;
        this.creator = creator;
    }

    public PipePluggable readFromNbt(IPipeHolder holder, Direction side, NbtCompound nbt) {
        return reader.readFromNbt(this, holder, side, nbt);
    }

    public PipePluggable loadFromBuffer(IPipeHolder holder, Direction side, PacketByteBuf buffer)
        throws InvalidInputDataException {
        return loader.loadFromBuffer(this, holder, side, buffer);
    }

    @FunctionalInterface
    public interface IPluggableNbtReader {
        PipePluggable readFromNbt(PluggableDefinition definition, IPipeHolder holder, Direction side,
            NbtCompound nbt);
    }

    @FunctionalInterface
    public interface IPluggableNetLoader {
        PipePluggable loadFromBuffer(PluggableDefinition definition, IPipeHolder holder, Direction side,
            PacketByteBuf buffer) throws InvalidInputDataException;
    }

    @FunctionalInterface
    public interface IPluggableCreator extends IPluggableNbtReader, IPluggableNetLoader {
        @Override
        default PipePluggable loadFromBuffer(PluggableDefinition definition, IPipeHolder holder, Direction side,
            PacketByteBuf buffer) {
            return createSimplePluggable(definition, holder, side);
        }

        @Override
        default PipePluggable readFromNbt(PluggableDefinition definition, IPipeHolder holder, Direction side,
            NbtCompound nbt) {
            return createSimplePluggable(definition, holder, side);
        }

        PipePluggable createSimplePluggable(PluggableDefinition definition, IPipeHolder holder, Direction side);
    }
}
