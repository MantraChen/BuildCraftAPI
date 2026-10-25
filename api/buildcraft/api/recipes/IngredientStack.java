/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */

package buildcraft.api.recipes;

import net.minecraft.recipe.Ingredient;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import java.util.Objects;

public final class IngredientStack {
    public final Ingredient ingredient;
    public final int count;

    public IngredientStack(Ingredient ingredient, int count) {
        this.ingredient = Objects.requireNonNull(ingredient, "ingredient");
        if (count < 1) throw new IllegalArgumentException("Ingredient count must be positive");
        this.count = count;
    }

    public IngredientStack(Ingredient ingredient) {
        this(ingredient, 1);
    }

    public static IngredientStack of(Object o) {
        if (o instanceof Ingredient ingredient) return new IngredientStack(ingredient);
        if (o instanceof ItemStack stack) return new IngredientStack(Ingredient.ofStacks(stack.copy()));
        if (o instanceof ItemConvertible item) return new IngredientStack(Ingredient.ofItems(item));
        if (o instanceof String name) {
            if (name.startsWith("#")) {
                return new IngredientStack(Ingredient.fromTag(TagKey.of(RegistryKeys.ITEM, new Identifier(name.substring(1)))));
            }
            Identifier id = new Identifier(name);
            if (!Registries.ITEM.containsId(id)) throw new IllegalArgumentException("Unknown item: " + name);
            return new IngredientStack(Ingredient.ofItems(Registries.ITEM.get(id)));
        }
        throw new IllegalArgumentException("Expected an ingredient, stack, item, block or item/tag identifier: " + o);
    }
}
