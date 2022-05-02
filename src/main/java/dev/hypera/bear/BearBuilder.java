package dev.hypera.bear;

import dev.hypera.bear.header.Header;
import dev.hypera.bear.section.Section;
import dev.hypera.bear.section.impl.JavaSection;
import dev.hypera.bear.section.impl.OperatingSystemSection;
import dev.hypera.bear.serialization.Serializer;
import dev.hypera.bear.serialization.impl.GsonSerializer;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class BearBuilder {

    private @NotNull Header header = Header.of();
    private final @NotNull Collection<Section> sections = new HashSet<>(Arrays.asList(new JavaSection(), new OperatingSystemSection()));
    private @NotNull Serializer serializer = GsonSerializer.pretty();

    BearBuilder() {}


    @Contract("_ -> this")
    public @NotNull BearBuilder header(@NotNull Header header) {
        this.header = header;
        return this;
    }


    @Contract("-> this")
    public @NotNull BearBuilder resetSections() {
        this.sections.clear();
        return this;
    }

    @Contract("_ -> this")
    public @NotNull BearBuilder sections(@NotNull Section... sections) {
        return sections(Arrays.asList(sections));
    }

    @Contract("_ -> this")
    public @NotNull BearBuilder sections(@NotNull Collection<Section> sections) {
        this.sections.addAll(sections);
        return this;
    }


    @Contract("_ -> this")
    public @NotNull BearBuilder serializer(@NotNull Serializer serializer) {
        this.serializer = serializer;
        return this;
    }


    @Contract("-> new")
    public @NotNull Bear build() {
        return new Bear(header, sections, serializer);
    }

}
