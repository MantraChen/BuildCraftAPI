package buildcraft.api.schematics;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import buildcraft.lib.compat.FluidStackBC;

import buildcraft.api.core.InvalidInputDataException;

public interface ISchematicEntity {
    void init(SchematicEntityContext context);

    Vec3d getPos();

    @Nonnull
    default List<ItemStack> computeRequiredItems() {
        return Collections.emptyList();
    }

    @Nonnull
    default List<FluidStackBC> computeRequiredFluids() {
        return Collections.emptyList();
    }

    ISchematicEntity getRotated(BlockRotation rotation);

    Entity build(World world, BlockPos basePos);

    Entity buildWithoutChecks(World world, BlockPos basePos);

    NbtCompound serializeNBT();

    /** @throws InvalidInputDataException If the input data wasn't correct or didn't make sense. */
    void deserializeNBT(NbtCompound nbt) throws InvalidInputDataException;
}
