package dev.hypera.bear.breadcrumb;

import java.time.Instant;
import org.jetbrains.annotations.NotNull;

public interface Breadcrumb {

    static @NotNull Breadcrumb of(@NotNull String title, @NotNull String description) {
        return new BreadcrumbImpl(title, description, Instant.now());
    }

}
