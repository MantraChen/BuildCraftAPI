/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.statements;

import java.util.Arrays;
import java.util.Objects;

import buildcraft.api.core.EnumPipePart;

public class StatementSlot {
    public IStatement statement;
    public IStatementParameter[] parameters;
    public EnumPipePart part = EnumPipePart.CENTER;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StatementSlot)) {
            return false;
        }
        StatementSlot s = (StatementSlot) o;
        if (s.statement != statement || parameters.length != s.parameters.length) {
            return false;
        }
        for (int i = 0; i < parameters.length; i++) {
            IStatementParameter p1 = parameters[i];
            IStatementParameter p2 = s.parameters[i];
            if (p1 == null) {
                if (p2 != null) return false;
                continue;
            }
            if (p2 == null) return false;
            if (!(p1.equals(p2))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(statement, Arrays.deepHashCode(parameters));
    }
}
