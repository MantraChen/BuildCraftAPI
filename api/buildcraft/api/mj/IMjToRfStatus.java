/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.mj;

// STUB(R.Chen): BCModules-backed factory removed (BCModules not yet ported to Fabric).
// Always returns the default conversion (RF auto-conversion disabled).
// TODO(R.Chen): wire up BCModules once it is ported to Fabric.
public interface IMjToRfStatus {

    static IMjToRfStatus get() {
        return MjToRfStatusHolder.STATUS;
    }

    MjRfConversion getConversion();

    boolean isAutoconvertEnabled();
}

final class MjToRfStatusHolder implements IMjToRfStatus {

    static final IMjToRfStatus STATUS = new MjToRfStatusHolder();

    private final MjRfConversion defaultConversion = MjRfConversion.createDefault();

    @Override
    public MjRfConversion getConversion() {
        return defaultConversion;
    }

    @Override
    public boolean isAutoconvertEnabled() {
        return false;
    }
}
