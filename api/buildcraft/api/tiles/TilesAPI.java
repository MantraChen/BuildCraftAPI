package buildcraft.api.tiles;

import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import javax.annotation.Nullable;

/** Native, sided machine services. Direct interface implementations expose all faces. */
public final class TilesAPI {
    public static final BlockApiLookup<IControllable, Direction> CONTROLLABLE = lookup("controllable", IControllable.class);
    public static final BlockApiLookup<IHasWork, Direction> HAS_WORK = lookup("has_work", IHasWork.class);
    public static final BlockApiLookup<IHeatable, Direction> HEATABLE = lookup("heatable", IHeatable.class);
    public static final BlockApiLookup<ITileAreaProvider, Direction> AREA_PROVIDER = lookup("area_provider", ITileAreaProvider.class);

    private TilesAPI() {}

    private static <T> BlockApiLookup<T, Direction> lookup(String name, Class<T> apiClass) {
        BlockApiLookup<T, Direction> lookup = BlockApiLookup.get(new Identifier("buildcraft", name), apiClass, Direction.class);
        lookup.registerFallback((world, pos, state, tile, side) -> apiClass.isInstance(tile) ? apiClass.cast(tile) : null);
        return lookup;
    }

    /** Only query a currently loaded entity; asking for a service must not load chunks. */
    @Nullable
    public static <T> T find(BlockApiLookup<T, Direction> lookup, @Nullable BlockEntity tile, @Nullable Direction side) {
        if (tile == null || tile.isRemoved()) return null;
        World world = tile.getWorld();
        if (world == null || !world.isChunkLoaded(tile.getPos()) || world.getBlockEntity(tile.getPos()) != tile) return null;
        return lookup.find(world, tile.getPos(), tile.getCachedState(), tile, side);
    }
}
