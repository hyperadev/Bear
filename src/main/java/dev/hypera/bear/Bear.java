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
import org.jetbrains.annotations.NotNull;

public class Bear {

    private final @NotNull Header header;
    private final @NotNull Collection<Section> sections;
    private final @NotNull Serializer serializer;

    private final @NotNull List<Breadcrumb> breadcrumbs = new ArrayList<>();

    Bear(@NotNull Header header, @NotNull Collection<Section> sections, @NotNull Serializer serializer) {
        this.header = header;
        this.sections = sections;
        this.serializer = serializer;
    }

    public static @NotNull BearBuilder builder() {
        return new BearBuilder();
    }

    public static void main(String[] args) throws Exception {
        Bear bear = Bear.builder().build();
        bear.record(Breadcrumb.of("Wholemeal breadcrumb", "This is a test breadcrumb"));
        Thread.sleep(1000 * 5);
        bear.record(Breadcrumb.of("Rye breadcrumb", "Another breadcrumb, these could be used for recording actions to help piece together what "
            + "happened"));
        System.out.println(bear.generateLog().get());
    }

    public void record(@NotNull Breadcrumb breadcrumb) {
        breadcrumbs.add(breadcrumb);
    }

    public @NotNull CompletableFuture<String> generateLog() {
        return CompletableFuture.supplyAsync(() -> {
            header.setTime(Instant.now());
            sections.forEach(Section::generate);

            return serializer.serialize(header, sections, breadcrumbs);
        });
    }

    public @NotNull List<Breadcrumb> getBreadcrumbs() {
        return breadcrumbs;
    }

}
