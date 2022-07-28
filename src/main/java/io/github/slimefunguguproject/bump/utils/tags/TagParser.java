package io.github.slimefunguguproject.bump.utils.tags;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.function.BiConsumer;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;

import io.github.slimefunguguproject.bump.api.exceptions.TagMisconfigurationException;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.utils.JsonUtils;
import io.github.slimefunguguproject.bump.utils.Patterns;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.CommonPatterns;

/**
 * The {@link TagParser} is responsible for parsing a JSON file into a {@link BumpTag}.
 *
 * @author ybw0014
 * @see BumpTag
 */
class TagParser implements Keyed {
    /**
     * Every {@link Tag} has a {@link NamespacedKey}.
     * This is the {@link NamespacedKey} for the resulting {@link Tag}.
     */
    private final NamespacedKey key;

    /**
     * This constructs a new {@link TagParser} for the given {@link BumpTag}
     *
     * @param tag The {@link BumpTag} to parse inputs for.
     */
    TagParser(@Nonnull BumpTag tag) {
        this.key = tag.getKey();
    }

    /**
     * This will parse the JSON and run the provided callback with {@link Set Sets} of
     * matched {@link Material Materials} and {@link Tag Tags}.
     *
     * @param callback A callback to run after successfully parsing the input.
     * @throws TagMisconfigurationException This is thrown whenever the given input is malformed or no adequate
     *                                      {@link Material} or {@link Tag} could be found
     */
    public void parse(@Nonnull BiConsumer<Set<Material>, Set<Tag<Material>>> callback) throws TagMisconfigurationException {
        String path = "/tags/" + key.getKey() + ".json";
        Set<Material> materials = EnumSet.noneOf(Material.class);
        Set<Tag<Material>> tags = new HashSet<>();

        try {
            final InputStream stream = Bump.class.getResourceAsStream(path);
            JsonReader reader = new JsonReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            JsonObject root = JsonUtils.parseReader(reader).getAsJsonObject();
            JsonElement child = root.get("values");

            if (child instanceof JsonArray) {
                JsonArray values = child.getAsJsonArray();

                for (JsonElement element : values) {
                    if (element instanceof JsonPrimitive primitive && primitive.isString()) {
                        parseString(element.getAsString(), materials, tags);
                    } else {
                        throw new TagMisconfigurationException(key, "Unexpected value format: " + element.getClass().getSimpleName() + " - " + element);
                    }
                }

                callback.accept(materials, tags);
            } else {
                throw new TagMisconfigurationException(key, "No values array specified");
            }
        } catch (NullPointerException | IllegalStateException | JsonParseException x) {
            throw new TagMisconfigurationException(key, x);
        }
    }

    @ParametersAreNonnullByDefault
    private void parseString(String value, Set<Material> materials, Set<Tag<Material>> tags) throws TagMisconfigurationException {
        if (Patterns.MINECRAFT_NAMESPACEDKEY.matcher(value).matches()) {
            Material material = Material.matchMaterial(value);

            if (material != null) {
                materials.add(material);
            }
        } else if (Patterns.BUMP_TAG.matcher(value).matches()) {
            String keyValue = CommonPatterns.COLON.split(value)[1].toUpperCase(Locale.ROOT);
            BumpTag tag = BumpTag.getTag(keyValue);

            if (tag != null) {
                tags.add(tag);
            } else {
                throw new TagMisconfigurationException(key, "Bump tag not exist: " + keyValue);
            }
        }
    }

    @Nonnull
    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
