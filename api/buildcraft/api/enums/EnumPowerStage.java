package buildcraft.api.enums;

import java.util.Locale;

import net.minecraft.util.StringIdentifiable;

// Ported to Fabric 1.20.1 by R.Chen: StringIdentifiable → StringIdentifiable, getName() → asString().
public enum EnumPowerStage implements StringIdentifiable {
    BLUE,
    GREEN,
    YELLOW,
    RED,
    OVERHEAT,
    BLACK;

    public static final EnumPowerStage[] VALUES = values();

    private final String modelName = name().toLowerCase(Locale.ROOT);

    public String getModelName() {
        return modelName;
    }

    @Override
    public String asString() {
        return getModelName();
    }
}
