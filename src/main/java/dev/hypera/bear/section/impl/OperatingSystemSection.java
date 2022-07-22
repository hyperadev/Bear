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
package dev.hypera.bear.section.impl;

import dev.hypera.bear.section.Section;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Operating system {@link Section}.
 */
@SuppressWarnings({ "Unused", "FieldCanBeLocal" })
public class OperatingSystemSection implements Section {

    private static final @NotNull String ID = "os";

    private @Nullable String name;
    private @Nullable String arch;
    private @Nullable String version;

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull String getId() {
        return ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generate() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        this.name = operatingSystemMXBean.getName();
        this.arch = operatingSystemMXBean.getArch();
        this.version = operatingSystemMXBean.getVersion();
    }

}
