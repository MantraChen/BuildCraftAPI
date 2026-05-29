/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.statements;

import java.util.Collection;

import javax.annotation.Nonnull;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.Direction;

public interface ITriggerProvider {
    void addInternalTriggers(Collection<ITriggerInternal> triggers, IStatementContainer container);

    void addInternalSidedTriggers(Collection<ITriggerInternalSided> triggers, IStatementContainer container, @Nonnull Direction side);

    /** Returns the list of triggers available to a gate next to the given block. */
    void addExternalTriggers(Collection<ITriggerExternal> triggers, @Nonnull Direction side, BlockEntity tile);
}
