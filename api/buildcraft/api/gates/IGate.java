/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.gates;

import java.util.List;

import buildcraft.api.statements.IStatement;
import buildcraft.api.statements.IStatementParameter;
import buildcraft.api.statements.StatementSlot;
import buildcraft.api.statements.containers.ISidedStatementContainer;
import buildcraft.api.transport.pipe.IPipeHolder;

public interface IGate extends ISidedStatementContainer {

    IPipeHolder getPipeHolder();

    List<IStatement> getTriggers();

    List<IStatement> getActions();

    List<StatementSlot> getActiveActions();

    List<IStatementParameter> getTriggerParameters(int slot);

    List<IStatementParameter> getActionParameters(int slot);
}
