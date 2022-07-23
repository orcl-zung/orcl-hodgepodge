package com.orcl.hashcode.hash;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-23 10:40
 * @history: 2022-07-23 10:40 created by orcl
 */
public class FileUtil {

    /**
     * 读取本地文件，单词表
     *
     * @param url 单词表.txt文件
     * @return 单词集合(去重)
     */
    public static Set<String> readWordList(String url) {
        Set<String> set = new HashSet<>();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(url), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] ss = line.split("\t");
                set.add(ss[1]);
            }
            br.close();
            isr.close();
        } catch (Exception e) {
            return null;
        }
        return set;
    }

}
