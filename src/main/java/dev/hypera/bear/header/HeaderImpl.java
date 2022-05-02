package dev.hypera.bear.header;

import java.time.Instant;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Internal
public class HeaderImpl implements Header {

    private @Nullable String name;
    private @Nullable String version;
    private @Nullable Instant time;

    HeaderImpl(@Nullable String name, @Nullable String version) {
        this.name = name;
        this.version = version;
    }


    @Override
    public void setTime(@NotNull Instant time) {
        this.time = time;
    }

}
