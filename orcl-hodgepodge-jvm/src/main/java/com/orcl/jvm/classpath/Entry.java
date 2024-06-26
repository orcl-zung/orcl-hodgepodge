package com.orcl.jvm.classpath;

import com.orcl.jvm.classpath.impl.CompositeEntry;
import com.orcl.jvm.classpath.impl.DirEntry;
import com.orcl.jvm.classpath.impl.WildcardEntry;
import com.orcl.jvm.classpath.impl.ZipEntry;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-01 23:40
 * @history: 2022-08-01 23:40 created by orcl
 */
public interface Entry {

    byte[] readClass(String className) throws IOException;

    static Entry create(String path) {
        // File.pathSeparator; 路径分隔符（win\linux）
        if (path.contains(File.pathSeparator)) {
            return new CompositeEntry(path);
        }

        if (path.endsWith("*")) {
            return new WildcardEntry(path);
        }

        if (path.endsWith(".jar") || path.endsWith(".JAR")
                || path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }
        return new DirEntry(path);
    }

}
