package com.orcl.hodgepodge.io.file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description:
 * @author: orcl
 * @since: 2022-11-28 07:31
 * @history: 2022-11-28 07:31 created by orcl
 */
public class FileConstructTest {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        File file = new File("/Users/orcl/Desktop/test.txt");
        System.out.println(file);

        if (!file.exists()) {
            file.createNewFile();
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

            osw.write("字符转成字节输出");

            osw.close();
        } else {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

            osw.write("字符转成字节输出\n");

            osw.close();
        }
        FutureTask<String> task = new FutureTask<>(() -> {
            File f = new File("/Users/orcl/Desktop/test.txt");
            if (f.exists()) {
                InputStreamReader isr = null;
                try {
                    isr = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);
                    char[] buf = new char[100];

                    int len = isr.read(buf);
                    return new String(buf, 0, len);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        });
        new Thread(task, "t1").start();

        System.out.println(task.get());

    }

}
