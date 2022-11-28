package com.orcl.hodgepodge.io.transfer;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: orcl
 * @since: 2022-11-28 06:25
 * @history: 2022-11-28 06:25 created by orcl
 */
public class Byte2Char {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/orcl/Desktop/a.txt");
        if (!file.exists()) {
            file.createNewFile();

            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            osw.write("测试字符流转字节流");

            osw.close();

        }
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

        char[] buf = new char[1024];


        int len = isr.read(buf);
        System.out.println(new String(buf, 0, len));

        isr.close();
    }

}
