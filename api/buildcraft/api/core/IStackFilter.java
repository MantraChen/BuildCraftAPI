/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.core;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

/** This interface provides a convenient means of dealing with entire classes of items without having to specify each
 * item individually. */
public interface IStackFilter {

    /** Check to see if a given stack matches this filter.
     *
     * @param stack The stack to test. stack.isEmpty will always return false.
     * @return True if it does match, false otherwise. */
    boolean matches(@Nonnull ItemStack stack);

    default IStackFilter and(IStackFilter filter) {
        IStackFilter before = this;
        return (stack) -> before.matches(stack) && filter.matches(stack);
    }

    /** Returns example stacks matching this filter */
    default List<ItemStack> getExamples() {
        return DefaultedList.of();
    }
}
