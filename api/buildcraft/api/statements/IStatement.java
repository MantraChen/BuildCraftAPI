/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.statements;

/** Designates some sort of statement. Most of the time you should implement {@link ITriggerExternal},
 * {@link ITriggerInternal}, {@link IActionExternal} or {@link IActionInternal} though. */
public interface IStatement extends IGuiSlot {

    /** Return the maximum number of parameter this statement can have, 0 if none. */
    int maxParameters();

    /** Return the minimum number of parameter this statement can have, 0 if none. */
    int minParameters();

    /** Create parameters for the statement. */
    IStatementParameter createParameter(int index);

    default IStatementParameter createParameter(IStatementParameter old, int index) {
        IStatementParameter _new = createParameter(index);
        if (old == null || _new == null) {
            return _new;
        } else if (old.getClass() == _new.getClass()) {
            return old;
        }
        return _new;
    }

    /** This returns the statement after a left rotation. Used in particular in blueprints orientation. */
    IStatement rotateLeft();

    /** This returns a group of related statements. */
    IStatement[] getPossible();

    default boolean isPossibleOrdered() {
        return false;
    }
}
