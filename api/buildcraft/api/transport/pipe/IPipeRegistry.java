/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.transport.pipe;

import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public interface IPipeRegistry {
    PipeDefinition getDefinition(Identifier identifier);

    void registerPipe(PipeDefinition definition);

    /** Maps the given {@link PipeDefinition} to an {@link IItemPipe}. This acts exactly akin to
     * {@link Map#put(Object, Object)}. */
    void setItemForPipe(PipeDefinition definition, @Nullable IItemPipe item);

    IItemPipe getItemForPipe(PipeDefinition definition);

    /** Creates an {@link IItemPipe} for the given {@link PipeDefinition}. If the {@link PipeDefinition} has been
     * registered with {@link #registerPipe(PipeDefinition)} then it will also be registered with
     * {@link #setItemForPipe(PipeDefinition, IItemPipe)}. The returned item will be automatically registered. */
    IItemPipe createItemForPipe(PipeDefinition definition);

    /** Identical to {@link #createItemForPipe(PipeDefinition)}, but doesn't require registering tags with buildcraft
     * lib in order to register.
     *
     * @param postCreate A function to call in order to set up the item's identifier and name. */
    IItemPipe createUnnamedItemForPipe(PipeDefinition definition, Consumer<Item> postCreate);

    Iterable<PipeDefinition> getAllRegisteredPipes();
}
