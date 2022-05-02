package dev.hypera.bear.section;

import org.jetbrains.annotations.NotNull;

public interface Section {

    @NotNull String getId();
    void generate();

}
