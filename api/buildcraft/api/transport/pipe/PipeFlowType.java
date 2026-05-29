/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import net.minecraft.nbt.NbtCompound;

public final class PipeFlowType {
    public final IFlowCreator creator;
    public final IFlowLoader loader;

    /** The default colour type, if none is given in {@link PipeDefinition}. if this is also null then the final
     * fallback type is {@link EnumPipeColourType#TRANSLUCENT}. */
    public EnumPipeColourType fallbackColourType;

    public PipeFlowType(IFlowCreator creator, IFlowLoader loader) {
        this(creator, loader, null);
    }

    public PipeFlowType(IFlowCreator creator, IFlowLoader loader, EnumPipeColourType colourType) {
        this.creator = creator;
        this.loader = loader;
        this.fallbackColourType = colourType;
    }

    @FunctionalInterface
    public interface IFlowCreator {
        PipeFlow createFlow(IPipe t);
    }

    @FunctionalInterface
    public interface IFlowLoader {
        PipeFlow loadFlow(IPipe t, NbtCompound u);
    }
}
