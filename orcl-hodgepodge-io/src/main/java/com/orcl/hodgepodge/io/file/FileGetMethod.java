package com.orcl.hodgepodge.io.file;

import java.io.File;

/**
 * @description:
 * @author: orcl
 * @since: 2022-11-28 08:20
 * @history: 2022-11-28 08:20 created by orcl
 */
public class FileGetMethod {

    public static void main(String[] args) {

        File file = new File("/Users/orcl/Desktop");
        System.out.println(getLength(file));
    }

    public static long getLength(File file) {
        return file.length();
    }

}
