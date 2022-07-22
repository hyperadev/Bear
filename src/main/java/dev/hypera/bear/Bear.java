/*
 * Bear - Simple debugging library
 *  Copyright (c) 2022 Joshua Sing
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package dev.hypera.bear;

import dev.hypera.bear.breadcrumb.Breadcrumb;
import dev.hypera.bear.header.Header;
import dev.hypera.bear.section.Section;
import dev.hypera.bear.serialization.Serializer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Bear.
 */
public class Bear {

    private static final @NotNull String VERSION = "@version@";
    private static final @NotNull String COMMIT = "@commit@";

    private final @NotNull Header header;
    private final @NotNull Collection<Section> sections;
    private final @NotNull Serializer serializer;

    private final @NotNull List<Breadcrumb> breadcrumbs = new ArrayList<>();

    @Internal
    Bear(@NotNull Header header, @NotNull Collection<Section> sections, @NotNull Serializer serializer) {
        this.header = header;
        this.sections = sections;
        this.serializer = serializer;
    }

    /**
     * Create new {@link BearBuilder}.
     *
     * @return new {@link BearBuilder}.
     */
    @Contract("-> new")
    public static @NotNull BearBuilder builder() {
        return new BearBuilder();
    }

    /**
     * Record a {@link Breadcrumb}.
     *
     * @param breadcrumb {@link Breadcrumb} to be recorded.
     */
    public void record(@NotNull Breadcrumb breadcrumb) {
        this.breadcrumbs.add(breadcrumb);
    }

    /**
     * Generate and serialize a log.
     *
     * @return serialized log.
     */
    public @NotNull CompletableFuture<String> generateLog() {
        return CompletableFuture.supplyAsync(() -> {
            this.header.setTime(Instant.now());
            this.sections.forEach(Section::generate);

            return this.serializer.serialize(this.header, this.sections, this.breadcrumbs);
        });
    }

    /**
     * Get stored {@link Breadcrumb}s.
     *
     * @return stored {@link Breadcrumb}s.
     */
    public @NotNull List<Breadcrumb> getBreadcrumbs() {
        return this.breadcrumbs;
    }


    /**
     * Get the current Bear version.
     *
     * @return the current {@link Bear} version.
     */
    public static @NotNull String getVersion() {
        return VERSION;
    }

    /**
     * Get the current Bear commit.
     *
     * @return the current {@link Bear} commit.
     */
    public static @NotNull String getCommit() {
        return COMMIT;
    }

}
