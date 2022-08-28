package io.github.slimefunguguproject.bump.utils;

import javax.annotation.Nonnull;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

import lombok.experimental.UtilityClass;

/**
 * Utility methods for json parsing.
 *
 * @author ybw0014
 */
@UtilityClass
public final class JsonUtils {
    /**
     * This method parses JSON from the {@link JsonReader}.
     *
     * @param reader The {@link JsonReader} to be read JSON from.
     *
     * @return The root {@link JsonElement}
     */
    public static JsonElement parseReader(@Nonnull JsonReader reader) {
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_18)) {
            return JsonParser.parseReader(reader);
        } else {
            return new JsonParser().parse(reader);
        }
    }
}
