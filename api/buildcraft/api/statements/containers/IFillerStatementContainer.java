/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.statements.containers;

import javax.annotation.Nullable;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.World;

import buildcraft.api.core.IBox;
import buildcraft.api.filler.IFillerPattern;
import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.IStatementParameter;

// TODO(R.Chen): deferred from libLeaf — blocked on api.filler.IFillerPattern migration.
public interface IFillerStatementContainer extends IStatementContainer {

    /** Unlike in {@link IStatementContainer} some containers might not be tile based (for example the volume box). */
    @Override
    @Nullable
    BlockEntity getTile();

    World getFillerWorld();

    /** @return True if this filler has a non-zero sized box. */
    boolean hasBox();

    /** @return The box that the filler will (default) to building in.
     * @throws IllegalStateException if {@link #hasBox()} returns false. */
    IBox getBox() throws IllegalStateException;

    void setPattern(IFillerPattern pattern, IStatementParameter[] params);
}
