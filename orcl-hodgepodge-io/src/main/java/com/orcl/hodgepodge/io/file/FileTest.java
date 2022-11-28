package com.orcl.hodgepodge.io.file;

import java.io.File;

/**
 * @description:
 *
 * 绝对路径：一个完整的路径
 *          以盘符开始的路径
 * 相对路径：一个简化的路径
 *          相对于根路径来说的一个路径
 * @author: orcl
 * @since: 2022-11-28 06:54
 * @history: 2022-11-28 06:54 created by orcl
 */
public class FileTest {

    public static void main(String[] args) {

        System.out.println(File.separator);
        System.out.println(File.pathSeparator);

        System.out.println(File.separatorChar);
        System.out.println(File.pathSeparatorChar);


    }
}
