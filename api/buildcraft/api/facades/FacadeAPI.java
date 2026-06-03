package buildcraft.api.facades;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import net.minecraftforge.fml.common.event.FMLInterModComms;

public final class FacadeAPI {
    public static final String IMC_MOD_TARGET = "buildcraftsilicon";
    public static final String IMC_FACADE_DISABLE = "facade_disable_block";
    public static final String IMC_FACADE_CUSTOM = "facade_custom_map_block_item";
    public static final String NBT_CUSTOM_BLOCK_REG_KEY = "block_registry_name";
    public static final String NBT_CUSTOM_BLOCK_META = "block_meta";
    public static final String NBT_CUSTOM_ITEM_STACK = "item_stack";

    public static IFacadeItem facadeItem;
    public static IFacadeRegistry registry;

    private FacadeAPI() {

    }

    public static void disableBlock(Block block) {
        // STUB(R.Chen): Forge FMLInterModComms (IMC) has a different Fabric API; facade-disable IMC deferred — Phase 10
    }

    public static void mapStateToStack(BlockState state, ItemStack stack) {
        // STUB(R.Chen): Forge FMLInterModComms (IMC) + getMetaFromState removed; facade IMC deferred — Phase 10
        NbtCompound nbt = new NbtCompound();
        nbt.putString(NBT_CUSTOM_BLOCK_REG_KEY, net.minecraft.registry.Registries.BLOCK.getId(state.getBlock()).toString());
        nbt.putInt(NBT_CUSTOM_BLOCK_META, 0);
        nbt.put(NBT_CUSTOM_ITEM_STACK, stack.writeNbt(new NbtCompound()));
    }

    public static boolean isFacadeMessageId(String id) {
        return IMC_FACADE_CUSTOM.equals(id) //
            || IMC_FACADE_DISABLE.equals(id);
    }
}
