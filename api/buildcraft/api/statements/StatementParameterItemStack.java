/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 *
 * Ported to Fabric 1.20.1 by R.Chen (https://github.com/MantraChen).
 */
package buildcraft.api.statements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import buildcraft.api.core.render.ISprite;

public class StatementParameterItemStack implements IStatementParameter {
    // needed because ItemStack.EMPTY doesn't have @Nonnull applied to it :/
    @Nonnull
    private static final ItemStack EMPTY_STACK;

    /** Immutable parameter that has the {@link ItemStack#EMPTY} as it's {@link #stack}. */
    public static final StatementParameterItemStack EMPTY;

    static {
        ItemStack stack = ItemStack.EMPTY;
        if (stack == null) throw new Error("Somehow ItemStack.EMPTY was null!");
        EMPTY_STACK = stack;
        EMPTY = new StatementParameterItemStack();
    }

    @Nonnull
    protected final ItemStack stack;

    public StatementParameterItemStack() {
        stack = EMPTY_STACK;
    }

    public StatementParameterItemStack(@Nonnull ItemStack stack) {
        this.stack = stack;
    }

    public StatementParameterItemStack(NbtCompound nbt) {
        ItemStack read = ItemStack.fromNbt(nbt.getCompound("stack"));
        if (read.isEmpty()) {
            stack = EMPTY_STACK;
        } else {
            stack = read;
        }
    }

    @Override
    public void writeToNbt(NbtCompound compound) {
        if (!stack.isEmpty()) {
            NbtCompound tagCompound = new NbtCompound();
            stack.writeNbt(tagCompound);
            compound.put("stack", tagCompound);
        }
    }

    @Override
    public ISprite getSprite() {
        return null;
    }

    @Override
    @Nonnull
    public ItemStack getItemStack() {
        return stack;
    }

    @Override
    public StatementParameterItemStack onClick(
        IStatementContainer source, IStatement stmt, ItemStack stack, StatementMouseClick mouse
    ) {
        if (stack.isEmpty()) {
            return EMPTY;
        } else {
            ItemStack newStack = stack.copy();
            newStack.setCount(1);
            return new StatementParameterItemStack(newStack);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof StatementParameterItemStack) {
            StatementParameterItemStack param = (StatementParameterItemStack) object;
            return ItemStack.areEqual(stack, param.stack);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(stack);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public String getDescription() {
        throw new UnsupportedOperationException("Don't call getDescription directly!");
    }

    @Override
    @Environment(EnvType.CLIENT)
    public List<String> getTooltip() {
        if (stack.isEmpty()) {
            return ImmutableList.of();
        }
        List<Text> raw = stack.getTooltip(null, TooltipContext.Default.BASIC);
        List<String> tooltip = new ArrayList<>(raw.size());
        for (Text t : raw) {
            tooltip.add(t.getString());
        }
        if (!tooltip.isEmpty()) {
            tooltip.set(0, stack.getRarity().formatting.toString() + tooltip.get(0));
            for (int i = 1; i < tooltip.size(); i++) {
                tooltip.set(i, Formatting.GRAY + tooltip.get(i));
            }
        }
        return tooltip;
    }

    @Override
    public String getUniqueTag() {
        return "buildcraft:stack";
    }

    @Override
    public IStatementParameter rotateLeft() {
        return this;
    }

    @Override
    public IStatementParameter[] getPossible(IStatementContainer source) {
        return null;
    }
}
