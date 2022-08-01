package com.orcl.jvm.classpath;

import com.orcl.jvm.classpath.impl.WildcardEntry;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-01 23:41
 * @history: 2022-08-01 23:41 created by orcl
 */
public class Classpath {

    /**
     * 启动类路径
     */
    private Entry bootstrapClasspath;

    /**
     * 扩展类路径
     */
    private Entry extensionClasspath;

    /**
     * 用户类路径
     */
    private Entry userClasspath;

    public Classpath(String jreOption, String cpOption) {
        // 启动类&扩展类 "C:\Program Files\Java\jdk1.8.0_161\jre
        bootstrapAndExtensionClasspath(jreOption);
        // 用户类 E:\..\com\orcl\jvm\test/HelloWorld
        parseUserClasspath(cpOption);
    }

    public byte[] readClass(String className) throws Exception {
        className = className + ".class";

        // [readClass]启动类路径
        try {
            return bootstrapClasspath.readClass(className);
        } catch (Exception ignored) {
            // ignored
        }

        // [readClass]扩展类路径
        try {
            return extensionClasspath.readClass(className);
        } catch (Exception ignored) {
            // ignored
        }
        // [readClass]用户类路径
        return userClasspath.readClass(className);
    }

    private void bootstrapAndExtensionClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);
        // ...jre/lib/*
        String jreLibPath = Paths.get(jreDir, "lib") + File.separator + "*";
        bootstrapClasspath = new WildcardEntry(jreLibPath);

        // .jre/lib/ext/*
        String jreExtPath = Paths.get(jreDir, "lib", "ext") + File.separator + "*";
        extensionClasspath = new WildcardEntry(jreExtPath);
    }

    private void parseUserClasspath(String cpOption) {
        if (cpOption != null) {
            cpOption = ".";
        }
        userClasspath = Entry.create(cpOption);
    }

    private String getJreDir(String jreOption) {
        if (jreOption != null && Files.exists(Paths.get(jreOption))) {
            return jreOption;
        }

        if (Files.exists(Paths.get("./jre"))) {
            return "./jre";
        }
        String jh = System.getenv("JAVA_HOME");
        if (jh != null) {
            return Paths.get(jh, "jre").toString();
        }
        throw new RuntimeException("Can not find JRE folder!");
    }
}
