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
import java.lang.management.RuntimeMXBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JavaSection implements Section {

    private static final @NotNull String ID = "java";

    private @Nullable JavaVersionSection vm;
    private @Nullable JavaVersionSection spec;


    @Override
    public @NotNull String getId() {
        return ID;
    }

    @Override
    public void generate() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        vm = new JavaVersionSection(runtimeMXBean.getVmName(), runtimeMXBean.getVmVendor(), runtimeMXBean.getVmVersion());
        spec = new JavaVersionSection(runtimeMXBean.getSpecName(), runtimeMXBean.getSpecVendor(), runtimeMXBean.getSpecVersion());
    }


    public static class JavaVersionSection {

        private final @NotNull String name;
        private final @NotNull String vendor;
        private final @NotNull String version;

        public JavaVersionSection(@NotNull String name, @NotNull String vendor, @NotNull String version) {
            this.name = name;
            this.vendor = vendor;
            this.version = version;
        }

        public @NotNull String getName() {
            return name;
        }

        public @NotNull String getVendor() {
            return vendor;
        }

        public @NotNull String getVersion() {
            return version;
        }

    }

}
