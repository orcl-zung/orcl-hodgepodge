package com.orcl.jvm.classpath.impl;

import com.orcl.jvm.classpath.Entry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-01 23:41
 * @history: 2022-08-01 23:41 created by orcl
 */
public class DirEntry implements Entry {

    private Path absolutePath;

    public DirEntry(String path) {
        this.absolutePath = Paths.get(path).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        return Files.readAllBytes(absolutePath.resolve(className));
    }

    @Override
    public String toString() {
        return this.absolutePath.toString();
    }
}
