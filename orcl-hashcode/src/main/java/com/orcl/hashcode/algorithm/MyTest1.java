package com.orcl.hashcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-01 21:39
 * @history: 2022-08-01 21:39 created by orcl
 */
public class MyTest1 {

    public static void main(String[] args) {

        // 给定两个数组，一个是所有人员的名单数组 allArray，另外一个是黑名单人员的数组 blackArray ，返回：去除黑名单之后的名单数组
        String[] allArray = {};
        String[] blackArray = {};
        String[] whiteArray = {};

        List<String> allList = new ArrayList<>(Arrays.asList(allArray));
        List<String> blackList = new ArrayList<>(Arrays.asList(blackArray));

        List<String> whiteList = allList.stream()
                .filter(all -> blackList.stream().noneMatch(all::equals)).collect(Collectors.toList());
        whiteArray = whiteList.toArray(whiteArray);
        System.out.println(Arrays.toString(whiteArray));

    }

}
