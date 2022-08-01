package com.orcl.jvm.classpath.impl;

import com.orcl.jvm.classpath.Entry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-01 23:41
 * @history: 2022-08-01 23:41 created by orcl
 */
public class CompositeEntry implements Entry {

    private final List<Entry> entryList = new ArrayList<>();

    public CompositeEntry(String pathList) {
        String[] paths = pathList.split(File.pathSeparator);
        for (String path : paths) {
            entryList.add(Entry.create(path));
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        for (Entry entry : entryList) {
            try {
                return entry.readClass(className);
            } catch (Exception ignored) {
                // ignored
            }
        }
        throw new IOException("class not found " + className);
    }
}
