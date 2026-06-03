package buildcraft.api.blocks;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import buildcraft.api.core.BCDebugging;
import buildcraft.api.core.BCLog;

public enum CustomRotationHelper {
    INSTANCE;

    /* If you want to test your class-based rotation registration then add the system property
     * "-Dbuildcraft.api.rotation.debug.class=true" to your launch. */
    private static final boolean DEBUG = BCDebugging.shouldDebugLog("api.rotation");

    private final Map<Block, List<ICustomRotationHandler>> handlers = Maps.newIdentityHashMap();

    public void registerHandlerForAll(Class<? extends Block> blockClass, ICustomRotationHandler handler) {
        for (Block block : net.minecraft.registry.Registries.BLOCK) {
            Class<? extends Block> foundClass = block.getClass();
            if (blockClass.isAssignableFrom(foundClass)) {
                if (DEBUG) {
                    BCLog.logger.info("[api.rotation] Found an assignable block " + net.minecraft.registry.Registries.BLOCK.getId(block) + " (" + foundClass + ") for " + blockClass);
                }
                registerHandlerInternal(block, handler);
            }
        }
    }

    public void registerHandler(Block block, ICustomRotationHandler handler) {
        if (registerHandlerInternal(block, handler)) {
            if (DEBUG) {
                BCLog.logger.info("[api.rotation] Setting a rotation handler for block " + net.minecraft.registry.Registries.BLOCK.getId(block));
            }
        } else if (DEBUG) {
            BCLog.logger.info("[api.rotation] Adding another rotation handler for block " + net.minecraft.registry.Registries.BLOCK.getId(block));
        }
    }

    private boolean registerHandlerInternal(Block block, ICustomRotationHandler handler) {
        if (!handlers.containsKey(block)) {
            List<ICustomRotationHandler> forBlock = Lists.newArrayList();
            forBlock.add(handler);
            handlers.put(block, forBlock);
            return true;
        } else {
            handlers.get(block).add(handler);
            return false;
        }
    }

    public ActionResult attemptRotateBlock(World world, BlockPos pos, BlockState state, Direction sideWrenched) {
        Block block = state.getBlock();
        if (block instanceof ICustomRotationHandler) {
            return ((ICustomRotationHandler) block).attemptRotation(world, pos, state, sideWrenched);
        }
        if (!handlers.containsKey(block)) return ActionResult.PASS;
        for (ICustomRotationHandler handler : handlers.get(block)) {
            ActionResult result = handler.attemptRotation(world, pos, state, sideWrenched);
            if (result != ActionResult.PASS) {
                return result;
            }
        }
        return ActionResult.PASS;
    }
}
