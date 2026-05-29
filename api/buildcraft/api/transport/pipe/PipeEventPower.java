/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import net.minecraft.util.math.Direction;

import buildcraft.api.mj.MjAPI;

public abstract class PipeEventPower extends PipeEvent {
    public final IFlowPower flow;

    protected PipeEventPower(IPipeHolder holder, IFlowPower flow) {
        super(holder);
        this.flow = flow;
    }

    protected PipeEventPower(boolean canBeCancelled, IPipeHolder holder, IFlowPower flow) {
        super(canBeCancelled, holder);
        this.flow = flow;
    }

    public static class Configure extends PipeEventPower {
        private long maxPower = 10 * MjAPI.MJ;
        private long powerResistance = -1;
        private long powerLoss = -1;
        private boolean receiver = false;
        private boolean disabled = false;

        public Configure(IPipeHolder holder, IFlowPower flow) {
            super(holder, flow);
        }

        public long getMaxPower() {
            return this.maxPower;
        }

        public void setMaxPower(long maxPower) {
            this.maxPower = maxPower;
        }

        public long getPowerLoss() {
            return this.powerLoss;
        }

        public void setPowerLoss(long powerLoss) {
            this.powerLoss = powerLoss;
        }

        public long getPowerResistance() {
            return this.powerResistance;
        }

        public void setPowerResistance(long powerResistance) {
            this.powerResistance = powerResistance;
        }

        public boolean isReceiver() {
            return this.receiver;
        }

        public void setReceiver(boolean receiver) {
            this.receiver = receiver;
        }

        public void disableTransfer() {
            disabled = true;
        }

        public boolean isTransferDisabled() {
            return disabled;
        }
    }

    public static class PrimaryDirection extends PipeEventPower {
        private Direction facing;

        public PrimaryDirection(IPipeHolder holder, IFlowPower flow, Direction facing) {
            super(holder, flow);
            this.facing = facing;
        }

        public Direction getFacing() {
            return facing;
        }

        public void setFacing(Direction facing) {
            this.facing = facing;
        }
    }
}
