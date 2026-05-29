/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Designates a method that will receive a pipe event. The method must be public and take a single parameter that
 * extends {@link PipeEvent}. <br>
 * An example is:<br>
 * <code>
    &#64;PipeEventHandler <br>
    public void sideCheck(PipeEventItem.SideCheck sideCheck) {<br>
     // Logic omitted<br>
    }
 *
 *   </code> */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PipeEventHandler {
    /** Designates the priority that the handler will be given. All handlers in vanilla BuildCraft use
     * {@link PipeEventPriority#NORMAL}, so this is given for other pipe mods to fire before or after all BC logic has
     * taken place. */
    PipeEventPriority priority() default PipeEventPriority.NORMAL;

    /** If true then the event handler will be called even if an event has already been cancelled. */
    boolean receiveCancelled() default false;
}
