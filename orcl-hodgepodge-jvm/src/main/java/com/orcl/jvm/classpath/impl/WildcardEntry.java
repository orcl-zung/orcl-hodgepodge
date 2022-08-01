package com.orcl.jvm.classpath.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-01 23:41
 * @history: 2022-08-01 23:41 created by orcl
 */
public class WildcardEntry extends CompositeEntry {

    public WildcardEntry(String pathList) {
        super(pathList);
    }

    private static String toPathList(String wildcardPath) {
        String baseDir = wildcardPath.replace("*", "");
        try {
            return Files.walk(Paths.get(baseDir))
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(p -> p.endsWith(".jar") || p.endsWith(".JAR"))
                    .collect(Collectors.joining(File.pathSeparator));
        } catch (IOException e) {
            return "";
        }
    }
}
