/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.statements;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;

// STUB(R.Chen): StatementManager.IParamReaderBuf/IParameterReader imports removed to break dep on unmigrated StatementManager.
public interface IStatementParameter extends IGuiSlot {

    @Nonnull
    ItemStack getItemStack();

    default DrawType getDrawType() {
        return DrawType.SPRITE_STACK;
    }

    IStatementParameter onClick(IStatementContainer source, IStatement stmt, ItemStack stack,
        StatementMouseClick mouse);

    void writeToNbt(NbtCompound nbt);

    default void writeToBuf(PacketByteBuf buffer) {
        NbtCompound nbt = new NbtCompound();
        writeToNbt(nbt);
        buffer.writeNbt(nbt);
    }

    IStatementParameter rotateLeft();

    IStatementParameter[] getPossible(IStatementContainer source);

    default boolean isPossibleOrdered() {
        return false;
    }

    public enum DrawType {
        SPRITE_ONLY,
        STACK_ONLY,
        STACK_ONLY_OR_QUESTION_MARK,
        SPRITE_STACK,
        SPRITE_STACK_OR_QUESTION_MARK,
        STACK_SPRITE,
        STACK_OR_QUESTION_MARK_THEN_SPRITE
    }
}
