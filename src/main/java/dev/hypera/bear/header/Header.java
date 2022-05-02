package dev.hypera.bear.header;

import java.time.Instant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Header {

    static @NotNull Header of() {
        return of(null, null);
    }

    static @NotNull Header of(@Nullable String name, @Nullable String version) {
        return new HeaderImpl(name, version);
    }

    void setTime(@NotNull Instant instant);

}
