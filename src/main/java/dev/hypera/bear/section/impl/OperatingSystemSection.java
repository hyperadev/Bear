package dev.hypera.bear.section.impl;

import dev.hypera.bear.section.Section;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OperatingSystemSection implements Section {

    private static final @NotNull String ID = "os";

    private @Nullable String name;
    private @Nullable String arch;
    private @Nullable String version;


    @Override
    public @NotNull String getId() {
        return ID;
    }

    @Override
    public void generate() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        this.name = operatingSystemMXBean.getName();
        this.arch = operatingSystemMXBean.getArch();
        this.version = operatingSystemMXBean.getVersion();
    }

}
