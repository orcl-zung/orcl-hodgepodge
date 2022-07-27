package com.orcl.hashcode;

import com.alibaba.fastjson.JSON;
import com.orcl.hashcode.hash.Disturb;
import com.orcl.hashcode.hash.FileUtil;
import com.orcl.hashcode.hash.HashCode;
import com.orcl.hashcode.hash.RateInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-23 10:37
 * @history: 2022-07-23 10:37 created by orcl
 */
public class ApiTest {

    private Set<String> words;

    @Before
    public void before() {
        // 读取文件，103976 个英文单词库
        words = FileUtil.readWordList("D:/IdeaProjects/interview/interview-03/103976个英语单词库.txt");
    }

    @Test
    public void test_collisionRate() {
        System.out.println("单词数量：" + words.size());
        Integer[] multipliers = {2, 3, 5, 7, 17, 31, 32, 33, 39, 41, 199};
        List<RateInfo> rateInfoList = HashCode.collisionRateList(words, multipliers);
        for (RateInfo rate : rateInfoList) {
            System.out.printf("乘数 = %4d, 最小Hash = %11d, 最大Hash = %10d, 碰撞数量 =%6d, 碰撞概率 = %.4f%%%n", rate.getMultiplier(), rate.getMinHash(), rate.getMaxHash(), rate.getCollisionCount(), rate.getCollisionRate() * 100);
        }
    }

    @Test
    public void test_hashArea() {
        System.out.println(HashCode.hashArea(words, 2).values());
        System.out.println(HashCode.hashArea(words, 3).values());
        System.out.println(HashCode.hashArea(words, 5).values());
        System.out.println(HashCode.hashArea(words, 7).values());
        System.out.println(HashCode.hashArea(words, 17).values());
        System.out.println(HashCode.hashArea(words, 31).values());
        System.out.println(HashCode.hashArea(words, 32).values());
        System.out.println(HashCode.hashArea(words, 33).values());
        System.out.println(HashCode.hashArea(words, 39).values());
        System.out.println(HashCode.hashArea(words, 41).values());
        System.out.println(HashCode.hashArea(words, 199).values());
    }

    @Test
    public void test_cal() {
        String str = "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 103910, 34, 0, 18, 0, 0, 7, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 2, 0, 0, 0, 0";
        String[] split = str.split(",");
        System.out.println(split.length);
    }

    @Test
    public void test_128hash() {
        // 初始化 7 个字符串列表
        List<String> strList = new ArrayList<String>() {{
            add("orcl");
            add("kobe");
            add("LeBron");
            add("bryant");
            add("hardon");
            add("curry");
            add("小傅哥");
            add("钟某");
        }};

        // 存放的数组，类比为 hash 表
        String[] tab = new String[8];

        // 循环存放
        for (String key : strList) {
            // 计算索引
            int idx = key.hashCode() & (tab.length - 1);
            System.out.printf("key值=%s idx=%d%n", key, idx);
            System.out.println();
            if (null == tab[idx]) {
                tab[idx] = key;
                continue;
            }
            tab[idx] = tab[idx] + "->" + key;
        }
        System.out.println(JSON.toJSONString(tab));

    }

    @Test
    public void test_disturb() {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (String word : words) {
            // 使用扰动函数
            int idx = Disturb.disturbHashIdx(word, 128);

            // 没有使用扰动该函数
//            int idx = Disturb.hashInx(word, 128);
            if (map.containsKey(idx)) {
                Integer integer = map.get(idx);
                map.put(idx, ++integer);
            } else {
                map.put(idx, 1);
            }
        }
        System.out.println(map.values());
    }

}
