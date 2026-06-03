package buildcraft.api.blocks;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.DyeColor;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import buildcraft.api.core.BCDebugging;
import buildcraft.api.core.BCLog;

/** Provides a simple way to paint a single block, iterating through all {@link ICustomPaintHandler}'s that are
 * registered for the block. */
public enum CustomPaintHelper {
    INSTANCE;

    /* If you want to test your class-based rotation registration then add the system property
     * "-Dbuildcraft.api.rotation.debug.class=true" to your launch. */
    private static final boolean DEBUG = BCDebugging.shouldDebugLog("api.painting");

    private final Map<Block, List<ICustomPaintHandler>> handlers = Maps.newIdentityHashMap();
    private final List<ICustomPaintHandler> allHandlers = Lists.newArrayList();

    /** Registers a handler that will be called LAST for ALL blocks, if all other paint handlers have returned PASS or
     * none are registered for that block. */
    public void registerHandlerForAll(ICustomPaintHandler handler) {
        if (DEBUG) {
            BCLog.logger.info("[api.painting] Adding a paint handler for ALL blocks (" + handler.getClass() + ")");
        }
        allHandlers.add(handler);
    }

    /** Register's a paint handler for every class of a given block. */
    public void registerHandlerForAll(Class<? extends Block> blockClass, ICustomPaintHandler handler) {
        for (Block block : net.minecraft.registry.Registries.BLOCK) {
            Class<? extends Block> foundClass = block.getClass();
            if (blockClass.isAssignableFrom(foundClass)) {
                if (DEBUG) {
                    BCLog.logger.info("[api.painting] Found an assignable block " + net.minecraft.registry.Registries.BLOCK.getId(block) + " (" + foundClass + ") for " + blockClass);
                }
                registerHandlerInternal(block, handler);
            }
        }
    }

    public void registerHandler(Block block, ICustomPaintHandler handler) {
        if (registerHandlerInternal(block, handler)) {
            if (DEBUG) {
                BCLog.logger.info("[api.painting] Setting a paint handler for block " + net.minecraft.registry.Registries.BLOCK.getId(block) + "(" + handler.getClass() + ")");
            }
        } else if (DEBUG) {
            BCLog.logger.info("[api.painting] Adding another paint handler for block " + net.minecraft.registry.Registries.BLOCK.getId(block) + "(" + handler.getClass() + ")");
        }
    }

    private boolean registerHandlerInternal(Block block, ICustomPaintHandler handler) {
        if (!handlers.containsKey(block)) {
            List<ICustomPaintHandler> forBlock = Lists.newArrayList();
            forBlock.add(handler);
            handlers.put(block, forBlock);
            return true;
        } else {
            handlers.get(block).add(handler);
            return false;
        }
    }

    /** Attempts to paint a block at the given position. Basically iterates through all registered paint handlers. */
    public ActionResult attemptPaintBlock(World world, BlockPos pos, BlockState state, Vec3d hitPos, @Nullable Direction hitSide, @Nullable DyeColor paint) {
        Block block = state.getBlock();
        if (block instanceof ICustomPaintHandler) {
            return ((ICustomPaintHandler) block).attemptPaint(world, pos, state, hitPos, hitSide, paint);
        }
        List<ICustomPaintHandler> custom = handlers.get(block);
        if (custom == null || custom.isEmpty()) {
            return defaultAttemptPaint(world, pos, state, hitPos, hitSide, paint);
        }
        for (ICustomPaintHandler handler : custom) {
            ActionResult result = handler.attemptPaint(world, pos, state, hitPos, hitSide, paint);
            if (result != ActionResult.PASS) {
                return result;
            }
        }
        return defaultAttemptPaint(world, pos, state, hitPos, hitSide, paint);
    }

    private ActionResult defaultAttemptPaint(World world, BlockPos pos, BlockState state, Vec3d hitPos, Direction hitSide, @Nullable DyeColor paint) {
        for (ICustomPaintHandler handler : allHandlers) {
            ActionResult result = handler.attemptPaint(world, pos, state, hitPos, hitSide, paint);
            if (result != ActionResult.PASS) {
                return result;
            }
        }
        if (paint == null) {
            return ActionResult.FAIL;
        }
        Block b = state.getBlock();
        // STUB(R.Chen): Forge Block.recolorBlock removed in Fabric 1.20.1 — recolouring deferred, Phase 10
        if (false) {
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.FAIL;
        }
    }
}
