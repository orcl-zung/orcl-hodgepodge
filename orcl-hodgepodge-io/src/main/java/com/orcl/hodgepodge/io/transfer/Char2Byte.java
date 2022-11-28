package com.orcl.hodgepodge.io.transfer;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: orcl
 * @since: 2022-11-28 06:30
 * @history: 2022-11-28 06:30 created by orcl
 */
public class Char2Byte {

    public static void main(String[] args) throws IOException {
        File file = new File("io.txt");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

        osw.write("字符转成字节输出");

        osw.close();
    }

}
