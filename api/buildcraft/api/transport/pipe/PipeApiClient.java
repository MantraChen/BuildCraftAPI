/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import buildcraft.api.transport.pluggable.IPlugDynamicRenderer;
import buildcraft.api.transport.pluggable.IPluggableStaticBaker;
import buildcraft.api.transport.pluggable.PipePluggable;
import buildcraft.api.transport.pluggable.PluggableModelKey;

@Environment(EnvType.CLIENT)
public enum PipeApiClient {
    INSTANCE;

    public static IClientRegistry registry;

    public interface IClientRegistry {

        /** Registers a dynamic renderer for the given pipe flow. Most {@link PipeFlow} types will have no use for
         * this. */
        <F extends PipeFlow> void registerRenderer(Class<? extends F> flowClass, IPipeFlowRenderer<F> renderer);

        /** Registers a dynamic renderer for the given pipe behaviour. Most {@link PipeBehaviour} types will have no
         * use for this. */
        <B extends PipeBehaviour> void registerRenderer(Class<? extends B> behaviourClass,
            IPipeBehaviourRenderer<B> renderer);

        <P extends PipePluggable> void registerRenderer(Class<? extends P> plugClass,
            IPlugDynamicRenderer<P> renderer);

        <P extends PluggableModelKey> void registerBaker(Class<? extends P> keyClass,
            IPluggableStaticBaker<P> renderer);
    }
}
