package com.orcl.jvm.classpath.impl;

import com.orcl.jvm.classpath.Entry;

import java.io.IOException;
import java.nio.file.*;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-01 23:41
 * @history: 2022-08-01 23:41 created by orcl
 */
public class ZipEntry implements Entry {

    private Path absolutePath;

    public ZipEntry(String path) {
        //获取绝对路径
        this.absolutePath = Paths.get(path).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        try (FileSystem zipFs = FileSystems.newFileSystem(absolutePath, null)) {
            return Files.readAllBytes(zipFs.getPath(className));
        }
    }

    @Override
    public String toString() {
        return this.absolutePath.toString();
    }

}
