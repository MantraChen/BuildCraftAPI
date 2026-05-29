/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.core;

import javax.annotation.Nullable;

/** An object that can be converted into another type. Implementing this interface makes no guarantees that
 * {@link #convertTo(Class)} will actually return anything other than this or null. */
public interface IConvertable {

    /** Attempts to convert this object to the given class. Returns this object if it is already an instance of the
     * given class, a separate object if it can be converted, or null if no conversion is possible. */
    @Nullable
    default <T> T convertTo(Class<T> clazz) {
        if (clazz.isInstance(this)) {
            return clazz.cast(this);
        }
        return null;
    }
}
