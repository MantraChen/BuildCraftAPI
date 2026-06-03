package buildcraft.api.facades;

import javax.annotation.Nullable;

import net.minecraft.util.DyeColor;

public interface IFacadePhasedState {
    IFacadeState getState();

    @Nullable
    DyeColor getActiveColor();
}
