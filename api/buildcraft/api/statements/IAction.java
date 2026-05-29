/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.statements;

/** Marker interface that designates a class as being an action. Note that you *must* implement ONE of the following
 * interfaces to be recognised as an action: {@link IActionInternal}, {@link IActionInternalSided}, or
 * {@link IActionExternal} */
public interface IAction extends IStatement {}
