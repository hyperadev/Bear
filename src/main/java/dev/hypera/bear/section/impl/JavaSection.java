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
