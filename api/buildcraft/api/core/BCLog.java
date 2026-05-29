/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */
package buildcraft.api.core;

import org.slf4j.Logger;

import buildcraft.BuildCraftFabric;

public final class BCLog {
    // STUB(R.Chen): log4j Logger -> slf4j wrapper around BuildCraftFabric.LOGGER
    public static final Logger logger = BuildCraftFabric.LOGGER;

    /** Deactivate constructor */
    private BCLog() {}

    @Deprecated
    public static void logErrorAPI(String mod, Throwable error, Class<?> classFile) {
        logErrorAPI(error, classFile);
    }

    public static void logErrorAPI(Throwable error, Class<?> classFile) {
        StringBuilder msg = new StringBuilder("API error! Please update your mods. Error: ");
        msg.append(error);
        StackTraceElement[] stackTrace = error.getStackTrace();
        if (stackTrace.length > 0) {
            msg.append(", ").append(stackTrace[0]);
        }

        logger.error(msg.toString());

        if (classFile != null) {
            msg.append("API error: ").append(classFile.getSimpleName()).append(" is loaded from ").append(classFile.getProtectionDomain()
                    .getCodeSource().getLocation());
            logger.error(msg.toString());
        }
    }

    // STUB(R.Chen): decouple from BuildCraftAPI.getVersion() to keep libLeaf minimal
    @Deprecated
    public static String getVersion() {
        return "unknown";
    }
}
