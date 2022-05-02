package dev.hypera.bear.breadcrumb;

import java.time.Instant;
import org.jetbrains.annotations.NotNull;

class BreadcrumbImpl implements Breadcrumb {

    private final @NotNull String title;
    private final @NotNull String description;
    private final @NotNull Instant time;

    public BreadcrumbImpl(@NotNull String title, @NotNull String description, @NotNull Instant time) {
        this.title = title;
        this.description = description;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Instant getTime() {
        return time;
    }

}
