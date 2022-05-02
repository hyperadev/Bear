package dev.hypera.bear.serialization.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dev.hypera.bear.breadcrumb.Breadcrumb;
import dev.hypera.bear.header.Header;
import dev.hypera.bear.section.Section;
import dev.hypera.bear.serialization.Serializer;
import dev.hypera.bear.serialization.impl.gson.InstantAdapter;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;

public class GsonSerializer implements Serializer {

    private final @NotNull Gson gson;

    @Internal
    private GsonSerializer(@NotNull Gson gson) {
        this.gson = gson;
    }

    public static @NotNull GsonSerializer gson(@NotNull Gson gson) {
        return new GsonSerializer(gson);
    }

    public static @NotNull GsonSerializer plain() {
        return new GsonSerializer(new GsonBuilder().registerTypeAdapter(
            TypeToken.get(Instant.class).getType(),
            new InstantAdapter(DateTimeFormatter.ISO_INSTANT)
        ).create());
    }

    public static @NotNull GsonSerializer pretty() {
        return new GsonSerializer(new GsonBuilder().setPrettyPrinting().registerTypeAdapter(
            TypeToken.get(Instant.class).getType(),
            new InstantAdapter(DateTimeFormatter.ISO_INSTANT)
        ).create());
    }


    @Override
    public @NotNull String serialize(@NotNull Header header, @NotNull Collection<Section> sections, @NotNull List<Breadcrumb> breadcrumbs) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.putAll(serializeHeader(header));
        map.putAll(sections.stream().collect(Collectors.toMap(Section::getId, s -> s)));
        map.put("breadcrumbs", breadcrumbs);
        return gson.toJson(map);
    }

    private @NotNull Map<String, Object> serializeHeader(@NotNull Header header) {
        Map<String, Object> fields = new LinkedHashMap<>();
        for (Field field : header.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                fields.put(field.getName(), field.get(header));
            } catch (IllegalAccessException ex) {
                throw new IllegalStateException(ex);
            }
        }

        return fields;
    }

}
