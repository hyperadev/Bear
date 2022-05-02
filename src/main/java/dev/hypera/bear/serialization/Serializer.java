package dev.hypera.bear.serialization;

import dev.hypera.bear.breadcrumb.Breadcrumb;
import dev.hypera.bear.header.Header;
import dev.hypera.bear.section.Section;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface Serializer {

    @NotNull String serialize(@NotNull Header header, @NotNull Collection<Section> sections, @NotNull List<Breadcrumb> breadcrumbs);

}
