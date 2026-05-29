/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import java.util.IdentityHashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import buildcraft.api.mj.MjAPI;
import buildcraft.api.transport.IInjectable;
import buildcraft.api.transport.IStripesRegistry;
import buildcraft.api.transport.pluggable.IPluggableRegistry;
import buildcraft.api.transport.pluggable.PipePluggable;

/** The central holding class for all pipe related registers and methods. */
public final class PipeApi {
    public static IPipeRegistry pipeRegistry;
    public static IPluggableRegistry pluggableRegistry;
    public static IStripesRegistry stripeRegistry;
    public static IPipeExtensionManager extensionManager;
    public static PipeFlowType flowStructure;
    public static PipeFlowType flowItems;
    public static PipeFlowType flowFluids;
    public static PipeFlowType flowPower;
    public static PipeFlowType flowRf;

    /** The default transfer information used if a pipe definition has not been registered. */
    public static FluidTransferInfo fluidInfoDefault = new FluidTransferInfo(20, 10);

    /** The default transfer information used if a pipe definition has not been registered. */
    public static PowerTransferInfo powerInfoDefault = PowerTransferInfo.createFromResistance(8 * MjAPI.MJ,
        MjAPI.MJ / 32, false);

    /** The default transfer information used if a pipe definition has not been registered. */
    public static RedstoneFluxTransferInfo rfInfoDefault = new RedstoneFluxTransferInfo(80, false);

    public static final Map<PipeDefinition, FluidTransferInfo> fluidTransferData = new IdentityHashMap<>();
    public static final Map<PipeDefinition, PowerTransferInfo> powerTransferData = new IdentityHashMap<>();
    public static final Map<PipeDefinition, RedstoneFluxTransferInfo> rfTransferData = new IdentityHashMap<>();

    // STUB(R.Chen): Forge Capability<T> fields replaced with Object stubs.
    // Restore as Fabric API Lookup objects in Phase 4E.
    @Nonnull
    public static final Object CAP_PIPE_HOLDER;

    @Nonnull
    public static final Object CAP_PIPE;

    @Nonnull
    public static final Object CAP_PLUG;

    @Nonnull
    public static final Object CAP_INJECTABLE;

    public static FluidTransferInfo getFluidTransferInfo(PipeDefinition def) {
        FluidTransferInfo info = fluidTransferData.get(def);
        if (info == null) {
            return fluidInfoDefault;
        } else {
            return info;
        }
    }

    public static PowerTransferInfo getPowerTransferInfo(PipeDefinition def) {
        PowerTransferInfo info = powerTransferData.get(def);
        if (info == null) {
            return powerInfoDefault;
        } else {
            return info;
        }
    }

    public static RedstoneFluxTransferInfo getRfTransferInfo(PipeDefinition def) {
        RedstoneFluxTransferInfo info = rfTransferData.get(def);
        if (info == null) {
            return rfInfoDefault;
        } else {
            return info;
        }
    }

    public static class FluidTransferInfo {
        public final int transferPerTick;
        public final double transferDelayMultiplier;

        public FluidTransferInfo(int transferPerTick, int transferDelay) {
            this.transferPerTick = transferPerTick;
            if (transferDelay <= 0) {
                transferDelay = 1;
            }
            this.transferDelayMultiplier = transferDelay;
        }
    }

    public static class PowerTransferInfo {
        public final long transferPerTick;
        public final long lossPerTick;
        /** The percentage resistance per tick. Should be a value between 0 and {@link MjAPI#MJ} */
        public final long resistancePerTick;
        public final boolean isReceiver;

        public static PowerTransferInfo createFromLoss(long transferPerTick, long lossPerTick, boolean isReceiver) {
            return new PowerTransferInfo(transferPerTick, lossPerTick,
                lossPerTick * MjAPI.MJ / transferPerTick, isReceiver);
        }

        public static PowerTransferInfo createFromResistance(long transferPerTick, long resistancePerTick,
            boolean isReceiver) {
            return new PowerTransferInfo(transferPerTick, resistancePerTick,
                resistancePerTick * transferPerTick / MjAPI.MJ, isReceiver);
        }

        public PowerTransferInfo(long transferPerTick, long lossPerTick, long resistancePerTick, boolean isReceiver) {
            if (transferPerTick < 10) {
                transferPerTick = 10;
            }
            this.transferPerTick = transferPerTick;
            this.lossPerTick = lossPerTick;
            this.resistancePerTick = resistancePerTick;
            this.isReceiver = isReceiver;
        }
    }

    public static class RedstoneFluxTransferInfo {
        public final int transferPerTick;
        public final boolean isReceiver;

        public RedstoneFluxTransferInfo(int transferPerTick, boolean isReceiver) {
            this.transferPerTick = transferPerTick;
            this.isReceiver = isReceiver;
        }
    }

    // STUB(R.Chen): Forge CapabilitiesHelper.registerCapability replaced with null stubs.
    // Restore as Fabric API ComponentRegistry lookups in Phase 4E.
    static {
        CAP_PIPE = null;
        CAP_PLUG = null;
        CAP_PIPE_HOLDER = null;
        CAP_INJECTABLE = null;
    }
}
