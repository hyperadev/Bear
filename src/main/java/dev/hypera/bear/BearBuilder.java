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
