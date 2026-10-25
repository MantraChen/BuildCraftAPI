/* Copyright (c) 2017 SpaceToad and the BuildCraft team. SPDX-License-Identifier: MPL-2.0 */
package buildcraft.api.facades;

import java.util.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;

/** Native facade compatibility hooks. Call during mod initialization, before recipe reload. */
public final class FacadeAPI {
    public static final String IMC_MOD_TARGET="buildcraftsilicon";
    public static final String IMC_FACADE_DISABLE="facade_disable_block";
    public static final String IMC_FACADE_CUSTOM="facade_custom_map_block_item";
    public static final String NBT_CUSTOM_BLOCK_REG_KEY="block_registry_name",NBT_CUSTOM_BLOCK_META="block_meta",NBT_CUSTOM_ITEM_STACK="item_stack";
    public static IFacadeItem facadeItem;
    public static IFacadeRegistry registry;
    private static final Set<Block> disabled=Collections.newSetFromMap(new IdentityHashMap<>());
    private static final Map<BlockState,ItemStack> mappings=new IdentityHashMap<>();
    private static int revision;
    private FacadeAPI(){}
    public static synchronized void disableBlock(Block block){if(disabled.add(Objects.requireNonNull(block)))revision++;}
    public static synchronized void mapStateToStack(BlockState state,ItemStack stack){
        mappings.put(Objects.requireNonNull(state),Objects.requireNonNull(stack).copyWithCount(stack.isEmpty()?0:1));revision++;
    }
    public static synchronized int configurationRevision(){return revision;}
    public static synchronized boolean isDisabled(Block block){return disabled.contains(block);}
    /** Null means unmapped; an empty stack deliberately hides this state from crafting and creative tabs. */
    public static synchronized ItemStack getMappedStack(BlockState state){var stack=mappings.get(state);return stack==null?null:stack.copy();}
    public static boolean isFacadeMessageId(String id){return IMC_FACADE_CUSTOM.equals(id)||IMC_FACADE_DISABLE.equals(id);}
}